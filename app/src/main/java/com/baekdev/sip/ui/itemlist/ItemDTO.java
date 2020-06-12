package com.baekdev.sip.ui.itemlist;

import android.net.Uri;

public class ItemDTO {
    private String imageSrc;
    private String name;
    private String store;
    private String cat;
    private int price;
    private float rating;
    private int fav;
    private String id;
    private Uri uri;

    public ItemDTO(){ }

    public ItemDTO(String imageSrc, String name, String store, String cat, int price, float rating, int fav){
        this.imageSrc = imageSrc;
        this.name = name;
        this.store = store;
        this.cat = cat;
        this.price = price;
        this.rating = rating;
        this.fav = fav;
    }

    public ItemDTO(String imageSrc, String name, String store, String cat, int price, float rating, int fav, String id, Uri uri){
        this.imageSrc = imageSrc;
        this.name = name;
        this.store = store;
        this.cat = cat;
        this.price = price;
        this.rating = rating;
        this.fav = fav;
        this.id = id;
        this.uri = uri;
    }

    public void setImageSrc(String s){
        imageSrc = s;
    }
    public void setName(String s){
        name = s;
    }
    public void setStore(String s){
        store = s;
    }
    public void setCat(String s){
        cat = s;
    }
    public void setPrice(int i){
        price = i;
    }
    public void setRating(float f){
        rating = f;
    }
    public void setFav(int i){
        fav = i;
    }
    public void setId(String s) {id = s;}
    public void setUri(Uri u) { uri = u; }

    public String getImageSrc(){
        return imageSrc;
    }
    public String getName(){
        return name;
    }
    public String getStore(){
        return store;
    }
    public String getCat(){
        return cat;
    }
    public int getPrice(){
        return price;
    }
    public float getRating(){
        return rating;
    }
    public int getFav(){
        return fav;
    }
    public String getId() {return id; }
    public Uri getUri() { return uri; }
}