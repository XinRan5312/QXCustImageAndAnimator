package com.example.androidsdemo.viewpager.pageTransformer;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v4.view.ViewPager.PageTransformer;
import android.view.View;

/**
 * 默认效果
 */
public class DefaultTransformer implements PageTransformer {

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	public void transformPage(View view, float arg1) {
		view.setAlpha(1);
		view.setTranslationX(0);
		view.setTranslationY(0);
		view.setPivotX(view.getWidth() / 2);
		view.setPivotY(view.getHeight() / 2);
		view.setScaleX(1);
		view.setScaleY(1);
		view.setRotation(0);
	}

}
