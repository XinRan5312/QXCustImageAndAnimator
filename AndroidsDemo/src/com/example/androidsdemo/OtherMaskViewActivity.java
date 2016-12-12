package com.example.androidsdemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.androidsdemo.view.ZCDialogView;


/**
 * Created by houqixin on 2016/12/8.
 */
public class OtherMaskViewActivity extends Activity implements ZCDialogView.OnTimeOverListner{
    private AlertDialog mDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mask_view_other);
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            ZCDialogView view=new ZCDialogView(this);
            view.setOnTimeOverListner(this);
            mDialog = new AlertDialog.Builder(this)
                    .setTitle("提示")
                    .setCancelable(false)
                    .setView(view) ////
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    })
                    .create();
            mDialog.setCanceledOnTouchOutside(false);
            mDialog.show();
        }catch (Exception e){
           e.printStackTrace();
        }

    }

    @Override
    public void onTimeOver() {
        if(mDialog!=null&&mDialog.isShowing()){
            mDialog.dismiss();
        }
    }
}
