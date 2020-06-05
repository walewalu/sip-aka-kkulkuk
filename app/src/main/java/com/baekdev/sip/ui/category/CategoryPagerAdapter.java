package com.baekdev.sip.ui.category;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.baekdev.sip.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class CategoryPagerAdapter extends FragmentPagerAdapter {

    private int mNumTabs;
    private static final int[] TAB_TITLES = new int[]{R.string.tab_1, R.string.tab_2};
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
                CategoryTabFragment tab1 = new CategoryTabFragment();
                return tab1;
            case 1:
                CategoryTabFragment2 tab2 = new CategoryTabFragment2();
                return tab2;
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return mNumTabs;
    }
}