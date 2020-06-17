package com.baekdev.sip;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.baekdev.sip.ui.category.CategoryPagerAdapter;
import com.google.android.material.tabs.TabLayout;


public class CategoryFragment extends Fragment {
    ViewPager viewPager;
    static CategoryPagerAdapter categoryPagerAdapter;
    public CategoryFragment() {
        // Required empty public constructor
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onResume(){
        super.onResume();
        categoryPagerAdapter.notifyDataSetChanged();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        TabLayout tabs = view.findViewById(R.id.tabs);

        tabs.addTab(tabs.newTab().setText(R.string.tab_1));
        tabs.addTab(tabs.newTab().setText(R.string.tab_2));

        viewPager= view.findViewById(R.id.view_pager);
        categoryPagerAdapter = new CategoryPagerAdapter(2, getContext(), getChildFragmentManager());
        viewPager.setAdapter(categoryPagerAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        return view;
    }
}
