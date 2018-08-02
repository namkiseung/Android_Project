package com.namkit.namki.webfilesaveexample;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    WebView webView;
    ListView listView;
    Animation animtop;
    LinearLayout linear;
    ArrayAdapter<String> adapter ;
    EditText et;

    ProgressDialog progressDialog ;

    ArrayList<Sitedata> data = new ArrayList<>();
    ArrayList<String> titles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = (EditText)findViewById(R.id.et);
        webView = (WebView)findViewById(R.id.webview);
        listView = (ListView)findViewById(R.id.listview);
        linear =(LinearLayout)findViewById(R.id.linear);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, titles);

        progressDialog = new ProgressDialog(this);

        webView.addJavascriptInterface(new JavaScriptMethod(), "myApp");

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        listView.setAdapter(adapter);

        animtop = AnimationUtils.loadAnimation(this, R.anim.translate_top);

        animtop.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                linear.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String url = data.get(i).getUrl();
                if(!url.contains("http://")) url = "http://" + url;
                webView.loadUrl(url);
                setVisible(0);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int position = i;
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                data.remove(position);
                                titles.remove(position);
                                adapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("취소", null)
                        .setMessage("삭제하시겠습니까?")
                        .show();

                return true;
            }
        });


        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressDialog.setMessage("Loading");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.show();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if(!url.equals("file:///android_asset/www/urladd.html")) et.setText(url);
            }
        });

        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if(newProgress >= 100) progressDialog.dismiss();
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,1, 0, "즐겨찾기추가");
        menu.add(0,2, 0, "즐겨찾기목록");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == 1){
            setVisible(0);
            webView.loadUrl("file:///android_asset/www/urladd.html");
            linear.startAnimation(animtop);
//            linear.setAnimation(animtop);
//            animtop.start();

        }else if(item.getItemId() == 2){
            linear.setVisibility(View.VISIBLE);
            setVisible(1);
        }
        return super.onOptionsItemSelected(item);
    }


    Handler handler = new Handler();

    class JavaScriptMethod{
        @JavascriptInterface
        public void addSite(String url, String title){
            for(int i = 0 ; i < data.size(); i++){
                if(data.get(i).getUrl().equals(url)){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            webView.loadUrl("javaScript:displayMsg()");
                        }
                    });
                    return;
                }
            }
            Sitedata temp = new Sitedata(url, title);
            data.add(temp);
            titles.add(temp.toString());
            adapter.notifyDataSetChanged();
        }
        @JavascriptInterface
        public void showUrl(){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    linear.setVisibility(View.VISIBLE);
                }
            });
        }
    }

    void setVisible(int i){
        if(i == 0){
            listView.setVisibility(View.GONE);
            webView.setVisibility(View.VISIBLE);
        }else{
            listView.setVisibility(View.VISIBLE);
            webView.setVisibility(View.GONE);
        }
    }

    public void onClick(View v){
        String url = (et.getText().toString());
        if(!url.contains("http://") && !url.contains("https://")) {
            url = "http://" + url;
        }
        webView.loadUrl(url);
    }
}