package com.namkit.namki.andinsideexamfragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //private static String[] NUMBERS = new String[]{"1", "2", "3", "4", "5"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] NUMBERS = new String[]{"1", "2", "3", "4", "5"};

        ArrayListFragment list = new ArrayListFragment();

        Bundle bundle = new Bundle();
        bundle.putStringArray("숫자", NUMBERS);
        list.setArguments(bundle);
        //숫자배열이 저장되어 있는 번들객체를 프래그먼트의 인자로 설정. Bundle객체에는 문자열 배열뿐만 아니라 원하는 데이터형 저장가능


          FragmentManager fm = getFragmentManager();
          FragmentTransaction ft = fm.beginTransaction();
          //ft.add(android.R.id.content, list);
          ft.commit();
        //만일 프래그먼트에서 액티비티 선언한 뷰 접근 할때는 TextView textView = (Textview)getActivity().findViewById(R.id.name);

        /**
         * 자바코드에서 프래그먼트 추가하는 코드
         * ArrayListFragment list = new ArrayListFragment();   가장먼저 프래그 먼트 객체를 얻어온다.
         *
         * FragmentManager fm = getFragmentManager();     프래그먼트 트랜잭션(프래그먼트 추가하고 교체하고 삭제하는 작업을 처리하기 위해 해당 객체얻음
         * 프래그먼트트랜잭션 객체로  add()나 replace()메소드로 안드로이드 기본제공 레이아웃에 제공가능
         * FragmentTransaction ft = fm.beginTransaction();  beginTransaction()은 현재 FragmentManager와 연관된 프래그먼트의 작업처리를 시작한다.
         * 주의할점은 프래그먼트 트랜잭션은 액티비티의 상태가 저장되기 전에만 생성하거나 요청(commit)할 수 있다. 만약 Activity.onSaveInstanceState() 메소드 호출뒤에 혹은 onStart(),Activity, Activity.onResume() 호출되기전이면 에러!! 안드로이드 프래그먼트는 현재프래그먼트를 상태로 저장하기 떄문에, 상태저장 후에 프래그먼트가 변경될경우 반영하지 않는다
         *
         * ft.add(android.R.id.content, list);
         * ft.commit();
         *
         *
         * 보너스 관련 메서드는 remove(Fragment fragment)*/

    }
    public static class ArrayListFragment extends ListFragment{
        @Override
        public void onActivityCreated(Bundle saveInstanceState){
            //안드로이드 기본제공 레이아웃과 숫자배열을 지정한 ArrayAdapter를 생성하고 이객체를 setListAdapter() 에 지정
            super.onActivityCreated(saveInstanceState);
            //setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1, Number));
            //선택 아이템 한개만 설정
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
        @Override
        //사용자가 선택한 아이템의 숫자를 알고 싶으면  NUMBERS[id]
        public void onListItemClick(ListView l, View v, int position, long id){
            //지정된 위치(posititon)의 아이템을 선택한 상태로 표시
            getListView().setItemChecked(position, true);
        }
    }
    /**
     * 액티비티에서 프래그먼트로 데이터 전달 크게4가지
     * 1. 생성자에 인자로 전달하는 방법
     * 2. 객체를 생성하는 메서드에 인자로 전달하는 방법
     * 3. 프래그먼트에 인자로 전달하는 방법
     * 4. static 변수로 공유하는 방법*/

    public void onSaveInstaceState(Bundle outState){
        super.onSaveInstanceState(outState);

        Log.d("lifeCycle", "액티비티 onSaveInstanceState");
    }
    public void onRestoreInstanceState(Bundle saveInstanceState){
        super.onRestoreInstanceState(saveInstanceState);

        Log.d("lifeCycle", "액티비티 onRestoreInstanceState");
    }
    public void onDestroy(){
        super.onDestroy();

        Log.d("lifeCycle", "액티비티 onDestroy");
    }
    public void onPause(){
        super.onPause();

        Log.d("lifeCycle", "액티비티 onPause");
    }
    public void onRestart(){
        super.onRestart();

        Log.d("lifeCycle", "액티비티 onRestart");
    }
    public void onResume(){
        super.onResume();

        Log.d("lifeCycle", "액티비티 onResume");
    }
    public void onStart(){
        super.onStart();

        Log.d("lifeCycle", "액티비티 onStart");
    }
    public void onStop(){
        super.onStop();

        Log.d("lifeCycle", "액티비티 onStop");
    }

}



