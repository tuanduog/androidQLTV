package com.example.nhom14didong.Activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nhom14didong.R;
import com.example.nhom14didong.adapter.TaiLieuAdapter_us;

public class TimKiem extends AppCompatActivity {
    private EditText edtTimKiem;
    private ImageButton btnTimKiem;
    private ListView lv;
    private TaiLieuAdapter_us adapter;
    private SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timkiem);
        edtTimKiem = findViewById(R.id.edtTimKiem);
        btnTimKiem = findViewById(R.id.btnTimKiem);
        lv = findViewById(R.id.lv);
        database = openOrCreateDatabase("mydatabase.db", MODE_PRIVATE, null);
        btnTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String keyword = edtTimKiem.getText().toString().trim();
                if(!keyword.isEmpty()){
                    TimKiemTaiLieu(keyword);
                } else {
                    Toast.makeText(TimKiem.this, "Vui lòng nhâp từ khóa muốn tìm kiếm!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void TimKiemTaiLieu(String keyword){
        String query = "SELECT IMAGE, TENTAILIEU, THELOAI, SOLUONG, TINHTRANG FROM TAILIEU WHERE TENTAILIEU LIKE ?";
        Cursor cursor = database.rawQuery(query, new String[]{"%" + keyword + "%"});

        if(cursor.getCount() > 0){
            adapter = new TaiLieuAdapter_us(this, cursor);
            lv.setAdapter(adapter);
        } else {
            Toast.makeText(this, "Không tìm thấy tài liệu nào", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (database != null && database.isOpen()) {
            database.close();
        }
    }
}
