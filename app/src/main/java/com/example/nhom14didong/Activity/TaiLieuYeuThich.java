package com.example.nhom14didong.Activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nhom14didong.R;
import com.example.nhom14didong.adapter.TaiLieuAdapter_us;
import com.example.nhom14didong.adapter.YeuThichAdapter;

public class TaiLieuYeuThich extends AppCompatActivity {
    private ListView listView;
    private YeuThichAdapter adapter;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tailieuyeuthich);

        listView = findViewById(R.id.lv);
        database = openOrCreateDatabase("mydatabase.db", MODE_PRIVATE, null);
        loadDataFromDatabase();
    }

    public void loadDataFromDatabase() {
        String userId = getSharedPreferences("UserPref", MODE_PRIVATE).getString("USERID", null);
        String query = "SELECT TAILIEU.TENTAILIEU, TAILIEU.THELOAI, TAILIEU.SOLUONG, TAILIEU.TINHTRANG, TAILIEU.IMAGE " +
                "FROM DANHSACHYEUTHICH " +
                "INNER JOIN TAILIEU ON DANHSACHYEUTHICH.TAILIEUID = TAILIEU.TAILIEUID " +
                "WHERE DANHSACHYEUTHICH.USERID = ?";
        Cursor cursor = database.rawQuery(query, new String[]{userId});

        // Gán dữ liệu vào adapter và hiển thị
        adapter = new YeuThichAdapter(this, cursor);
        listView.setAdapter(adapter);
    }
    @Override
    protected void onResume() {
        super.onResume();
        loadDataFromDatabase(); // Tải lại dữ liệu khi quay lại màn hình
    }
}
