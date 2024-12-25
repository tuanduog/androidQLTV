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
private ImageButton imgbtnProfile, imgbtnQLPM, imgbtnDuyetPM, imgbtnQLND;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_homepage);
        imgbtnQLTaiLieu = findViewById(R.id.imgbtnQLTL);
        imgbtnXemThongKe = findViewById(R.id.imgbtnXemThongKe);
        imgbtnDuyetPM = findViewById(R.id.imgbtnDuyetPM);
        imgbtnProfile = findViewById(R.id.imgbtnProfile);
        imgbtnQLPM = findViewById(R.id.imgbtnQLPM);
        imgbtnQLND = findViewById(R.id.imgbtnQLND);
        imgbtnQLPM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_Homepage.this, QuanLyMuonTra.class);
                startActivity(intent);
            }
        });
        imgbtnQLND.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_Homepage.this, QuanLyUser.class);
                startActivity((intent));
            }
        });

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
                Intent intent = new Intent(Admin_Homepage.this, UserProfile.class);
                startActivity(intent);
            }
        });
        imgbtnDuyetPM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_Homepage.this, DanhSachPhieuMuonUS.class);
                startActivity(intent);
            }
        });

    }
}
