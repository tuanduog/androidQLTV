package com.example.nhom14didong.Activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nhom14didong.R;

import java.io.ByteArrayInputStream;
import java.util.Calendar;

public class ChiTietTaiLieu extends AppCompatActivity {

    private TextView txtTenSach, txtLoaiSach, txtTacGia, txtSoLuong, txtMoTa, txtTrangThai;
    private Button btnThemVaoPM;
    private ImageView image;

    private SQLiteDatabase database;
    private long taiLieuId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_tai_lieu);

        anhXa();

        // Mở cơ sở dữ liệu
        database = openOrCreateDatabase("mydatabase.db", MODE_PRIVATE, null);

        // Lấy ID tài liệu từ Intent
        taiLieuId = getIntent().getLongExtra("TAILIEUID", -1);
        if (taiLieuId != -1) {
            loadDataChiTiet(taiLieuId);
        }

        // Xử lý sự kiện nút "Thêm vào Phiếu Mượn"
        btnThemVaoPM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themVaoPhieuMuon();
            }
        });

    }

    private void anhXa() {
        txtTenSach = findViewById(R.id.txtTenSach);
        txtLoaiSach = findViewById(R.id.txtLoaiSach);
        txtTacGia = findViewById(R.id.txtTacGia);
        txtSoLuong = findViewById(R.id.txtSoLuong);
        txtMoTa = findViewById(R.id.txtMoTa);
        txtTrangThai = findViewById(R.id.txtTrangThai);
        btnThemVaoPM = findViewById(R.id.btnThemVaoPM);
        image = findViewById(R.id.imgAnhSach);
    }

    private void loadDataChiTiet(long taiLieuId) {
        String query = "SELECT IMAGE, TENTAILIEU, THELOAI, TACGIA, SOLUONG, MOTA, TINHTRANG FROM TAILIEU WHERE TAILIEUID = ?";
        Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(taiLieuId)});

        if (cursor.moveToFirst()) {
            // Load hình ảnh từ cột IMAGE (giả sử cột IMAGE lưu trữ ảnh dạng blob)
            byte[] imageBytes = cursor.getBlob(cursor.getColumnIndex("IMAGE"));
            if (imageBytes != null) {
                ByteArrayInputStream imageStream = new ByteArrayInputStream(imageBytes);
                Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
                image.setImageBitmap(bitmap);
            } else {
                image.setImageResource(R.drawable.book_img); // Hình mặc định
            }

            // Hiển thị thông tin tài liệu
            txtTenSach.setText(cursor.getString(cursor.getColumnIndex("TENTAILIEU")));
            txtLoaiSach.setText(cursor.getString(cursor.getColumnIndex("THELOAI")));
            txtTacGia.setText(cursor.getString(cursor.getColumnIndex("TACGIA")));
            txtSoLuong.setText(String.valueOf(cursor.getInt(cursor.getColumnIndex("SOLUONG"))));
            txtMoTa.setText(cursor.getString(cursor.getColumnIndex("MOTA")));
            txtTrangThai.setText(cursor.getString(cursor.getColumnIndex("TINHTRANG")));
        } else {
            Toast.makeText(this, "Không tìm thấy tài liệu!", Toast.LENGTH_SHORT).show();
        }

        cursor.close();
    }

    private void themVaoPhieuMuon() {
        showCustomDialog();
    }

    private void showCustomDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_them_phieu_muon);
        dialog.setCancelable(false);

        EditText edtNgayHenTra = dialog.findViewById(R.id.edtNgayHenTra);
        EditText edtGhiChu = dialog.findViewById(R.id.etGhiChu);
        Button btnXacNhan = dialog.findViewById(R.id.btnXacNhan);
        Button btnHuy = dialog.findViewById(R.id.btnHuy);

        edtNgayHenTra.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    this,
                    (view, year, month, dayOfMonth) -> {
                        String selectedDate = String.format("%d-%02d-%02d", year, month + 1, dayOfMonth);
                        edtNgayHenTra.setText(selectedDate);
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
            );
            calendar.add(Calendar.DAY_OF_YEAR, 3);
            datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
            datePickerDialog.show();
        });

        btnXacNhan.setOnClickListener(v -> {
            String ngayHenTra = edtNgayHenTra.getText().toString().trim();
            String ghiChu = edtGhiChu.getText().toString().trim();

            if (ngayHenTra.isEmpty()) {
                Toast.makeText(this, "Vui lòng chọn ngày trả!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (ghiChu.isEmpty()) {
                ghiChu = "Mặc định";
            }

            themTaiLieuVaoPhieuMuon(ngayHenTra, ghiChu);
            dialog.dismiss();
        });

        btnHuy.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }

    private void themTaiLieuVaoPhieuMuon(String ngayHenTra, String ghiChu) {
        try {
            String userID = getSharedPreferences("UserPref", MODE_PRIVATE).getString("USERID", null);
            String tinhTrang = "Chưa xác nhận";
            String query = "INSERT INTO PHIEUMUON (USERID, TAILIEUID, NGAYMUON, NGAYHENTRA, TINHTRANG, GHICHU) VALUES (?, ?, ?, ?, ?)";
            long ngayMuon = System.currentTimeMillis();

            database.execSQL(query, new Object[]{userID,taiLieuId, ngayMuon, ngayHenTra, tinhTrang, ghiChu});
            Toast.makeText(this, "Đã thêm vào phiếu mượn!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Thêm vào phiếu mượn thất bại!", Toast.LENGTH_SHORT).show();
        }
    }

}
