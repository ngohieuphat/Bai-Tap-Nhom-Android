package com.example.minh.facebooklogin;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.minh.facebooklogin.activity.NhacNhoActivity;
import com.example.minh.facebooklogin.activity.SuaActivity;
import com.example.minh.facebooklogin.activity.ThemPhuTungActivity;
import com.example.minh.facebooklogin.activity.ThemXeActivity;
import com.example.minh.facebooklogin.adapter.DanhSachAdapter;
import com.example.minh.facebooklogin.ipaddress.IPConnect;
import com.example.minh.facebooklogin.model.BienTam;
import com.example.minh.facebooklogin.model.ChiTietBD;
import com.example.minh.facebooklogin.model.MyDatabase;
import com.example.minh.facebooklogin.model.MyDate;
import com.example.minh.facebooklogin.model.PhuTung;
import com.example.minh.facebooklogin.model.Xe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static android.R.attr.value;
import static java.nio.file.Paths.get;

public class DanhSachBaoDuongActivity extends AppCompatActivity {


    ExpandableListView expandableListView;

    ArrayList<Xe> listdataHeader;
    HashMap<Xe, List<ChiTietBD>> listdataChild;

    DanhSachAdapter adapter;

    ChiTietBD CHILD;
    Xe HEADER;

    String TENXE = "";
    String MAXE = "";

    final String DATABASE_NAME = "baoduongxe.sqlite";
    SQLiteDatabase sqLiteDatabase;

    Calendar calendar;

    int HAN_MUC_NGAY = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_bao_duong);
        init();
        addEvent();
        getDSBaoDuong(IPConnect.GET_BAO_DUONG);
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


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }


                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DanhSachBaoDuongActivity.this, "Lỗi mạng ds phụ tùng", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        requestQueue.add(jsonArrayRequest);
//        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    private void addEvent() {


        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                HEADER = listdataHeader.get(groupPosition);


                return false;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                CHILD = listdataChild.get(listdataHeader.get(groupPosition)).get(childPosition);


                return false;
            }
        });


        expandableListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {


            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(DanhSachBaoDuongActivity.this);
                builder.setTitle("Xác nhận");
                builder.setMessage("Lựa chọn của bạn?");
                builder.setNegativeButton("Sửa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(DanhSachBaoDuongActivity.this, SuaActivity.class);

                        intent.putExtra("CTBD", CHILD);
                        startActivity(intent);
                    }
                });
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(DanhSachBaoDuongActivity.this);
                        builder.setTitle("Xác nhận");
                        builder.setMessage("Bạn muốn xóa không?");
                        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                XoaPhuTung(IPConnect.XOA_PHU_TUNG);

                            }
                        });
                        builder.show();

                    }
                });
                builder.show();


                return false;
            }
        });


    }

