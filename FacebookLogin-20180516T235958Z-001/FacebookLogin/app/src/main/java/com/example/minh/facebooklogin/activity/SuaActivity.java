package com.example.minh.facebooklogin.activity;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.minh.facebooklogin.DanhSachBaoDuongActivity;
import com.example.minh.facebooklogin.R;
import com.example.minh.facebooklogin.ipaddress.IPConnect;
import com.example.minh.facebooklogin.model.ChiTietBD;

import java.util.HashMap;
import java.util.Map;

import ru.katso.livebutton.LiveButton;

public class SuaActivity extends AppCompatActivity {
    EditText edttenpt, edtngay, edtghichu;
    RadioButton rdbaoduong, rdthaythe;
    LiveButton btnThem, btnHuy;
    String CACHTHUC = "";

    ChiTietBD CTBD = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua);
        init();

        CTBD = (ChiTietBD) getIntent().getSerializableExtra("CTBD");
        setValue();
        addEvent();
    }

    private void setValue() {
        edttenpt.setText(CTBD.getTenpt());
        String cachthuc = CTBD.getCachthuc();
        CACHTHUC=cachthuc;
        if (cachthuc.equals("Thay thế")) {
            rdthaythe.setChecked(true);
            rdbaoduong.setChecked(false);
        }
        if (cachthuc.equals("Bảo dưỡng")) {
            rdbaoduong.setChecked(true);
            rdthaythe.setChecked(false);
        }
        edtngay.setText(CTBD.getNgay());
        edtghichu.setText(CTBD.getGhichu());
    }

    private void addEvent() {

        edtngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(SuaActivity.this);
                dialog.setTitle("Dialog chọn ngày-tháng-năm");
                dialog.setContentView(R.layout.dialog_item_ngay_thang);
                dialog.show();
                LiveButton btnChonNgay = dialog.findViewById(R.id.btnChonNgay);
                final DatePicker datePicker = dialog.findViewById(R.id.datePickerOK);
                btnChonNgay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int nam = datePicker.getYear();
                        int thang = datePicker.getMonth() + 1;
                        int ngay = datePicker.getDayOfMonth();

                        String YEAR_FORMAT="";
                        String MONTH_FORMAT="";
                        String DAY_FORMAT="";
                        YEAR_FORMAT=nam+"";
                        MONTH_FORMAT=thang+"";
                        DAY_FORMAT=ngay+"";


                        String s = DAY_FORMAT + "-" + MONTH_FORMAT + "-" + YEAR_FORMAT;


//
                        edtngay.setText(s);
                        dialog.dismiss();
                    }
                });
            }
        });
        rdthaythe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rdthaythe.setChecked(true);
                CACHTHUC = "Thay thế";
                rdbaoduong.setChecked(false);
            }
        });
        rdbaoduong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rdbaoduong.setChecked(true);
                CACHTHUC = "Bảo dưỡng";
                rdthaythe.setChecked(false);
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ngay = edtngay.getText().toString();
                String ghichu = edtghichu.getText().toString();
                ChiTietBD ct = new ChiTietBD(CTBD.getMabaoduong(), CTBD.getMapt(),
                        CACHTHUC, ngay, ghichu);

                SuaPhuTung(IPConnect.SUA_PHU_TUNG,ct);




            }
        });
    }

    public void SuaPhuTung(String url, final ChiTietBD ct) {
        RequestQueue requestQueue = Volley.newRequestQueue(SuaActivity.this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("ok")){
                    Toast.makeText(SuaActivity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(SuaActivity.this, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                }
                startActivity(new Intent(SuaActivity.this, DanhSachBaoDuongActivity.class));

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SuaActivity.this, "Lỗi mạng phụ tùng", Toast.LENGTH_SHORT).show();

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String ,String> map=new HashMap<>();
                map.put("mabd",ct.getMabaoduong());
                map.put("mapt",ct.getMapt());
                map.put("cachthuc",ct.getCachthuc());
                map.put("ngaybd",ct.getNgay());
                map.put("ghichu",ct.getGhichu());
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void init() {
        edttenpt = (EditText) findViewById(R.id.edttenpt);
        edtngay = (EditText) findViewById(R.id.edtngaybd);
        edtghichu = (EditText) findViewById(R.id.edtghichu);
        btnThem = (LiveButton) findViewById(R.id.btnCapNhat);
        btnHuy = (LiveButton) findViewById(R.id.btnHuy);
        rdbaoduong = (RadioButton) findViewById(R.id.radiobaoduong);
        rdthaythe = (RadioButton) findViewById(R.id.radiothaythe);

    }
}
