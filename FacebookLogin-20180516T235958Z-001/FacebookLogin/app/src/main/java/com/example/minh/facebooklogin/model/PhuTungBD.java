package com.example.minh.facebooklogin.model;

/**
 * Created by LaVanDuc on 5/17/2018.
 */

public class PhuTungBD {
    private String maxe;
    private String tenxe;
    private String mabaoduong;
    private String mapt;
    private String tenpt;
    private String cachthuc;
    private String ngaybd;
    private String ghichu;

    public String getMaxe() {
        return maxe;
    }

    public void setMaxe(String maxe) {
        this.maxe = maxe;
    }

    public String getTenxe() {
        return tenxe;
    }

    public void setTenxe(String tenxe) {
        this.tenxe = tenxe;
    }

    public String getMabaoduong() {
        return mabaoduong;
    }

    public void setMabaoduong(String mabaoduong) {
        this.mabaoduong = mabaoduong;
    }

    public String getMapt() {
        return mapt;
    }

    public void setMapt(String mapt) {
        this.mapt = mapt;
    }

    public String getTenpt() {
        return tenpt;
    }

    public void setTenpt(String tenpt) {
        this.tenpt = tenpt;
    }

    public String getCachthuc() {
        return cachthuc;
    }

    public void setCachthuc(String cachthuc) {
        this.cachthuc = cachthuc;
    }

    public String getNgaybd() {
        return ngaybd;
    }

    public void setNgaybd(String ngaybd) {
        this.ngaybd = ngaybd;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public PhuTungBD(String maxe, String tenxe, String mabaoduong, String mapt, String tenpt, String cachthuc, String ngaybd, String ghichu) {

        this.maxe = maxe;
        this.tenxe = tenxe;
        this.mabaoduong = mabaoduong;
        this.mapt = mapt;
        this.tenpt = tenpt;
        this.cachthuc = cachthuc;
        this.ngaybd = ngaybd;
        this.ghichu = ghichu;
    }
}
