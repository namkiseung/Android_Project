package com.namkit.namki.teamnova2.Menu1_Wakeup;

/**
 * Created by namki on 2018-03-23.
 */


public class WakeupProduct {
    private String image;
    private String name;
    private String price;
    private Boolean checked = false;
    public WakeupProduct(String image, String name, String price) {
        this.image = image;
        this.name = name;
        this.price = price;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Boolean getChecked() {        return checked;    }
    public void setChecked(Boolean checked) {        this.checked = checked;    }
}
