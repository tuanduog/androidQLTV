package com.example.nhom14didong.Model;

public class DanhSachYeuThich {
    public int yeuThichID;
    public int userID;
    public int taiLieuID;
    public String ngayThem;

    public DanhSachYeuThich() {
    }

    public DanhSachYeuThich(int yeuThichID, int userID, int taiLieuID, String ngayThem) {
        this.yeuThichID = yeuThichID;
        this.userID = userID;
        this.taiLieuID = taiLieuID;
        this.ngayThem = ngayThem;
    }

    public int getYeuThichID() {
        return yeuThichID;
    }

    public void setYeuThichID(int yeuThichID) {
        this.yeuThichID = yeuThichID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getTaiLieuID() {
        return taiLieuID;
    }

    public void setTaiLieuID(int taiLieuID) {
        this.taiLieuID = taiLieuID;
    }

    public String getNgayThem() {
        return ngayThem;
    }

    public void setNgayThem(String ngayThem) {
        this.ngayThem = ngayThem;
    }
}
