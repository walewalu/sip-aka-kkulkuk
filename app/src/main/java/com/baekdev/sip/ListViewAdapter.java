package com.baekdev.sip;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

    private ArrayList<ListViewItem> listViewItems = new ArrayList<ListViewItem>();

    public ListViewAdapter() {

    }

    @Override
    public int getCount() {
        return listViewItems.size();
    }

    @Override
    public Object getItem(int pos) {
        return listViewItems.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listviewstyle_1, parent,false);
        }

        ImageView iconImageView = (ImageView) convertView.findViewById(R.id.productImage);
        TextView nameTextView = (TextView) convertView.findViewById(R.id.productName);
        TextView manufTextView = (TextView) convertView.findViewById(R.id.productManuf);
        TextView ratingTextView = (TextView) convertView.findViewById(R.id.productRating);
        RatingBar ratingBar = (RatingBar) convertView.findViewById(R.id.productRatingBar);

        ListViewItem listViewItem = listViewItems.get(pos);

        iconImageView.setImageDrawable(listViewItem.getProductIcon());
        nameTextView.setText(listViewItem.getProductName());
        manufTextView.setText(listViewItem.getProductManufact() + " · " + listViewItem.getProductPrice() + "원");
        ratingTextView.setText(Double.toString(listViewItem.getProductRating()));
        ratingBar.setRating(listViewItem.getProductRating());

        return convertView;
    }

    public void addItem(Drawable icon, String name, int price, String manuf){
        ListViewItem item = new ListViewItem(icon, name, price, manuf, 0);
        listViewItems.add(item);
    }
}
