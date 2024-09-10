package com.example.swager.models;

import java.util.concurrent.TimeoutException;

public enum XepLoai {
    Gioi("Giỏi"),Kha("Khá"),TRUNG_BINH("Trung Binh"),YEU("Yếu");
    private String xl;
    XepLoai(String xl){
        this.xl=xl;
    }
    public String getXl(){
        return xl;
    }
    public static XepLoai fromTen(String ten){
        for(XepLoai x:XepLoai.values()){
            if(x.getXl().equals(ten))
                return x;
        }
        throw new IllegalArgumentException(ten);
    }


}