//    public void XoaPhuTungChiTiet(String url) {
//        RequestQueue requestQueue = Volley.newRequestQueue(DanhSachBaoDuongActivity.this);
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                if (response.equals("ok")) {
//                    Toast.makeText(DanhSachBaoDuongActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
//                    getDSBaoDuong(IPConnect.GET_BAO_DUONG);
//
//                } else {
//                    Toast.makeText(DanhSachBaoDuongActivity.this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
//                }
//
//
//            }
//        },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(DanhSachBaoDuongActivity.this, "Lỗi mạng", Toast.LENGTH_SHORT).show();
//
//                    }
//                }
//        ) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> map = new HashMap<>();
//                map.put("mabd",CHILD.getMabaoduong());
//                map.put("mapt", CHILD.getMapt());
//                return map;
//            }
//        };
//        requestQueue.add(stringRequest);
//    }


    public void XoaPhuTung(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(DanhSachBaoDuongActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("ok")) {
                    Toast.makeText(DanhSachBaoDuongActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    getDSBaoDuong(IPConnect.GET_BAO_DUONG);

                } else {
                    Toast.makeText(DanhSachBaoDuongActivity.this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                }


            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DanhSachBaoDuongActivity.this, "Lỗi mạng", Toast.LENGTH_SHORT).show();

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("maxe", HEADER.getMaxe());
                map.put("mabd", CHILD.getMabaoduong());
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }


    public void getDSBaoDuong(String url) {
        listdataHeader.clear();
        listdataChild.clear();

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject obj = response.getJSONObject(i);
                                String maxe = obj.getString("maxe");
                                String mabd = obj.getString("mabd");


                                Xe xe = getTenXe(maxe);
                                TENXE = xe.getTenxe();
                                MAXE = xe.getMaxe();

                                listdataHeader.add(xe);

                                getChiTietBD(IPConnect.GET_PHU_TUNG_BAO_DUONG, mabd, i);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter = new DanhSachAdapter(DanhSachBaoDuongActivity.this, listdataHeader, listdataChild);
                        expandableListView.setAdapter(adapter);

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DanhSachBaoDuongActivity.this, "Looix mangj", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        requestQueue.add(jsonArrayRequest);
//        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }


    //Lấy data

    public void getChiTietBD(String url, final String mabd, final int index) {
        final List<ChiTietBD> child = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(DanhSachBaoDuongActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray arr = new JSONArray(response);
                    for (int i = 0; i < arr.length(); i++) {

                        JSONObject obj = arr.getJSONObject(i);
                        String mabd = obj.getString("mabaoduong");
                        String mapt = obj.getString("mapt");
                        String tenpt = obj.getString("tenpt");
                        String cachthuc = obj.getString("cachthuc");
                        String ngay = obj.getString("ngay");
                        String ghichu = obj.getString("ghichu");

                        ChiTietBD ct = new ChiTietBD(mabd, mapt, tenpt, cachthuc, ngay, ghichu);
//                        Toast.makeText(DanhSachBaoDuongActivity.this, "ten="+tenpt, Toast.LENGTH_SHORT).show();

                        child.add(ct);

                        //Tính toán -------------------------------

                        SimpleDateFormat fm = new SimpleDateFormat("dd-MM-yyyy");
                        String hientai = fm.format(Calendar.getInstance().getTime());

                        MyDate ht = ConVertNgay(hientai);
                        MyDate d = ConVertNgay(ngay);

                        int sumDay = Tru(ht, d);

                        getNgayByMa(IPConnect.GET_NGAY_BY_MA, mapt, sumDay, tenpt);


                    }
                    listdataChild.put(listdataHeader.get(index), child);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DanhSachBaoDuongActivity.this, "Lỗi mạng chi tiết bd", Toast.LENGTH_SHORT).show();

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("mabd", mabd);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void getNgayByMa(String url, final String mapt, final int sumDay, final String tenpt) {
        final RequestQueue requestQueue = Volley.newRequestQueue(DanhSachBaoDuongActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onResponse(String response) {

//                Toast.makeText(DanhSachBaoDuongActivity.this, "kq=" + response, Toast.LENGTH_SHORT).show();
                try {
                    JSONArray arr = new JSONArray(response);
                    for (int i = 0; i < arr.length(); i++) {

                        JSONObject obj = arr.getJSONObject(i);
                        HAN_MUC_NGAY = obj.getInt("ngay");


                        int result = 0;
                        try {
                            result= HAN_MUC_NGAY - sumDay;
                        }catch (Exception e){
                            Toast.makeText(DanhSachBaoDuongActivity.this, "Lỗi hạn mức", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        int temp = 15 + new Random().nextInt(20);

                        if (result < temp) {

                            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                            Intent intent = new Intent(DanhSachBaoDuongActivity.this, NhacNhoActivity.class);
                            BienTam.MAXE = MAXE;
                            BienTam.TENXE = TENXE;
                            BienTam.MAPT = mapt;
                            BienTam.TENPT = tenpt;

//                            intent.putExtra("TENXE",TENXE);
//                            intent.putExtra("MAPT",mapt);
//                            intent.putExtra("TENPT",tenpt);
//                            Toast.makeText(DanhSachBaoDuongActivity.this, "tenxe="+TENXE, Toast.LENGTH_SHORT).show();


                            PendingIntent pendingIntent = PendingIntent.getActivity(DanhSachBaoDuongActivity.this, 1, intent, 0);
                            Notification.Builder notifiBuilder = new Notification.Builder(DanhSachBaoDuongActivity.this);

                            notifiBuilder.setContentTitle("Thông báo !");
                            notifiBuilder.setContentText("Xin thông báo: xe " + TENXE + " phụ tùng " + tenpt + " đã đến ngày bảo dưỡng, thay thế.");
                            notifiBuilder.setSmallIcon(R.drawable.facebook);

                            notifiBuilder.setContentIntent(pendingIntent); //Chuyển màn hình khi click

                            notifiBuilder.setAutoCancel(true);

                            notificationManager.notify(1, notifiBuilder.build());

                        } else {
//                            Toast.makeText(DanhSachBaoDuongActivity.this, "LỚn qua=", Toast.LENGTH_SHORT).show();
                        }
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DanhSachBaoDuongActivity.this, "Lỗi mạng", Toast.LENGTH_SHORT).show();

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("mapt", mapt);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    private MyDate ConVertNgay(String day) {
        String[] s = day.split("-");
        int ngay = Integer.parseInt(s[0]);
        int thang = Integer.parseInt(s[1]);
        int nam = Integer.parseInt(s[2]);
        MyDate d = new MyDate(ngay, thang, nam);
        return d;
    }

    public static int Tru(MyDate d1, MyDate d2) {

        int kc = Math.abs(d1.getNam() - d2.getNam()) * 365;

        int ngay1 = 0;
        int ngay2 =0;
        try {
            ngay1 = d1.getNgay() - 1 + (d1.getThang() * 30 - 30);
            ngay2 = d2.getNgay() - 1 + (d2.getThang() * 30 - 30);
        }catch (Exception e){

            return 0;
        }



        return kc = kc + Math.abs(ngay1 - ngay2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_them_nguoi_dung, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuthem) {
            startActivity(new Intent(DanhSachBaoDuongActivity.this, ThemXeActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    public void getJSON(String url) {

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Toast.makeText(DanhSachBaoDuongActivity.this, response.toString(), Toast.LENGTH_LONG).show();
                        try {
                            JSONObject mnag = response.getJSONObject(0);
//                            byte[]m=mnag.get

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("BBB", "Eror: " + error.toString());
                        Toast.makeText(DanhSachBaoDuongActivity.this, "Lỗi mạng", Toast.LENGTH_LONG).show();
                    }
                }
        );

        requestQueue.add(jsonArrayRequest);
//        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    private void init() {
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        listdataHeader = new ArrayList<>();
        listdataChild = new HashMap<>();

//        //Add header
//        listdataHeader.add(new Xe("Hon đa"));
//        listdataHeader.add(new Xe("Adlate"));
//        listdataHeader.add(new Xe("SH"));
//
//        //Add child
//        List<ChiTietBD> one = new ArrayList<>();
//        one.add(new ChiTietBD("Dầu nhớt", "bảo dưỡng", "12-6-2017", "ghi gì đó"));
//        one.add(new ChiTietBD("Bugi", "bảo dưỡng", "12-6-2017", "ghi gì đó"));
//
//
//        List<ChiTietBD> two = new ArrayList<>();
//        two.add(new ChiTietBD("Ác quy", "bảo dưỡng", "12-6-2017", "ghi gì đó"));
//        two.add(new ChiTietBD("Nước làm mát", "bảo dưỡng", "12-6-2017", "ghi gì đó"));
//
//        List<ChiTietBD> three = new ArrayList<>();
//        three.add(new ChiTietBD("Lọc gió", "bảo dưỡng", "12-6-2017", "ghi gì đó"));
//        three.add(new ChiTietBD("Phanh", "bảo dưỡng", "12-6-2017", "ghi gì đó"));
//        ////////////////////
//        listdataChild.put(listdataHeader.get(0), one);
//        listdataChild.put(listdataHeader.get(1), two);
//        listdataChild.put(listdataHeader.get(2), three);


        sqLiteDatabase = MyDatabase.initDatabase(this, DATABASE_NAME);

    }

    private Xe getTenXe(String ma) {
        Cursor cursor = sqLiteDatabase.rawQuery("select * from XeMay where maxe='" + ma + "'", null);
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            String maxe = cursor.getString(0);
            String tenxe = cursor.getString(1);
            byte[] hinhanh = cursor.getBlob(2);
            return new Xe(maxe, tenxe, hinhanh);
        }
        return null;
    }
}
