package com.example.androidsdemo;

import android.os.Bundle;
import android.widget.Toast;

import com.example.androidsdemo.view.ZCPayPasswordView;

/**
 * Created by houqixin on 2016/12/20.
 */
public class PwdViewActivity extends QxBaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwd_view);
        final ZCPayPasswordView payPwdEditText= (ZCPayPasswordView) findViewById(R.id.pwd_view);
//        payPwdEditText.initStyle(R.drawable.edit_num_bg, 6, 0.33f, R.color.pwd_text, R.color.pwd_text, 20);
        payPwdEditText.setShowPwd(true);
        payPwdEditText.setOnTextFinishListener(new ZCPayPasswordView.OnTextFinishListener() {
            @Override
            public void onFinish(String str) {
                Toast.makeText(PwdViewActivity.this, str, Toast.LENGTH_SHORT).show();
//                payPwdEditText.setShowPwd(true);
//                payPwdEditText.clearText();
            }
        });

    }
}
