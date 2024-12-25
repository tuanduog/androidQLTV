package com.example.nhom14didong.Activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.nhom14didong.Database.Database;
import com.example.nhom14didong.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class AddUserActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private EditText edtUsername, edtFullName, edtEmail, edtPassword;
    private Spinner spRole;
    private Button btnSave;
    private final List<String> Role = new ArrayList<>(Arrays.asList("admin", "user"));
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        anhxa();
        setSupportActionBar(toolbar);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Role);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spRole.setAdapter(arrayAdapter);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNewUser();
            }
        });
    }

    private void saveNewUser() {
        String username = edtUsername.getText().toString();
        String fullName = edtFullName.getText().toString();
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();
        String role = spRole.getSelectedItem().toString();
        String date = new Date().toString();

        if (username.isEmpty() || fullName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }
        if (isUsernameExist(username)) {
            Toast.makeText(this, "Tên tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!isValidEmail(email)) {
            Toast.makeText(this, "Email không hợp lệ", Toast.LENGTH_SHORT).show();
            return;
        }

        db = openOrCreateDatabase("mydatabase.db", MODE_PRIVATE, null);
        ContentValues values = new ContentValues();
        values.put("USERNAME", username);
        values.put("FULLNAME", fullName);
        values.put("EMAIL", email);
        values.put("USERPASS", password);
        values.put("ROLE", role);
        values.put("DATECREATE", date);

        long newRowId = db.insert("NGUOIDUNG", null, values);
        if (newRowId != -1) {
            Toast.makeText(this, "Thêm người dùng thành công", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);
            finish();
        } else {
            Toast.makeText(this, "Thêm người dùng thất bại", Toast.LENGTH_SHORT).show();
        }
    }

    public void anhxa() {
        toolbar = findViewById(R.id.toolbaradd);
        edtUsername = findViewById(R.id.edtUsername);
        edtFullName = findViewById(R.id.edtFullName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        spRole = findViewById(R.id.spRole);
        btnSave = findViewById(R.id.btnSave);
    }
    private boolean isValidEmail(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return email.matches(emailPattern);
    }
    private boolean isUsernameExist(String username) {
        db = openOrCreateDatabase("mydatabase.db", MODE_PRIVATE, null);
        Cursor cursor = db.query(
                "NGUOIDUNG", // Tên bảng
                new String[]{"USERNAME"}, // Cột cần truy vấn
                "USERNAME = ?", // Điều kiện WHERE
                new String[]{username}, // Giá trị thay thế ? trong điều kiện
                null, // Nhóm điều kiện
                null, // Điều kiện HAVING
                null // Điều kiện sắp xếp
        );

        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }
}