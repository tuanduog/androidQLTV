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
    private TextView soLuongTK, soLuongTL, soLuongPM;
    private ListView lv;
    private ThongKeAdapter adapter;
    private SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongke);
        soLuongTK = findViewById(R.id.soLuongTK);
        soLuongTL = findViewById(R.id.soLuongTL);
        soLuongPM = findViewById(R.id.soLuongPM);
        lv = findViewById(R.id.lv);
        database = openOrCreateDatabase("mydatabase.db", MODE_PRIVATE, null);
        loadDataFromDatabase();
    }

    private void loadDataFromDatabase() {
        String query = "SELECT USERID, USERNAME FROM NGUOIDUNG WHERE ROLE = ?";
        Cursor cursor = database.rawQuery(query, new String[]{"user"});
        int recordCount = cursor.getCount();
        soLuongTK.setText("" + recordCount);

        String query2 = "SELECT COUNT(*) FROM TAILIEU";
        Cursor cursor2 = database.rawQuery(query2, null);
        if (cursor2.moveToFirst()) {
            int soLuongTaiLieu = cursor2.getInt(0);
            soLuongTL.setText("" + soLuongTaiLieu);
        }
        cursor2.close();

        String query3 = "SELECT COUNT(*) FROM PHIEUMUON";
        Cursor cursor3 = database.rawQuery(query3, null);
        if (cursor3.moveToFirst()) {
            int soLuongPhieuMuon = cursor3.getInt(0);
            soLuongPM.setText("" + soLuongPhieuMuon);
        }
        cursor3.close();

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
