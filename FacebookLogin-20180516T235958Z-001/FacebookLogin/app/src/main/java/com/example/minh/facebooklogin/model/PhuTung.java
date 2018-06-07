package com.example.minh.facebooklogin.model;

/**
 * Created by LaVanDuc on 5/17/2018.
 */

public class PhuTung {
    private String mapt;
    private String tenpt;
    private int hanmuc_km;
    private int sokm_tbngay;
    private int hanmuc_ngay;

    public PhuTung(String mapt, String tenpt, int hanmuc_km, int hanmuc_ngay) {
        this.mapt = mapt;
        this.tenpt = tenpt;
        this.hanmuc_km = hanmuc_km;
        this.hanmuc_ngay = hanmuc_ngay;
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

    public int getHanmuc_km() {
        return hanmuc_km;
    }

    public void setHanmuc_km(int hanmuc_km) {
        this.hanmuc_km = hanmuc_km;
    }

    public int getSokm_tbngay() {
        return sokm_tbngay;
    }

    public void setSokm_tbngay(int sokm_tbngay) {
        this.sokm_tbngay = sokm_tbngay;
    }

    public int getHanmuc_ngay() {
        return hanmuc_ngay;
    }

    public void setHanmuc_ngay(int hanmuc_ngay) {
        this.hanmuc_ngay = hanmuc_ngay;
    }

    public PhuTung(String mapt, String tenpt, int hanmuc_km, int sokm_tbngay, int hanmuc_ngay) {
        this.mapt = mapt;
        this.tenpt = tenpt;
        this.hanmuc_km = hanmuc_km;
        this.sokm_tbngay = sokm_tbngay;
        this.hanmuc_ngay = hanmuc_ngay;
    }
}
