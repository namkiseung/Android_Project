package com.namkit.namki.android1p;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by namki on 2018-02-24.
 */
public class FragmentThree extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_layout, container, false);
//레이아웃 인플레이트는 공식처럼 외우면 된다고 합니다!
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onViewCreated(view, savedInstanceState);
        TextView tv = (TextView)view.findViewById(R.id.textView1);
        tv.setText("Fragment One");
//이런식으로 프래그먼트를 3개 만듭니다. 탭이 4개면 4개~
    }
}

