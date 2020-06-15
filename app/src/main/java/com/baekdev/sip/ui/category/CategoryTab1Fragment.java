package com.baekdev.sip.ui.category;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.baekdev.sip.MainActivity;
import com.baekdev.sip.R;
import com.baekdev.sip.ui.itemlist.ItemListFragment1;

public class CategoryTab1Fragment extends Fragment{
    private String[] cats = {"Blended", "Soda", "Espresso", "Tea", "Juice", "Brewed", "Others"};
    private int[] images = {R.drawable.blended, R.drawable.soda, R.drawable.espresso, R.drawable.tea, R.drawable.juice, R.drawable.brewed, R.drawable.others};

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
                int image = data.getImageSrc();
                String cat = data.getCategoryName();
                ItemListFragment1 newFragment = ItemListFragment1.newInstance();
                Bundle bundle = new Bundle();
                bundle.putString("Key", "cat");
                bundle.putString("Value", cat);
                newFragment.setArguments(bundle);
                ((MainActivity)getActivity()).replaceFragment(newFragment);
            }
        });

        return view;
    }
}