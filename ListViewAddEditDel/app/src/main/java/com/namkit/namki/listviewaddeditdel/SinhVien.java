package com.namkit.namki.listviewaddeditdel;

/**
 * Created by namki on 2018-03-06.
 */

public class SinhVien {
    int avatar;
    String tenSinhVien;
    String sdtSinhVien;

    public SinhVien() {

    }

    public SinhVien(int avatar, String tenSinhVien, String sdtSinhVien) {
        this.avatar = avatar;
        this.tenSinhVien = tenSinhVien;
        this.sdtSinhVien = sdtSinhVien;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getTenSinhVien() {
        return tenSinhVien;
    }

    public void setTenSinhVien(String tenSinhVien) {
        this.tenSinhVien = tenSinhVien;
    }

    public String getSdtSinhVien() {
        return sdtSinhVien;
    }

    public void setSdtSinhVien(String sdtSinhVien) {
        this.sdtSinhVien = sdtSinhVien;
    }
}
