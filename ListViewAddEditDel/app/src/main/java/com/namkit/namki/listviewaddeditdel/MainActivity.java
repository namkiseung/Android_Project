package com.namkit.namki.listviewaddeditdel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    int id = -1;
    ListView listSinhVien;
    EditText editTen;
    EditText editSDT;
    Button btnThem, btnSua, btnXoa;
    ArrayList<SinhVien> arrSinhVien;
    CustomAdapter myadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        arrSinhVien = new ArrayList<SinhVien>();

        arrSinhVien.add(new SinhVien(R.mipmap.ic_launcher," 판 벤 손1", "01094065290"));

        myadapter = new CustomAdapter(this, R.layout.item_layout, arrSinhVien);
        listSinhVien.setAdapter(myadapter);

        listSinhVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                id=position;

                    editTen.setText(arrSinhVien.get(position).getTenSinhVien());
                    editSDT.setText(arrSinhVien.get(position).getSdtSinhVien());

            }
        });
        if(savedInstanceState != null) {
            editTen.setText(savedInstanceState.getString("message"));
            editSDT.setText(savedInstanceState.getString("message"));
        }
        listSinhVien.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                arrSinhVien.remove(position);
                myadapter.notifyDataSetChanged();

                return false;
            }
        });
    }
    private void init(){
        listSinhVien = (ListView)findViewById(R.id.list_sinhvien);
        editTen =(EditText)findViewById(R.id.edit_ten);
        editSDT =(EditText)findViewById(R.id.edit_sdt);
        btnThem = (Button)findViewById(R.id.btn_them);
        btnSua = (Button)findViewById(R.id.btn_sua);
        btnXoa = (Button)findViewById(R.id.btn_xoa);

        btnThem.setOnClickListener(this);
        btnSua.setOnClickListener(this);
        btnXoa.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
    switch (view.getId()){
        case R.id.btn_them:

            String ten = editTen.getText().toString();
            String sdt = editSDT.getText().toString();
            SinhVien temp = new SinhVien(R.mipmap.ic_launcher, ten, sdt);
            arrSinhVien.add(temp);
            myadapter.notifyDataSetChanged();
            break;
        case R.id.btn_sua:
            String tenDaSua = editTen.getText().toString();
            String sdtDaSua = editSDT.getText().toString();

            SinhVien svDaSua = new SinhVien(R.mipmap.ic_launcher, tenDaSua, sdtDaSua);
            arrSinhVien.set(id, svDaSua);
            id = -1;
            myadapter.notifyDataSetChanged();
            break;

    }
}
  /*  protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        for(int i=0; i<=arrSinhVien.size(); i++) {
            init();
            listSinhVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    id=position;

                    editTen.setText(arrSinhVien.get(position).getTenSinhVien());
                    editSDT.setText(arrSinhVien.get(position).getSdtSinhVien());

                }
            });
            btnThem.setOnClickListener(this);
            onClick(btnThem);
        }
        outState.putString("message", ""+editTen);
        outState.putString("message2", ""+editSDT);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        editTen.setText(savedInstanceState.getString("message"));
        editSDT.setText(savedInstanceState.getString("message2"));
    }*/
}
