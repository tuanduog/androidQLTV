package com.example.nhom14didong.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.nhom14didong.R;
import com.example.nhom14didong.adapter.TaiLieuAdapter;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class QuanLyTaiLieu extends AppCompatActivity {

    private ListView lv;
    private Button btnThem;
    private TaiLieuAdapter adapter;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ql_tailieu); // Use your actual layout file

        lv = findViewById(R.id.lv);
        btnThem = findViewById(R.id.btnThem);
        database = openOrCreateDatabase("mydatabase.db", MODE_PRIVATE, null);
        loadDataFromDatabase();

        btnThem.setOnClickListener(v -> {
            Intent intent = new Intent(QuanLyTaiLieu.this, ThemSach.class);
            startActivity(intent);
        });
    }

    private void loadDataFromDatabase() {
        String query = "SELECT * FROM TAILIEU";
        Cursor cursor = database.rawQuery(query, null);

        adapter = new TaiLieuAdapter(this, cursor);
        lv.setAdapter(adapter);
    }
    @Override
    protected void onResume() {
        super.onResume();
        loadDataFromDatabase(); // Tải lại dữ liệu khi quay lại màn hình
    }

}


