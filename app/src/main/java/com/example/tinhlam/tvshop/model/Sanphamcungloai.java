package com.example.tinhlam.tvshop.model;

import java.io.Serializable;

/**
 * Created by TINHLAM on 11/8/2017.
 */

public class Sanphamcungloai {
    public int Id;
    public int Gia;
    public String Hinhnhacc;
    public String Duongdan;
    public int sanpham_id;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getGia() {
        return Gia;
    }

    public void setGia(int gia) {
        Gia = gia;
    }

    public String getHinhnhacc() {
        return Hinhnhacc;
    }

    public void setHinhnhacc(String hinhnhacc) {
        Hinhnhacc = hinhnhacc;
    }

    public String getDuongdan() {
        return Duongdan;
    }

    public void setDuongdan(String duongdan) {
        Duongdan = duongdan;
    }

    public int getSanpham_id() {
        return sanpham_id;
    }

    public void setSanpham_id(int sanpham_id) {
        this.sanpham_id = sanpham_id;
    }

    public Sanphamcungloai(int id, int gia, String hinhnhacc, String duongdan, int sanpham_id) {
        Id = id;
        Gia = gia;
        Hinhnhacc = hinhnhacc;
        Duongdan = duongdan;
        this.sanpham_id = sanpham_id;
    }
}