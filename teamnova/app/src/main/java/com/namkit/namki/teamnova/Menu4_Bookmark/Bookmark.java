package com.namkit.namki.teamnova.Menu4_Bookmark;

/**
 * Created by namki on 2018-03-14.
 */

public class Bookmark {
    private String title;
    private String url;
    private boolean checked = false;

    public Bookmark(String title, String url) {
        this.title = title;
        this.url = url;
    }
    public Bookmark(String title, String url, Boolean checked) {
        this.title = title;
        this.url = url;
        this.checked = checked;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public Boolean getChecked() {
        return checked;
    }
    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}

