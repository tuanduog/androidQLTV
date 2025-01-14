package com.example.nhom14didong.Model;

public class PhieuMuon {
    public int phieuMuonID;
    public int userID;
    public int taiLieuID;
    public String ngayMuon;
    public String ngayHenTra;
    public String ngayTra;
    public String tinhTrang;
    public String ghiChu;
    public String ngayTao;

    public PhieuMuon() {
    }

    public PhieuMuon(int phieuMuonID, int userID, int taiLieuID, String ngayMuon, String ngayHenTra, String ngayTra, String tinhTrang, String ghiChu, String ngayTao) {
        this.phieuMuonID = phieuMuonID;
        this.userID = userID;
        this.taiLieuID = taiLieuID;
        this.ngayMuon = ngayMuon;
        this.ngayHenTra = ngayHenTra;
        this.ngayTra = ngayTra;
        this.tinhTrang = tinhTrang;
        this.ghiChu = ghiChu;
        this.ngayTao = ngayTao;
    }
    public int getPhieuMuonID() {
        return phieuMuonID;
    }

    public void setPhieuMuonID(int phieuMuonID) {
        this.phieuMuonID = phieuMuonID;
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

    public String getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(String ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public String getNgayHenTra() {
        return ngayHenTra;
    }

    public void setNgayHenTra(String ngayHenTra) {
        this.ngayHenTra = ngayHenTra;
    }

    public String getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(String ngayTra) {
        this.ngayTra = ngayTra;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }
}
