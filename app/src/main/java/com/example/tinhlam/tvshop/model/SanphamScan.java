package com.example.tinhlam.tvshop.model;

import java.io.Serializable;

/**
 * Created by TINHLAM on 11/29/2017.
 */

public class SanphamScan implements Serializable {
    public int ID;
    public String Ten;
    public int Luotxem;
    public int Diemtb;

    public String Hinhspdt;
    public String Thongtin;
    public int loai_id;
    public String Barcode;

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

    public int getLuotxem() {
        return Luotxem;
    }

    public void setLuotxem(int luotxem) {
        Luotxem = luotxem;
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

    public String getBarcode() {
        return Barcode;
    }

    public void setBarcode(String barcode) {
        Barcode = barcode;
    }

    public SanphamScan(int ID, String ten, int luotxem, int diemtb, String hinhspdt, String thongtin, int loai_id, String barcode) {
        this.ID = ID;
        Ten = ten;
        Luotxem = luotxem;
        Diemtb = diemtb;
        Hinhspdt = hinhspdt;
        Thongtin = thongtin;
        this.loai_id = loai_id;
        Barcode = barcode;
    }
}
