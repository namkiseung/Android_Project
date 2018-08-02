package com.namkit.namki.a0507progresswork;

/**
 * Created by namki on 2018-03-16.
 */

public class list_item {

    private String name;
    private String content;
    private String date;

    public list_item(String name, String content, String date) {

        this.name = name;
        this.content = content;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}