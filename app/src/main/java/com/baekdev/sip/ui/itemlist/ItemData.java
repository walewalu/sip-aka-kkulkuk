package com.baekdev.sip.ui.itemlist;

public class ItemData {
    private String mName;
    private String mStore;
    private String mCat;
    private int mImage;
    private int mPrice;
    private float mRating;
    private int mFav;
    private int Uri;

    public ItemData(){ }

    public ItemData(String name, String store, String cats, int images, int price){
        this.mName = name;
        this.mStore = store;
        this.mCat = cats;
        this.mImage = images;
        this.mPrice = price;
        this.mRating = 0.0f;
        this.mFav = 0;
    }

    public void setName(String s){
        mName = s;
    }
    public void setStore(String s){
        mStore = s;
    }
    public void setCategoryName(String s){
        mCat = s;
    }
    public void setImage(int i){
        mImage = i;
    }
    public void setPrice(int i){
        mPrice = i;
    }
    public void setRating(float f) { mRating = f; }
    public void setFav(int i) { mFav = i; }

    public String getName(){
        return mName;
    }
    public String getStore(){
        return mStore;
    }
    public String getCategoryName(){
        return mCat;
    }
    public int getImage(){
        return mImage;
    }
    public int getPrice(){
        return mPrice;
    }
    public float getRating(){
        return mRating;
    }
    public int getFav(){
        return mFav;
    }
}
