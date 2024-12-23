package com.example.nhom14didong.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nhom14didong.R;
import com.example.nhom14didong.adapter.YeuThichAdapter;

public class ThongKeChiTiet extends AppCompatActivity {
    private SQLiteDatabase database;
    private ListView listView;
    private YeuThichAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tkchitiet);
        String userID = getIntent().getStringExtra("USERID");
        String userName = getIntent().getStringExtra("USERNAME");
        TextView txtUserName = findViewById(R.id.user_name);
        TextView txtUserID = findViewById(R.id.user_id);
        txtUserID.setText(userID);
        listView = findViewById(R.id.lv);
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
                phieuMuonIDText.setPadding(0, 8, 0, 8);
                phieuMuonIDText.setGravity(Gravity.CENTER);
                phieuMuonIDText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                phieuMuonIDText.setBackgroundResource(R.drawable.cell_border);
                phieuMuonIDText.setHeight(100);
                phieuMuonIDText.setTextColor(Color.BLACK);

                TextView tenTaiLieuText = new TextView(this);
                tenTaiLieuText.setText(tenTL);
                tenTaiLieuText.setPadding(8, 0, 8, 0);
                tenTaiLieuText.setGravity(Gravity.CENTER);
                tenTaiLieuText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                tenTaiLieuText.setMaxLines(2);
                tenTaiLieuText.setEllipsize(TextUtils.TruncateAt.END);
                tenTaiLieuText.setMaxWidth(190);
                tenTaiLieuText.setHeight(100);
                tenTaiLieuText.setTextColor(Color.BLACK);
                tenTaiLieuText.setBackgroundResource(R.drawable.cell_border);
                tenTaiLieuText.post(new Runnable() {
                    @Override
                    public void run() {
                        int lineCount = tenTaiLieuText.getLineCount();
                        if (lineCount == 2) {
                            // Nếu số dòng là 2, thay đổi marginTop
                            TableRow.LayoutParams params = (TableRow.LayoutParams) tenTaiLieuText.getLayoutParams();
                            params.topMargin = -16; // Thêm marginTop 10
                            tenTaiLieuText.setLayoutParams(params);
                        }
                    }
                });

                TextView tinhTrangText = new TextView(this);
                tinhTrangText.setText(tinhTrang);
                tinhTrangText.setPadding(8, 8, 8, 8);
                tinhTrangText.setGravity(Gravity.CENTER);
                tinhTrangText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                tinhTrangText.setBackgroundResource(R.drawable.cell_border);
                tinhTrangText.setHeight(100);
                tinhTrangText.setTextColor(Color.BLACK);

                // Thêm các TextView vào TableRow
                tableRow.addView(phieuMuonIDText);
                tableRow.addView(tenTaiLieuText);
                tableRow.addView(tinhTrangText);

                // Thêm TableRow vào TableLayout
                tbPhieuMuon.addView(tableRow);
            } while (cursor1.moveToNext());
        }
        TextView tvNoFavorites = findViewById(R.id.tv_no_favorites);
        String query2 = "SELECT TAILIEU.TAILIEUID, TAILIEU.TENTAILIEU, TAILIEU.THELOAI, TAILIEU.SOLUONG, TAILIEU.TINHTRANG, TAILIEU.IMAGE " +
                "FROM DANHSACHYEUTHICH " +
                "INNER JOIN TAILIEU ON DANHSACHYEUTHICH.TAILIEUID = TAILIEU.TAILIEUID " +
                "WHERE DANHSACHYEUTHICH.USERID = ?";
        Cursor cursor2 = database.rawQuery(query2, new String[]{userID});

        // Gán dữ liệu vào adapter và hiển thị
        if(cursor2 != null && cursor2.getCount() > 0){
            tvNoFavorites.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
            adapter = new YeuThichAdapter(this, cursor2);
            listView.setAdapter(adapter);
        } else {
            tvNoFavorites.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
        }
    }
}
