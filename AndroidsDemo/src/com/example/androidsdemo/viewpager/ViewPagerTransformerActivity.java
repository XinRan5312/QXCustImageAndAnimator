package com.example.androidsdemo.viewpager;

import android.app.Activity;

import android.os.Bundle;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;


import com.example.androidsdemo.R;
import com.example.androidsdemo.viewpager.pageTransformer.AccordionTransformer;
import com.example.androidsdemo.viewpager.pageTransformer.CubeTransformer;
import com.example.androidsdemo.viewpager.pageTransformer.DefaultTransformer;
import com.example.androidsdemo.viewpager.pageTransformer.DepthPageTransformer;
import com.example.androidsdemo.viewpager.pageTransformer.InRightDownTransformer;
import com.example.androidsdemo.viewpager.pageTransformer.InRightUpTransformer;
import com.example.androidsdemo.viewpager.pageTransformer.RotateTransformer;
import com.example.androidsdemo.viewpager.pageTransformer.ZoomOutPageTransformer;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by houqixin on 2016/12/12.
 */
public class ViewPagerTransformerActivity extends Activity {

    ViewPager vPage = null;
    MyAdapter mAdapter = null;
    List<ImageView> mData = new ArrayList<ImageView>();
    private ArrayAdapter<String> adapter = null;
    int[] ids = new int[]{R.drawable.icon_1, R.drawable.icon_2,
            R.drawable.icon_3, R.drawable.icon_4, R.drawable.icon_5,
            R.drawable.icon_6, R.drawable.icon_7, R.drawable.icon_8,
            R.drawable.icon_9, R.drawable.icon_10,};
    String[] effectType = {"默认", "深入浅出", "立方体", "旋转", "左右折叠", "右上角进入", "右下角进入", "淡入淡出"};

    Spinner mSpinner;
    ListView g = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_transformer);

        mSpinner = (Spinner) findViewById(R.id.spinner);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, effectType);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(ISelectedListener);

        vPage = (ViewPager) findViewById(R.id.main_page);
        resetView();
    }

    private void getData(List<ImageView> data) {
        data.clear();
        for (int i = 0; i < ids.length; i++) {
            ImageView image = new ImageView(this);
            image.setImageResource(ids[i]);
            data.add(image);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    AdapterView.OnItemSelectedListener ISelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view,
                                   int position, long id) {
            resetView();
            switch (position) {
                case 0:
                    vPage.setPageTransformer(true, new DefaultTransformer());//	"默认"
                    break;
                case 1:
                    vPage.setPageTransformer(true, new DepthPageTransformer());//"深入浅出"
                    break;
                case 2:
                    vPage.setPageTransformer(true, new CubeTransformer());//"立方体"
                    break;
                case 3:
                    vPage.setPageTransformer(true, new RotateTransformer());//"旋转"
                    break;
                case 4:
                    vPage.setPageTransformer(true, new AccordionTransformer());//"左右折叠"
                    break;
                case 5:
                    vPage.setPageTransformer(true, new InRightUpTransformer());//"右上角进入"
                    break;
                case 6:
                    vPage.setPageTransformer(true, new InRightDownTransformer());//"右下角进入"
                    break;
                case 7:
                    vPage.setPageTransformer(true, new ZoomOutPageTransformer());//"淡入淡出"
                    break;
                default:
                    break;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }

    };

    private void resetView() {
        vPage.removeAllViews();
        getData(mData);
        mAdapter = new MyAdapter(mData, vPage);
        vPage.setAdapter(mAdapter);
        vPage.setCurrentItem(mData.size() / 2);
        vPage.setPageTransformer(true, new DefaultTransformer());
    }

    public class MyAdapter extends PagerAdapter {

        List<ImageView> mList = null;

        ViewPager vPage = null;

        MyAdapter(List<ImageView> list, ViewPager page) {
            mList = list;
            vPage = page;
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = mList.get(position);
            view.setId(position);
            container.addView(view);
            return view;
        }

    }
}
