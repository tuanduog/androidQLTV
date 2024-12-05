package com.example.nhom14didong.Model;

public class TaiLieu {
    public int taiLieuID;
    public String tenTaiLieu;
    public String image;
    public String tacGia;
    public String theLoai;
    public String ngayXuatBan;
    public String moTa;
    public int soLuong;
    public String tinhTrang;
    public String ngayTao;

    public TaiLieu() {
    }

    public TaiLieu(int taiLieuID, String tenTaiLieu, String image, String tacGia, String theLoai, String ngayXuatBan, String moTa, int soLuong, String tinhTrang, String ngayTao) {
        this.taiLieuID = taiLieuID;
        this.tenTaiLieu = tenTaiLieu;
        this.image = image;
        this.tacGia = tacGia;
        this.theLoai = theLoai;
        this.ngayXuatBan = ngayXuatBan;
        this.moTa = moTa;
        this.soLuong = soLuong;
        this.tinhTrang = tinhTrang;
        this.ngayTao = ngayTao;
    }

    public int getTaiLieuID() {
        return taiLieuID;
    }

    public void setTaiLieuID(int taiLieuID) {
        this.taiLieuID = taiLieuID;
    }

    public String getTenTaiLieu() {
        return tenTaiLieu;
    }

    public void setTenTaiLieu(String tenTaiLieu) {
        this.tenTaiLieu = tenTaiLieu;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public String getNgayXuatBan() {
        return ngayXuatBan;
    }

    public void setNgayXuatBan(String ngayXuatBan) {
        this.ngayXuatBan = ngayXuatBan;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }
}
