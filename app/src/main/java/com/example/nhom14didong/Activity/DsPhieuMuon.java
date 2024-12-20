package com.example.nhom14didong.Activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nhom14didong.Database.Database;
import com.example.nhom14didong.Model.PhieuMuon;
import com.example.nhom14didong.R;
import com.example.nhom14didong.adapter.PhieuMuonAdapter;

import java.util.ArrayList;

public class DsPhieuMuon extends AppCompatActivity {
    final String DATABASE_NAME = "mydatabase.db";
    SQLiteDatabase database;
    ListView listView;
    ArrayList<PhieuMuon> list;
    PhieuMuonAdapter adapter;
    Button btnChuaXacNhanPM;
    Button btnDaXacNhanPM;
    private String tinhTrang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dsphieumuon);

        list = new ArrayList<>();
        database = Database.initDatabase(this, DATABASE_NAME);

        anhXa();
        adapter = new PhieuMuonAdapter(this, list, database, tinhTrang);
        listView.setAdapter(adapter);

        // Mặc định trạng thái "Chưa xác nhận"
        tinhTrang = "Chưa xác nhận";
        setupButtonStyles(btnChuaXacNhanPM, btnDaXacNhanPM);
        readData(tinhTrang);

        btnChuaXacNhanPM.setOnClickListener(v -> {
            tinhTrang = "Chưa xác nhận";
            setupButtonStyles(btnChuaXacNhanPM, btnDaXacNhanPM);
            updateData();
        });

        btnDaXacNhanPM.setOnClickListener(v -> {
            tinhTrang = "Đã xác nhận";
            setupButtonStyles(btnDaXacNhanPM, btnChuaXacNhanPM);
            updateData();
        });
    }

    private void updateData() {
        adapter.setTinhTrang(tinhTrang);
        readData(tinhTrang);
    }

    private void readData(String tinhTrang) {
        Cursor cursor = database.rawQuery(
                "SELECT PHIEUMUONID, USERID, TAILIEUID, NGAYMUON, NGAYHENTRA, NGAYTRA, TINHTRANG, GHICHU, NGAYTAO " +
                        "FROM PHIEUMUON WHERE TINHTRANG = ?", new String[]{tinhTrang});
        list.clear();

        while (cursor.moveToNext()) {
            int phieuMuonID = cursor.getInt(0);
            int userID = cursor.getInt(1);
            int taiLieuID = cursor.getInt(2);
            String ngayMuon = cursor.getString(3);
            String ngayHenTra = cursor.getString(4);
            String ngayTra = cursor.getString(5);
            String tinhTrangPM = cursor.getString(6);
            String ghiChu = cursor.getString(7);
            String ngayTao = cursor.getString(8);
            list.add(new PhieuMuon(phieuMuonID, userID, taiLieuID, ngayMuon, ngayHenTra, ngayTra, tinhTrangPM, ghiChu, ngayTao));
        }

        adapter.notifyDataSetChanged();
        if (list.isEmpty()) {
            Toast.makeText(this, "Danh sách phiếu mượn trống", Toast.LENGTH_SHORT).show();
        }
        cursor.close();
    }

    private void setupButtonStyles(Button selected, Button unselected) {
        selected.setBackgroundResource(R.drawable.button_selected);
        selected.setTextColor(getResources().getColor(R.color.white));
        unselected.setBackgroundResource(R.drawable.button_default);
        unselected.setTextColor(getResources().getColor(R.color.black));
    }

    private void anhXa() {
        listView = findViewById(R.id.lvDSPM);
        btnDaXacNhanPM = findViewById(R.id.btnDaXacNhanPM);
        btnChuaXacNhanPM = findViewById(R.id.btnChuaXacNhanPM);
    }
}