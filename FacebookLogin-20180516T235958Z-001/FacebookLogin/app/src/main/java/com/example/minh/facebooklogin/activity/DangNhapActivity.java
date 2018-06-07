package com.example.minh.facebooklogin.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.minh.facebooklogin.DanhSachBaoDuongActivity;
import com.example.minh.facebooklogin.MainActivity;
import com.example.minh.facebooklogin.R;
import com.example.minh.facebooklogin.ipaddress.IPConnect;
import com.example.minh.facebooklogin.model.NguoiDung;

import java.util.HashMap;
import java.util.Map;

import ru.katso.livebutton.LiveButton;

public class DangNhapActivity extends AppCompatActivity {

    EditText edtUsername, edtPassword;
    LiveButton btnDangnhap, btnHuy;
    Button btnFB;

    ProgressDialog dialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        init();
        addEvent();
    }

    private void addEvent() {
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtUsername.getText().toString();
                String pass = edtPassword.getText().toString();
                NguoiDung nd = new NguoiDung(name, pass);
                if (name.equals("") || pass.equals("")) {
                    Toast.makeText(DangNhapActivity.this, "Mời nhập tài khoản", Toast.LENGTH_SHORT).show();
                    return;
                }

                CheckTaiKhoan(IPConnect.CHECK_TAI_KHOAN, nd);


            }
        });

        btnFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DangNhapActivity.this, MainActivity.class));
            }
        });
    }


//    private boolean CheckTK(String user, String pas) {
////        Cursor cursor = sqLiteDatabase.rawQuery("select tentk from NguoiDung where tentk='" + user + "' and matkhau='" + pas + "'", null);
////        cursor.moveToFirst();
//////        if(cursor.getCount()>0)
//////            TempAccount.ACCOUNT=cursor.getString(0);
////        if (cursor.getCount() > 0) {
////            return true;
////        }
//        return false;
//    }

    public void CheckTaiKhoan(String url, final NguoiDung nd) {
        RequestQueue requestQueue = Volley.newRequestQueue(DangNhapActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("ok")) {
                    dialog = new ProgressDialog(DangNhapActivity.this);
                    dialog.setTitle("Đăng nhập");
                    dialog.setMessage("Đang xử lý....");
                    dialog.setCanceledOnTouchOutside(false);//click ra ngoài vẫn ko tắt
                    dialog.show();
                    new CountDownTimer(3000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {

                        }

                        @Override
                        public void onFinish() {
                            dialog.dismiss();
                            Toast.makeText(DangNhapActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
//                            if (!TempAccount.ACCOUNT.equals(TempAccount.BAN_LANH_DAO) &&
//                                    !TempAccount.ACCOUNT.equals(TempAccount.NHAN_VIEN_TO_CHUC)){
                            startActivity(new Intent(DangNhapActivity.this, DanhSachBaoDuongActivity.class));
//                            }else
                        }
                    }.start();


                } else {
                    Toast.makeText(DangNhapActivity.this, "Tài khoản không đúng", Toast.LENGTH_SHORT).show();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(DangNhapActivity.this, "Lỗi mạng", Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("name", nd.getUsername());
                map.put("pass", nd.getPassword());
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void init() {
        edtUsername = (EditText) findViewById(R.id.edtusername);
        edtPassword = (EditText) findViewById(R.id.edtpassword);
        btnDangnhap = (LiveButton) findViewById(R.id.btnDangNhap);
        btnHuy = (LiveButton) findViewById(R.id.btnHuy);
        btnFB = (Button) findViewById(R.id.btnFB);
    }
}
