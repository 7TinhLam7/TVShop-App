package com.example.tinhlam.tvshop.activity;

import android.accessibilityservice.AccessibilityService;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.tinhlam.tvshop.R;

import com.example.tinhlam.tvshop.adapter.SanphamAdapter;
import com.example.tinhlam.tvshop.adapter.SanphamcungloaiAdapter;
import com.example.tinhlam.tvshop.model.Sanpham;
import com.example.tinhlam.tvshop.model.Sanphamcungloai;
import com.example.tinhlam.tvshop.ultil.CheckConnection;
import com.example.tinhlam.tvshop.ultil.Server;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ChiTietSanPhamActivity extends AppCompatActivity {
    public String TAG = ChiTietSanPhamActivity.class.getSimpleName();

    Toolbar toolbarChitiet;
    ImageView imgChitiet;
    TextView txtten, txtdiemtb;





    ListView listviewmanhinhchinh;

    ArrayList<Sanpham> mangsanpham;
    SanphamAdapter sanphamAdapter;
    ArrayList<Sanphamcungloai> mangsanphamcungloai;
    SanphamcungloaiAdapter sanphamcungloaiAdapter;
    int idcungloai = 0;
    int Gia = 0;
    int Sanpham_id = 0;


    String Duongdan = "";
    String Hinhanhnhacc = "";
    TextView txtGia, txtDuongdan, txtluotxem;
    ImageView imghinhnhacc;
    //int idsanpham = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        Anhxa();
        ActionToolbar();
        GetInformation();
        GetDataspcungloai();


    }




    private void GetDataspcungloai() {
        Sanpham sanpham = (Sanpham) getIntent().getSerializableExtra("thong tin san pham");
        int id = 0;
        id = sanpham.getID();



        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        final int finalId = id;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.Duongdanspcungloai, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) { // log nay  ha neu co du lieu thi moi lay ve tranh gay loi cho app

                Log.d(TAG, response + "");
                if ( response != null) {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);

                            idcungloai = jsonObject.getInt("id");
                            Gia = jsonObject.getInt("gia");//cái "ten" chuẩn như trên json chưa
                            Duongdan = jsonObject.getString("duongdan");
                            Hinhanhnhacc = jsonObject.getString("hinhnhacc");
                            String Hinh = "http://172.30.249.1" + Hinhanhnhacc;
                            Sanpham_id = jsonObject.getInt("sanpham_id");
                            if(Sanpham_id == finalId){
                                mangsanphamcungloai.add(new Sanphamcungloai(idcungloai, Gia, Hinh, Duongdan, Sanpham_id));
                                sanphamcungloaiAdapter.notifyDataSetChanged(); //khi them san pham moi se tu update

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    Log.d(TAG, mangsanphamcungloai.size() + "AAAAAAAAAAAAAAA");
                    // mangsanphamcungloai.add(7, new Sanphamcungloai(0,"Liên hệ","https://cdn.tgdd.vn/Files/2016/05/27/834116/androidpit-murder-scene-1-w782.jpg"));
                    //    mangsanphamcungloai.add(4, new Sanphamcungloai(0,"Thông tin","https://cdn.tgdd.vn/Files/2016/05/27/834116/androidpit-murder-scene-1-w782.jpg"));
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Log.d(TAG, "Error: " + error.getMessage());
                CheckConnection.ShowToast_Short(getApplicationContext(),error.toString());

            }
        });
        requestQueue.add(jsonArrayRequest);




    }

    private void GetInformation() {
        int id = 0;
        String TenChitiet = "";
        int Diemtb = 0;
        String HinhanhChitiet = "";
        //String MotaChitiet = "";
        int loai_id = 0;
        int luotxem = 0;

        Sanpham sanpham = (Sanpham) getIntent().getSerializableExtra("thong tin san pham");

                    id = sanpham.getID();
                    TenChitiet = sanpham.getTen();//cái "ten" chuẩn như trên json chưa
                    Diemtb = sanpham.getDiemtb();
                    HinhanhChitiet = sanpham.getHinhspdt();
                   // MotaChitiet = sanpham.getThongtin();
                    loai_id = sanpham.getLoai_id();
                    luotxem =sanpham.getLuotxem();


                    txtten.setText(TenChitiet);
                    txtluotxem.setText(String.valueOf(luotxem));
                   // txtdiemtb.setText("");
                   // txtmota.setText(MotaChitiet);


                     if(sanpham.getHinhspdt() != null && !sanpham.getHinhspdt().isEmpty()) {
                     Picasso.with(getApplicationContext()).load(sanpham.getHinhspdt())
                    .placeholder(R.drawable.icon_1)
                    .error(R.drawable.newicon)
                    .into(imgChitiet);
        }

    }

    private void ActionToolbar() {
        setSupportActionBar(toolbarChitiet);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarChitiet.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void Anhxa() {


        toolbarChitiet = (Toolbar) findViewById(R.id.toolbarchitietsanpham);
        imgChitiet = (ImageView) findViewById(R.id.imageviewchitietsanpham);
        txtten = (TextView) findViewById(R.id.textviewtenchitietsanpham);
        txtdiemtb = (TextView) findViewById(R.id.textviewdiemtbsanpham);
        txtluotxem = (TextView) findViewById(R.id.txtluotxem) ;

        mangsanphamcungloai = new ArrayList<>();
        mangsanpham = new ArrayList<>();
        //sanphamAdapter = new SanphamAdapter(mangsanpham,getApplicationContext());






        listviewmanhinhchinh = (ListView) findViewById(R.id.listviewmanhinhchinh);
        imghinhnhacc = (ImageView) findViewById(R.id.imageviewnhacc);
        txtGia = (TextView) findViewById(R.id.textviewgia);

//        if (txtDuongdan != null) {
//            txtDuongdan.setMovementMethod(LinkMovementMethod.getInstance());
//        }

        //mangsanphamcungloai.add(0,new Sanphamcungloai(0,"Trang Chinh","http://freedesignfile.com/upload/2016/12/High-tech-background-blue-styles-vector-01.jpg")); //thiet lap load trang chinh dau tien
        sanphamcungloaiAdapter = new SanphamcungloaiAdapter(mangsanphamcungloai,getApplicationContext());
        listviewmanhinhchinh.setAdapter(sanphamcungloaiAdapter);





    }

}
