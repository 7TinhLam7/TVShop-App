package com.example.tinhlam.tvshop.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tinhlam.tvshop.R;
import com.example.tinhlam.tvshop.adapter.DienthoaiAdapter;
import com.example.tinhlam.tvshop.model.Loaisp;
import com.example.tinhlam.tvshop.model.Sanpham;
import com.example.tinhlam.tvshop.ultil.CheckConnection;
import com.example.tinhlam.tvshop.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DienthoaiActivity extends AppCompatActivity {
    public String TAG = DienthoaiActivity.class.getSimpleName();
    Toolbar toolbardt;
    ListView listViewdt;
    DienthoaiAdapter dienthoaiAdapter;
    ArrayList <Sanpham> mangdt;
    int loai_id = 0;
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
        setContentView(R.layout.activity_dienthoai);
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
        listViewdt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),ChiTietSanPhamActivity.class);
                intent.putExtra("thong tin san pham", mangdt.get(position));
                startActivity(intent);
            }
        });
        listViewdt.setOnScrollListener(new AbsListView.OnScrollListener() {
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
       setSupportActionBar(toolbardt);
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       toolbardt.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {
               finish();
           }
       });
   }




    private void GetData(int page) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String duongdan = Server.Duongdandienthoai+String.valueOf(this.page);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int id = 0;
                String Tendt = "";
                int Diemtb = 0;
                String Hinhanhdt = "";
                String Thongtin = "";
                int loai_id = 0;
                int luotxem = 0;
                if (response != null && response.length() != 2) {
                    listViewdt.removeFooterView(footerview);
                    try {

                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            Tendt = jsonObject.getString("ten");//cái "ten" chuẩn như trên json chưa
                            Diemtb = jsonObject.getInt("diemtb");
                            Hinhanhdt = jsonObject.getString( "hinhspdt" );
                            String hinh="http://172.30.249.1"+Hinhanhdt;
                            Thongtin = jsonObject.getString("thongtin");
                            loai_id = jsonObject.getInt("loai_id");
                            luotxem = jsonObject.getInt("luotxem");
                            mangdt.add(new Sanpham(id, Tendt, Diemtb, hinh,Thongtin, loai_id,luotxem));
                            dienthoaiAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }else {
                limitdata = true;
                listViewdt.removeFooterView(footerview);
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
                param.put("loai_id",String.valueOf(loai_id));
                return param;
            }
        };
        requestQueue.add(stringRequest);


    }






    private void GetIdloaisp() {
        loai_id =getIntent().getIntExtra("loai_id",-1);
        Log.d("giatriloaisanpham",loai_id+"");

    }

    private void Anhxa() {
        toolbardt = (Toolbar) findViewById(R.id.toolbardt);
        listViewdt = (ListView) findViewById(R.id.listviewdt);
        luotxem =(TextView) findViewById(R.id.txtluotxem);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        mangdt = new ArrayList<>();
        dienthoaiAdapter = new DienthoaiAdapter(getApplicationContext(),mangdt);
        listViewdt.setAdapter(dienthoaiAdapter);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerview = inflater.inflate(R.layout.progressbar,null);
        mHandler = new mHandler();
    }

    public class mHandler extends Handler{
        @Override
        public void handleMessage(Message msg){
            switch (msg.what){
                case 0:
                    listViewdt.addFooterView(footerview);
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
//            dienthoaiAdapter.notifyDataSetChanged();
            super.run();
        }
    }
}
