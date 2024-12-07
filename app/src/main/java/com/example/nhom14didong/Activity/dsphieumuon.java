package com.example.nhom14didong.Activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nhom14didong.Database.Database;
import com.example.nhom14didong.Model.PhieuMuon;
import com.example.nhom14didong.R;
import com.example.nhom14didong.adapter.PhieuMuonAdapter;

import java.util.ArrayList;

public class dsphieumuon extends AppCompatActivity {
    final String DATABASE_NAME = "mydatabase.db";
    SQLiteDatabase database;
    ListView listView;
    ArrayList<PhieuMuon> list;
    PhieuMuonAdapter adapter;
    Button btnChuaXacNhanPM;
    Button btnDaXacNhanPM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dsphieumuon);
        anhXa();
        addControls();
        readData("Chưa xác nhận");
    }
    private void anhXa(){
        listView = findViewById(R.id.lvDSPM);
        btnDaXacNhanPM= findViewById(R.id.btnDaXacNhanPM);
        btnChuaXacNhanPM= findViewById(R.id.btnChuaXacNhanPM);

    }

    private void addControls() {
        list = new ArrayList<>();
        database = Database.initDatabase(this, DATABASE_NAME); // Khởi tạo database
        adapter = new PhieuMuonAdapter(this, list, database);  // Truyền database vào adapter
        listView.setAdapter(adapter);
    }

    private void readData(String checkTinhTrang) {
        // Truy vấn danh sách phiếu mượn chưa xác nhận ( mặc định)
        Cursor cursor = database.rawQuery("SELECT PHIEUMUONID, USERID, TAILIEUID, NGAYMUON, NGAYHENTRA, NGAYTRA, TINHTRANG, GHICHU, NGAYTAO FROM PHIEUMUON WHERE USERID=? AND TINHTRANG=? ", new String[]{"2",checkTinhTrang});
        list.clear(); // Xóa dữ liệu cũ
        while (cursor.moveToNext()) {
            int phieuMuonID = cursor.getInt(0);
            int userID = cursor.getInt(1);
            int taiLieuID = cursor.getInt(2);
            String ngayMuon = cursor.getString(3);
            String ngayHenTra = cursor.getString(4);
            String ngayTra = cursor.getString(5);
            String tinhTrang = cursor.getString(6);
            String ghiChu = cursor.getString(7);
            String ngayTao = cursor.getString(8);
            list.add(new PhieuMuon(phieuMuonID, userID, taiLieuID, ngayMuon, ngayHenTra, ngayTra, tinhTrang, ghiChu, ngayTao));
        }
        if (list.isEmpty()) {
            Toast.makeText(this, "Danh sách phiếu mượn trống", Toast.LENGTH_SHORT).show();
        }
        cursor.close();
        adapter.notifyDataSetChanged();

    }
}
