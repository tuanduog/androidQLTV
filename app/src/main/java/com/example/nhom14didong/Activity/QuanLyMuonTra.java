package com.example.nhom14didong.Activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nhom14didong.Database.Database;
import com.example.nhom14didong.Model.PhieuMuon;
import com.example.nhom14didong.R;
import com.example.nhom14didong.adapter.QLPhieuMuonTraAdapter;

import java.util.ArrayList;

public class QuanLyMuonTra extends AppCompatActivity {
    final String DATABASE_NAME = "mydatabase.db";
    SQLiteDatabase database;
    ListView listView;
    ArrayList<PhieuMuon> list;
    QLPhieuMuonTraAdapter adapter;
    Button btnPhieuMuon;
    Button btnPhieuTra;
    Button btnTimKiem;
    EditText edtTimKiem;
    private String tinhTrang;
    private String ngayTra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_muon_tra);

        list = new ArrayList<>();
        database = Database.initDatabase(this, DATABASE_NAME);
        adapter = new QLPhieuMuonTraAdapter(this, list, database, ngayTra);

        anhXa();
        listView.setAdapter(adapter);

        // Mặc định là Quản lý phiếu mượn
        btnPhieuMuon.setBackgroundResource(R.drawable.button_selected);
        btnPhieuMuon.setTextColor(getResources().getColor(R.color.white));
        btnPhieuTra.setBackgroundResource(R.drawable.button_default);
        btnPhieuTra.setTextColor(getResources().getColor(R.color.black));
        tinhTrang = "Đã xác nhận"; // Mặc định
        ngayTra = null; // Mặc định
        readData(tinhTrang);

        // Cài đặt sự kiện cho btnPhieuMuon
        btnPhieuMuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ngayTra = null;
                adapter.setNgayTra(ngayTra);
                readData(tinhTrang);
                btnPhieuMuon.setBackgroundResource(R.drawable.button_selected);
                btnPhieuMuon.setTextColor(getResources().getColor(R.color.white));
                btnPhieuTra.setBackgroundResource(R.drawable.button_default);
                btnPhieuTra.setTextColor(getResources().getColor(R.color.black));
            }
        });

        // Cài đặt sự kiện cho btnPhieuTra
        btnPhieuTra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ngayTra = "datra";
                adapter.setNgayTra(ngayTra);
                readData(tinhTrang);
                btnPhieuTra.setBackgroundResource(R.drawable.button_selected);
                btnPhieuTra.setTextColor(getResources().getColor(R.color.white));
                btnPhieuMuon.setBackgroundResource(R.drawable.button_default);
                btnPhieuMuon.setTextColor(getResources().getColor(R.color.black));
            }
        });

        timKiem();
    }

    // Hàm tìm kiếm
    private void timKiem() {
        btnTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword = edtTimKiem.getText().toString().trim();
                if (keyword.isEmpty()) {
                    Toast.makeText(QuanLyMuonTra.this, "Vui lòng nhập từ khóa tìm kiếm", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    list.clear();
                    list.addAll(searchPhieuMuon(keyword, tinhTrang, ngayTra, database));
                    adapter.notifyDataSetChanged();
                    listView.setAdapter(adapter);
                    if (list.isEmpty()) {
                        Toast.makeText(QuanLyMuonTra.this, "Không tìm thấy kết quả phù hợp", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(QuanLyMuonTra.this, "Đã xảy ra lỗi khi tìm kiếm: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    // Hàm đọc dữ liệu từ database
    public void readData(String tinhTrang) {
        String query = "SELECT PHIEUMUONID,USERID, TAILIEUID, NGAYMUON, " +
                "NGAYHENTRA, NGAYTRA, TINHTRANG, " +
                "GHICHU, NGAYTAO FROM PHIEUMUON " +
                "WHERE TINHTRANG=? ";
        if (ngayTra == null) {
            query += " AND NGAYTRA IS NULL";
        } else {
            query += " AND NGAYTRA IS NOT NULL";
        }

        Cursor cursor = database.rawQuery(query, new String[]{tinhTrang});
        list.clear();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int phieuMuonID = cursor.getInt(0);
                int userID = cursor.getInt(1);
                int taiLieuID = cursor.getInt(2);
                String ngayMuon = cursor.getString(3);
                String ngayHenTra = cursor.getString(4);
                String ngayTraReal = cursor.getString(5);
                String tinhTrangPM = cursor.getString(6);
                String ghiChu = cursor.getString(7);
                String ngayTao = cursor.getString(8);
                list.add(new PhieuMuon(phieuMuonID, userID, taiLieuID, ngayMuon, ngayHenTra, ngayTraReal, tinhTrangPM, ghiChu, ngayTao));
            }
            adapter.notifyDataSetChanged();
            listView.setAdapter(adapter);
            cursor.close();
        }
        if (list.isEmpty()) {
            Toast.makeText(this, "Danh sách trống", Toast.LENGTH_SHORT).show();
        }
    }

    // Phương thức khởi tạo các view
    private void anhXa() {
        listView = findViewById(R.id.lvQLPhieuMuonTra);
        btnPhieuMuon = findViewById(R.id.btnQLPhieuMuon);
        btnPhieuTra = findViewById(R.id.btnQLPhieuTra);
        btnTimKiem = findViewById(R.id.btnTimKiemMuonTra);
        edtTimKiem = findViewById(R.id.edt_TimKiemMuonTra);
    }

    // Hàm tìm kiếm phiếu mượn
    private ArrayList<PhieuMuon> searchPhieuMuon(String keyword, String tinhTrang, String ngayTra, SQLiteDatabase database) {
        ArrayList<PhieuMuon> resultList = new ArrayList<>();
        String query = "SELECT PHIEUMUON.PHIEUMUONID, PHIEUMUON.USERID, PHIEUMUON.TAILIEUID, " +
                "PHIEUMUON.NGAYMUON, PHIEUMUON.NGAYHENTRA, PHIEUMUON.NGAYTRA, PHIEUMUON.TINHTRANG, PHIEUMUON.GHICHU, " +
                "PHIEUMUON.NGAYTAO, NGUOIDUNG.FULLNAME AS USER_FULLNAME, TAILIEU.TENTAILIEU AS DOCUMENT_NAME " +
                "FROM PHIEUMUON INNER JOIN TAILIEU ON PHIEUMUON.TAILIEUID = TAILIEU.TAILIEUID " +
                "INNER JOIN NGUOIDUNG ON PHIEUMUON.USERID = NGUOIDUNG.USERID " +
                "WHERE PHIEUMUON.TINHTRANG = ? " +
                "AND (CAST(PHIEUMUON.PHIEUMUONID AS TEXT) LIKE ? OR " +
                "CAST(PHIEUMUON.USERID AS TEXT) LIKE ? OR " +
                "NGUOIDUNG.FULLNAME LIKE ? OR " +
                "TAILIEU.TENTAILIEU LIKE ?) ";

        String searchKeyword = "%" + keyword + "%";
        if (ngayTra != null) {
            query += " AND NGAYTRA IS NOT NULL";
        } else {
            query += " AND NGAYTRA IS NULL";
        }

        try (Cursor cursor = database.rawQuery(query, new String[]{tinhTrang, searchKeyword, searchKeyword, searchKeyword, searchKeyword})) {
            while (cursor.moveToNext()) {
                int phieuMuonID = cursor.getInt(0);
                int userID = cursor.getInt(1);
                int taiLieuID = cursor.getInt(2);
                String ngayMuon = cursor.getString(3);
                String ngayHenTra = cursor.getString(4);
                String ngayTraReal = cursor.getString(5);
                String tinhTrangPM = cursor.getString(6);
                String ghiChu = cursor.getString(7);
                String ngayTao = cursor.getString(8);
                resultList.add(new PhieuMuon(phieuMuonID, userID, taiLieuID, ngayMuon, ngayHenTra, ngayTraReal, tinhTrangPM, ghiChu, ngayTao));
            }
        } catch (Exception e) {
            Log.e("DB Error", "Error during search: " + e.getMessage());
        }

        return resultList;
    }

}
