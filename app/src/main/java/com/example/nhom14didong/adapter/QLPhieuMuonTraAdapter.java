package com.example.nhom14didong.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhom14didong.Model.PhieuMuon;
import com.example.nhom14didong.R;

import java.util.ArrayList;
import java.util.Calendar;

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

    private void showDatePickerDialog(final TextView targetView) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                targetView.setText(selectedDate);
            }
        }, year, month, day);

        datePickerDialog.show();
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

                TextView txtTenSach = (TextView) convertView.findViewById(R.id.txtTenSachPM);
                TextView txtNguoiMuon = (TextView) convertView.findViewById(R.id.txtNguoiMuonPM);
                TextView txtNgayMuon= (TextView) convertView.findViewById(R.id.txtNgayMuonPM);
                TextView txtNgayHenTra = (TextView) convertView.findViewById(R.id.txtNgayHenTraPM);
                Button btnSua = (Button) convertView.findViewById(R.id.btnSuaPM);
                Button btnXoa = (Button) convertView.findViewById(R.id.btnXoaPM);
                Button btnXacNhanTra = (Button) convertView.findViewById(R.id.btnDaXacNhanPM);
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
                    txtTenSach.setText(tenSach);
                    txtNguoiMuon.setText(tenNguoiMuon);
                    txtNgayMuon.setText(ngayMuon);
                    txtNgayHenTra.setText(ngayHenTra);
                }
                //NUT XOA done
                btnXoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage("Bạn có chắc muốn xóa phiếu mượn này không?")
                                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        String updateQuery = "UPDATE PHIEUMUON SET TINHTRANG = 'Đã xóa' WHERE PHIEUMUONID = ?";
                                        database.execSQL(updateQuery, new Object[]{pm.phieuMuonID});
                                        Toast.makeText(context, "Phiếu mượn đã bị xóa", Toast.LENGTH_SHORT).show();
                                        notifyDataSetChanged();
                                    }
                                })
                                .setNegativeButton("Không", null);
                        builder.create().show();
                    }
                });
                //NUT XAC NHAN TRA done
                btnXacNhanTra.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage("Bạn có chắc muốn xác nhận trả không?")
                                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        String updateQuery = "UPDATE PHIEUMUON SET NGAYTRA = CURRENT_TIMESTAMP WHERE PHIEUMUONID = ?";
                                        database.execSQL(updateQuery, new Object[]{pm.phieuMuonID});
                                        Toast.makeText(context, "Xác nhận trả thành công", Toast.LENGTH_SHORT).show();
                                        notifyDataSetChanged();
                                    }
                                })
                                .setNegativeButton("Không", null);
                        builder.create().show();
                    }
                });
                //NUT SUA done
                btnSua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LayoutInflater inflater = context.getLayoutInflater();
                        View dialogView = inflater.inflate(R.layout.dialog_sua_phieumuon, null);
                        TextView edtNgayMuon = dialogView.findViewById(R.id.edtNgayMuon);
                        TextView edtNgayHenTra = dialogView.findViewById(R.id.edtNgayHenTra);
                        // DO DU LIEU LEN
                        edtNgayMuon.setText(txtNgayMuon.getText().toString());
                        edtNgayHenTra.setText(txtNgayHenTra.getText().toString());
                        edtNgayMuon.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showDatePickerDialog(edtNgayMuon);
                            }
                        });
                        edtNgayHenTra.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showDatePickerDialog(edtNgayHenTra);
                            }
                        });
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setView(dialogView)
                                .setTitle("Sửa phiếu mượn")
                                .setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Lấy dữ liệu người dùng sửa
                                        String ngayMuonMoi = edtNgayMuon.getText().toString();
                                        String ngayHenTraMoi = edtNgayHenTra.getText().toString();

                                        // Cập nhật cơ sở dữ liệu
                                        String updateQuery = "UPDATE PHIEUMUON SET NGAYMUON = ?, NGAYHENTRA = ? WHERE PHIEUMUONID = ?";
                                        database.execSQL(updateQuery, new Object[]{ ngayMuonMoi, ngayHenTraMoi, pm.phieuMuonID});
                                        txtNgayMuon.setText(ngayMuonMoi);
                                        txtNgayHenTra.setText(ngayHenTraMoi);
                                        Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                                        notifyDataSetChanged();
                                    }
                                })
                                .setNegativeButton("Hủy", null);

                        builder.create().show();
                    }
                });
                if (cursor!=null) {
                    cursor.close();
                }
                return convertView;
            } else {
                // Layout cho "Phieu tra "
                convertView = inflater.inflate(R.layout.layout_item_ql_phieutra ,parent, false);
                //anh xa

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
                    txtTenSach.setText(tenSach);
                    txtNguoiMuon.setText(tenNguoiMuon);
                    txtNgayMuon.setText(ngayMuon);
                    txtNgayHenTra.setText(ngayHenTra);
                    txtNgayTra.setText(ngayTra);
                }
                btnXoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage("Bạn có chắc muốn xóa phiếu mượn này không?")
                                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        String updateQuery = "UPDATE PHIEUMUON SET TINHTRANG = 'Đã xóa' WHERE PHIEUMUONID = ?";
                                        database.execSQL(updateQuery, new Object[]{pm.phieuMuonID});
                                        Toast.makeText(context, "Phiếu mượn đã bị xóa", Toast.LENGTH_SHORT).show();
                                        notifyDataSetChanged();
                                    }
                                })
                                .setNegativeButton("Không", null);
                        builder.create().show();
                    }
                });

                if (cursor!=null) {
                    cursor.close();
                }
                return convertView;


            }
        }
        return convertView;
    }
}
