package com.example.androidsdemo;

import android.os.Bundle;

import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by houqixin on 2016/12/13.
 */
public class QxBaseActivity extends SwipeBackActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSwipeBackEnable(true);
        overridePendingTransition(R.anim.left_in,R.anim.right_out);
    }

    @Override
    public void finish() {
        super.finish();

    }
}
