package com.example.nhom14didong.Activity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nhom14didong.Database.Database;
import com.example.nhom14didong.R;

public class SuaSach extends AppCompatActivity {
    private EditText edtChonAnh, edtTenSach, edtLoaiSach, edtSoLuong, edtTacGia, edtMoTa;
    private Button btnSua, btnHuy;
    private String currentBookName, currentCategory, currentCount, currentStatus, currentImagePath;
    private String currentBookAuthor, currentBookDes;
    private RadioGroup rgTT;
    private RadioButton rbDangCon, rbDaHet;
    private long currentItemId;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sua_tailieu);

        // Get data from the intent
        currentBookName = getIntent().getStringExtra("BOOK_NAME");
        currentCategory = getIntent().getStringExtra("CATEGORY");
        currentCount = getIntent().getStringExtra("COUNT");
        currentStatus = getIntent().getStringExtra("STATUS");
        currentBookAuthor = getIntent().getStringExtra("AUTHOR");
        currentBookDes = getIntent().getStringExtra("DESCRIBE");
        currentImagePath = getIntent().getStringExtra("IMAGE_PATH");
        currentItemId = getIntent().getLongExtra("ITEM_ID", -1);

        edtChonAnh = findViewById(R.id.edtChonAnh);
        edtTenSach = findViewById(R.id.edtTenSach);
        edtLoaiSach = findViewById(R.id.edtLoaiSach);
        edtSoLuong = findViewById(R.id.edtSoLuong);
        edtTacGia = findViewById(R.id.edtTacGia);
        edtMoTa = findViewById(R.id.edtMoTa);
        rgTT = findViewById(R.id.rgTT);
        rbDaHet = findViewById(R.id.rbDaHet);
        rbDangCon = findViewById(R.id.rbDangCon);

        // hiển thị từ intent
        edtTenSach.setText(currentBookName);
        edtLoaiSach.setText(currentCategory);
        edtSoLuong.setText(currentCount);
        edtMoTa.setText(currentBookDes);
        edtTacGia.setText(currentBookAuthor);
        edtChonAnh.setText(currentImagePath);
        if ("Đang còn".equals(currentStatus)) {
            rbDangCon.setChecked(true);
            rbDaHet.setChecked(false);
        } else if ("Đã hết".equals(currentStatus)) {
            rbDaHet.setChecked(true);
            rbDangCon.setChecked(false);
        }
        btnSua = findViewById(R.id.btnSua);
        btnHuy = findViewById(R.id.btnHuy);
        // sửa
        btnSua.setOnClickListener(v -> {
            String updatedBookName = edtTenSach.getText().toString();
            String updatedCategory = edtLoaiSach.getText().toString();
            String updatedCount = edtSoLuong.getText().toString();
            String updatedAuthor = edtTacGia.getText().toString();
            String updatedDes = edtMoTa.getText().toString();
            String updatedStatus = "";
            String updatedImagePath = edtChonAnh.getText().toString();
            if(rbDangCon.isChecked()){
                updatedStatus = "Đang còn";
            }
            if(rbDaHet.isChecked()){
                updatedStatus = "Đã hết";
                updatedCount = "0";
                edtSoLuong.setText("0");
            }
            if(updatedCount.equals("0")){
                rbDaHet.setChecked(true);
                updatedStatus = "Đã hết";
            }
            try {
                int cnt = Integer.parseInt(updatedCount);
                if(cnt < 0){
                    edtSoLuong.setError("Số lượng sách không được âm!");
                    return;
                }
            } catch (NumberFormatException e) {
                edtSoLuong.setError("Vui lòng nhập số hợp lệ!");
                return;
            }
            // update
            db = Database.initDatabase(this, "mydatabase.db");
            ContentValues values = new ContentValues();
            values.put("TENTAILIEU", updatedBookName);
            values.put("THELOAI", updatedCategory);
            values.put("SOLUONG", updatedCount);
            values.put("TACGIA", updatedAuthor);
            values.put("MOTA", updatedDes);
            values.put("TINHTRANG", updatedStatus);
            values.put("IMAGE", updatedImagePath);

            String whereClause = "TAILIEUID = ?";
            String[] whereArgs = {String.valueOf(currentItemId)};

            db.update("TAILIEU", values, whereClause, whereArgs);
            Toast.makeText(this, "Sửa tài liệu thành công!", Toast.LENGTH_SHORT).show();
            finish();
        });

        // Handle "Hủy" button click
        btnHuy.setOnClickListener(v -> finish());
    }
}
