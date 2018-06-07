package com.example.minh.facebooklogin.activity;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.minh.facebooklogin.DanhSachBaoDuongActivity;
import com.example.minh.facebooklogin.MainActivity;
import com.example.minh.facebooklogin.R;
import com.example.minh.facebooklogin.ipaddress.IPConnect;
import com.example.minh.facebooklogin.model.ChiTietBD;
import com.example.minh.facebooklogin.model.PhuTung;
import com.example.minh.facebooklogin.model.Xe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import ru.katso.livebutton.LiveButton;

public class ThemPhuTungActivity extends AppCompatActivity {

    EditText edttenpt, edtngay, edtghichu;
    RadioButton rdbaoduong, rdthaythe;
    LiveButton btnThem, btnHuy;

    Spinner spinner;
    String MA_PHU_TUNG = "";

    String CACHTHUC = "";
    Random random;
    int MA_BAO_DUONG = 0;
    Xe XE = null;

    ChiTietBD ct=null;
    static String MBD = "";

    static {
        MBD = "MBD" + (1 + new Random().nextInt(5000));
    }

    ArrayAdapter adapter;

    ArrayList<String> mangPT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_phu_tung);
        init();
        XE = (Xe) getIntent().getSerializableExtra("XE");
        getDanhSachPT(IPConnect.GET_DANH_SACH_PHU_TUNG);
        addEvent();

    }

    public void ThemBaoDuong(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(ThemPhuTungActivity.this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                if (response.equals("ok")) {
//                    Toast.makeText(ThemPhuTungActivity.this, "Ok", Toast.LENGTH_SHORT).show();
//
//                } else {
////                    Toast.makeText(ThemPhuTungActivity.this, "Not", Toast.LENGTH_SHORT).show();
//                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ThemPhuTungActivity.this, "Lỗi mạng", Toast.LENGTH_SHORT).show();

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String ,String> map=new HashMap<>();
                map.put("maxe",XE.getMaxe());
                map.put("mabaoduong",MBD);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }


    private void addEvent() {
        edtngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(ThemPhuTungActivity.this);
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

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String s[] = mangPT.get(i).split("-");

                MA_PHU_TUNG = s[0];

                ResetValue();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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
               startActivity(new Intent(ThemPhuTungActivity.this, DanhSachBaoDuongActivity.class));
                finish();
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ngay = edtngay.getText().toString();
                String ghichu = edtghichu.getText().toString();

                 ct = new ChiTietBD(MBD, MA_PHU_TUNG, CACHTHUC, ngay, ghichu);
                CheckPhuTung(IPConnect.CHECK_PHU_TUNG,MBD,MA_PHU_TUNG);



            }
        });
    }
    public void CheckPhuTung(String url, final String mbd, final String mpt) {
        RequestQueue requestQueue = Volley.newRequestQueue(ThemPhuTungActivity.this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.equals("ok")) {
                    ThemPhuTung(IPConnect.THEM_PHU_TUNG,ct);
                } else {
                    Toast.makeText(ThemPhuTungActivity.this, "Phụ tùng này bạn đã nhập rồi", Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String ,String> map=new HashMap<>();
                map.put("mabd",mbd);
                map.put("mapt",mpt);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }


    public void ThemPhuTung(String url, final ChiTietBD ct) {
        RequestQueue requestQueue = Volley.newRequestQueue(ThemPhuTungActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("ok")) {
                    Toast.makeText(ThemPhuTungActivity.this, "Thêm phụ tùng thành công", Toast.LENGTH_SHORT).show();
                    ThemBaoDuong(IPConnect.THEM_BAO_DUONG);
                } else {
                    Toast.makeText(ThemPhuTungActivity.this, "Thêm phụ tùng thất bại", Toast.LENGTH_SHORT).show();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ThemPhuTungActivity.this, "Lỗi mạng thêm phụ tùng", Toast.LENGTH_SHORT).show();

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("mabd",ct.getMabaoduong());
                map.put("mapt",ct.getMapt());
                map.put("cachthuc",ct.getCachthuc());
                map.put("ngay",ct.getNgay());
                map.put("ghichu",ct.getGhichu());
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }


    private void ResetValue() {
        rdbaoduong.setChecked(false);
        rdthaythe.setChecked(false);
        edtngay.setText("");
        edtghichu.setText("");

    }


    public void getDanhSachPT(String url) {

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject obj = response.getJSONObject(i);
                                String mapt = obj.getString("mapt");
                                String tenpt = obj.getString("tenpt");
                                int km = obj.getInt("km");
                                int ngay = obj.getInt("ngay");
                                PhuTung pt = new PhuTung(mapt, tenpt, km, ngay);
                                String temp = mapt + "-" + tenpt;
                                mangPT.add(temp);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter = new ArrayAdapter(ThemPhuTungActivity.this, android.R.layout.simple_list_item_1, mangPT);
                        spinner.setAdapter(adapter);


                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ThemPhuTungActivity.this, "Lỗi mạng thêm phụ tùng", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        requestQueue.add(jsonArrayRequest);
//        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    private void init() {
        mangPT = new ArrayList<>();
        random = new Random();
        edttenpt = (EditText) findViewById(R.id.edttenpt);
        edtngay = (EditText) findViewById(R.id.edtngaybd);
        edtghichu = (EditText) findViewById(R.id.edtghichu);
        btnThem = (LiveButton) findViewById(R.id.btnThem);
        btnHuy = (LiveButton) findViewById(R.id.btnHuy);
        rdbaoduong = (RadioButton) findViewById(R.id.radiobaoduong);
        rdthaythe = (RadioButton) findViewById(R.id.radiothaythe);

        spinner = (Spinner) findViewById(R.id.spinnerphutung);

        MA_BAO_DUONG = 1 + random.nextInt(2000);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mangPT);
        spinner.setAdapter(adapter);
    }
}
