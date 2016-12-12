package com.example.androidsdemo.viewpager.pageTransformer;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v4.view.ViewPager.PageTransformer;
import android.view.View;

/**
 * "右下角进入"
 */
public class InRightDownTransformer implements PageTransformer {
	/**
	 * position参数指明给定页面相对于屏幕中心的位置。它是一个动态属性，会随着页面的滚动而改变。当一个页面填充整个屏幕是，它的值是0，
	 * 当一个页面刚刚离开屏幕的右边时，它的值是1。当两个也页面分别滚动到一半时，其中一个页面的位置是-0.5，另一个页面的位置是0.5。基于屏幕上页面的位置
	 * ，通过使用诸如setAlpha()、setTranslationX()、或setScaleY()方法来设置页面的属性，来创建自定义的滑动动画。
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	public void transformPage(View view, float position) {
		int pageHeight = view.getHeight();
		if (position < -1) {
			view.setAlpha(1);
			view.setTranslationY(0);
		} else if (position <= 0) {
			view.setTranslationY(pageHeight * position);
			view.setAlpha(1 + position);

		} else if (position <= 1) {
			view.setTranslationY(view.getHeight() * position);
			view.setAlpha(1 - position);
		} else {
			view.setTranslationY(0);
			view.setAlpha(1);
		}
	}

}
