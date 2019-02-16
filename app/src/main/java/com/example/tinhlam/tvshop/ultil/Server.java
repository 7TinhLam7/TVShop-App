package com.example.tinhlam.tvshop.ultil;

/**
 * Created by TINHLAM on 10/28/2017.
 */

public class Server {

    public static String localhost = "http://172.30.249.1"; // phan giai localhost cho cac thiet bi nhu: dt,..hieu
    public static  String Duongdanloaisp = "http://" + localhost + "/host/getloaisp.php";
    public static String Duongdansanphammoinhat = "http://" + localhost + "/host/getsanphammoinhat.php";
    public static String Duongdandienthoai = "http://" + localhost + "/host/getsanpham.php?page=";
    public static String Duongdanlaptop = "http://" + localhost + "/host/getsanphamlaptop.php?page=";
    public static String Duongdanspcungloai = "http://" + localhost + "/host/getsanphamcungloai.php";
    public static String Duongdanthongtinlienhe = "http://" + localhost + "/host/guilienhe.php";
    public static String Duongdantatcasanpham = "http://" + localhost + "/host/gettatcasp.php";
}
