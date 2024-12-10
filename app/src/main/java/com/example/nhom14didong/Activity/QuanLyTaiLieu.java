package com.example.nhom14didong.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.nhom14didong.R;
import com.example.nhom14didong.adapter.TaiLieuAdapter;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class QuanLyTaiLieu extends AppCompatActivity {

    private ListView listView;
    private Button btnThem;
    private TaiLieuAdapter adapter;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ql_tailieu); // Use your actual layout file

        listView = findViewById(R.id.lv);
        btnThem = findViewById(R.id.btnThem);

        // Open the database (you can replace this with your actual database helper)
        database = openOrCreateDatabase("mydatabase.db", MODE_PRIVATE, null);

        // Fetch data from the database and set up the adapter
        loadDataFromDatabase();

        btnThem.setOnClickListener(v -> {
            Intent intent = new Intent(QuanLyTaiLieu.this, ThemSach.class);
            startActivity(intent);
        });
    }

    private void loadDataFromDatabase() {
        // Query to fetch all records from the database table (replace "your_table_name" with actual table name)
        String query = "SELECT * FROM TAILIEU";
        Cursor cursor = database.rawQuery(query, null);

        // Create and set the adapter
        adapter = new TaiLieuAdapter(this, cursor);
        listView.setAdapter(adapter);
    }
    @Override
    protected void onResume() {
        super.onResume();
        loadDataFromDatabase(); // Tải lại dữ liệu khi quay lại màn hình
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        if (adapter != null && adapter.getCursor() != null) {
//            adapter.getCursor().close();
//        }
//        if (database != null) {
//            database.close();
//        }
//    }
}


