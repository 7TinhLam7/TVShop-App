package com.example.tinhlam.tvshop.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tinhlam.tvshop.R;

import com.example.tinhlam.tvshop.adapter.LaptopAdapter;
import com.example.tinhlam.tvshop.model.Sanpham;
import com.example.tinhlam.tvshop.ultil.CheckConnection;
import com.example.tinhlam.tvshop.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LaptopActivity extends AppCompatActivity {
    public String TAG = LaptopActivity.class.getSimpleName();
    Toolbar toolbarlt;
    ListView listViewlt;
    LaptopAdapter laptopAdapter;
    ArrayList<Sanpham> manglaptop;
    int idlaptop = 0;
    int page =1;
    View footerview;
    boolean isLoading = false;
    boolean limitdata = false;
    mHandler mHandler;
    TextView luotxem;
    RatingBar ratingBar;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laptop);
        Anhxa();
        if(CheckConnection.haveNetworkConnection(getApplicationContext())){

            GetIdloaisp();
            ActionToolBar();
            GetData(page);
            LoadMoreData();




        }else {
            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
            finish();
        }

    }

    private void LoadMoreData() {
        listViewlt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),ChiTietSanPhamActivity.class);
                intent.putExtra("thong tin san pham", manglaptop.get(position));
                laptopAdapter.notifyDataSetChanged();
                startActivity(intent);
            }
        });
        listViewlt.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView absListView, int FirstItem, int VisibleItem, int TotalItem) {
                if (FirstItem + VisibleItem == TotalItem && TotalItem != 0 && isLoading == false && limitdata == false){
                    isLoading = true;
                    ThreadData threadData = new ThreadData();
                    threadData.start();
                }
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void ActionToolBar() {
        setSupportActionBar(toolbarlt);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarlt.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }




    private void GetData(int page) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String duongdan = Server.Duongdanlaptop
                +String.valueOf(this.page);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int id = 0;
                String Tenlt = "";
                int Diemtb = 0;
                String Hinhanhlt = "";
                String Thongtin = "";
                int loai_id = 0;
                int luotxem = 0;
                if (response != null && response.length() != 2) {
                    listViewlt.removeFooterView(footerview);
                    try {

                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            Tenlt = jsonObject.getString("ten");//cái "ten" chuẩn như trên json chưa
                            Diemtb = jsonObject.getInt("diemtb");
                            Hinhanhlt = jsonObject.getString( "hinhspdt" );
                            String hinh="http://10.1.17.88"+Hinhanhlt;
                            Thongtin = jsonObject.getString("thongtin");
                            idlaptop = jsonObject.getInt("loai_id");
                            luotxem = jsonObject.getInt("luotxem");
                            manglaptop.add(new Sanpham(id, Tenlt, Diemtb, hinh,Thongtin, idlaptop,luotxem));
                            laptopAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }else {
                    limitdata = true;
                    listViewlt.removeFooterView(footerview);
                    CheckConnection.ShowToast_Short(getApplicationContext(), "Da het du lieu");
                }
            }



        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "Error: " + error.getMessage());
                        //    CheckConnection.ShowToast_Short(getApplicationContext(),error.toString());

                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<String, String>();
                param.put("loai_id",String.valueOf(idlaptop));

                return param;
            }
        };
        requestQueue.add(stringRequest);


    }






    private void GetIdloaisp() {
        idlaptop =getIntent().getIntExtra("loai_id",-1);
        Log.d("giatriloaisanpham",idlaptop+"");

    }

    private void Anhxa() {
        toolbarlt = (Toolbar) findViewById(R.id.toolbarlt);
        luotxem =(TextView) findViewById(R.id.txtluotxem);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        listViewlt = (ListView) findViewById(R.id.listviewlt);
        manglaptop = new ArrayList<>();
        laptopAdapter = new LaptopAdapter(getApplicationContext(),manglaptop);
        listViewlt.setAdapter(laptopAdapter);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerview = inflater.inflate(R.layout.progressbar,null);
        mHandler = new mHandler();
    }

    public class mHandler extends Handler {
        @Override
        public void handleMessage(Message msg){
            switch (msg.what){
                case 0:
                    listViewlt.addFooterView(footerview);
                    break;
                case 1:
                    // page ++;
                    GetData(++page);
                    isLoading = false;
                    break;
            }
            super.handleMessage(msg);

        }
    } //Tao luon du lieu

    public class ThreadData extends Thread{
        @Override
        public void run() {
            mHandler.sendEmptyMessage(0);
            try {
                Thread.sleep(3000);

            }catch (InterruptedException e){
                e.printStackTrace();
            }
            Message message = mHandler.obtainMessage(1);
            mHandler.sendMessage(message);

            super.run();
        }
    }
}

