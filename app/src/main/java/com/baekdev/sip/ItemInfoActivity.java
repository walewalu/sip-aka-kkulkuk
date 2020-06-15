package com.baekdev.sip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.baekdev.sip.ui.itemlist.ItemDTO;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;

public class ItemInfoActivity extends AppCompatActivity {
    private ItemDTO data;
    private boolean isEvaluate = false;
    private float fin_rating = 0.0f;
    private float pre_rating = 0.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_info);

        //위젯 선언
        final ImageView imageView = (ImageView) findViewById(R.id.info_image);
        final TextView textView_name = (TextView) findViewById(R.id.info_name);
        final TextView textView_store = (TextView) findViewById(R.id.info_store);
        final TextView textView_price = (TextView) findViewById(R.id.info_price);
        final RatingBar ratingBar = (RatingBar) findViewById(R.id.info_rating);
        final Button acceptButton = (Button) findViewById(R.id.info_accept);

        //CategoryTabFragment로부터 넘겨 받은 정보
        Intent intent = getIntent();
        final String id = intent.getExtras().getString("id");
        String store = intent.getExtras().getString("store");
        Log.d("tag", id + ", " + store);

        if (store.equals("스타벅스")) {
            store = "starbucks";
            Log.d("tag", id + ", " + store);
        } else if (store.equals("커피빈")) {
            store = "coffeebean";
            Log.d("tag", id + ", " + store);
        }

        //파이어베이스 인증, 현재 유저, 데이터베이스, 스토리지 사용 시 선언
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser mUser = mAuth.getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        final DocumentReference docRef = db.collection(store).document(id);
        final FirebaseStorage storage = FirebaseStorage.getInstance();

        //유저 데이터 불러오기 ("user" 컬렉션의 사용자 uid로 저장되어 있는 문서)
        final DocumentReference userRef = db.collection("user").document(mUser.getUid());

        //상품 정보 데이터베이스에서 불러오기
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        data = document.toObject(ItemDTO.class);
                        //평가를 이미 했는지 찾아보기
                        userRef.collection("evaluate")
                                .document("evaluate").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists() && document.getDouble(id) != null) {
                                        if (document.getDouble(id) > 0) {
                                            float f = document.getDouble(id).floatValue();
                                            pre_rating = pre_rating - f;
                                            fin_rating = f;
                                            ratingBar.setRating(fin_rating);
                                            isEvaluate = true;
                                        } else {
                                            fin_rating = 0.0f;
                                            isEvaluate = false;
                                        }
                                    } else {
                                        fin_rating = 0.0f;
                                        isEvaluate = false;
                                    }
                                }
                            }
                        });
                        textView_name.setText(data.getName());
                        textView_store.setText(data.getStore());
                        textView_price.setText(data.getPrice() + "원~");
                        pre_rating = data.getRating();
                        StorageReference ref = storage.getReference().child(document.getString("imageSrc"));
                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Glide.with(getApplicationContext()).load(uri).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
                            }
                        });
                    }
                }
            }
        });

        //현재 내가 정한 평점을 저장
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener(){
            @Override
            public void onRatingChanged(RatingBar ratingBar1, final float rating, boolean fromUser){
                fin_rating = rating;
            }
        });

        // 확인 버튼 클릭시 평점이 데이터 베이스에 저장 밑 창 닫음
        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, Object> map = new HashMap<>();
                data.setRating(fin_rating);
                map.put("rating", pre_rating + data.getRating());
                if (!isEvaluate)
                    map.put("rating_person", data.getRating_person() + 1);
                map.put("fav", data.getFav() + 1);
                docRef.update(map);
                HashMap<String, Object> usermap = new HashMap<>();
                usermap.put(id, fin_rating);
                userRef.collection("evaluate").document("evaluate").update(usermap);
                Log.v("msg",Float.toString(data.getRating()));
                finish();
            }
        });

        // 텍스트뷰 설정
        textView_name.setShadowLayer(1.0f, 1.0f,1.0f, Color.GRAY);
        textView_store.setShadowLayer(1.0f, 1.0f,1.0f, Color.GRAY);
        textView_price.setShadowLayer(1.0f, 1.0f,1.0f, Color.GRAY);
    }
}
