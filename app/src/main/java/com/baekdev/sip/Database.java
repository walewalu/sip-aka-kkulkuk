package com.baekdev.sip;

import android.provider.BaseColumns;

import com.google.firebase.firestore.Exclude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class Database {
    public static final class CreateDB implements BaseColumns {
        public static final String NAME = "name";
        public static final String BRAND = "brand";
        public static final String MIN_PRICE = "min_price";
        public static final String MAX_PRICE = "max_price";
        public static final String CATEGORY = "category";
        public static final String SUBCATEGORY = "subcategory";
        public static final String _TABLENAME0 = "usertable";
        public static final String _CREATE0 = "create table if not exists " + _TABLENAME0 + "("
                + _ID + " integer primary key autoincrement, "
                + NAME + " text not null , "
                + BRAND + " text not null , "
                + MIN_PRICE + " integer not null );"
                + MAX_PRICE + " integer not null );"
                + CATEGORY + " integer not null );"
                + SUBCATEGORY + " integer not null );";
    }
}