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
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.baekdev.sip.ItemInfoActivity;
import com.baekdev.sip.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.MyViewHolder> {
    private ArrayList<ItemDTO> mDataset;
    private Context mContext;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        RelativeLayout relativeLayout;
        LinearLayout linearLayout;
        ImageView imageView;
        TextView textView_name;
        TextView textView_store;
        TextView textView_price;
        TextView textView_rating;
        TextView textView_fav;

        public MyViewHolder(View v) {
            super(v);
            relativeLayout = (RelativeLayout) v.findViewById(R.id.relative_layout);
            linearLayout = (LinearLayout) v.findViewById(R.id.layout_style2);
            imageView = (ImageView) v.findViewById(R.id.item_image);
            textView_name = (TextView) v.findViewById(R.id.item_name);
            textView_store = (TextView) v.findViewById(R.id.store_name);
            textView_price = (TextView) v.findViewById(R.id.item_price);
            textView_rating = (TextView) v.findViewById(R.id.item_rating);
            textView_fav = (TextView) v.findViewById(R.id.item_fav);
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
    @SuppressLint({"SetTextI18n", "ResourceAsColor"})
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
        holder.textView_name.setTextColor(R.color.SipDarkGray);
        holder.textView_name.setShadowLayer(1.0f, 1.0f,1.0f, Color.GRAY);

        holder.textView_store.setText(itemDTO.getStore());
        holder.textView_store.setTextColor(Color.GRAY);
        holder.textView_store.setShadowLayer(1.0f, 1.0f,1.0f, Color.GRAY);

        holder.textView_price.setText(itemDTO.getPrice() + "원~");
        holder.textView_price.setTextColor(Color.GRAY);
        holder.textView_price.setShadowLayer(1.0f, 1.0f,1.0f, Color.GRAY);

        float avrgRating = 0.0f;
        if (itemDTO.getRating_person() > 0) {
            avrgRating = itemDTO.getRating() / itemDTO.getRating_person();
        }

        holder.textView_rating.setText(String.format("%.2f", avrgRating));
        holder.textView_rating.setTextColor(R.color.SipDarkGray);
        holder.textView_rating.setShadowLayer(1.0f, 1.0f,1.0f, Color.GRAY);

        holder.textView_fav.setText(Integer.toString(itemDTO.getFav()));
        holder.textView_fav.setTextColor(R.color.SipDarkGray);
        holder.textView_fav.setShadowLayer(1.0f, 1.0f,1.0f, Color.GRAY);

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ItemInfoActivity.class);
                intent.putExtra("id", itemDTO.getId());
                intent.putExtra("store", itemDTO.getStore());
                v.getContext().startActivity(intent);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}

