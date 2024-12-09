package com.example.nhom14didong.Activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
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
    private String tinhTrang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dsphieumuon);
        list = new ArrayList<>();
        database = Database.initDatabase(this, DATABASE_NAME);
        adapter = new PhieuMuonAdapter(this, list, database, tinhTrang);
        anhXa();
        listView.setAdapter(adapter);
        //Mặc định la chua xac nhan
        btnChuaXacNhanPM.setBackgroundResource(R.drawable.button_selected);
        btnChuaXacNhanPM.setTextColor(getResources().getColor(R.color.white));
        btnDaXacNhanPM.setBackgroundResource(R.drawable.button_default); // Nền mặc định
        btnDaXacNhanPM.setTextColor(getResources().getColor(R.color.black));
        tinhTrang="Chưa xác nhận"; //Mac dinh
        readData(tinhTrang);
        btnChuaXacNhanPM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tinhTrang = "Chưa xác nhận";
                adapter.setTinhTrang(tinhTrang);
                readData(tinhTrang);
                btnChuaXacNhanPM.setBackgroundResource(R.drawable.button_selected);
                btnChuaXacNhanPM.setTextColor(getResources().getColor(R.color.white));
                btnDaXacNhanPM.setBackgroundResource(R.drawable.button_default); // Nền mặc định
                btnDaXacNhanPM.setTextColor(getResources().getColor(R.color.black));
            }
        });
        btnDaXacNhanPM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tinhTrang = "Đã xác nhận";
                adapter.setTinhTrang(tinhTrang);
                readData(tinhTrang);
                btnDaXacNhanPM.setBackgroundResource(R.drawable.button_selected);
                btnDaXacNhanPM.setTextColor(getResources().getColor(R.color.white));
                btnChuaXacNhanPM.setBackgroundResource(R.drawable.button_default);
                btnChuaXacNhanPM.setTextColor(getResources().getColor(R.color.black));
            }
        });
    }

    private void readData(String tinhTrang) {
        Cursor cursor = database.rawQuery(
                "SELECT PHIEUMUONID,USERID, TAILIEUID, NGAYMUON," +
                        " NGAYHENTRA, NGAYTRA, TINHTRANG," +
                        "GHICHU, NGAYTAO FROM PHIEUMUON " +
                "WHERE TINHTRANG=?", new String[]{tinhTrang});
        //Khi nào có user id thi them where id
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
            adapter.notifyDataSetChanged();
        }
        if (list.isEmpty()) {
            Toast.makeText(this, "Danh sách phiếu mượn trống", Toast.LENGTH_SHORT).show();
        }
        if(cursor!=null){
            cursor.close();
        }

    }

    private void anhXa(){
        listView = findViewById(R.id.lvDSPM);
        btnDaXacNhanPM= findViewById(R.id.btnDaXacNhanPM);
        btnChuaXacNhanPM= findViewById(R.id.btnChuaXacNhanPM);
    }

}
