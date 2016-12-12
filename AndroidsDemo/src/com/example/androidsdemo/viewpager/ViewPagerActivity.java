package com.example.androidsdemo.viewpager;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;
import com.example.androidsdemo.R;

import java.lang.reflect.Field;

/**
 * Created by houqixin on 2016/12/12.
 */
public class ViewPagerActivity extends Activity {

    private ZCViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        viewPager = (ZCViewPager)findViewById(R.id.zc_custom_viewpager);
        /**
         * Standard
         * Tablet
         * CubeIn
         * CubeOut
         * FlipVertical
         * FlipHorizontal
         * Stack
         * ZoomIn
         * ZoomOut
         * RotateUp
         * RotateDown
         * Accordion
         * TransitionEffect effect = TransitionEffect.valueOf("Tablet");
         * setupJazziness(effect);
         */
        viewPager.setTransitionEffect(ZCViewPager.TransitionEffect.CubeIn);
        viewPager.setPageMargin(100);
        viewPager.setFadeEnabled(true);
        viewPager.setOutlineEnabled(true);
        viewPager.setOutlineColor(0xff0000ff);
        viewPager.setAdapter(new MyAdapter());
        viewPager.setOnPageChangeListener(null);
    }

    class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return 10;
        }
        @Override
        public boolean isViewFromObject(View view, Object obj) {
            if (view instanceof OutlineContainer) {
                return ((OutlineContainer) view).getChildAt(0) == obj;
            } else {
                return view == obj;
            }
        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            TextView tv = new TextView(getApplicationContext());
            tv.setText("Page " + (position + 1));
            tv.setTextColor(Color.parseColor("#000000"));
            tv.setTextSize(30);
            tv.setGravity(Gravity.CENTER);
            tv.setBackground(getWallpaper());
            container.addView(tv, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

            viewPager.setObjectForPosition(tv, position);
            return tv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object obj) {
            container.removeView(viewPager.findViewFromObject(position));
        }


    }
    private void setViewPagerAnimatorTime(int duration){
        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            MSFixedSpeedScroller scroller = new MSFixedSpeedScroller(viewPager.getContext(), new LinearInterpolator());
            field.set(viewPager, scroller);
            scroller.setmDuration(duration);
        } catch (Exception e) {

        }
    }
}
