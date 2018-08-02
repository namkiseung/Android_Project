package com.namkit.namki.listviewpasstext;

import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by namki on 2018-03-07.
 */

public class Product {
    private String text;
    public String editText;

    public Product(String text) {
        this.text = text;
    }

    public Product(String text, String editText) {
        this.text = text;
        this.editText = editText;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getEditText() {
        return editText;
    }

    public void setEditText(String editText) {
        this.editText = editText;
    }
}
