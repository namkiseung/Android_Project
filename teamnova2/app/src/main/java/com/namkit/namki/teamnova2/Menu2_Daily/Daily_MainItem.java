package com.namkit.namki.teamnova2.Menu2_Daily;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by namki on 2018-03-05.
 */

public class Daily_MainItem implements Parcelable {
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

    public Daily_MainItem(String title, String category,  String time, int type) {
        this.title = title;
        this.category = category;
        this.time = time;
        this.type = type;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(category);
        parcel.writeString(description);
        parcel.writeString(time);
        parcel.writeInt(type);
    }
    public Boolean getChecked() {        return checked;    }
    public void setChecked(Boolean checked) {        this.checked = checked;    }
}