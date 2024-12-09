package com.example.nhom14didong.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.Toast;

import com.example.nhom14didong.Activity.dsphieumuon;
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
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(R.layout.layout_item_chuaxacnhan_pm, null);
        }
        PhieuMuon pm = mylist.get(position);
        ImageView imgSachMuon = convertView.findViewById(R.id.imgSachMuon);
        TextView txtTenSach = convertView.findViewById(R.id.txtTenSachChuaXacNhan);
        TextView txtTheLoai = convertView.findViewById(R.id.txtTheLoaiChuaXacNhan);
        TextView txtNguoiMuon = convertView.findViewById(R.id.txtNguoiMuonChuaXacNhan);
        TextView txtThoiGianMuon = convertView.findViewById(R.id.txtThoiGianMuonCXN);
        TextView txtThoiGianTra = convertView.findViewById(R.id.txtThoiGianTraCXN);
        Button btnDongY = convertView.findViewById(R.id.btnDongY);
        Button btnHuy = convertView.findViewById(R.id.btnHuy);
        Cursor cursor = database.rawQuery("SELECT TAILIEU.TENTAILIEU, TAILIEU.THELOAI, TAILIEU.IMAGE, NGUOIDUNG.FULLNAME," +
                " PHIEUMUON.NGAYMUON, PHIEUMUON.NGAYHENTRA FROM PHIEUMUON INNER JOIN TAILIEU ON PHIEUMUON.TAILIEUID=TAILIEU.TAILIEUID " +
                "INNER JOIN NGUOIDUNG ON PHIEUMUON.USERID=NGUOIDUNG.USERID WHERE PHIEUMUON.PHIEUMUONID = ? ",new String[]{String.valueOf(pm.phieuMuonID)});

        if (cursor.moveToFirst()) {
            String tenSach = cursor.getString(0);
            String theLoai = cursor.getString(1);
            byte[] imageBytes = cursor.getBlob(2);
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            imgSachMuon.setImageBitmap(bitmap);
            txtTenSach.setText(tenSach);
            txtTheLoai.setText(theLoai);
            txtNguoiMuon.setText("Người muượn: "+cursor.getString(3));
            txtThoiGianMuon.setText("Ngày muợn mượn: "+cursor.getString(4));
            txtThoiGianTra.setText("Ngày hẹn trả: "+cursor.getString(5));
        }
        if (cursor!=null) {
            cursor.close();
        }
        return convertView;
    }

}
