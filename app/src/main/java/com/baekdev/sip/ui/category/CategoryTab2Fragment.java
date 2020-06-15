package com.baekdev.sip.ui.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.baekdev.sip.MainActivity;
import com.baekdev.sip.R;
import com.baekdev.sip.ui.itemlist.ItemListFragment1;
import com.baekdev.sip.ui.itemlist.ItemListFragment2;

public class CategoryTab2Fragment extends Fragment {
    private String[] cats = {"Starbucks Coffee", "The Coffee Bean"};
    private int[] images = {R.drawable.starbucks, R.drawable.coffeebean};

    public CategoryTab2Fragment() {
        // Required empty public constructor
    }

    public static CategoryTab2Fragment newInstance(){
        return new CategoryTab2Fragment();
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

        ListView listView = (ListView) view.findViewById(R.id.category_list2);
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
                ItemListFragment2 newFragment = ItemListFragment2.newInstance();
                Bundle bundle = new Bundle();
                bundle.putString("Value", cat);
                newFragment.setArguments(bundle);
                ((MainActivity)getActivity()).replaceFragment(newFragment);
            }
        });

        return view;
    }
}
