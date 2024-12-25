package com.example.nhom14didong.Activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.nhom14didong.Database.Database;
import com.example.nhom14didong.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EditUser extends AppCompatActivity {
        private Toolbar toolbar;
        private  EditText edtFullName, edtEmail ;
        Spinner spRole;
        private Button btnSave;
        private int userId;
        private final List<String> Role = new ArrayList<String>(Arrays.asList("admin","user"));
        private SQLiteDatabase db;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_edit_user);
            anhxa();
            setSupportActionBar(toolbar);
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Role);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spRole.setAdapter(arrayAdapter);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed(); // This will act the same as the back button
                }

            });
            Intent intent = getIntent();
            userId = intent.getIntExtra("USER_ID", -1);

            edtFullName.setText(intent.getStringExtra("FULL_NAME"));
            edtEmail.setText(intent.getStringExtra("EMAIL"));
            if(intent.getStringExtra("ROLE").equals(spRole.getSelectedItem().toString())){
                spRole.setSelection(0);
            }else {
                spRole.setSelection(1);
            };
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveUserChanges();
                }
            });
        }
        private void saveUserChanges() {

            String fullName = edtFullName.getText().toString();
            String email = edtEmail.getText().toString();
            String role = spRole.getSelectedItem().toString();
            if(fullName.equals("") || email.length()<3){
                Toast.makeText(this, "Tên phải từ 3 ký tự", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!isValidEmail(email)) {
                Toast.makeText(this, "Email không hợp lệ", Toast.LENGTH_SHORT).show();
                return;
            }
            // Thực hiện cập nhật trong cơ sở dữ liệu
            db = openOrCreateDatabase("mydatabase.db", MODE_PRIVATE, null);
            ContentValues values = new ContentValues();
            values.put("FULLNAME", fullName);
            values.put("EMAIL", email);
            values.put("ROLE", role);

            int rowsAffected = db.update("NGUOIDUNG", values, "USERID = ?", new String[]{String.valueOf(userId)});
            if (rowsAffected > 0) {
                Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK);
                finish();
            } else {
                Toast.makeText(this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
            }
        }

        public void anhxa() {
            toolbar =findViewById(R.id.toolbaredit);

            edtFullName = findViewById(R.id.edtFullName);
            edtEmail = findViewById(R.id.edtEmail);
            spRole = findViewById(R.id.edtRole);
            btnSave = findViewById(R.id.btnSave);
        }
    private boolean isValidEmail(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return email.matches(emailPattern);
    }
    }


