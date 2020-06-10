package com.baekdev.sip.ui.itemlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.baekdev.sip.ItemInfoFragment;
import com.baekdev.sip.MainActivity;
import com.baekdev.sip.R;

public class ItemListFragment extends Fragment{
    public ItemListFragment() {
        // Required empty public constructor
    }

    public static ItemListFragment newInstance(){
        return new ItemListFragment();
        // Required empty public constructor
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category_tab1, container, false);
        ListView listView = (ListView) view.findViewById(R.id.category_list1);
        ItemListViewAdapter adapter = new ItemListViewAdapter();
        listView.setAdapter(adapter);
        adapter.addItem("돌체 콜드브루", "스타벅스", "Coffee", R.drawable.dolcecoldbrew, 5000);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ItemData data = (ItemData) parent.getItemAtPosition(position);
                int image = data.getImage();
                String name = data.getName();
                String cat = data.getCategoryName();
                String store = data.getStore();
                int price = data.getPrice();

                ((MainActivity)getActivity()).replaceFragment(ItemInfoFragment.newInstance());
            }
        });

        return view;
    }
}