package com.example.namki.jsonlistview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView plistView;
    private ArrayList<Product> productArrayList = new ArrayList<>();
    private ProductAdapter productAdapter;
    Button add;
    EditText edit_title, edit_desc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        plistView = (ListView)findViewById(R.id.mychatlistview);
        productAdapter = new ProductAdapter(this, R.layout.custom_chatlist,productArrayList);
        plistView.setAdapter(productAdapter);
      //  plistView.setOnItemClickListener(mItemClickListener);  //리스트뷰에 아이템 장착
        productArrayList.add(new Product(R.drawable.ic_launcher_foreground, "HI222","Im Ethan"));
        productArrayList.add(new Product(R.drawable.ic_launcher_foreground, "Hello","ㅁㅁㅁㅁ"));
        productArrayList.add(new Product(R.drawable.ic_launcher_foreground, "Hello","ㅇㅇㅇㅇㅇ"));
        productArrayList.add(new Product(R.drawable.ic_launcher_foreground, "Hello","ㄹㄹㄹㄹㄹ"));
        productArrayList.add(new Product(R.drawable.ic_launcher_foreground, "Hello","시커토처머"));
        add = (Button)findViewById(R.id.btn_add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                builder.setTitle("추가");
                builder.setMessage("글을 작성하세요");

                edit_desc = new EditText(getApplicationContext());
                edit_title = new EditText(getApplicationContext());
                builder.setView(edit_title);
                builder.setView(edit_desc);

                builder.setPositiveButton("Sumit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String txt = edit_title.getText().toString();
                        String txt2 = edit_desc.getText().toString();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                //다이얼로그 생성
                final AlertDialog ad = builder.create();

                //버튼
                add = (Button)findViewById(R.id.btn_add);
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ad.show();
                    }
                });
                /*ExampleDialog exampleDialog = new ExampleDialog();
                exampleDialog.show(getSupportFragmentManager(), "example dialog");*/
            }
        });

    }
   /* private AdapterView.OnItemClickListener mItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            Toast.makeText(MainActivity.this, "리스트뷰아이템 클릭", Toast.LENGTH_SHORT).show();
        }
    };*/
}
class Product {

    private int icon;
    private String name;
    private String content;

    public Product(int icon, String name, String content) {
        this.icon = icon;
        this.name = name;
        this.content = content;

    }

    public int getIcon() {

        return icon;

    }

    public void setIcon(int icon) {
        this.icon = icon;

    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;

    }

    public String getContent() {

        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

}
class ProductHolder {
    TextView productname;
    TextView productcontent;
    ImageView productimage;
}

class ProductAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Product> productlist = new ArrayList<>();

    public ProductAdapter (Context context , int layout , ArrayList<Product> productlist) {
        this.context = context;
        this.layout = layout;
        this.productlist = productlist;
    }
    @Override
    public int getCount() {

        return productlist.size();
    }

    @Override
    public Product getItem(int position) {


        return productlist.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /*final ProductHolder productHolder;

        if(convertView == null) {
            productHolder = new ProductHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


            convertView = layoutInflater.inflate(layout, null);
            productHolder.productimage = (ImageView) convertView.findViewById(R.id.img_Picture);
            productHolder.productname = (TextView) convertView.findViewById(R.id.tv_Name);
            productHolder.productcontent = (TextView) convertView.findViewById(R.id.tv_Content);

            convertView.setTag(productHolder);
        }else {
            productHolder = (ProductHolder) convertView.getTag();
        }

        Product product = productlist.get(position);

        productHolder.productimage.setImageResource(product.getIcon());
        productHolder.productname.setText(product.getName());
        productHolder.productcontent.setText(product.getContent());
*/

        final Product product = productlist.get(position);
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
        }
        TextView tv_name1 = (TextView) convertView.findViewById(R.id.chattingname);
        TextView tv_content2 = (TextView) convertView.findViewById(R.id.chattingcontent);

        tv_name1.setText(product.getName());

        tv_content2.setText(product.getContent());

        return convertView;
    }
    public void removeitem(int position) {
        productlist.remove(position);
    }
}