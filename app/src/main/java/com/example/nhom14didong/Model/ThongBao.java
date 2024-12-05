package com.example.nhom14didong.Model;

public class ThongBao {
    public int thongBaoID;
    public String tieuDe;
    public String noiDung;
    public String ngayThongBao;
    public String nguoiTao;
    public String trangThai;
    public int userID;

    public ThongBao() {
    }

    public ThongBao(int thongBaoID, String tieuDe, String noiDung, String ngayThongBao, String nguoiTao, String trangThai, int userID) {
        this.thongBaoID = thongBaoID;
        this.tieuDe = tieuDe;
        this.noiDung = noiDung;
        this.ngayThongBao = ngayThongBao;
        this.nguoiTao = nguoiTao;
        this.trangThai = trangThai;
        this.userID = userID;
    }

    public int getThongBaoID() {
        return thongBaoID;
    }

    public void setThongBaoID(int thongBaoID) {
        this.thongBaoID = thongBaoID;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getNgayThongBao() {
        return ngayThongBao;
    }

    public void setNgayThongBao(String ngayThongBao) {
        this.ngayThongBao = ngayThongBao;
    }

    public String getNguoiTao() {
        return nguoiTao;
    }

    public void setNguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
