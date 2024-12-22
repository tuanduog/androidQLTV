package com.example.nhom14didong.Activity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.nhom14didong.Database.Database;
import com.example.nhom14didong.R;

public class ThemSach extends AppCompatActivity {

    private EditText edtBookImage, edtBookName, edtBookCategory, edtBookCount;
    private EditText edtBookAuthor, edtBookDescribe;
    private SQLiteDatabase db;
    private RadioButton rbDangCon, rbDaHet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_them_tailieu);

        // Khởi tạo các EditText từ giao diện
        edtBookImage = findViewById(R.id.edtChonAnh);
        edtBookName = findViewById(R.id.edtTenSach);
        edtBookCategory = findViewById(R.id.edtLoaiSach);
        edtBookCount = findViewById(R.id.edtSoLuong);
        edtBookAuthor = findViewById(R.id.edtTacGia);
        edtBookDescribe = findViewById(R.id.edtMoTa);
        rbDangCon = findViewById(R.id.rbDangCon);
        rbDaHet = findViewById(R.id.rbDaHet);

        db = Database.initDatabase(this, "mydatabase.db");

        // add
        findViewById(R.id.btnThem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBook();
            }
        });
        findViewById(R.id.btnHuy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void addBook() {
        // Lấy dữ liệu từ các EditText
        String bookImage = edtBookImage.getText().toString();
        String bookName = edtBookName.getText().toString();
        String bookCategory = edtBookCategory.getText().toString();
        String bookCount = edtBookCount.getText().toString();
        String bookAuthor = edtBookAuthor.getText().toString();
        String bookDescribe = edtBookDescribe.getText().toString();
        String bookStatus = "";

        if (rbDangCon.isChecked()) {
            bookStatus = "Đang còn";
        }
        if (rbDaHet.isChecked()) {
            bookStatus = "Đã hết";
            bookCount = "0";
            edtBookCount.setText("0");
        }
        if (bookCount.equals("0")) {
            rbDaHet.setChecked(true);
            bookStatus = "Đã hết";
        }
        // Check
        if (bookImage.isEmpty() || bookName.isEmpty() || bookCategory.isEmpty() || bookCount.isEmpty() || bookAuthor.isEmpty()) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        } else {
            try {
                int cnt = Integer.parseInt(bookCount);
                if(cnt < 0){
                    edtBookCount.setError("Số lượng sách không được âm!");
                    return;
                }
            } catch (NumberFormatException e) {
                edtBookCount.setError("Vui lòng nhập số hợp lệ!");
                return;
            }
            if(rbDaHet.isChecked()){
                edtBookCount.setText("0");
            }
            // Thêm sách vào cơ sở dữ liệu
            ContentValues values = new ContentValues();
            values.put("IMAGE", bookImage);
            values.put("TENTAILIEU", bookName);
            values.put("THELOAI", bookCategory);
            values.put("SOLUONG", bookCount);
            values.put("TINHTRANG", bookStatus);
            values.put("TACGIA", bookAuthor);
            values.put("MOTA", bookDescribe);

            // Thực hiện insert vào bảng "TAILIEU"
            long result = db.insert("TAILIEU", null, values);

            if (result == -1) {
                Toast.makeText(this, "Thêm tài liệu thất bại", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Thêm tài liệu thành công", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
}

