package com.namkit.namki.teamnova.Menu2_Wakeup;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.namkit.namki.teamnova.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WakeupUpload extends AppCompatActivity {
    private static final int MY_PERMISSION_CAMERA = 1111;
    Uri uri;
    EditText edit_url, edit_name, edit_date;
    Button btn, btn2; //btn은 앨범가져오고, btn2는 데이터를 메인으로 보내기
    String Sname, Surl, Sdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wakeup_upload);
        //checkPermission();
        init();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickUpPicture();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Sname = edit_name.getText().toString(); //이름 받아오기
                intent.putExtra("image", uri);
                intent.putExtra("wakeurl", Surl);
                intent.putExtra("wakename", Sname);
                intent.putExtra("wakedate", Sdate);
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@온크리에이트 btn2 uri 값 :" + uri);
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@온크리에이트 btn2 Surl 값 :" + Surl);
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@온크리에이트 btn2 Sname 값 :" + Sname);
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@온크리에이트 btn2 Sdate 값 :" + Sdate);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    void init() {
        edit_url = (EditText) findViewById(R.id.imageurl); //url 정보
        edit_name = (EditText) findViewById(R.id.edit1); //이름 넣는 정보
        edit_date = (EditText) findViewById(R.id.edit2); //시간 들어가는 정보
        btn = (Button) findViewById(R.id.btnbtn); //앨범 선택하는 버튼
        btn2 = (Button) findViewById(R.id.btn_image_send); //앨범 선택하는 버튼
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        Surl = "";
        buytime();
        edit_date.setText(Sdate); // 날짜 정보가 져와서 Edit에 뿌려준다
    }
    void pickUpPicture() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, MY_PERMISSION_CAMERA);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode ==MY_PERMISSION_CAMERA){
            if (resultCode == RESULT_OK ) {
                uri = data.getData();
                ImageView imageView = (ImageView) findViewById(R.id.imageView);
                imageView.setImageURI(uri);
                Surl = uri.toString();
                edit_url.setText(Surl);
            }
        }
    }
    void buytime(){
        SimpleDateFormat curFormat = new SimpleDateFormat("MMMM"+"/dd"+"일");
        Date dateObj = new Date(System.currentTimeMillis());
        Sdate = curFormat.format(dateObj);
    }
   /* private void checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // 처음 호출시엔 if()안의 부분은 false로 리턴 됨 -> else{..}의 요청으로 넘어감
            if ((ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) ||
                    (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA))) {
                new AlertDialog.Builder(this)
                        .setTitle("알림")
                        .setMessage("저장소 권한이 거부되었습니다. 사용을 원하시면 설정에서 해당 권한을 직접 허용하셔야 합니다.")
                        .setNeutralButton("설정", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                intent.setData(Uri.parse("package:" + getPackageName()));
                                startActivity(intent);
                            }
                        })
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        })
                        .setCancelable(false)
                        .create()
                        .show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, MY_PERMISSION_CAMERA);
            }
        }
    }*/

}



