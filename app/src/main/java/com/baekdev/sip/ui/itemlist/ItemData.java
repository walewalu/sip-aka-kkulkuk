package com.baekdev.sip.ui.itemlist;

import android.annotation.SuppressLint;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ItemData {
    String[] imageSrc;
    String[] name;
    int[] price;
    ArrayList<ItemDTO> items;
    String cat;
    String store;
    String code;
    String col;

    public ItemData(){

    }

    public void createData(){
        ArrayList<ItemDTO> items = new ArrayList<ItemDTO>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        imageSrc = new String[]{
                /*1*/"store/coffeebean/espresso/Iced Espresso.jpg",
                /*2*/"store/coffeebean/espresso/Flat White.jpg",
                /*3*/"store/coffeebean/espresso/Cappuccino.jpg",
                /*4*/"store/coffeebean/espresso/Coffeebean Coffee.jpg",
                /*5*/"store/coffeebean/espresso/Cafe Sua.jpg",
                /*6*/"store/coffeebean/espresso/Caramel Macchiato.jpg", "store/coffeebean/espresso/Iced Caramel Macchiato.jpg",
                /*7*/"store/coffeebean/espresso/Hazelnut Latte.jpg",
                /*8*/"store/coffeebean/espresso/Mocha Latte.jpg", "store/coffeebean/espresso/Iced Mocha Latte.jpg",
                /*9*/"store/coffeebean/espresso/Vanilla Latte.jpg", "store/coffeebean/espresso/Iced Vanilla Latte.jpg",
                /*10*/"store/coffeebean/espresso/Double Cafe Latte.jpg", "store/coffeebean/espresso/Iced Double Cafe Latte.jpg",
                /*11*/"store/coffeebean/espresso/Cafe Latte.jpg", "store/coffeebean/espresso/Iced Cafe Latte.jpg",
                /*12*/"store/coffeebean/espresso/Hazelnut Americano.jpg", "store/coffeebean/espresso/Hazelnut Iced Coffee.jpg",
                /*13*/"store/coffeebean/espresso/Americano.jpg", "store/coffeebean/espresso/Iced Coffee.jpg",
        };

        name = new String[]{
                /*1*/"아이스 에스프레소",
                /*2*/"플랫화이트",
                /*3*/"카푸치노",
                /*4*/"커피빈 커피",
                /*5*/"카페수아",
                /*6*/"캐러멜 마키아또", "아이스 캐러멜 마키아또",
                /*7*/"헤이즐넛 라떼",
                /*8*/"모카 라떼", "아이스 모카 라떼",
                /*9*/"바닐라 라떼", "아이스 바닐라 라떼",
                /*10*/"더블카페라떼", "아이스 더블카페라떼",
                /*11*/"카페라떼", "아이스 카페라떼",
                /*12*/"헤이즐넛 아메리카노", "헤이즐넛 아이스 커피",
                /*13*/"아메리카노", "아이스커피"
        };

        price = new int[]{
                /*1*/5500,
                /*2*/5000,
                /*3*/5300,
                /*4*/5000,
                /*5*/6300,
                /*6*/6300, 6300,
                /*7*/6300,
                /*8*/5800, 5800,
                /*9*/5800, 5800,
                /*10*/5800, 5800,
                /*11*/5300, 5300,
                /*12*/5300, 5300,
                /*13*/4800, 4800
        };

        cat = "Espresso";
        store = "커피빈";
        code = "cbeanespresso1";
        col = "coffeebean";

        for(int i = 0; i < price.length; i++){
            items.add(new ItemDTO(imageSrc[i],
                    name[i], store,
                    cat, price[i], 0.0f, 0, 0));
        }

        int n = 1;
        for (ItemDTO i : items){
            @SuppressLint("DefaultLocale") String s = code + String.format("%03d", n++);
            i.setId(s);
            db.collection(col).document(i.getId()).set(i);
        }
    }
}
