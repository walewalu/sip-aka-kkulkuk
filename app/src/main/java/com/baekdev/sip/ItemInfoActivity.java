package com.baekdev.sip;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class ItemInfoActivity extends AppCompatActivity {
    private ItemDTO data;
    RatingBar ratingBar;
    float person = 0.0f;
    float fin_rating = 0.0f;
    float pre_rating = 0.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_info);
        Button btn_rating = (Button)findViewById(R.id.button_ratingg);
        final ImageView imageView = (ImageView) findViewById(R.id.info_image);
        final TextView textView_name = (TextView) findViewById(R.id.info_name);
        final TextView textView_store = (TextView) findViewById(R.id.info_store);
        final TextView textView_price = (TextView) findViewById(R.id.info_price);
        /*
        Intent intent = getIntent();
        String id = intent.getExtras().getString("id");
        String store = intent.getExtras().getString("store");


        if (store == "스타벅스") {
            store = "starbucks";
        } else if (store == "커피빈") {
            store = "coffeebean";
        }
         */

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // final DocumentReference docRef = db.collection(store).document(id);
        final DocumentReference docRef = db.collection("coffeebean").document("cbeanblended1001");
        final FirebaseStorage storage = FirebaseStorage.getInstance();

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        data = document.toObject(ItemDTO.class);
                        textView_name.setText(data.getName());
                        textView_store.setText(data.getStore());
                        textView_price.setText(data.getPrice() + "원~");
                        person = data.getRating_person();
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

        textView_name.setTextColor(Color.BLACK);
        textView_name.setShadowLayer(1.0f, 1.0f,1.0f, Color.GRAY);

        textView_store.setTextColor(Color.BLACK);
        textView_store.setShadowLayer(1.0f, 1.0f,1.0f, Color.GRAY);

        textView_price.setTextColor(Color.BLACK);
        textView_price.setShadowLayer(1.0f, 1.0f,1.0f, Color.GRAY);

        //ratingBar와 연동
        ratingBar = (RatingBar)findViewById(R.id.infoRating);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener(){
            @Override
            public void onRatingChanged(RatingBar ratingBar1, final float rating, boolean fromUser){
               //현재 내가 정한 평점을 저장

                data.setRating(rating);
            }
        });

        // 확인 버튼 클릭시 평점이 데이터 베이스에 저장 밑 창 닫음
        btn_rating.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                HashMap<String, Object> rating_map = new HashMap<>();
                HashMap<String, Object> person_map = new HashMap<>();

                rating_map.put("rating",data.getRating()+pre_rating);
                person_map.put("rating_person",person+1);
                docRef.update(rating_map);
                docRef.update(person_map);

                fin_rating = (data.getRating()+pre_rating)/person;
                Log.v("평점",Float.toString(fin_rating));
                finish();
            }
        });



    }

}
