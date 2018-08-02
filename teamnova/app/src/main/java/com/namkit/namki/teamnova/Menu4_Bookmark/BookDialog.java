package com.namkit.namki.teamnova.Menu4_Bookmark;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.namkit.namki.teamnova.R;

/**
 * Created by namki on 2018-03-27.
 */

public class BookDialog extends AppCompatDialogFragment {

    private EditText Mtitle;
    private EditText Murl;

    private BookmarkDialog listeners;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view2 = inflater.inflate(R.layout.book_custom_item, null);

        builder2.setView(view2)
                .setTitle("URL 스크랩")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        String userdesc = Mtitle.getText().toString();
                        String usertime = Murl.getText().toString();
                        //String usertimesec = edittimesec.getText().toString();
                        if(usertime.equals("")){
                            usertime="입력없음";
                        }else if(userdesc.equals("")){
                            userdesc = "입력없음";
                        }
                        listeners.applyTexts(userdesc, usertime);

                    }
                });
        Mtitle = view2.findViewById(R.id.ed_title);
        Murl = view2.findViewById(R.id.ed_url);

        return builder2.create();
    }

    public interface BookmarkDialog {
        void applyTexts(String title, String url);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listeners = (BookmarkDialog) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "반드시 임플리먼트 Book");
        }
    }

}
