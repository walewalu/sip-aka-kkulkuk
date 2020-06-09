package com.baekdev.sip.ui.category;

import android.graphics.drawable.Drawable;

class CategoryData {
    private int mImages;
    private String mCats;

    CategoryData(int images, String cats){
        this.mImages = images;
        this.mCats = cats;
    }

    public void setImages(int images){
        mImages = images;
    }

    public void setCategoryName(String cats){
        mCats = cats;
    }

    public int getImages(){
        return mImages;
    }

    public String getCategoryName(){
        return mCats;
    }
}
