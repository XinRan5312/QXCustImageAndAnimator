package com.example.androidsdemo.viewpager.pageTransformer;

import android.support.v4.view.ViewPager.PageTransformer;
import android.view.View;

import com.nineoldandroids.view.ViewHelper;

/**
 * 左右折叠
 */
public class AccordionTransformer implements PageTransformer {
	/**
	 * position参数指明给定页面相对于屏幕中心的位置。它是一个动态属性，会随着页面的滚动而改变。当一个页面填充整个屏幕是，它的值是0，
	 * 当一个页面刚刚离开屏幕的右边时，它的值是1。当两个也页面分别滚动到一半时，其中一个页面的位置是-0.5，另一个页面的位置是0.5。基于屏幕上页面的位置
	 * ，通过使用诸如setAlpha()、setTranslationX()、或setScaleY()方法来设置页面的属性，来创建自定义的滑动动画。
	 */
	@Override
	public void transformPage(View view, float position) {
		if (position < -1) {
			ViewHelper.setPivotX(view, view.getMeasuredWidth() * 0.5f);
			ViewHelper.setPivotY(view, view.getMeasuredHeight() * 0.5f);
			ViewHelper.setScaleX(view, 1);
		} else if (position <= 0) {
			ViewHelper.setPivotX(view, view.getMeasuredWidth());
			ViewHelper.setPivotY(view, 0);
			ViewHelper.setScaleX(view, 1 + position);
		} else if (position <= 1) {
			ViewHelper.setPivotX(view, 0);
			ViewHelper.setPivotY(view, 0);
			ViewHelper.setScaleX(view, 1 - position);
		} else {
			ViewHelper.setPivotX(view, view.getMeasuredWidth() * 0.5f);
			ViewHelper.setPivotY(view, view.getMeasuredHeight() * 0.5f);
			ViewHelper.setScaleX(view, 1);
		}
	}
}
