package com.example.nhom14didong.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nhom14didong.R;

public class UserProfile extends AppCompatActivity {
    private TextView txtUserID, txtuserName, txtEmail, txtHoten, txtTitle;
    ImageButton imgbtnLogout;
    TextView txtLogout;
    private SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        txtUserID = findViewById(R.id.txtUserID);
        txtuserName = findViewById(R.id.txtUserName);
        txtEmail = findViewById(R.id.txtEmail);
        txtHoten = findViewById(R.id.txtHoten);
        imgbtnLogout = findViewById(R.id.imgbtnLogout);
        txtLogout = findViewById(R.id.txtLogout);
        txtTitle = findViewById(R.id.txtTitle);
        database = openOrCreateDatabase("mydatabase.db", MODE_PRIVATE, null);
        loadUserProfile();
        imgbtnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
        txtLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
    }

    private void loadUserProfile() {
        String userID = getSharedPreferences("UserPref", MODE_PRIVATE).getString("USERID", null);

        if(userID != null){
            Cursor cursor = database.rawQuery("SELECT * FROM NGUOIDUNG WHERE USERID = ?"
            , new String[]{userID});
            if(cursor != null && cursor.moveToFirst()){
                String userName = cursor.getString(cursor.getColumnIndexOrThrow("USERNAME"));
                String userEmail = cursor.getString(cursor.getColumnIndexOrThrow("EMAIL"));
                String hoTen = cursor.getString(cursor.getColumnIndexOrThrow("FULLNAME"));
                String role = cursor.getString(cursor.getColumnIndexOrThrow("ROLE"));

                txtUserID.setText(userID);
                txtuserName.setText(userName);
                txtEmail.setText(userEmail);
                txtHoten.setText(hoTen);
                if(role.equals("user")){
                    txtTitle.setText("Thông tin người dùng");
                } else if(role.equals("admin")){
                    txtTitle.setText("Thông tin admin");
                }
                cursor.close();
            } else {
                Toast.makeText(this, "Nguời dùng không tồn tại!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Không tìm thấy userid", Toast.LENGTH_SHORT).show();
        }
    }
    private void logout() {
        SharedPreferences pref = getSharedPreferences("UserPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.apply();
        Toast.makeText(this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(UserProfile.this, DangNhap.class);
        startActivity(intent);
        finish();
    }
}
