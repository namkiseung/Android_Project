package com.namkit.namki.a0601fragmentanimation;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by namki on 2018-03-22.
 */

public class MyFragment1 extends Fragment {//app은 아이스크림부터 하고 v4는 하위버전이면서 버그가 더 많다
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);
        Button btn = (Button)view.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
/*플래그 먼트에서 액티비티 접근

((MainActivity)getActivity).변수

((MainActivity)getActivity).메서드



출처: http://itpangpang.xyz/309 [ITPangPang]
*
* */