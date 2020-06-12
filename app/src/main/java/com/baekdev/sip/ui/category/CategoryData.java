package com.baekdev.sip.ui.category;

class CategoryData {
    private int mImageSrc;
    private String mCat;

    CategoryData(){}

    CategoryData(int imageSrc, String cat){
        this.mImageSrc = imageSrc;
        this.mCat = cat;
    }

    public int getImageSrc(){
        return mImageSrc;
    }

    public String getCategoryName(){
        return mCat;
    }
}
