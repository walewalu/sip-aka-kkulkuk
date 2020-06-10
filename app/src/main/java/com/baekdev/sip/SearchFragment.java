package com.baekdev.sip;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;




public class SearchFragment extends Fragment {
    int i=0;
    ViewPager viewPager;
    private pageFrag_1 fragment1;
    private pageFrag_2 fragment2;
    private pageFrag_3 fragment3;
    public SearchFragment(){

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        fragment1=new pageFrag_1();
        fragment2=new pageFrag_2();
        fragment3=new pageFrag_3();
        viewPager=(ViewPager)view.findViewById(R.id.viewPager);
        viewPager.setAdapter(new PagerAdapter(getChildFragmentManager()));
        viewPager.setCurrentItem(0);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
    private class PagerAdapter extends FragmentPagerAdapter{
        public PagerAdapter(FragmentManager fm){
            super(fm);
            getItem(0);
        }
        public Fragment getItem(int position){
            if(position==0)
            {
                return fragment1;
            }else if(position==1){
                return fragment2;
            }else {
                return fragment3;
            }
        }
        public int getCount(){
            return 3;
        }

    }




}



