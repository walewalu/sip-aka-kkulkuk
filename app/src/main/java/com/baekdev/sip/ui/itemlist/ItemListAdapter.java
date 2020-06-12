package com.baekdev.sip.ui.itemlist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.baekdev.sip.ItemInfoActivity;
import com.baekdev.sip.MainActivity;
import com.baekdev.sip.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.Collections;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.MyViewHolder> {
    private ArrayList<ItemDTO> mDataset;
    private Context mContext;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        LinearLayout linearLayout;
        ImageView imageView;
        TextView textView_name;
        TextView textView_store;
        TextView textView_price;

        public MyViewHolder(View v) {
            super(v);
            linearLayout = (LinearLayout) v.findViewById(R.id.layout_style2);
            imageView = (ImageView) v.findViewById(R.id.item_image);
            textView_name = (TextView) v.findViewById(R.id.item_name);
            textView_store = (TextView) v.findViewById(R.id.store_name);
            textView_price = (TextView) v.findViewById(R.id.item_price);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ItemInfoActivity itemInfoActivity = new ItemInfoActivity();
                    Intent intent = new Intent(v.getContext(), itemInfoActivity.getClass());
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    public ItemListAdapter(ArrayList<ItemDTO> myDataset, Context context) {
        mDataset = myDataset;
        mContext = context;
    }

    @Override
    public ItemListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardlayout_style_2, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final ItemDTO itemDTO = mDataset.get(position);

        if(position % 2 == 0){
            holder.linearLayout.setBackgroundColor(0xFFDEDEDE);
        } else {
            holder.linearLayout.setBackgroundColor(Color.WHITE);
        }

        holder.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(mContext).load(itemDTO.getUri()).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView);

        holder.textView_name.setText(itemDTO.getName());
        holder.textView_name.setTextColor(Color.BLACK);
        holder.textView_name.setShadowLayer(1.0f, 1.0f,1.0f, Color.GRAY);

        holder.textView_store.setText(itemDTO.getStore());
        holder.textView_store.setTextColor(Color.BLACK);
        holder.textView_store.setShadowLayer(1.0f, 1.0f,1.0f, Color.GRAY);

        holder.textView_price.setText(itemDTO.getPrice() + "원~");
        holder.textView_price.setTextColor(Color.BLACK);
        holder.textView_price.setShadowLayer(1.0f, 1.0f,1.0f, Color.GRAY);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}

