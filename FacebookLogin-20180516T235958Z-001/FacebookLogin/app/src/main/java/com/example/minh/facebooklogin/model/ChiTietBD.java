package com.example.minh.facebooklogin.model;

import java.io.Serializable;

/**
 * Created by LaVanDuc on 5/17/2018.
 */

public class ChiTietBD implements Serializable {
    private String mabaoduong;
    private String mapt;
    private String tenpt;
    private String cachthuc;
    private String ngay;
    private String ghichu;

    public ChiTietBD(String mabaoduong, String mapt, String cachthuc, String ngay, String ghichu) {
        this.mabaoduong = mabaoduong;
        this.mapt = mapt;
        this.cachthuc = cachthuc;
        this.ngay = ngay;
        this.ghichu = ghichu;
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

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }


    public ChiTietBD(String tenpt, String cachthuc, String ngay, String ghichu) {
        this.tenpt = tenpt;
        this.cachthuc = cachthuc;
        this.ngay = ngay;
        this.ghichu = ghichu;
    }

    public ChiTietBD(String mabaoduong, String mapt, String tenpt, String cachthuc, String ngay, String ghichu) {

        this.mabaoduong = mabaoduong;
        this.mapt = mapt;
        this.tenpt = tenpt;
        this.cachthuc = cachthuc;
        this.ngay = ngay;
        this.ghichu = ghichu;
    }
}
