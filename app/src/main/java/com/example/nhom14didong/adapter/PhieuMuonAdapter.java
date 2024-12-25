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

import com.bumptech.glide.Glide;
import com.example.nhom14didong.Activity.DanhSachPhieuMuonUS;
import com.example.nhom14didong.Model.PhieuMuon;
import com.example.nhom14didong.R;

import java.util.ArrayList;

public class PhieuMuonAdapter extends BaseAdapter {
    private static final int TYPE_CHUA_XAC_NHAN = 0;
    private static final int TYPE_DA_XAC_NHAN = 1;

    private Activity context;
    private ArrayList<PhieuMuon> mylist;
    private SQLiteDatabase database;
    private String tinhTrang;

    public PhieuMuonAdapter(Activity context, ArrayList<PhieuMuon> mylist, SQLiteDatabase database, String tinhTrang) {
        this.context = context;
        this.mylist = mylist;
        this.database = database;
        this.tinhTrang = tinhTrang != null ? tinhTrang : "Chưa xác nhận";
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
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
        return "Chưa xác nhận".equals(tinhTrang) ? TYPE_CHUA_XAC_NHAN : TYPE_DA_XAC_NHAN;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int viewType = getItemViewType(position);
        PhieuMuon pm = mylist.get(position);
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            holder = new ViewHolder();

            if (viewType == TYPE_CHUA_XAC_NHAN) {
                convertView = inflater.inflate(R.layout.layout_item_chuaxacnhan_pm, parent, false);
                setupChuaXacNhanView(holder, convertView);
            } else {
                convertView = inflater.inflate(R.layout.layout_item_daxacnhan_pm, parent, false);
                setupDaXacNhanView(holder, convertView);
            }

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        populateData(holder, pm, viewType);
        return convertView;
    }

    private void setupChuaXacNhanView(ViewHolder holder, View view) {
        holder.imgSachMuon = view.findViewById(R.id.imgSachMuonCXNQL);
        holder.txtTenSach = view.findViewById(R.id.txtTenSachCXNQL);
        holder.txtTheLoai = view.findViewById(R.id.txtTheLoaiCXNQL);
        holder.txtNguoiMuon = view.findViewById(R.id.txtNguoiMuonCXNQL);
        holder.txtThoiGianMuon = view.findViewById(R.id.txtThoiGianMuonCXNQL);
        holder.txtThoiGianTra = view.findViewById(R.id.txtThoiGianTraCXNQL);
        holder.btnDongY = view.findViewById(R.id.btnDongY);
        holder.btnHuy = view.findViewById(R.id.btnHuy);
    }

    private void setupDaXacNhanView(ViewHolder holder, View view) {
        holder.imgSachMuon = view.findViewById(R.id.imgSachMuonDXNQL);
        holder.txtTenSach = view.findViewById(R.id.txtTenSachDXNQL);
        holder.txtTheLoai = view.findViewById(R.id.txtTheLoaiDXNQL);
        holder.txtNguoiMuon = view.findViewById(R.id.txtNguoiMuonDXNQL);
        holder.txtThoiGianMuon = view.findViewById(R.id.txtThoiGianMuonDXNQL);
        holder.txtThoiGianTra = view.findViewById(R.id.txtThoiGianTraDXNQL);
    }

    private void populateData(ViewHolder holder, PhieuMuon pm, int viewType) {
        Cursor cursor = database.rawQuery(
                "SELECT TAILIEU.TENTAILIEU, TAILIEU.THELOAI, TAILIEU.IMAGE, NGUOIDUNG.FULLNAME, " +
                        " PHIEUMUON.NGAYMUON, PHIEUMUON.NGAYHENTRA FROM PHIEUMUON INNER JOIN TAILIEU ON PHIEUMUON.TAILIEUID=TAILIEU.TAILIEUID " +
                        "INNER JOIN NGUOIDUNG ON PHIEUMUON.USERID=NGUOIDUNG.USERID WHERE PHIEUMUON.PHIEUMUONID = ? ",
                new String[]{String.valueOf(pm.phieuMuonID)});

        if (cursor.moveToFirst()) {
            holder.txtTenSach.setText(cursor.getString(0));
            holder.txtTheLoai.setText(cursor.getString(1));

            String imagePath = cursor.getString(2); // IMAGE
            ImageView bookImageView = holder.imgSachMuon; // Changed to use imgSachMuon

            // Load image using Glide
            if (imagePath != null && !imagePath.isEmpty()) {
                int resId = context.getResources().getIdentifier(imagePath, "drawable", context.getPackageName());
                if (resId != 0) {
                    Glide.with(context)
                            .load(resId) // Load resource by ID
                            .placeholder(R.drawable.book_img)
                            .error(R.drawable.book_img)
                            .into(bookImageView);
                } else {
                    bookImageView.setImageResource(R.drawable.book_img); // Fallback image
                }
            } else {
                bookImageView.setImageResource(R.drawable.book_img);
            }

            holder.txtNguoiMuon.setText( cursor.getString(3));
            holder.txtThoiGianMuon.setText( cursor.getString(4));
            holder.txtThoiGianTra.setText( cursor.getString(5));
        }

        if (cursor != null) {
            cursor.close();
        }

        if (viewType == TYPE_CHUA_XAC_NHAN) {
            setupButtonListeners(holder, pm);
        }
    }

    private void setupButtonListeners(ViewHolder holder, PhieuMuon pm) {
        holder.btnDongY.setOnClickListener(v -> showConfirmationDialog("Xác nhận", pm.phieuMuonID, "Đã xác nhận"));
        holder.btnHuy.setOnClickListener(v -> showConfirmationDialog("Hủy", pm.phieuMuonID, "Hủy"));
    }

    private void showConfirmationDialog(String action, int phieuMuonID, String newStatus) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Bạn có chắc chắn muốn " + action.toLowerCase() + " phiếu mượn này không?")
                .setPositiveButton("Có", (dialog, which) -> {
                    String updateQuery = "UPDATE PHIEUMUON SET TINHTRANG = ? WHERE PHIEUMUONID = ?";
                    database.execSQL(updateQuery, new Object[]{newStatus, phieuMuonID});
                    Toast.makeText(context, "Phiếu mượn đã được " + action.toLowerCase(), Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();

                    if (context instanceof DanhSachPhieuMuonUS) {
                        ((DanhSachPhieuMuonUS) context).readData(tinhTrang);
                    }
                })
                .setNegativeButton("Không", null)
                .create()
                .show();
    }

    private static class ViewHolder {
        ImageView imgSachMuon;
        TextView txtTenSach, txtTheLoai, txtNguoiMuon, txtThoiGianMuon, txtThoiGianTra;
        Button btnDongY, btnHuy;
    }
}
