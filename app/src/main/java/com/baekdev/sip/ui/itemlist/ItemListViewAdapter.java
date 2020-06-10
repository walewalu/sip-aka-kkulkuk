package com.baekdev.sip.ui.itemlist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baekdev.sip.R;

import java.util.ArrayList;

public class ItemListViewAdapter extends BaseAdapter {
    private ArrayList<ItemData> itemDataList = new ArrayList<ItemData>();

    public ItemListViewAdapter(){

    }

    @Override
    public int getCount() {
        return itemDataList.size();
    }

    public void addItem(String name, String store, String cats, int images, int price){
        itemDataList.add(new ItemData(name, store, cats, images, price));
    }

    @Override
    public Object getItem(int position) {
        return itemDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.cardlayout_style_2, parent, false);
        }

        ImageView imageView = (ImageView) convertView.findViewById(R.id.item_image);
        TextView textView_name = (TextView) convertView.findViewById(R.id.item_name);
        TextView textView_store = (TextView) convertView.findViewById(R.id.store_name);
        TextView textView_price = (TextView) convertView.findViewById(R.id.item_price);

        ItemData itemData = itemDataList.get(position);

        imageView.setImageResource(itemData.getImage());
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        textView_name.setText(itemData.getName());
        textView_name.setTextColor(Color.LTGRAY);
        textView_name.setShadowLayer(1.0f, 1.0f,1.0f, Color.GRAY);

        textView_store.setText(itemData.getStore());
        textView_store.setTextColor(Color.LTGRAY);
        textView_store.setShadowLayer(1.0f, 1.0f,1.0f, Color.GRAY);

        textView_name.setText(itemData.getPrice() + "원~");
        textView_name.setTextColor(Color.LTGRAY);
        textView_name.setShadowLayer(1.0f, 1.0f,1.0f, Color.GRAY);

        return convertView;
    }
}
