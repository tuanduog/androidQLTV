package com.example.nhom14didong.DTO;

public class TaiLieuDTO {
    private int taiLieuID;
    private String tenTaiLieu;
    private String image;
    private String tacGia;
    private String theLoai;
    private String ngayXuatBan;
    private String moTa;
    private int soLuong;
    private String trangThai;
    private String ngayTao;

    public TaiLieuDTO(int taiLieuID, String tenTaiLieu, String image, String tacGia, String theLoai, String ngayXuatBan, String moTa, int soLuong, String trangThai, String ngayTao) {
        this.taiLieuID = taiLieuID;
        this.tenTaiLieu = tenTaiLieu;
        this.image = image;
        this.tacGia = tacGia;
        this.theLoai = theLoai;
        this.ngayXuatBan = ngayXuatBan;
        this.moTa = moTa;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
        this.ngayTao = ngayTao;
    }

    public TaiLieuDTO() {
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

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }
}
