package com.example.nhom14didong.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.nhom14didong.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DangKy extends AppCompatActivity {
    String DB_PATH_SUFFIX = "/databases/";
    SQLiteDatabase database=null;
    String DATABASE_NAME="mydatabase.db";
    private EditText edtUsername, edtPassword1, edtEmail, edtPassword2, edtHotenDK;
    private Button btnDangKy, btnQuaylai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        processCopy();
        database = SQLiteDatabase.openDatabase(getDatabasePath(), null, SQLiteDatabase.OPEN_READWRITE);
        edtUsername = findViewById(R.id.edtTaiKhoanDK);
        edtPassword1 = findViewById(R.id.edtMatKhauDK1);
        edtEmail = findViewById(R.id.edtEmailDK);
        edtPassword2 = findViewById(R.id.edtMatKhau2);
        edtHotenDK = findViewById(R.id.edtHoTenDk);
        btnDangKy = findViewById(R.id.btnTaoTaiKhoan);
        btnQuaylai = findViewById(R.id.btnQuayLai);
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DangKyTaiKhoan();
            }
        });
        btnQuaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangKy.this, Start.class);
                startActivity(intent);
            }
        });
    }
    private void DangKyTaiKhoan() {
        String username = edtUsername.getText().toString().trim();
        String password1 = edtPassword1.getText().toString().trim();
        String password2 = edtPassword2.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String hoten = edtHotenDK.getText().toString().trim();
        // Kiểm tra rỗng
        if (username.isEmpty() || password1.isEmpty() || email.isEmpty() || hoten.isEmpty() || password2.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Email không hợp lệ!", Toast.LENGTH_SHORT).show();
            return;
        }
        // Kiểm tra tài khoản đã tồn tại
        Cursor cursor = database.rawQuery("SELECT * FROM NGUOIDUNG WHERE USERNAME = ?", new String[]{username});
        if (cursor.getCount() > 0) {
            Toast.makeText(this, "Tài khoản đã tồn tại!", Toast.LENGTH_SHORT).show();
            cursor.close();
            return;
        }

        if(password1.length() < 6){
            Toast.makeText(this, "Mật khẩu phải chứa ít nhất 6 kí tự!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!password1.equals(password2)){
            Toast.makeText(this, "Mật khẩu xác nhận không đúng!", Toast.LENGTH_SHORT).show();
            return;
        }
        cursor.close();

        // Thêm tài khoản vào cơ sở dữ liệu
        try {
            String query = "INSERT INTO NGUOIDUNG (USERNAME, USERPASS, FULLNAME, EMAIL, ROLE) VALUES ('" + username + "', '" + password1 + "', '" + hoten + "', '" + email + "', 'user')";
            database.execSQL(query);
            Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();

            // Xóa nội dung nhập sau khi đăng ký thành công
            edtUsername.setText("");
            edtPassword1.setText("");
            edtHotenDK.setText("");
            edtPassword2.setText("");
            edtEmail.setText("");
            Intent intent = new Intent(DangKy.this, DangNhap.class);
            startActivity(intent);

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Lỗi khi đăng ký: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void processCopy() {
        File dbFile = getDatabasePath(DATABASE_NAME);
        if (!dbFile.exists())
        {
            try{CopyDataBaseFromAsset();
                Toast.makeText(this, "Copying sucess from Assets folder",
                        Toast.LENGTH_LONG).show();
            }
            catch (Exception e){
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }
    private String getDatabasePath() {
        return getApplicationInfo().dataDir + DB_PATH_SUFFIX + DATABASE_NAME;
    }
    public void CopyDataBaseFromAsset() {

        try {
            InputStream myInput;
            myInput = getAssets().open(DATABASE_NAME);

            String outFileName = getDatabasePath();

            File f = new File(getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if (!f.exists())
                f.mkdir();
            OutputStream myOutput = new FileOutputStream(outFileName);
            int size = myInput.available();
            byte[] buffer = new byte[size];
            myInput.read(buffer);
            myOutput.write(buffer);
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}