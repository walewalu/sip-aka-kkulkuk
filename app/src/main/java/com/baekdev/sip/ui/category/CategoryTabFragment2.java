package com.baekdev.sip.ui.category;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.baekdev.sip.ItemInfoFragment;
import com.baekdev.sip.ListViewAdapter;
import com.baekdev.sip.ListViewItem;
import com.baekdev.sip.MainActivity;
import com.baekdev.sip.R;

public class CategoryTabFragment2 extends Fragment {
    public CategoryTabFragment2() {
        // Required empty public constructor
    }

    public static CategoryTabFragment2 newInstance(){
        return new CategoryTabFragment2();
        // Required empty public constructor
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category_tab2, container, false);
        ListView listView = (ListView) view.findViewById(R.id.Itemlist2);
        ListViewAdapter adapter = new ListViewAdapter();
        listView.setAdapter(adapter);

        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.nitrovanillacream), "Nitro Vanilla Cream", 5400, "Starbucks");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.dolcecoldbrew), "Dolce Cold Brew", 5800,"Starbucks");

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
