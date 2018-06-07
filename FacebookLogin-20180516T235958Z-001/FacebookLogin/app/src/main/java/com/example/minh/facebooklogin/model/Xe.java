package com.example.minh.facebooklogin.model;

import java.io.Serializable;

/**
 * Created by LaVanDuc on 5/17/2018.
 */

public class Xe implements Serializable {
    private String maxe;
    private String tenxe;
    private byte[] hinhanh;

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

    public byte[] getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(byte[] hinhanh) {
        this.hinhanh = hinhanh;
    }

    public Xe(String tenxe) {
        this.tenxe = tenxe;
    }

    public Xe(String maxe, String tenxe, byte[] hinhanh) {

        this.maxe = maxe;
        this.tenxe = tenxe;
        this.hinhanh = hinhanh;
    }
}
