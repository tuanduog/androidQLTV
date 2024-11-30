package com.example.nhom14didong.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CreateDatabase extends SQLiteOpenHelper {
    public static String TB_NGUOIDUNG="NGUOIDUNG";
    public static String TB_TAILIEU="TAILIEU";
    public static String TB_PHIEUMUON="PHIEUMUON";
    public static String TB_THONGBAO="THONGBAO";
    public static String TB_BINHLUAN="BINHLUAN";
    public static String TB_THONGKE="THONGKE";
    public static String TB_DANHSACHYEUTHICH="DANHSACHYEUTHICH";
    // CÁC THUỘC TÍNH BẢNG NGƯỜI DÙNG
    public static String TB_NGUOIDUNG_USERID="USERID";
    public static String TB_NGUOIDUNG_USERNAME="USERNAME";
    public static String TB_NGUOIDUNG_USERPASS="USERPASS";
    public static String TB_NGUOIDUNG_FULLNAME="FULLNAME";
    public static String TB_NGUOIDUNG_EMAIL="EMAIL";
    public static String TB_NGUOIDUNG_ROLE="ROLE";
    public static String TB_NGUOIDUNG_DATACREATE="DATACREATE";
    // CÁC THUỘC TÍNH BẢNG TÀI LIỆU
    public static String TB_TAILIEU_TAILIEUID="TAILIEUID";
    public static String TB_TAILIEU_TENTAILIEU="TENTAILIEU";
    public static String TB_TAILIEU_IMAGE="IMAGE";
    public static String TB_TAILIEU_TACGIA="TACGIA";
    public static String TB_TAILIEU_THELOAI="THELOAI";
    public static String TB_TAILIEU_NGAYXUATBAN="NGAYXUATBAN";
    public static String TB_TAILIEU_MOTA="MOTA";
    public static String TB_TAILIEU_SOLUONG="SOLUONG";
    public static String TB_TAILIEU_TINHTRANG="TINHTRANG";
    public static String TB_TAILIEU_NGAYTAO="NGAYTAO";
    //CÁC THUỘC TÍNH BẢNG PHIẾU MƯỢN
    public static String TB_PHIEUMUON_PHIEUMUONID="PHIEUMUONID";
    public static String TB_PHIEUMUON_USERID="USERID";
    public static String TB_PHIEUMUON_TAILIEUID="TAILIEUID";
    public static String TB_PHIEUMUON_NGAYMUON="NGAYMUON";
    public static String TB_PHIEUMUON_NGAYHENTRA="NGAYHANTRA";
    public static String TB_PHIEUMUON_NGAYTRA="NGAYTRA";
    public static String TB_PHIEUMUON_TINHTRANG="TINHTRANG";
    public static String TB_PHIEUMUON_GHICHU="GHICHU";
    public static String TB_PHIEUMUON_NGAYTAO="NGAYTAO";
    //CÁC THUỘC TÍNH BẢNG THÔNG BÁO
    public static String TB_THONGBAO_THONGBAOID="THONGBAOID";
    public static String TB_THONGBAO_TIEUDE="TIEUDE";
    public static String TB_THONGBAO_NOIDUNG="NOIDUNG";
    public static String TB_THONGBAO_NGAYTHONGBAO="NGAYTHONGBAO";
    public static String TB_THONGBAO_NGUOITAO="NGUOITAO";
    public static String TB_THONGBAO_TRANGTHAI="TRANGTHAI";
    //CÁC THUỘC TÍNH BẢNG BÌNH LUẬN
    public static String TB_BINHLUAN_BINHLUANID="BINHLUANID";
    public static String TB_BINHLUAN_USERID="USERID";
    public static String TB_BINHLUAN_TAILIEUID="TAILIEUID";
    public static String TB_BINHLUAN_NOIDUNG="NOIDUNG";
    public static String TB_BINHLUAN_NGAYBINHLUAN="NGAYBINHLUAN";
    //CÁC THUỘC TÍNH BẢNG THỐNG KÊ
    public static String TB_THONGKE_THONGKEID="THONGKEID";
    public static String TB_THONGKE_SLNGUOIDUNG="SLNGUOIDUNG";
    public static String TB_THONGKE_SLTAILIEUDANGMUON="SLTAILIEUDANGMUON";
    public static String TB_THONGKE_SLTAILIEUDATRA="SLTAILIEUDATRA";
    public static String TB_THONGKE_SLTAILIEUYEUTHICH="SLTAILIEUYEUTHICH";
    public static String TB_THONGKE_SLTAILIEUQUAHAN="SLTAILIEUQUAHAN";
    public static String TB_THONGKE_SLTAILIEUCHUATRA="SLTAILIEUCHUATRA";
    public static String TB_THONGKE_SLBINHLUAN="SLBINHLUAN";
    public static String TB_THONGKE_NGAYTK="NGAYTK";
    //CÁC THUỘC TÍNH BẢNG DANH SACH YEU THICH
    public static String TB_DANHSACHYEUTHICH_YEUTHICHID="YEUTHICHID";
    public static String TB_DANHSACHYEUTHICH_USERID="USERID";
    public static String TB_DANHSACHYEUTHICH_TAILIEUID="TAILIEUID";
    public static String TB_DANHSACHYEUTHICH_NGAYTHEM="NGAYTHEM";


    public CreateDatabase(@Nullable Context context) {
        super(context, "QLThuVien", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tbNguoiDung = "CREATE TABLE " + TB_NGUOIDUNG + " (" +
                TB_NGUOIDUNG_USERID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TB_NGUOIDUNG_USERNAME + " TEXT, " +
                TB_NGUOIDUNG_USERPASS + " TEXT, " +
                TB_NGUOIDUNG_FULLNAME + " TEXT, " +
                TB_NGUOIDUNG_EMAIL + " TEXT, " +
                TB_NGUOIDUNG_ROLE + " TEXT, " +
                TB_NGUOIDUNG_DATACREATE + " TEXT)";
        String tbTailieu = "CREATE TABLE " + TB_TAILIEU + "( " +
                TB_TAILIEU_TAILIEUID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TB_TAILIEU_TENTAILIEU + " TEXT, " +
                TB_TAILIEU_IMAGE + " TEXT, " +
                TB_TAILIEU_TACGIA + " TEXT, " +
                TB_TAILIEU_THELOAI + " TEXT, " +
                TB_TAILIEU_NGAYXUATBAN + " TEXT, " +
                TB_TAILIEU_MOTA + " TEXT, " +
                TB_TAILIEU_SOLUONG + " INTEGER, " +
                TB_TAILIEU_TINHTRANG + " TEXT, " +
                TB_TAILIEU_NGAYTAO + " TEXT)";
        String tbPhieuMuon="CREATE TABLE "+ TB_PHIEUMUON+ " ("+
                TB_PHIEUMUON_PHIEUMUONID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TB_PHIEUMUON_USERID + " INTEGER, " +
                TB_PHIEUMUON_TAILIEUID + " INTEGER, " +
                TB_PHIEUMUON_NGAYMUON + " TEXT, " +
                TB_PHIEUMUON_NGAYHENTRA + " TEXT, " +
                TB_PHIEUMUON_NGAYTRA + " TEXT, " +
                TB_PHIEUMUON_TINHTRANG + " TEXT, " +
                TB_PHIEUMUON_GHICHU + " TEXT, " +
                TB_PHIEUMUON_NGAYTAO + " TEXT)";
        String tbThongBao = "CREATE TABLE " + TB_THONGBAO + " (" +
                TB_THONGBAO_THONGBAOID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TB_THONGBAO_TIEUDE + " TEXT, " +
                TB_THONGBAO_NOIDUNG + " TEXT, " +
                TB_THONGBAO_NGAYTHONGBAO + " TEXT, " +
                TB_THONGBAO_NGUOITAO + " TEXT, " +
                TB_THONGBAO_TRANGTHAI + " TEXT)";
        String tbBinhLuan = "CREATE TABLE " + TB_BINHLUAN + " (" +
                TB_BINHLUAN_BINHLUANID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TB_BINHLUAN_USERID + " INTEGER, " +
                TB_BINHLUAN_TAILIEUID + " INTEGER, " +
                TB_BINHLUAN_NOIDUNG + " TEXT, " +
                TB_BINHLUAN_NGAYBINHLUAN + " TEXT)";
        String tbThongKe = "CREATE TABLE " + TB_THONGKE + " (" +
                TB_THONGKE_THONGKEID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TB_THONGKE_SLNGUOIDUNG + " INTEGER, " +
                TB_THONGKE_SLTAILIEUDANGMUON + " INTEGER, " +
                TB_THONGKE_SLTAILIEUDATRA + " INTEGER, " +
                TB_THONGKE_SLTAILIEUYEUTHICH + " INTEGER, " +
                TB_THONGKE_SLTAILIEUQUAHAN + " INTEGER, " +
                TB_THONGKE_SLTAILIEUCHUATRA + " INTEGER, " +
                TB_THONGKE_SLBINHLUAN + " INTEGER, " +
                TB_THONGKE_NGAYTK + " TEXT)";
        String tbDanhSachYeuThich = "CREATE TABLE " + TB_DANHSACHYEUTHICH + " (" +
                TB_DANHSACHYEUTHICH_YEUTHICHID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TB_DANHSACHYEUTHICH_USERID + " INTEGER, " +
                TB_DANHSACHYEUTHICH_TAILIEUID + " INTEGER, " +
                TB_DANHSACHYEUTHICH_NGAYTHEM + " TEXT)";
        db.execSQL(tbNguoiDung);
        db.execSQL(tbTailieu);
        db.execSQL(tbPhieuMuon);
        db.execSQL(tbThongBao);
        db.execSQL(tbBinhLuan);
        db.execSQL(tbThongKe);
        db.execSQL(tbDanhSachYeuThich);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public SQLiteDatabase open(){
        return this.getWritableDatabase();
    }
}
