package com.example.nhom14didong.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nhom14didong.R;

public class Admin_Homepage extends AppCompatActivity {
private ImageButton btnQLTaiLieu;
private ImageButton btnXemThongKe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_homepage);
        btnQLTaiLieu = findViewById(R.id.btnQLTaiLieu);
        btnXemThongKe = findViewById(R.id.btnXemThongKe);
        btnQLTaiLieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_Homepage.this, QuanLyTaiLieu.class);
                startActivity(intent);
            }
        });
        btnXemThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_Homepage.this, XemThongKe.class);
                startActivity(intent);
            }
        });
    }
}
