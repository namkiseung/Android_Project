package com.namkit.namki.examfunction;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class CustomProgressBar extends Dialog {
    public CustomProgressBar(Activity activity) {
        super(activity);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //다이얼 로그 제목을 삭제하자
        setContentView(R.layout.activity_custom_progress_bar); // 다이얼로그 보여줄 레이아웃

    }
}
