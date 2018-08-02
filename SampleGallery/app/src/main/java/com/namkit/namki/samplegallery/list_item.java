package com.namkit.namki.samplegallery;

import android.net.Uri;

import java.util.Date;

/**
 * Created by namki on 2018-03-16.
 */

public class list_item {//이클래스는 xml을 반복해서 안의 내용을 바꾸어서 보여주기 위해 해당 내용을 구성하는 부분을 위해 만듬
    //추가한 변수
    private String profile_image; //int 를 String으로 바꾸어 이미지경로를 받도록 수정
    private String nickname;
    private String title;
    private Date write_date;
    private String content;

    public list_item(String profile_image, String nickname, String title, Date write_date, String content) {
        this.profile_image = profile_image;
        this.nickname = nickname;
        this.title = title;
        this.write_date = write_date;
        this.content = content;
    }


    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getWrite_date() {
        return write_date;
    }

    public void setWrite_date(Date write_date) {
        this.write_date = write_date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
