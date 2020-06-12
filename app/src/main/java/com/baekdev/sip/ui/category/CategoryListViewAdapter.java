package com.baekdev.sip.ui.category;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baekdev.sip.R;
import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class CategoryListViewAdapter extends BaseAdapter {
    private ArrayList<CategoryData> catDataList = new ArrayList<CategoryData>();
    public CategoryListViewAdapter(){

    }

    @Override
    public int getCount() {
        return catDataList.size();
    }

    public void addItem(int imageSrc, String cat){
        catDataList.add(new CategoryData(imageSrc, cat));
    }

    @Override
    public Object getItem(int position) {
        return catDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.cardlayout_style_1, parent, false);
        }

        ImageView imageView = (ImageView) convertView.findViewById(R.id.imgc);
        TextView textView = (TextView) convertView.findViewById(R.id.txtc);
        CategoryData catData = catDataList.get(position);

        imageView.setImageResource(catData.getImageSrc());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY);
        textView.setText(catData.getCategoryName());
        textView.setTextColor(Color.WHITE);
        textView.setShadowLayer(1.0f, 1.0f,1.0f, Color.GRAY);

        return convertView;
    }
}
