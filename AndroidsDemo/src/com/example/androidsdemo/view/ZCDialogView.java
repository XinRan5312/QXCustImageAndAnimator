package com.example.androidsdemo.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.androidsdemo.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by houqixin on 2016/12/9.
 */
public class ZCDialogView extends LinearLayout {
    private TextView timeView;
    private int count;
    private Timer timer;
    public ZCDialogView(Context context) {
        super(context);
        init(context);
    }

    public ZCDialogView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public ZCDialogView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ZCDialogView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        View.inflate(context, R.layout.activity_time_dialog, this);
        timeView = (TextView) findViewById(R.id.dialog_time);
        count=Integer.parseInt(timeView.getText().toString().trim());
        timer=new Timer();
        timer.schedule(task,1000,1000);

    }

    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if(msg.what>0) {
                timeView.setText(String.valueOf(count));
            }else{
                timer.cancel();
                if(onTimeOverListner!=null){
                    onTimeOverListner.onTimeOver();
                }
            }
        }
    };
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            count--;
            Message message = new Message();
            message.what = count;
            handler.sendMessage(message);
        }
    };
    private OnTimeOverListner onTimeOverListner;

    public void setOnTimeOverListner(OnTimeOverListner onTimeOverListner) {
        this.onTimeOverListner = onTimeOverListner;
    }

    public interface OnTimeOverListner{
        void onTimeOver();
    }
}

