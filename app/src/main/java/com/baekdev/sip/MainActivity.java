package com.baekdev.sip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private FragmentManager fm = getSupportFragmentManager();
    private SearchFragment searchFragment = new SearchFragment();
    private CategoryFragment categoryFragment = new CategoryFragment();
    private NewsfeedFragment newsfeedFragment = new NewsfeedFragment();
    private MyPageFragment myPageFragment = new MyPageFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.frameLayout) != null) {
            if (savedInstanceState != null){
                return;
            }
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.frameLayout, searchFragment).commitAllowingStateLoss();
        }

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction ft = fm.beginTransaction();
                switch(item.getItemId()){
                    case R.id.searchItem:
                        ft.replace(R.id.frameLayout, searchFragment).commitAllowingStateLoss();
                        break;
                    case R.id.categoryItem:
                        ft.replace(R.id.frameLayout, categoryFragment).commitAllowingStateLoss();
                        break;
                    case R.id.newsfeedItem:
                        ft.replace(R.id.frameLayout, newsfeedFragment).commitAllowingStateLoss();
                        break;
                    case R.id.myPageItem:
                        ft.replace(R.id.frameLayout, myPageFragment).commitAllowingStateLoss();
                        break;
                }
                return true;
            }
        });
    }



    public void replaceFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameLayout, fragment);
        ft.addToBackStack(null);
        ft.commitAllowingStateLoss();
    }
}
