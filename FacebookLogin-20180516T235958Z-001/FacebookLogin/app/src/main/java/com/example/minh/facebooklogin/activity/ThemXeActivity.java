package com.example.minh.facebooklogin.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.example.minh.facebooklogin.model.MyDatabase;
import com.example.minh.facebooklogin.model.Xe;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import ru.katso.livebutton.LiveButton;

public class ThemXeActivity extends AppCompatActivity {

    EditText edtmaxe, edttenxe;
    ImageView imgHinh;
    CircleImageView imgChon, imgChup;
    LiveButton btnThem, btnHuy;
    final String DATABASE_NAME = "baoduongxe.sqlite";
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them);

        init();

        addEvent();
    }

    private void addEvent() {
        imgChon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 2018);
            }
        });
        imgChup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(ThemXeActivity.this,
                        new String[]{android.Manifest.permission.CAMERA}, 2017);
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
                String ma = edtmaxe.getText().toString();
                String ten = edttenxe.getText().toString();
                byte[] anh = ImageView_To_Byte(imgHinh);
                Xe xe = new Xe(ma, ten, anh);

                ContentValues contentValues = new ContentValues();
                contentValues.put("maxe", ma);
                contentValues.put("tenxe", ten);
                contentValues.put("hinhanh", anh);

                long check = sqLiteDatabase.insert("XeMay", null, contentValues);
                if (check > 0) {
                    Toast.makeText(ThemXeActivity.this, "Thêm xe thành công", Toast.LENGTH_SHORT).show();
                    ThemXe(IPConnect.THEM_XE,xe);
                } else {
                    Toast.makeText(ThemXeActivity.this, "Thêm xe thất bại", Toast.LENGTH_SHORT).show();
                }
                Intent intent=new Intent(ThemXeActivity.this, ThemPhuTungActivity.class);
                intent.putExtra("XE",xe);
                startActivity(intent);
                finish();


            }
        });

    }

    public void ThemXe(String url, final Xe xe) {
        RequestQueue requestQueue = Volley.newRequestQueue(ThemXeActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                if (response.equals("ok")) {
//                    Toast.makeText(ThemXeActivity.this, "Thêm online thành công", Toast.LENGTH_SHORT).show();
//
//                } else {
//                    Toast.makeText(ThemXeActivity.this, "Thêm online thất bại", Toast.LENGTH_SHORT).show();
//                }


            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(ThemXeActivity.this, "Lỗi mạng thêm xe", Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("maxe", xe.getMaxe());
                map.put("tenxe", xe.getTenxe());
                map.put("hinhanh", xe.getHinhanh() + "");
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    public byte[] ImageView_To_Byte(ImageView imgv) {

        BitmapDrawable drawable = (BitmapDrawable) imgv.getDrawable();
        Bitmap bmp = drawable.getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 2017 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 2017);
        } else {
            Toast.makeText(this, "Bạn k cho phép mở camera!", Toast.LENGTH_SHORT).show();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 2017) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                imgHinh.setImageBitmap(bitmap);


            }
            if (requestCode == 2018) {
                Uri img = data.getData();
                try {
                    InputStream is = getContentResolver().openInputStream(img);
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    imgHinh.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private void init() {
        sqLiteDatabase = MyDatabase.initDatabase(this, DATABASE_NAME);
        edtmaxe = (EditText) findViewById(R.id.edtmaxe);
        edttenxe = (EditText) findViewById(R.id.edttenxe);
        imgHinh = (ImageView) findViewById(R.id.imgHinh);
        imgChup = (CircleImageView) findViewById(R.id.imgchuphinh);
        imgChon = (CircleImageView) findViewById(R.id.imgchonhinh);

        btnThem = (LiveButton) findViewById(R.id.btnThem);
        btnHuy = (LiveButton) findViewById(R.id.btnHuy);
    }
}
