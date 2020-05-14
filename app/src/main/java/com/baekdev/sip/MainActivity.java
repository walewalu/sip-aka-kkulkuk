package com.baekdev.sip;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.Itemlist);
        ListViewAdapter adapter = new ListViewAdapter();
        listView.setAdapter(adapter);

        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.melonsmoothie), "Melon Smoothie", 5500, "Krispy Kreme");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.strawberrysmoothie), "Strawberry Smoothie", 5500,"Krispy Kreme");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position);

                Bitmap iconDrawable = ((BitmapDrawable)item.getProductIcon()).getBitmap();
                String titleStr = item.getProductName();
                String manufStr = item.getProductManufact();

                Intent intent = new Intent(MainActivity.this, ItemInfoActivity.class);
                startActivity(intent);
            }
        });

    }
}
