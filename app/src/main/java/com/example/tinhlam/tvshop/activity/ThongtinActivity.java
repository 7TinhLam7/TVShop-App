package com.example.tinhlam.tvshop.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tinhlam.tvshop.R;
import com.example.tinhlam.tvshop.ultil.CheckConnection;
import com.example.tinhlam.tvshop.ultil.Server;

import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class ThongtinActivity extends AppCompatActivity {
    EditText edtsdt,edtemail, edtthongtin;
    Button btnxacnhan, btntrove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtin);
        Anhxa();
        btntrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        if (CheckConnection.haveNetworkConnection(getApplicationContext())){
            EvenButton();
        }else {
            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
        }
    }

    private void EvenButton() {
        btnxacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String sdt = edtsdt.getText().toString().trim();
                final String email = edtemail.getText().toString().trim();
                final String thongtin = edtthongtin.getText().toString().trim();

                if (sdt.length() >= 10 && email.length() >0 && thongtin.length() >0 ){
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.Duongdanthongtinlienhe, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                        }

                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String,String> hashMap = new HashMap<String, String>();
                            hashMap.put("sdt",sdt);
                            hashMap.put("email",email);
                            hashMap.put("thongtin",thongtin);

                            return hashMap;
                        }
                    };
                    requestQueue.add(stringRequest);
                    Toast.makeText(getApplicationContext(),"Gửi thành công",Toast.LENGTH_LONG).show();
                }else {
                    CheckConnection.ShowToast_Short(getApplicationContext(),"Hãy kiểm tra lại thông tin đã nhập");
                }
            }
        });

    }

    private void Anhxa() {

        edtsdt = (EditText) findViewById(R.id.edittextsdt);
        edtemail = (EditText) findViewById(R.id.edittextemail);
        edtthongtin = (EditText) findViewById(R.id.edittextthongtin);
        btnxacnhan =(Button) findViewById(R.id.btnxacnhan);
        btntrove = (Button) findViewById(R.id.btntrove);
    }


}
