package com.example.nhom14didong.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nhom14didong.Database.Database;
import com.example.nhom14didong.R;

public class DangNhap extends AppCompatActivity {
    final String DATABASE_NAME = "mydatabase.db";
    private EditText edtTaiKhoan, edtMatKhau;
    private SQLiteDatabase db;
    private Button dangnhap, dangky;
    private TextView tvQMK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        edtTaiKhoan = findViewById(R.id.edtTaiKhoan);
        edtMatKhau = findViewById(R.id.edtMatKhau);
        dangnhap = findViewById(R.id.btnDangNhap);
        dangky = findViewById(R.id.btnDangKy);
        tvQMK = findViewById(R.id.tvQuenMatKhau);
        db = Database.initDatabase(this, DATABASE_NAME);
        dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLogin(v);
            }
        });
        dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRegister(v);
            }
        });
//        tvQMK.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onForgotPassword(); //
//            }
//        });
    }

    public void onLogin(View view) {
        String taiKhoan = edtTaiKhoan.getText().toString();
        String matKhau = edtMatKhau.getText().toString();

        // kiem tra rong
        if (taiKhoan.isEmpty() || matKhau.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            Cursor cursor = db.rawQuery(
                    "SELECT * FROM NGUOIDUNG WHERE USERNAME = ? AND USERPASS = ?",
                    new String[]{taiKhoan, matKhau}
            );

            if (cursor != null && cursor.moveToFirst()) {
                String userName = cursor.getString(cursor.getColumnIndexOrThrow("USERNAME"));
                Toast.makeText(this, "Đăng nhập thành công: " + userName, Toast.LENGTH_SHORT).show();
                cursor.close();
                 // Chuyển sang màn hình HomeActivity
                 Intent intent = new Intent(DangNhap.this, HomePage.class);
                 startActivity(intent);
            } else {
                Toast.makeText(this, "Sai tên đăng nhập hoặc mật khẩu", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Đã xảy ra lỗi khi đăng nhập" + e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void onRegister(View view) {
         //Chuển tới màn hình đăng ký
         Intent intent = new Intent(DangNhap.this, DangKy.class);
         startActivity(intent);
    }
}