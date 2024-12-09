package com.example.nhom14didong.adapter;

import android.app.Activity;
import android.content.Context;
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

public class QLPhieuMuonTraAdapter extends BaseAdapter {
    private Activity context;
    private ArrayList<PhieuMuon> mylist;
    private SQLiteDatabase database;
    private String ngayTra;

    public QLPhieuMuonTraAdapter(Activity context, ArrayList<PhieuMuon> mylist, SQLiteDatabase database, String ngayTra) {
        this.context = context;
        this.mylist = mylist;
        this.database = database;
        this.ngayTra = ngayTra;
    }
    public void setNgayTra(String ngayTra) {
        this.ngayTra = ngayTra;
        notifyDataSetChanged();
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
    public int getItemViewType(int position) {
        if (ngayTra == null) {
            return 0;
        } else {
            return 1;
        }
    }
    public int getViewTypeCount() {
        return 2;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int viewType = getItemViewType(position);
        PhieuMuon pm = mylist.get(position);
        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            if (viewType == 0) {
                // Layout cho "Phieu muon"
                convertView = inflater.inflate(R.layout.layout_item_ql_phieumuon, parent, false);
                // Anh xa
                TextView txtPhieuMuonID = (TextView) convertView.findViewById(R.id.txtIDPM);
                TextView txtTenSach = (TextView) convertView.findViewById(R.id.txtTenSachPM);
                TextView txtNguoiMuon = (TextView) convertView.findViewById(R.id.txtNguoiMuonPM);
                TextView txtNgayMuon= (TextView) convertView.findViewById(R.id.txtNgayMuonPM);
                TextView txtNgayHenTra = (TextView) convertView.findViewById(R.id.txtNgayHenTraPM);
                Button btnSua = (Button) convertView.findViewById(R.id.btnSuaPM);
                Button btnXoa = (Button) convertView.findViewById(R.id.btnXoaPM);
                Button btnXacNhanTra = (Button) convertView.findViewById(R.id.btnXacNhanTra);
                //Truy van phieu muon
                Cursor cursor= database.rawQuery(
                        "SELECT PHIEUMUON.PHIEUMUONID, TAILIEU.TENTAILIEU, NGUOIDUNG.FULLNAME, " +
                                " PHIEUMUON.NGAYMUON, PHIEUMUON.NGAYHENTRA  FROM PHIEUMUON INNER JOIN TAILIEU ON PHIEUMUON.TAILIEUID=TAILIEU.TAILIEUID " +
                                "INNER JOIN NGUOIDUNG ON PHIEUMUON.USERID=NGUOIDUNG.USERID WHERE PHIEUMUON.PHIEUMUONID = ? ",new String[]{String.valueOf(pm.phieuMuonID)});

                if (cursor.moveToFirst()) {
                    String phieuMuonID = cursor.getString(0);
                    String tenSach = cursor.getString(1);
                    String tenNguoiMuon = cursor.getString(2);
                    String ngayMuon = cursor.getString(3);
                    String ngayHenTra = cursor.getString(4);
                    txtPhieuMuonID.setText(phieuMuonID);
                    txtTenSach.setText(tenSach);
                    txtNguoiMuon.setText(tenNguoiMuon);
                    txtNgayMuon.setText(ngayMuon);
                    txtNgayHenTra.setText(ngayHenTra);
                }
                if (cursor!=null) {
                    cursor.close();
                }
                return convertView;
            } else {
                // Layout cho "Phieu tra "
                convertView = inflater.inflate(R.layout.layout_item_ql_phieutra ,parent, false);
                //anh xa
                TextView txtPhieuMuonID = (TextView) convertView.findViewById(R.id.txtIDPT);
                TextView txtTenSach = (TextView) convertView.findViewById(R.id.txtTenSachPT);
                TextView txtNguoiMuon = (TextView) convertView.findViewById(R.id.txtNguoiMuonPT);
                TextView txtNgayMuon= (TextView) convertView.findViewById(R.id.txtNgayMuonPT);
                TextView txtNgayHenTra = (TextView) convertView.findViewById(R.id.txtNgayHenTraPT);
                TextView txtNgayTra = (TextView) convertView.findViewById(R.id.txtNgayTraPT);
                Button btnXoa = (Button) convertView.findViewById(R.id.btnXoaPT);
                Cursor cursor= database.rawQuery(
                        "SELECT PHIEUMUON.PHIEUMUONID, TAILIEU.TENTAILIEU, NGUOIDUNG.FULLNAME, " +
                                " PHIEUMUON.NGAYMUON, PHIEUMUON.NGAYHENTRA , PHIEUMUON.NGAYTRA FROM PHIEUMUON INNER JOIN TAILIEU ON PHIEUMUON.TAILIEUID=TAILIEU.TAILIEUID " +
                                "INNER JOIN NGUOIDUNG ON PHIEUMUON.USERID=NGUOIDUNG.USERID WHERE PHIEUMUON.PHIEUMUONID = ? ",new String[]{String.valueOf(pm.phieuMuonID)});
                if (cursor.moveToFirst()) {
                    String phieuMuonID = cursor.getString(0);
                    String tenSach = cursor.getString(1);
                    String tenNguoiMuon = cursor.getString(2);
                    String ngayMuon = cursor.getString(3);
                    String ngayHenTra = cursor.getString(4);
                    String ngayTra = cursor.getString(5);
                    txtPhieuMuonID.setText(phieuMuonID);
                    txtTenSach.setText(tenSach);
                    txtNguoiMuon.setText(tenNguoiMuon);
                    txtNgayMuon.setText(ngayMuon);
                    txtNgayHenTra.setText(ngayHenTra);
                    txtNgayTra.setText(ngayTra);
                }
                if (cursor!=null) {
                    cursor.close();
                }
                return convertView;

            }
        }
        return convertView;
    }
}
