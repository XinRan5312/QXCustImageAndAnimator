package com.example.androidsdemo;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by houqixin on 2016/12/8.
 * 第一个参数view顾名思义就是我们需要滑动的那个View。第二个参数position，这个float类型的参数并不是我们平常所理解的位置，
 * 而是表示滑动的View的一种状态，比如：当滑动到正全屏时，position是0，而向左滑动，使得右边刚好有一部被进入屏幕时，
 * position是1，如果前一页和下一页基本各在屏幕占一半时，前一页的position是-0.5，后一页的posiotn是0.5，
 * 所以根据position的值我们就可以自行设置需要的alpha，ScaleX/Y,translationX/Y等参数值。
 */
public class ViewPagerAnimator implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.75f;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void transformPage(View view, float position) {
        //有了View动画怎么设置都可以，下面只是一个例子，可以设置各种属性动画
        int pageWidth = view.getWidth();
        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.setAlpha(0);
        } else if (position <= 0) { // [-1,0]
            // Use the default slide transition when
            // moving to the left page
            view.setAlpha(1);
            view.setTranslationX(0);
            view.setScaleX(1);
            view.setScaleY(1);
        } else if (position <= 1) { // (0,1]
            // Fade the page out.
            view.setAlpha(1 - position);
            // Counteract the default slide transition
            view.setTranslationX(pageWidth * -position);
            // Scale the page down (between MIN_SCALE and 1)
            float scaleFactor = MIN_SCALE + (1 - MIN_SCALE)
                    * (1 - Math.abs(position));
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);
        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setAlpha(0);

        }
    }
}
