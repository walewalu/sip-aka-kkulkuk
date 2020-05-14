package com.baekdev.sip;

import android.graphics.drawable.Drawable;

public class ListViewItem {
    private Drawable productIcon;
    private String productName;
    private int productPrice;
    private String productManufact;
    private float productRating;

    public ListViewItem(Drawable productIcon, String productName, int productPrice, String productManufact, float productRating){
        this.productIcon = productIcon;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productManufact = productManufact;
        this.productRating = productRating;
    }

    public void setProductIcon(Drawable icon){
        productIcon = icon;
    }

    public void setProductName(String name){
        productName = name;
    }

    public void setProductPrice(int price) {
        productPrice = price;
    }

    public void setProductManufact(String manufact){
        productManufact = manufact;
    }

    public void setProductRating(float rating){
        productRating = rating;
    }

    public Drawable getProductIcon(){
        return productIcon;
    }

    public String getProductName(){
        return productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public String getProductManufact(){
        return productManufact;
    }

    public float getProductRating(){
        return productRating;
    }
}
