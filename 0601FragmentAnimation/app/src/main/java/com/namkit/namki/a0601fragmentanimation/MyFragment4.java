package com.namkit.namki.a0601fragmentanimation;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by namki on 2018-03-22.
 */

public class MyFragment4 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment4, container, false);
    }//app은 아이스크림부터 하고 v4는 하위버전이면서 버그가 더 많다
}
