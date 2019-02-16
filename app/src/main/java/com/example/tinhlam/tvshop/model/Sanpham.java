package com.example.tinhlam.tvshop.model;

import java.io.Serializable;

/**
 * Created by TINHLAM on 10/29/2017.
 */

public class Sanpham implements Serializable{
    public int ID;
    public String Ten;
    public int Diemtb;


    public String Hinhspdt;
    public String Thongtin;
    public int loai_id;
    public int luotxem;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public int getDiemtb() {
        return Diemtb;
    }

    public void setDiemtb(int diemtb) {
        Diemtb = diemtb;
    }

    public String getHinhspdt() {
        return Hinhspdt;
    }

    public void setHinhspdt(String hinhspdt) {
        Hinhspdt = hinhspdt;
    }

    public String getThongtin() {
        return Thongtin;
    }

    public void setThongtin(String thongtin) {
        Thongtin = thongtin;
    }

    public int getLoai_id() {
        return loai_id;
    }

    public void setLoai_id(int loai_id) {
        this.loai_id = loai_id;
    }

    public int getLuotxem() {
        return luotxem;
    }

    public void setLuotxem(int luotxem) {
        this.luotxem = luotxem;
    }

    public Sanpham(int ID, String ten, int diemtb, String hinhspdt, String thongtin, int loai_id, int luotxem) {
        this.ID = ID;
        Ten = ten;
        Diemtb = diemtb;
        Hinhspdt = hinhspdt;
        Thongtin = thongtin;
        this.loai_id = loai_id;
        this.luotxem = luotxem;
    }
}
