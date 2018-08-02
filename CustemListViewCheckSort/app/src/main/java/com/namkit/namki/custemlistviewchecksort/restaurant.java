package com.namkit.namki.custemlistviewchecksort;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by namki on 2018-03-24.
 */

public class restaurant implements Parcelable {
    private String name, homepage, tell, date;
    private String[] menu = new String[3];
    private int type;
    private Boolean checked = false;

    public restaurant(restaurant r) {
        this.name = r.getName();
        this.homepage = r.getHomepage();
        this.tell = r.getTell();
        this.menu = r.getMenu();
        this.type = r.getType();
        this.checked = r.getChecked();
    }
    public restaurant(String name, String homepage, String tell, String date, String[] menu, int type) {
        this.name = name;
        this.homepage = homepage;
        this.tell = tell;
        this.date = date;
        this.menu = menu;
        this.type = type;
        this.checked = false;
    }
    public Boolean getChecked() {        return checked;    }
    public void setChecked(Boolean checked) {        this.checked = checked;    }

    protected restaurant(Parcel in) {
        name = in.readString();
        homepage = in.readString();
        tell = in.readString();
        date = in.readString();
        menu = in.createStringArray();
        type = in.readInt();
    }

    public static final Creator<restaurant> CREATOR = new Creator<restaurant>() {
        @Override
        public restaurant createFromParcel(Parcel in) {
            return new restaurant(in);
        }

        @Override
        public restaurant[] newArray(int size) {
            return new restaurant[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(homepage);
        parcel.writeString(tell);
        parcel.writeString(date);
        parcel.writeStringArray(menu);
        parcel.writeInt(type);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getTell() {
        return tell;
    }

    public void setTell(String tell) {
        this.tell = tell;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String[] getMenu() {
        return menu;
    }

    public void setMenu(String[] menu) {
        this.menu = menu;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}