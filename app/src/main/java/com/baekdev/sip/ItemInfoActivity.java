package com.baekdev.sip;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.baekdev.sip.ui.itemlist.ItemDTO;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
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

public class ItemInfoActivity extends AppCompatActivity {
    private ItemDTO data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_info);

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
        DocumentReference docRef = db.collection("coffeebean").document("cbeanblended1001");
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
    }
}
