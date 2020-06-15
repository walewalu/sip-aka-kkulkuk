package com.baekdev.sip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.baekdev.sip.ui.itemlist.ItemDTO;
import com.baekdev.sip.ui.mypage.UserModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class UserInfoActivity extends AppCompatActivity {
    private UserModel userModel;
    private ImageView uImage;
    private Button saveButton;
    private EditText uNick;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private FirebaseFirestore db;
    private FirebaseStorage mStorage;
    private DocumentReference docRef;
    private StorageReference storageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        uImage = (ImageView) findViewById(R.id.edit_user_image);
        uNick = (EditText) findViewById(R.id.edit_user_nick);
        saveButton = (Button) findViewById(R.id.save_button);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mStorage = FirebaseStorage.getInstance();
        db = FirebaseFirestore.getInstance();
        docRef = db.collection("user").document(mUser.getUid());

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        userModel = document.toObject(UserModel.class);
                        uNick.setText(document.getString("name"));
                        if (document.getString("uri") != null){
                            String uri = document.getString("uri");
                            Glide.with(getApplicationContext()).load(uri).diskCacheStrategy(DiskCacheStrategy.ALL).into(uImage);
                        } else {
                            uImage.setImageResource(R.drawable.fui_ic_anonymous_white_24dp);
                        }
                    }
                }
            }
        });

        uImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
                intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = FirebaseFirestore.getInstance();
                DocumentReference ref = db.collection("user").document(mUser.getUid());
                userModel.setName(uNick.getText().toString());
                ref.set(userModel);
                finish();
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        super.onActivityResult(requestCode, resultCode, data);
        mStorage = FirebaseStorage.getInstance("gs://sip-kkulkuk.appspot.com");
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        storageRef = mStorage.getReference().child("userimage/" + mUser.getUid() + ".jpg");

        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                try {
                    // 선택한 이미지에서 비트맵 생성
                    final InputStream in = getContentResolver().openInputStream(data.getData());
                    final Bitmap img = BitmapFactory.decodeStream(in);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    img.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    byte[] bitmapdata = baos.toByteArray();

                    UploadTask uploadTask = storageRef.putBytes(bitmapdata);
                    uploadTask.addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            Log.e("Sip", "Error");
                        }
                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            userModel.setImageSrc("userimage/" + mUser.getUid() + ".jpg");
                            storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    userModel.setUri(uri.toString());
                                    uImage.setImageBitmap(img);
                                }
                            });
                        }
                    });
                    // 이미지 표시
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
