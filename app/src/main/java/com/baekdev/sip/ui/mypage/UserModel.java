package com.baekdev.sip.ui.mypage;

import android.net.Uri;

public class UserModel {
    public String name;
    public String imageSrc;
    public String uid;
    public String email;
    public String uri;

    public UserModel() { };
    public UserModel(String name, String imageSrc, String uid, String email){
        this.name = name;
        this.imageSrc = imageSrc;
        this.uid = uid;
        this.email = email;
    }

    public UserModel(String name, String imageSrc, String uid, String email, String uri){
        this.name = name;
        this.imageSrc = imageSrc;
        this.uid = uid;
        this.email = email;
        this.uri = uri;
    }


    public void setName(String s){
        name = s;
    }
    public void setImageSrc(String s) {
        imageSrc = s;
    }
    public void setUid(String s) {
        uid = s;
    }
    public void setEmail(String s) {
        email = s;
    }
    public void setUri(String u) {
        uri = u;
    }


    public String getName(){
        return name;
    }
    public String getImageSrc(){
        return imageSrc;
    }
    public String getUid() {
        return uid;
    }
    public String getEmail() {
        return email;
    }
    public String getUri() {
        return uri;
    }
}
