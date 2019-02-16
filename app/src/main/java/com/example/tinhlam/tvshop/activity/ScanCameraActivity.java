package com.example.tinhlam.tvshop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.tinhlam.tvshop.R;
import com.example.tinhlam.tvshop.adapter.SanphamScanAdapter;
import com.example.tinhlam.tvshop.adapter.SanphamcungloaiAdapter;
import com.example.tinhlam.tvshop.model.SanphamScan;
import com.example.tinhlam.tvshop.model.Sanphamcungloai;
import com.example.tinhlam.tvshop.ultil.CheckConnection;
import com.example.tinhlam.tvshop.ultil.Server;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;



public class ScanCameraActivity extends AppCompatActivity {
    private   String codeFormat,codeContent;
    private   TextView formatTxt, contentTxt;

    public String TAG = ScanCameraActivity.class.getSimpleName();
    Toolbar toolbarChitiet;






    ListView listviewmanhinhketqua, listviewmanhinhchinh;

    //    ArrayList<SanphamScan> mangsanpham;
//    SanphamScanAdapter sanphamScanAdapter;
    ArrayList<SanphamScan> mangsanpham;
    SanphamScanAdapter sanphamScanAdapter;
    int idcungloai = 0;
    String ten = "";
    int Luotxem = 0;
    int Diemdg = 0;
    String thongtin = "";
    int loai_id = 0;
    String barcode ="";


    String Hinhanhsp = "";
    TextView txtLuotxem, txtDiemdg;
    ImageView imghinhanhsp;
    //int idsanpham = 0;
    Button btntrove;




    RatingBar ratingBar;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);// Programmatically initialize the scanner view
        setContentView(R.layout.activity_scan_camera);                // Set the scanner view as the content view

        formatTxt = (TextView) findViewById(R.id.scan_format);
        contentTxt = (TextView) findViewById(R.id.scan_content);



        mangsanpham = new ArrayList<>();


        //sanphamScanAdapter = new SanphamScanAdapter(mangsanpham,getApplicationContext());






        listviewmanhinhketqua = (ListView) findViewById(R.id.listviewmanhinhkq);
        TextView emptyText = (TextView)findViewById(android.R.id.empty);
        listviewmanhinhketqua.setEmptyView(emptyText);

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);




        imghinhanhsp = (ImageView) findViewById(R.id.imageviewhinhsp);
        txtLuotxem = (TextView) findViewById(R.id.textviewluotxem);
//        txtDiemdg = (TextView) findViewById(R.id.textviewdiemtb) ;



        //mangsanpham.add(0,new SanphamScan(0,"Trang Chinh","http://freedesignfile.com/upload/2016/12/High-tech-background-blue-styles-vector-01.jpg")); //thiet lap load trang chinh dau tien
        sanphamScanAdapter = new SanphamScanAdapter(mangsanpham,getApplicationContext());
        listviewmanhinhketqua.setAdapter(sanphamScanAdapter);


         EvenButton();

    }

    private void EvenButton() {
        btntrove = (Button) findViewById(R.id.btntrove);
        btntrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    /**
     * event handler for scan button
     * @param view view of the activity
     */
    public void scanNow(View view){

        mangsanpham.clear();
        sanphamScanAdapter.notifyDataSetChanged();
        IntentIntegrator integrator = new IntentIntegrator(this);

        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Đưa mã vạch vào đây để scan");
        integrator.setResultDisplayDuration(0);
        integrator.setScanningRectangle(600, 400);  // Wide scanning rectangle, may work better for 1D barcodes
        integrator.setCameraId(0);  // Use a specific camera of the device
        integrator.initiateScan();

    }




    /**
     * function handle scan result
     * @param requestCode scanned code
     * @param resultCode  result of scanned code
     * @param intent intent
     */
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //retrieve scan result

        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

        if (scanningResult != null) {
            //we have a result
            codeContent = scanningResult.getContents();
            codeFormat = scanningResult.getFormatName();

            // display it on screen
            formatTxt.setText("FORMAT: " + codeFormat);
            contentTxt.setText("CONTENT: " + codeContent);


//ket qua sp
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.Duongdantatcasanpham, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) { // log nay  ha neu co du lieu thi moi lay ve tranh gay loi cho app

                    Log.d(TAG, response + "");
                    if ( response != null) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);

                                idcungloai = jsonObject.getInt("id");
                                ten = jsonObject.getString("ten");
                                Luotxem = jsonObject.getInt("luotxem");//cái "ten" chuẩn như trên json chưa
                                Diemdg = jsonObject.getInt("diemtb");
                                Hinhanhsp = jsonObject.getString("hinhspdt");
                                String Hinh = "http://172.30.249.1" + Hinhanhsp;
                                thongtin = jsonObject.getString("thongtin");
                                loai_id = jsonObject.getInt("loai_id");
                                barcode = jsonObject.getString("barcode");

                                if(barcode.equals(codeContent) ) {
                                    mangsanpham.add(new SanphamScan(idcungloai, ten, Luotxem, Diemdg, Hinh, thongtin, loai_id, barcode));

                                    sanphamScanAdapter.notifyDataSetChanged(); //khi them san pham moi se tu update
                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        Log.d(TAG, mangsanpham.size() + "AAAAAAAAAAAAAAA");
                        // mangsanpham.add(7, new SanphamScan(0,"Liên hệ","https://cdn.tgdd.vn/Files/2016/05/27/834116/androidpit-murder-scene-1-w782.jpg"));
                        //    mangsanpham.add(4, new SanphamScan(0,"Thông tin","https://cdn.tgdd.vn/Files/2016/05/27/834116/androidpit-murder-scene-1-w782.jpg"));
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

        }else{

            Toast toast = Toast.makeText(getApplicationContext(),"No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }




}

