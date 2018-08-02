package com.namkit.namki.teamnova.Menu3_Daily;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by namki on 2018-03-05.
 */

public class Daily_MainItem implements Parcelable{
    protected String category;
    protected String title;
    protected String description;
    protected String time;
    private int type;
    private Boolean checked = false;

    public Daily_MainItem(String title, String category,  String time) {
        this.title = title;
        this.category = category;
        this.time = time;
    }

    public Daily_MainItem(String title, String category,  String time, String description) {
        this.title = title;
        this.category = category;
        this.time = time;
        this.description = description;
        this.checked = false;
    }

    public Daily_MainItem(Daily_MainItem item) {
        this.title = item.getTitle();
        this.category = item.getCategory();
        this.time = item.getTime();
        this.description = item.getDescription();
        this.type = item.getType();
        this.checked = item.getChecked();
    }

    protected Daily_MainItem(Parcel in) {
        category = in.readString();
        title = in.readString();
        description = in.readString();
        time = in.readString();
        type = in.readInt();
    }

    public static final Creator<Daily_MainItem> CREATOR = new Creator<Daily_MainItem>() {
        @Override
        public Daily_MainItem createFromParcel(Parcel in) {
            return new Daily_MainItem(in);
        }

        @Override
        public Daily_MainItem[] newArray(int size) {
            return new Daily_MainItem[size];
        }
    };

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }


    public Boolean getChecked() {        return checked;    }
    public void setChecked(Boolean checked) {        this.checked = checked;    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(category);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(time);
        dest.writeInt(type);
        dest.writeByte((byte) (checked == null ? 0 : checked ? 1 : 2));
    }
}