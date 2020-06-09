package com.baekdev.sip.ui.category;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class CategoryPagerAdapter extends FragmentPagerAdapter {

    private int mNumTabs;
    private final Context mContext;

    public CategoryPagerAdapter(int numTabs, Context context, FragmentManager fm) {
        super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mNumTabs = numTabs;
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        switch (position){
            case 0 :
                CategoryTab1Fragment tab1 = new CategoryTab1Fragment();
                return tab1;
            case 1:
                CategoryTab2Fragment tab2 = new CategoryTab2Fragment();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return mNumTabs;
    }
}