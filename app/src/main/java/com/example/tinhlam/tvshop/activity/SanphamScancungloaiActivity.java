package com.example.tinhlam.tvshop.activity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.tinhlam.tvshop.R;
import com.example.tinhlam.tvshop.adapter.SanphamcungloaiAdapter;
import com.example.tinhlam.tvshop.model.SanphamScan;
import com.example.tinhlam.tvshop.model.Sanphamcungloai;
import com.example.tinhlam.tvshop.ultil.CheckConnection;
import com.example.tinhlam.tvshop.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SanphamScancungloaiActivity extends AppCompatActivity {
    public String TAG = SanphamScancungloaiActivity.class.getSimpleName();
    ArrayList<Sanphamcungloai> mangsanphamcungloai;
    SanphamcungloaiAdapter sanphamcungloaiAdapter;

    int idcungloai = 0;
    int Gia = 0;
    int Sanpham_id = 0;
    ListView listviewmanhinhchinh;

    String Duongdan = "";
    String Hinhanhnhacc = "";
    TextView txtGia, txtDuongdan, txtchuyenactivity;
    ImageView imghinhnhacc;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sanpham_scancungloai);
        Getsanphamcungloai();



        mangsanphamcungloai = new ArrayList<>();
        listviewmanhinhchinh = (ListView)  findViewById(R.id.listviewmanhinhchinh);
        TextView emptyText2 = (TextView)findViewById(android.R.id.empty);
        listviewmanhinhchinh.setEmptyView(emptyText2);











        txtGia = (TextView) findViewById(R.id.textviewgia);



        imghinhnhacc = (ImageView) findViewById(R.id.imageviewnhacc);

        sanphamcungloaiAdapter = new SanphamcungloaiAdapter(mangsanphamcungloai,getApplicationContext());
        listviewmanhinhchinh.setAdapter(sanphamcungloaiAdapter);
    }




    private void Getsanphamcungloai() {

        SanphamScan sanphamscan = (SanphamScan) getIntent().getSerializableExtra("thong tin san pham");
        int id = 0;
        id = sanphamscan.getID();

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
}
