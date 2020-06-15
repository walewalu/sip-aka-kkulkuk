package com.baekdev.sip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.baekdev.sip.ui.itemlist.ItemDTO;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fm = getSupportFragmentManager();
    private SearchFragment searchFragment = new SearchFragment();
    private CategoryFragment categoryFragment = new CategoryFragment();
    private MyPageFragment myPageFragment = new MyPageFragment();
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null) finish();

        db = FirebaseFirestore.getInstance();

        if (findViewById(R.id.frameLayout) != null) {
            if (savedInstanceState != null) {
                return;
            }
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.frameLayout, searchFragment).commitAllowingStateLoss();
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_toolbar);
        setSupportActionBar(toolbar);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
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
                    case R.id.myPageItem:
                        ft.replace(R.id.frameLayout, myPageFragment).commitAllowingStateLoss();
                        break;
                }
                return true;
            }
        });
        // createData();
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameLayout, fragment);
        ft.addToBackStack(null);
        ft.commitAllowingStateLoss();
    }

    // 데이터 추가
    private void createData(){
        ArrayList<ItemDTO> items = new ArrayList<ItemDTO>();
        items.add(new ItemDTO("store/coffeebean/blended/Sparkling Mango.jpg",
                "스파클링 망고", "커피빈",
                "Soda", 6300, 0.0f, 0, 0));

        int n = 1;
        for (ItemDTO i : items){
            String s = "cbeansoda1" + String.format("%03d", n++);
            i.setId(s);
            db.collection("coffeebean").document(i.getId()).set(i);
        }
    }
}
