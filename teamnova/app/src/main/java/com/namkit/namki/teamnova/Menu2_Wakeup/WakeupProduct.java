package com.namkit.namki.teamnova.Menu2_Wakeup;

/**
 * Created by namki on 2018-03-23.
 */


public class WakeupProduct {
    private String image;
    private String name;
    private String time;
    private Boolean checked = false;


    public WakeupProduct(String image, String name, String time, Boolean checked) {
        this.image = image;
        this.name = name;
        this.time = time;
        this.checked = checked;
    }

    public WakeupProduct(String image, String name, String time) {
        this.image = image;
        this.name = name;
        this.time = time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
