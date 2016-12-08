package com.example.androidsdemo.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.androidsdemo.R;

/**
 * Created by houqx on 2016/12/5.
 * 一个带CheckAble的LinearLayout布局，一荣俱荣，一损俱损，哈哈
 */

public class ZCCanChoiceView extends LinearLayout implements Checkable {
    private TextView mTextView;
    private RadioButton mRadioButton;

    public ZCCanChoiceView(Context context) {
        super(context);
        View.inflate(context, R.layout.item_risk_test_choice, this);
        mTextView = (TextView) findViewById(R.id.text);
        mRadioButton = (RadioButton) findViewById(R.id.checkedView);
        mRadioButton.setButtonDrawable(R.drawable.selector_risk_choices_icon);
    }

    public ZCCanChoiceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public ZCCanChoiceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ZCCanChoiceView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setText(String text) {
        mTextView.setText(text);
    }

    @Override
    public boolean isChecked() {
        return mRadioButton.isChecked();
    }

    @Override
    public void setChecked(boolean checked) {
        mRadioButton.setChecked(checked);
        if (checked) {
            setBackgroundResource(R.drawable.shape_risk_item_bg_checked_true);
        } else {
            setBackgroundResource(R.drawable.selector_risk_test_item_bg);
        }
    }

    @Override
    public void toggle() {
        mRadioButton.toggle();
    }
}
