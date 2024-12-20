package com.example.nhom14didong.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import com.example.nhom14didong.Database.Database;
import com.example.nhom14didong.R;

public class HomePage extends AppCompatActivity {
    ImageButton imgbtnDSTL, imgbtnTimKiem, imgbtnDSPM, imgbtnProfile;
    FrameLayout flDSTaiLieu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        imgbtnDSTL = findViewById(R.id.imgbtnDSTL);
        flDSTaiLieu = findViewById(R.id.flDSTaiLieu);
        imgbtnTimKiem = findViewById(R.id.imgbtnTimKiem);
        imgbtnDSPM = findViewById(R.id.imgbtnDSPM);
        imgbtnProfile = findViewById(R.id.imgbtnProfile);
        imgbtnDSTL.setOnClickListener(v -> {
            Intent intent = new Intent(HomePage.this, DanhSachTaiLieu.class);
            startActivity(intent);
        });
        imgbtnProfile.setOnClickListener(v -> {
            Intent intent = new Intent(HomePage.this, UserProfile.class);
            startActivity(intent);
        });
        imgbtnTimKiem.setOnClickListener(v -> {
            Intent intent = new Intent(HomePage.this, TimKiem.class);
            startActivity(intent);
        });
        imgbtnDSPM.setOnClickListener(v -> {
            Intent intent = new Intent(HomePage.this, DsPhieuMuon.class);
            startActivity(intent);
        });
        flDSTaiLieu.setOnClickListener(v -> {
            Intent intent = new Intent(HomePage.this, DanhSachTaiLieu.class);
            startActivity(intent);
        });
    }
}
