package com.example.nhom14didong.Activity;

import android.content.Context;
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
import com.example.nhom14didong.adapter.PhieuMuonUsAdapter;
import com.example.nhom14didong.adapter.TaiLieuAdapter_us;

import java.util.ArrayList;

public class DanhSachPhieuMuonUS extends AppCompatActivity {
    final String DATABASE_NAME = "mydatabase.db";
    SQLiteDatabase database;
    ListView listView;
    ArrayList<PhieuMuon> list;
    PhieuMuonUsAdapter adapter;
    Button btnChuaXacNhanPM;
    Button btnDaXacNhanPM;
    private String tinhTrang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_phieu_muon_us);

        list = new ArrayList<>();
        database = Database.initDatabase(this, DATABASE_NAME);

        anhXa();
        adapter = new PhieuMuonUsAdapter(this, list, database, tinhTrang);
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
    public void readData(String tinhTrang) {
        String userId =getSharedPreferences("UserPref", Context.MODE_PRIVATE).getString("USERID", null);
        Cursor cursor = database.rawQuery(
                "SELECT PHIEUMUONID, USERID, TAILIEUID, NGAYMUON, NGAYHENTRA, NGAYTRA, TINHTRANG, GHICHU, NGAYTAO " +
                        "FROM PHIEUMUON WHERE TINHTRANG = ? AND USERID = ?", new String[]{tinhTrang, userId});
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
        listView.setAdapter(adapter);
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
        listView = findViewById(R.id.lvDSPMUS);
        btnDaXacNhanPM = findViewById(R.id.btnDaXacNhanPMUS);
        btnChuaXacNhanPM = findViewById(R.id.btnChuaXacNhanPMUS);
    }
}
