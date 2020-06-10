package com.baekdev.sip.ui.category;

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
import com.baekdev.sip.ui.itemlist.ItemListFragment;

public class CategoryTab1Fragment extends Fragment{
    private String[] cats = {"Coffee", "Beverage", "Tea", "Dessert"};
    private int[] images = {R.drawable.category1_img_1, R.drawable.category1_img_2, R.drawable.category1_img_3, R.drawable.category1_img_4};

    public CategoryTab1Fragment() {
        // Required empty public constructor
    }

    public static CategoryTab1Fragment newInstance(){
        return new CategoryTab1Fragment();
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
        CategoryListViewAdapter adapter = new CategoryListViewAdapter();
        listView.setAdapter(adapter);

        for (int i = 0; i < cats.length; i++){
            adapter.addItem(images[i], cats[i]);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CategoryData data = (CategoryData) parent.getItemAtPosition(position);
                int image = data.getImages();
                String cat = data.getCategoryName();

                ((MainActivity)getActivity()).replaceFragment(ItemListFragment.newInstance());
            }
        });

        return view;
    }
}