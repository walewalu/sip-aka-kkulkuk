package com.baekdev.sip;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class CategoryFragment extends Fragment {
    public CategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        ListView listView = (ListView) view.findViewById(R.id.Itemlist);
        ListViewAdapter adapter = new ListViewAdapter();
        listView.setAdapter(adapter);

        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.melonsmoothie), "Melon Smoothie", 5500, "Krispy Kreme");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.strawberrysmoothie), "Strawberry Smoothie", 5500,"Krispy Kreme");
        // Inflate the layout for this fragment

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position);

                Bitmap iconDrawable = ((BitmapDrawable)item.getProductIcon()).getBitmap();
                String titleStr = item.getProductName();
                String manufStr = item.getProductManufact();

                ((MainActivity)getActivity()).replaceFragment(ItemInfoFragment.newInstance());
            }
        });

        return view;
    }
}
