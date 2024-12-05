package com.example.nhom14didong.Model;

public class ThongKe {
    public int thongKeID;
    public int slNguoiDung;
    public int slTaiLieuDangMuon;
    public int slTaiLieuDaTra;
    public int slTaiLieuYeuThich;
    public int slTaiLieuQuaHan;
    public int slTaiLieuChuaTra;
    public int slBinhLuan;
    public int ngayThongKe;

    public ThongKe() {
    }

    public ThongKe(int thongKeID, int slNguoiDung, int slTaiLieuDangMuon, int slTaiLieuDaTra, int slTaiLieuYeuThich, int slTaiLieuQuaHan, int slTaiLieuChuaTra, int slBinhLuan, int ngayThongKe) {
        this.thongKeID = thongKeID;
        this.slNguoiDung = slNguoiDung;
        this.slTaiLieuDangMuon = slTaiLieuDangMuon;
        this.slTaiLieuDaTra = slTaiLieuDaTra;
        this.slTaiLieuYeuThich = slTaiLieuYeuThich;
        this.slTaiLieuQuaHan = slTaiLieuQuaHan;
        this.slTaiLieuChuaTra = slTaiLieuChuaTra;
        this.slBinhLuan = slBinhLuan;
        this.ngayThongKe = ngayThongKe;
    }

    public int getThongKeID() {
        return thongKeID;
    }

    public void setThongKeID(int thongKeID) {
        this.thongKeID = thongKeID;
    }

    public int getSlNguoiDung() {
        return slNguoiDung;
    }

    public void setSlNguoiDung(int slNguoiDung) {
        this.slNguoiDung = slNguoiDung;
    }

    public int getSlTaiLieuDangMuon() {
        return slTaiLieuDangMuon;
    }

    public void setSlTaiLieuDangMuon(int slTaiLieuDangMuon) {
        this.slTaiLieuDangMuon = slTaiLieuDangMuon;
    }

    public int getSlTaiLieuDaTra() {
        return slTaiLieuDaTra;
    }

    public void setSlTaiLieuDaTra(int slTaiLieuDaTra) {
        this.slTaiLieuDaTra = slTaiLieuDaTra;
    }

    public int getSlTaiLieuYeuThich() {
        return slTaiLieuYeuThich;
    }

    public void setSlTaiLieuYeuThich(int slTaiLieuYeuThich) {
        this.slTaiLieuYeuThich = slTaiLieuYeuThich;
    }

    public int getSlTaiLieuQuaHan() {
        return slTaiLieuQuaHan;
    }

    public void setSlTaiLieuQuaHan(int slTaiLieuQuaHan) {
        this.slTaiLieuQuaHan = slTaiLieuQuaHan;
    }

    public int getSlTaiLieuChuaTra() {
        return slTaiLieuChuaTra;
    }

    public void setSlTaiLieuChuaTra(int slTaiLieuChuaTra) {
        this.slTaiLieuChuaTra = slTaiLieuChuaTra;
    }

    public int getSlBinhLuan() {
        return slBinhLuan;
    }

    public void setSlBinhLuan(int slBinhLuan) {
        this.slBinhLuan = slBinhLuan;
    }

    public int getNgayThongKe() {
        return ngayThongKe;
    }

    public void setNgayThongKe(int ngayThongKe) {
        this.ngayThongKe = ngayThongKe;
    }
}
