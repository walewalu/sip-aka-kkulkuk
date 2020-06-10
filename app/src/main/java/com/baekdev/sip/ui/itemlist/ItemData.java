package com.baekdev.sip.ui.itemlist;

public class ItemData {
    private String mName;
    private String mStore;
    private String mCat;
    private int mImage;
    private int mPrice;

    ItemData(String name, String store, String cats, int images, int price){
        this.mName = name;
        this.mStore = store;
        this.mCat = cats;
        this.mImage = images;
        this.mPrice = price;

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
}
