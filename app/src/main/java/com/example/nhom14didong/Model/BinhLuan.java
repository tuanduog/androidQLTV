package com.example.nhom14didong.Model;

public class BinhLuan {
    public int binhLuanID;
    public int userID;
    public int taiLieuID;
    public String noiDung;
    public String ngayBinhLuan;

    public BinhLuan() {
    }

    public BinhLuan(int binhLuanID, int userID, int taiLieuID, String noiDung, String ngayBinhLuan) {
        this.binhLuanID = binhLuanID;
        this.userID = userID;
        this.taiLieuID = taiLieuID;
        this.noiDung = noiDung;
        this.ngayBinhLuan = ngayBinhLuan;
    }

    public int getBinhLuanID() {
        return binhLuanID;
    }

    public void setBinhLuanID(int binhLuanID) {
        this.binhLuanID = binhLuanID;
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

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getNgayBinhLuan() {
        return ngayBinhLuan;
    }

    public void setNgayBinhLuan(String ngayBinhLuan) {
        this.ngayBinhLuan = ngayBinhLuan;
    }
}
