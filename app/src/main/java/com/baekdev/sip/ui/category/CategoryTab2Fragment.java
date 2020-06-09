package com.baekdev.sip.ui.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.baekdev.sip.ItemInfoFragment;
import com.baekdev.sip.MainActivity;
import com.baekdev.sip.R;

public class CategoryTab2Fragment extends Fragment {
    private String[] cats = {"STARBUCKS", "TOM 'N TOMS", "TWOSOME PLACE"};
    private int[] images = {R.drawable.category2_img_1, R.drawable.category2_img_2, R.drawable.category2_img_3};

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
                int image = data.getImages();
                String cat = data.getCategoryName();

                ((MainActivity)getActivity()).replaceFragment(ItemInfoFragment.newInstance());
            }
        });

        return view;
    }
}
