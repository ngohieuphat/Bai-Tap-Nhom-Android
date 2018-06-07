package com.example.minh.facebooklogin.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.minh.facebooklogin.R;
import com.example.minh.facebooklogin.ipaddress.IPConnect;
import com.example.minh.facebooklogin.model.BienTam;
import com.example.minh.facebooklogin.model.MyDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class NhacNhoActivity extends AppCompatActivity {

    String tenxe, mapt, tenpt;
    Intent intent;

    TextView txttenxe,txtphutung,txtloikhuyen;
    ImageView imgHinh;

    final String DATABASE_NAME = "baoduongxe.sqlite";
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhac_nho);

        init();
        setValue();
        getNoiDungnhacNho(IPConnect.GET_NOI_DUNG_NHAC_NHO);
//        tenxe=getPen().getStringExtra("TENXE");
//        txtnhacnho.setText(BienTam.TENXE + "-" + BienTam.MAPT + "-" + BienTam.TENPT);

    }

    private void setValue() {
        txttenxe.setText(BienTam.TENXE);
        txtphutung.setText(BienTam.TENPT);
        setImage(BienTam.MAXE);
    }

    private void setImage(String maxe) {
        Cursor cursor=sqLiteDatabase.rawQuery("select hinhanh from XeMay where maxe='"+maxe+"'",null);
        for (int i = 0; i <cursor.getCount() ; i++) {
            cursor.moveToPosition(i);
            byte[] hinhanh=cursor.getBlob(0);
            Bitmap bitmap= BitmapFactory.decodeByteArray(hinhanh,0,hinhanh.length);

            imgHinh.setImageBitmap(bitmap);

        }
    }

    public void getNoiDungnhacNho(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(NhacNhoActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray arr = new JSONArray(response);
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject obj=arr.getJSONObject(i);
                        txtloikhuyen.setText(obj.getString("noidung"));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("mapt",BienTam.MAPT);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }


    private void init() {
        sqLiteDatabase = MyDatabase.initDatabase(this, DATABASE_NAME);
        txttenxe = (TextView) findViewById(R.id.txttenxe);
        txtphutung = (TextView) findViewById(R.id.txtphutung);
        txtloikhuyen = (TextView) findViewById(R.id.txtloikhuyen);
        imgHinh= (ImageView) findViewById(R.id.imgHinh);
    }
}
