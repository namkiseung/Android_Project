package com.namkit.namki.sharedpreferencesexam04_06;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;

    EditText editName, editMajor;
    CheckBox chkDevelop, chkDesign, chkPM;
    RadioGroup radioGroup;
    Button btnSave, btnLoad, btnReset;
    SeekBar seekBar;

    /**
 * 데이터 뿐아니라 UI액티비티 까지 저장하기위해 SharedPreferences 사용*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initPreferences();
        initView();
        btn_Event();
        loadState();

    }
    /**
     * btn_Event()
     *
     * 버튼 클릭 시의 이벤트
     * btnSave : saveState() -> 상태를 저장하는 메소드 호출
     * btnLoad : loadState() -> 상태를 불러오는 메소드 호출
     * btnReset : 화면 상의 뷰들의 값을 초기화
     */

    private void btn_Event() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveState(); //상태를 저장하는 메소드
                Toast.makeText(getApplicationContext(), "저장되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadState(); //상태를 불러오는 메소드
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editName.setText("");
                editMajor.setText("");
                chkDevelop.setChecked(false);
                chkDesign.setChecked(false);
                chkPM.setChecked(false);
                radioGroup.check(0);
                seekBar.setProgress(0);
            }
        });

    }

    /**
     * loadState()
     *
     * 상태를 불러오는 메소드입니다.
     * sharedPreferences.getString("key", defaultValue)  key는 식별자, defaultValue는 해당 key에 값이 없을 때 디폴트로 가져오는 값
     *                   getInt
     *                   getBoolean 등등
     * 해당하는 자료형에 따라 메소드가 달라집니다.
     * EditText의 경우 String 형으로 저장이 됩니다.
     * CheckBox의 경우는 Boolean 형으로 저장이 됩니다.
     * seekBar의 경우는 Int 형으로 저장이 됩니다.
     */

    private void loadState() {
        editName.setText(sharedPreferences.getString("name", ""));
        editMajor.setText(sharedPreferences.getString("major",""));
        chkDevelop.setChecked(sharedPreferences.getBoolean("chkDevelop", false));
        chkDesign.setChecked(sharedPreferences.getBoolean("chkDesign", false));
        chkPM.setChecked(sharedPreferences.getBoolean("chkPM", false));
        radioGroup.check(sharedPreferences.getInt("radioGroup", 0));
        seekBar.setProgress(sharedPreferences.getInt("seekBar",0));
    }
    /**
     * saveState()
     *
     * 상태를 저장하는 메소드입니다.
     * SharedPreferences 데이터에 변화를 주려면 Editor를 사용해야 합니다.
     * SharedPreferences.Editor editor = sharedPreferences.edit(); -> 변화를 주는 메소드를 작성하기 전 반드시 editor를 생성합니다.
     *
     * editor.putString("key", value)  key는 식별자, value는 해당 key(식별자)에 대응하는 값
     *        putInt
     *        putBoolean 등등
     * 해당하는 자료형에 따라 메소드가 달라집니다.
     * EditText의 경우 String 형
     * CheckBox의 경우는 Boolean 형
     * seekBar의 경우는 Int 형
     *
     * 마지막에 반드시 commit() 메소드를 호출해야 SharedPreferences에 저장이 됩니다.
     */

    private void saveState() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name"+editName.getText().toString(),editName.getText().toString());
        editor.putString("major", editMajor.getText().toString());
        editor.putBoolean("chkDevelop", chkDevelop.isChecked());
        editor.putBoolean("chkDesign", chkDesign.isChecked());
        editor.putBoolean("chkPM", chkPM.isChecked());
        editor.putInt("radioGroup", radioGroup.getCheckedRadioButtonId());
        editor.putInt("seekBar", seekBar.getProgress());
        editor.commit();
    }

    /**
     * initPreferences()
     *
     * SharedPreferences를 초기화하는 메소드
     * sharedPreferences = getSharedPreferences("이름", 모드);
     * 이름은 파일명 -> SharedPreferences의 저장경로는 해당 단말기 "data/data/[패키지 이름]/shared_prefs/이름.xml"입니다.
     * 모드는 접근 권한입니다.
     * MODE_PRIVATE 모드로 설정하면 해당 앱에서만 SharedPreferences 데이터에 접근 가능합니다.
     */
    private void initPreferences() {
        sharedPreferences = getSharedPreferences("pref", MODE_PRIVATE);
    }

    private void initView() {
        editName = (EditText)findViewById(R.id.editName);
        editMajor = (EditText)findViewById(R.id.editMajor);
        chkDevelop = (CheckBox)findViewById(R.id.chkDevelop);
        chkDesign = (CheckBox)findViewById(R.id.chkDesign);
        chkPM = (CheckBox)findViewById(R.id.chkPM);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        btnSave = (Button)findViewById(R.id.btnSave);
        btnLoad = (Button)findViewById(R.id.btnLoad);
        btnReset = (Button)findViewById(R.id.btnReset);
        seekBar = (SeekBar)findViewById(R.id.seekBar);
    }
}