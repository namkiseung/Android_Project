package com.namkit.namki.samplegallery;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private static final int MY_PERMISSION_CAMERA = 1111;
    private static final int REQUEST_TAKE_PHOTO = 2222;
    private static final int REQUEST_TAKE_ALBUM = 3333;
    private static final int REQUEST_IMAGE_CROP = 4444;

    GridView listView;
    MyListAdapter myListAdapter;
    ArrayList<list_item> list_itemArrayList;

    Button btn_capture, btn_album;
    ImageView iv_view;
    String mCurrentPhotoPath;
    Uri imageUri;
    Uri photoURI, albumURI;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addarray();
        btn_album = (Button) findViewById(R.id.btn_album);
        iv_view = (ImageView) findViewById(R.id.profile_imageView);

        list_itemArrayList = new ArrayList<list_item>();
        listView = (GridView)findViewById(R.id.my_listView);
        btn_album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAlbum();
            }
        });


        checkPermission();


        /*list_itemArrayList.add(new list_item("https://www.google.co.kr/search?q=%EC%95%84%EC%9D%B4%EC%9C%A0&source=lnms&tbm=isch&sa=X&ved=0ahUKEwiH_r3z7-7ZAhXJvbwKHeXXC-gQ_AUICigB&biw=1922&bih=951#imgrc=R-YVH63nH4ThmM:","보라돌이","제목1",new Date(System.currentTimeMillis()),"내용1"));

        list_itemArrayList.add(new list_item("https://cdn-images-1.medium.com/fit/c/36/36/0*HgJ2Psmia7PjQsp9.jpg",
                "나나","제목3",new Date(System.currentTimeMillis()),"내용3"));

        list_itemArrayList.add(new list_item("https://cdn-images-1.medium.com/fit/c/36/36/0*HgJ2Psmia7PjQsp9.jpg",
                "뽀","제목4",new Date(System.currentTimeMillis()),"내용4"));

        list_itemArrayList.add(new list_item("https://cdn-images-1.medium.com/fit/c/36/36/0*HgJ2Psmia7PjQsp9.jpg",
                "햇님","제목5",new Date(System.currentTimeMillis()),"내용5"));*/

        myListAdapter = new MyListAdapter(MainActivity.this, list_itemArrayList);
        listView.setAdapter(myListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this ,list_itemArrayList.get(position).getNickname(),Toast.LENGTH_LONG).show();
            }
        });
    }
    private void addarray() {
        //for(int i=0; i<10; i++) {
        try {
            ImageView imageView = null;
            URL url = new URL("이미지 주소");
            URLConnection conn = url.openConnection();
            conn.connect();
            BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
            Bitmap bm = BitmapFactory.decodeStream(bis);
            bis.close();
            imageView.setImageBitmap(bm);
        } catch (Exception e) {
        }
        //}
    }
    public File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + ".jpg";
        File imageFile = null;
        File storageDir = new File(Environment.getExternalStorageDirectory() + "/Pictures", "namkit");

        if (!storageDir.exists()) {
            storageDir.mkdirs();
        }

        imageFile = new File(storageDir, imageFileName);
        mCurrentPhotoPath = imageFile.getAbsolutePath();

        return imageFile;
    }
    private void getAlbum(){
        Log.d("getAlbum", "getAlbum() 메서드 진입");
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, REQUEST_TAKE_ALBUM);
        Log.d("getAlbum", "getAlbum() 메서드 탈출");
    }

    private void galleryAddPic(){
                                                            Log.d("galleryAddPic", "galleryAddPic() 메서드 진입");
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        // 해당 경로에 있는 파일을 객체화(새로 파일을 만든다는 것으로 이해하면 안 됨)
        File f = new File(mCurrentPhotoPath);
                                                                    Log.d("galleryAddPic", "File f = new File(mCurrentPhotoPath); 진입");
        Uri contentUri = Uri.fromFile(f);
                                                                    Log.d("galleryAddPic", "Uri contentUri = Uri.fromFile(f); 진입");
        mediaScanIntent.setData(contentUri);
                                                                    Log.d("galleryAddPic", "mediaScanIntent.setData(contentUri); 진입");
        sendBroadcast(mediaScanIntent);
                                                                    Log.d("galleryAddPic", " sendBroadcast(mediaScanIntent); 진입");
        Toast.makeText(this, "사진이 앨범에 저장되었습니다.", Toast.LENGTH_SHORT).show();
    }

    // 카메라 전용 크랍
    public void cropImage(){
        Log.d("cropImage", "cropImage() 메서드 진입");
        Log.i("cropImage", "photoURI : " + photoURI + " / albumURI : " + albumURI);

        Intent cropIntent = new Intent("com.android.camera.action.CROP");
//.setImageUri(photoURI);
        // 50x50픽셀미만은 편집할 수 없다는 문구 처리 + 갤러리, 포토 둘다 호환하는 방법
        cropIntent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        cropIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        cropIntent.setDataAndType(photoURI, "image/*");
        //cropIntent.putExtra("outputX", 200); // crop한 이미지의 x축 크기, 결과물의 크기
        //cropIntent.putExtra("outputY", 200); // crop한 이미지의 y축 크기
        cropIntent.putExtra("aspectX", 1); // crop 박스의 x축 비율, 1&1이면 정사각형
        cropIntent.putExtra("aspectY", 1); // crop 박스의 y축 비율
        cropIntent.putExtra("scale", true);
        cropIntent.putExtra("output", albumURI); // 크랍된 이미지를 해당 경로에 저장
        startActivityForResult(cropIntent, REQUEST_IMAGE_CROP);
    }
    public String getPathFromUri(Uri uri){
        Cursor cursor = getContentResolver().query(uri, null, null, null, null );
        cursor.moveToNext();
        String path = cursor.getString( cursor.getColumnIndex( "_data" ) );
        cursor.close();

        return path;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_TAKE_PHOTO:
                if (resultCode == Activity.RESULT_OK) {
                        galleryAddPic();
                        iv_view.setImageURI(imageUri);
                    //adapter.addItem(new Wakeup_Item(Integer.parseInt(getPathFromUri(imageUri))));
                    /*list_itemArrayList.add(new list_item(R.id.));
                    list_itemArrayList.notifyDataSetChanged();*/
                } else {
                    Toast.makeText(MainActivity.this, "사진찍기를 취소하였습니다.", Toast.LENGTH_SHORT).show();
                }
                break;

            case REQUEST_TAKE_ALBUM:
                if (resultCode == Activity.RESULT_OK) {

                    if(data.getData() != null){
                        try {
                            File albumFile = null;
                            albumFile = createImageFile();
                            photoURI = data.getData();
                            albumURI = Uri.fromFile(albumFile);
                            cropImage();
                        }catch (Exception e){
                            Log.e("TAKE_ALBUM_SINGLE ERROR", e.toString());
                        }
                    }
                }
                break;

            case REQUEST_IMAGE_CROP:
                Log.d("onActivityResult", "onActivityResult() 메서드 안에 케이스 : REQUEST_IMAGE_CROP 진입");

                Log.d("onActivityResult", "iv_view.setImageURI(albumURI); "+albumURI);
                if (resultCode == Activity.RESULT_OK) {
                    galleryAddPic();
                        iv_view.setImageURI(albumURI);
                    list_itemArrayList.add(new list_item(albumURI.toString(),
                            "햇님","제목5",new Date(System.currentTimeMillis()),"내용5"));
                        // Glide.with(this).load("http://www.selphone.co.kr/homepage/img/team/3.jpg").into(imageView);
                }
                break;
        }
    }

    private void checkPermission(){
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
                                Log.d("경고 알아보자","Uri.parse(\"package:\" + getPackageName())"+Uri.parse("package:" + getPackageName()));
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
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, MY_PERMISSION_CAMERA);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSION_CAMERA:
                for (int i = 0; i < grantResults.length; i++) {
                    // grantResults[] : 허용된 권한은 0, 거부한 권한은 -1
                    if (grantResults[i] < 0) {
                        Toast.makeText(MainActivity.this, "해당 권한을 활성화 하셔야 합니다.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                // 허용했다면 이 부분에서..

                break;
        }
    }
}
