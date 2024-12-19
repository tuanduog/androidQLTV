package com.example.nhom14didong.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nhom14didong.R;

public class Admin_Homepage extends AppCompatActivity {
private ImageButton imgbtnQLTaiLieu;
private ImageButton imgbtnXemThongKe;
private ImageButton imgbtnProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_homepage);
        imgbtnQLTaiLieu = findViewById(R.id.imgbtnQLTaiLieu);
        imgbtnXemThongKe = findViewById(R.id.imgbtnXemThongKe);
        imgbtnProfile = findViewById(R.id.imgbtnProfile);
        imgbtnQLTaiLieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_Homepage.this, QuanLyTaiLieu.class);
                startActivity(intent);
            }
        });
        imgbtnXemThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_Homepage.this, XemThongKe.class);
                startActivity(intent);
            }
        });
        imgbtnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
