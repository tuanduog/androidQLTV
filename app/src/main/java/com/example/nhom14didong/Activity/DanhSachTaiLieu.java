package com.example.nhom14didong.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nhom14didong.R;
import com.example.nhom14didong.adapter.TaiLieuAdapter;
import com.example.nhom14didong.adapter.TaiLieuAdapter_us;

public class DanhSachTaiLieu extends AppCompatActivity {

    private ListView listView;
    private TaiLieuAdapter_us adapter;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dstailieu);

        listView = findViewById(R.id.lv);
        database = openOrCreateDatabase("mydatabase.db", MODE_PRIVATE, null);
        loadDataFromDatabase();
    }

    private void loadDataFromDatabase() {
        String query = "SELECT * FROM TAILIEU";
        Cursor cursor = database.rawQuery(query, null);
        adapter = new TaiLieuAdapter_us(this, cursor);
        listView.setAdapter(adapter);
    }
    @Override
    protected void onResume() {
        super.onResume();
        loadDataFromDatabase(); // Tải lại dữ liệu khi quay lại màn hình
    }
}
