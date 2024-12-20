package com.example.nhom14didong.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nhom14didong.R;
import com.example.nhom14didong.adapter.TaiLieuAdapter;
import com.example.nhom14didong.adapter.ThongKeAdapter;

public class XemThongKe extends AppCompatActivity {
    private TextView soLuongTK;
    private ListView lv;
    private ThongKeAdapter adapter;
    private SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongke);
        soLuongTK = findViewById(R.id.soLuongTK);
        lv = findViewById(R.id.lv);
        database = openOrCreateDatabase("mydatabase.db", MODE_PRIVATE, null);
        loadDataFromDatabase();
    }

    private void loadDataFromDatabase() {
        String query = "SELECT USERID, USERNAME FROM NGUOIDUNG WHERE ROLE = ?";
        Cursor cursor = database.rawQuery(query, new String[]{"user"});
        int recordCount = cursor.getCount();
        soLuongTK.setText("" + recordCount);
        adapter = new ThongKeAdapter(this, cursor);
        lv.setAdapter(adapter);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (adapter != null) {
            adapter.closeCursor();
        }
        if (database != null && database.isOpen()) {
            database.close();
        }
    }

}
