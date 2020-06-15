package com.baekdev.sip.ui.itemlist;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.baekdev.sip.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class ItemListFragment1 extends Fragment{
    private ArrayList<ItemDTO> myDataset = new ArrayList<ItemDTO>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public ItemListFragment1() {

    }

    public static ItemListFragment1 newInstance(){
        return new ItemListFragment1();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_itemlist_1, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_1);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ItemListAdapter(myDataset, getContext());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //카테고리 프래그먼트로부터 정보를 받음
        Bundle bundle = getArguments();
        String key = bundle.getString("Key");
        String value = bundle.getString("Value");

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        final FirebaseStorage storage = FirebaseStorage.getInstance();
        CollectionReference mRef = db.collection("starbucks");
        CollectionReference mRef2 = db.collection("coffeebean");

        mRef.whereEqualTo(key, value).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        final ItemDTO i = document.toObject(ItemDTO.class);
                        StorageReference ref = storage.getReference().child(document.getString("imageSrc"));
                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                i.setUri(uri.toString());
                                myDataset.add(i);
                                mAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                }
            }
        });

        mRef2.whereEqualTo(key, value).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        final ItemDTO i = document.toObject(ItemDTO.class);
                        StorageReference ref = storage.getReference().child(document.getString("imageSrc"));
                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                i.setUri(uri.toString());
                                myDataset.add(i);
                                mAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                }
            }
        });

        return view;
    }
}