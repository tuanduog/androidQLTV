package com.example.nhom14didong.adapter;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nhom14didong.Model.PhieuMuon;
import com.example.nhom14didong.R;

import java.util.ArrayList;

public class PhieuMuonAdapter extends BaseAdapter {
    private Activity context;
    private ArrayList<PhieuMuon> mylist;
    private SQLiteDatabase database;

    public PhieuMuonAdapter(Activity context, ArrayList<PhieuMuon> mylist, SQLiteDatabase database) {
        this.context = context;
        this.mylist = mylist;
        this.database = database;
    }

    @Override
    public int getCount() {
        return mylist.size();
    }

    @Override
    public Object getItem(int position) {
        return mylist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            // Sử dụng layout cụ thể trong mã (không cần truyền idLayout)
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(R.layout.layout_item_chuaxacnhan_pm, null);
        }

        // Ánh xạ các view
        ImageView imgSachMuon = convertView.findViewById(R.id.imgSachMuon);
        TextView txtTenSach = convertView.findViewById(R.id.txtTenSachChuaXacNhan);
        TextView txtTheLoai = convertView.findViewById(R.id.txtTheLoaiChuaXacNhan);
        Button btnDongY = convertView.findViewById(R.id.btnDongY);
        Button btnHuy = convertView.findViewById(R.id.btnHuy);

        // Lấy dữ liệu phiếu mượn
        PhieuMuon pm = mylist.get(position);

        // Truy vấn dữ liệu từ bảng tài liệu
        Cursor cursor = database.rawQuery(
                "SELECT TENTAILIEU, THELOAI, IMAGE FROM TAILIEU WHERE TAILIEUID = ?",
                new String[]{String.valueOf(pm.taiLieuID)}
        );

        if (cursor != null && cursor.moveToFirst()) {
            // Lấy dữ liệu từ cursor
            String tenSach = cursor.getString(0);
            String theLoai = cursor.getString(1);
            byte[] hinhAnh = cursor.getBlob(2);

            // Gán dữ liệu vào view
            txtTenSach.setText(tenSach);
            txtTheLoai.setText(theLoai);

            // Hiển thị hình ảnh (nếu có)
            if (hinhAnh != null) {
                Bitmap bmSach = BitmapFactory.decodeByteArray(hinhAnh, 0, hinhAnh.length);
                imgSachMuon.setImageBitmap(bmSach);
            } else {
                imgSachMuon.setImageResource(R.drawable.ic_launcher_background); // Hình mặc định
            }
        } else {
            // Xử lý khi không có dữ liệu
            txtTenSach.setText("Không tìm thấy tài liệu");
            txtTheLoai.setText("N/A");
            imgSachMuon.setImageResource(R.drawable.ic_launcher_background); // Hình mặc định
        }

        // Đóng cursor
        if (cursor != null) cursor.close();

        return convertView;
    }
}
