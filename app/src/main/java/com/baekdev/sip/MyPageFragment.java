package com.baekdev.sip;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.baekdev.sip.ui.itemlist.ItemDTO;
import com.baekdev.sip.ui.mypage.UserModel;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Random;

public class MyPageFragment extends Fragment {
    private UserModel user;

    public MyPageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_page,container,false);

        final ImageView uImage = (ImageView) view.findViewById(R.id.user_image);
        final TextView uNick = (TextView) view.findViewById(R.id.user_nick);
        final TextView uEmail = (TextView) view.findViewById(R.id.user_email);
        Button button = (Button) view.findViewById(R.id.edit_button);
        Button logout = (Button) view.findViewById(R.id.logout_button);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        final FirebaseUser mUser = mAuth.getCurrentUser();

        final DocumentReference docRef = db.collection("user").document(mUser.getUid());
        final FirebaseStorage storage = FirebaseStorage.getInstance();

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        user = document.toObject(UserModel.class);
                        if (user.getUri() != null) {
                            Glide.with(getContext()).load(user.getUri()).diskCacheStrategy(DiskCacheStrategy.ALL).into(uImage);
                        } else {
                            uImage.setImageResource(R.drawable.fui_ic_anonymous_white_24dp);
                        }
                        uNick.setText(user.getName());
                        uEmail.setText(user.getEmail());
                    } else {
                        String name = "user" + String.format("%06d", new Random().nextInt(10000000));
                        docRef.set(new UserModel(name, null, mUser.getUid(), mUser.getEmail()));
                    }
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UserInfoActivity.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setMessage("로그아웃 하시겠습니까?").setCancelable(false);
                dialog.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseAuth mAuth = FirebaseAuth.getInstance();
                        mAuth.signOut();
                        Intent intent = new Intent(getActivity(), SignInActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }
                }).setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog d = dialog.create();
                d.show();
            }
        });

        return view;
    }


}


