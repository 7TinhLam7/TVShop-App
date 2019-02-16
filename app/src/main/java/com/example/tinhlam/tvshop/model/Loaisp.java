package com.example.tinhlam.tvshop.model;

/**
 * Created by TINHLAM on 10/28/2017.
 */

public class Loaisp {
    public int Id;
    public String Ten;
    public String Hinhanhloaisp;

    public Loaisp(int id, String ten, String hinhanhloaisp) {
        Id = id;
        Ten = ten;
        Hinhanhloaisp = hinhanhloaisp;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTen() {
        return Ten;
    }

    public String getHinhanhloaisp() {
        return Hinhanhloaisp;
    }

}