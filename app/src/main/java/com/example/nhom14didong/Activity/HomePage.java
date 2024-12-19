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
    ImageButton imgbtnDSTL, imgbtnTimKiem;
    FrameLayout flDSTaiLieu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        imgbtnDSTL = findViewById(R.id.imgbtnDSTL);
        flDSTaiLieu = findViewById(R.id.flDSTaiLieu);
        imgbtnTimKiem = findViewById(R.id.imgbtnTimKiem);
        imgbtnDSTL.setOnClickListener(v -> {
            Intent intent = new Intent(HomePage.this, DanhSachTaiLieu.class);
            startActivity(intent);
        });
        imgbtnTimKiem.setOnClickListener(v -> {
            Intent intent = new Intent(HomePage.this, TimKiem.class);
            startActivity(intent);
        });
        flDSTaiLieu.setOnClickListener(v -> {
            Intent intent = new Intent(HomePage.this, DanhSachTaiLieu.class);
            startActivity(intent);
        });
    }
}
