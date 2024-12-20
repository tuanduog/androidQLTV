package com.example.nhom14didong.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nhom14didong.R;

public class ThongKeChiTiet extends AppCompatActivity {
    private SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tkchitiet);
        String userID = getIntent().getStringExtra("USERID");
        String userName = getIntent().getStringExtra("USERNAME");
        TextView txtUserName = findViewById(R.id.user_name);
        TextView txtUserID = findViewById(R.id.user_id);
        txtUserID.setText(userID);
        txtUserName.setText(userName);
        database = openOrCreateDatabase("mydatabase.db", MODE_PRIVATE, null);
        loadDataFromDatabase();
    }

    private void loadDataFromDatabase() {
        // lấy số phiếu
        String userID = getIntent().getStringExtra("USERID");
        String query = "SELECT COUNT(*) AS Total FROM PHIEUMUON WHERE USERID = ?";
        Cursor cursor = database.rawQuery(query, new String[]{userID});
        if(cursor != null && cursor.moveToFirst()){
            int totalIndex = cursor.getColumnIndex("Total");
            if(totalIndex != -1){
                int total = cursor.getInt(totalIndex);
                TextView txtSoPhieuMuon = findViewById(R.id.txtSoPhieuMuon);
                txtSoPhieuMuon.setText(String.valueOf(total));
            }
        }

        // lấy thông tin phiếu
        String query1 = "SELECT PHIEUMUONID, TAILIEUID, TINHTRANG FROM PHIEUMUON WHERE USERID = ?";
        Cursor cursor1 = database.rawQuery(query1, new String[]{userID});
        TableLayout tbPhieuMuon = findViewById(R.id.tbPhieuMuon);
        if(cursor1 != null && cursor1.moveToFirst()){
            do {
                int phieuMuonIDIndex = cursor1.getColumnIndex("PHIEUMUONID");
                int taiLieuIDIndex = cursor1.getColumnIndex("TAILIEUID");
                int tinhTrangIndex = cursor1.getColumnIndex("TINHTRANG");
                String phieuMuonID = (phieuMuonIDIndex != -1) ? cursor1.getString(phieuMuonIDIndex) : "Unknown ID";
                String taiLieuID = (taiLieuIDIndex != -1) ? cursor1.getString(taiLieuIDIndex) : "Unknown Document ID";
                String tinhTrang = (tinhTrangIndex != -1) ? cursor1.getString(tinhTrangIndex) : "Unknown Status";

                String tenTLQuery = "SELECT TENTAILIEU FROM TAILIEU WHERE TAILIEUID = ?";
                Cursor tenTLCursor = database.rawQuery(tenTLQuery, new String[]{taiLieuID});
                String tenTL = "";
                if(tenTLCursor != null && tenTLCursor.moveToFirst()){
                    int tenTLIndex = tenTLCursor.getColumnIndex("TENTAILIEU");
                    if(tenTLIndex != -1){
                        tenTL = tenTLCursor.getString(tenTLIndex);
                    }
                    tenTLCursor.close();
                }
                TableRow tableRow = new TableRow(this);

                // Tạo + thêm các TextView vào TableRow
                TextView phieuMuonIDText = new TextView(this);
                phieuMuonIDText.setText(phieuMuonID);
                phieuMuonIDText.setPadding(8, 8, 8, 8);
                phieuMuonIDText.setGravity(Gravity.CENTER);
                phieuMuonIDText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);

                TextView tenTaiLieuText = new TextView(this);
                tenTaiLieuText.setText(tenTL);
                tenTaiLieuText.setPadding(8, 8, 8, 8);
                tenTaiLieuText.setGravity(Gravity.CENTER);
                tenTaiLieuText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);

                TextView tinhTrangText = new TextView(this);
                tinhTrangText.setText(tinhTrang);
                tinhTrangText.setPadding(8, 8, 8, 8);
                tinhTrangText.setGravity(Gravity.CENTER);
                tinhTrangText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);

                // Thêm các TextView vào TableRow
                tableRow.addView(phieuMuonIDText);
                tableRow.addView(tenTaiLieuText);
                tableRow.addView(tinhTrangText);

                // Thêm TableRow vào TableLayout
                tbPhieuMuon.addView(tableRow);
            } while (cursor.moveToNext());
        }
    }
}
