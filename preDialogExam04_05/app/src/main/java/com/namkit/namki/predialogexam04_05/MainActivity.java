package com.namkit.namki.predialogexam04_05;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends ListActivity {

   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }*/

    private String[] mMenuText;
    private String[] mMenuSummary;

    private String keyName = "name";
    private String keyDesc = "desc";
    private String TAG;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getClass().getName();

        int length = 7;
        mMenuText = new String[length];
        mMenuSummary = new String[length];

        mMenuText[0] = "다중선택 새창";
        mMenuSummary[0] = "다중선택을 할수 있는 Alert 예제구현이다.";
        mMenuText[1] = "HTML 적용 새창";
        mMenuSummary[1] = "Text 에 HTML 을 적용하는 Alert 예제구현이다.";
        mMenuText[2] = "프로그레시브바 새창";
        mMenuSummary[2] = "진행중을 나타내는 프로그레시브바 Alert 예제구현다.";
        mMenuText[3] = "Radio 버튼 새창";
        mMenuSummary[3] = "Radio 버튼이 들어간 새창 이며 선택하면 창이 닫힌다. ";
        mMenuText[4] = "Simple Dialog";
        mMenuSummary[4] = "선택에 따른 로직구현을 위한 다이얼로그 창 구현";
        mMenuText[5] = "Time Picker";
        mMenuSummary[5] = "Time Picker 시간선택 컨트롤을 다이얼로그에 구현";
        mMenuText[6] = "Date Picker";
        mMenuSummary[6] = "Date Picker 날짜선택 컨트롤을 다이얼로그에 구현";

        setListAdapter(new SimpleAdapter(this, getListValues(),
                android.R.layout.simple_list_item_2, new String[] {
                keyName, keyDesc }, new int[] { android.R.id.text1, android.R.id.text2 }));
    }

    private List<Map<String, String>> getListValues() {
        List<Map<String, String>> values = new ArrayList<Map<String, String>>();
        int length = mMenuText.length;
        for (int i = 0; i < length; i++) {
            Map<String, String> v = new HashMap<String, String>();
            v.put(keyName, mMenuText[i]);
            v.put(keyDesc, mMenuSummary[i]);
            values.add(v);
        }
        return values;
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Log.d(TAG, "id : " + id + ", position : " + position);
        switch (position) {
            case 0:
                // 다중선택 옵션창
                this.DialogSelectOption();
                break;
            case 1:
                // HTML 구현
                this.DialogHtmlView();
                break;
            case 2:
                // 프로그레시브바 구현
                this.DialogProgress();
                break;
            case 3:
                // Radio 버튼이 추가된 다중선택 창
                this.DialogRadio();
                break;
            case 4:
                // 가장 일반적인 Yes/NO기능구현 Dialog
                this.DialogSimple();
                break;
            case 5:
                this.DialogTimePicker();
                break;
            case 6:
                // 날짜 선택 Dialog 구현
                this.DialogDatePicker();
                break;
            default:
                break;
        }
    }

    private void DialogSelectOption() {
        final String items[] = { "item1", "item2", "item3" };
        AlertDialog.Builder ab = new AlertDialog.Builder(MainActivity.this);
        ab.setTitle("Title");
        ab.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // 각 리스트를 선택했을때
            }
        }).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // OK 버튼 클릭시 , 여기서 선택한 값을 메인 Activity 로 넘기면 된다.
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Cancel 버튼 클릭시
            }
        });
        ab.show();
    }

    private void DialogHtmlView() {
        AlertDialog.Builder ab = new AlertDialog.Builder(MainActivity.this);
        ab.setMessage(Html.fromHtml("<strong><font color=\"#ff0000\"> " + "Html 표현여부 "
                + "</font></strong><br>HTML 이 제대로 표현되는지 본다."));
        ab.setPositiveButton("ok", null);
        ab.show();
    }

    private void DialogProgress() {
        ProgressDialog dialog =

                ProgressDialog.show(MainActivity.this, "", "잠시만 기다려 주세요 ...", true);

        // 창을 내린다.
        // dialog.dismiss();
    }

    private void DialogRadio() {
        final CharSequence[] PhoneModels = { "iPhone", "Nokia", "Android" };
        AlertDialog.Builder alt_bld = new AlertDialog.Builder(this);
        alt_bld.setIcon(R.drawable.ic_launcher_background);
        alt_bld.setTitle("Select a Phone Model");
        alt_bld.setSingleChoiceItems(PhoneModels, -1,
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int item) {
                        Toast.makeText(getApplicationContext(), "Phone Model = "
                                + PhoneModels[item], Toast.LENGTH_SHORT)
                                .show();
                        dialog.cancel();
                    }
                });
        AlertDialog alert = alt_bld.create();
        alert.show();
    }

    private void DialogSimple() {
        AlertDialog.Builder alt_bld = new AlertDialog.Builder(this);
        alt_bld.setMessage("Do you want to close this window ?").setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Action for 'Yes' Button
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Action for 'NO' Button
                dialog.cancel();
            }
        });
        AlertDialog alert = alt_bld.create();
        // Title for AlertDialog
        alert.setTitle("Title");
        // Icon for AlertDialog
        alert.setIcon(R.drawable.ic_launcher_background);
        alert.show();
    }

    private void DialogTimePicker() {
        TimePickerDialog.OnTimeSetListener mTimeSetListener =
                new TimePickerDialog.OnTimeSetListener() {
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Toast.makeText(MainActivity.this, "Time is=" + hourOfDay + ":"
                                + minute, Toast.LENGTH_SHORT).show();
                    }
                };
        TimePickerDialog alert =
                new TimePickerDialog(this, mTimeSetListener, 0, 0, false);
        alert.show();
    }

    private void DialogDatePicker() {
        Calendar c = Calendar.getInstance();
        int cyear = c.get(Calendar.YEAR);
        int cmonth = c.get(Calendar.MONTH);
        int cday = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog.OnDateSetListener mDateSetListener =
                new DatePickerDialog.OnDateSetListener() {
                    // onDateSet method
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String date_selected = String.valueOf(monthOfYear + 1) + " /"
                                + String.valueOf(dayOfMonth) + " /"
                                + String.valueOf(year);
                        Toast.makeText(MainActivity.this, "Selected Date is ="
                                + date_selected, Toast.LENGTH_SHORT).show();
                    }
                };
        DatePickerDialog alert =
                new DatePickerDialog(this, mDateSetListener, cyear, cmonth, cday);
        alert.show();
    }

}