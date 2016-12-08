package com.example.androidsdemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.example.androidsdemo.R;

/**
 * Created by houqixin on 2016/12/8.
 * 按照指定宽高比例显示布局内容
 */
public class WitdhRatioHeightRelativeLayout extends RelativeLayout {
    private int widthHeightRatio = 11;

    public WitdhRatioHeightRelativeLayout(Context context) {
        this(context, null);
    }

    public void setWidthHeightRatio(int widthHeightRatio) {
        this.widthHeightRatio = widthHeightRatio;
        requestLayout();
    }

    public WitdhRatioHeightRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WitdhRatioHeightRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    /**
     * 有时候我们自定义布局，有些属性可能是根据具体的使用场景是变化的，尤其是我们自己自定义的属性，都需要在atrrs.xml中
     * 类似  <declare-styleable name="WitdhRatioHeightLayout">
                 <attr name="width_height_ratio" format="integer"></attr>//这个就是个属性的名字我们在布局里
                  使用的时候可以根据自己的情况设置自己想要的值
           </declare-styleable>
     布局中使用的时候要在跟布局中声明自定标签然后才能使用比如xmlns:qx="http://schemas.android.com/apk/res-auto"
     就像系统的xmlns:android="http://schemas.android.com/apk/res/android"标签一样
          本方法就是得到atrrs.xml--declare-styleable 中设置的值
     * @param attrs
     */
    private void init(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.WitdhRatioHeightLayout);
        widthHeightRatio = ta.getInt(R.styleable.WitdhRatioHeightLayout_width_height_ratio, 11);
        ta.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //根据所给的widthHeightRatio重新利用MeasureSpec重新构建新的widthMeasureSpec,heightMeasureSpec
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        String ratioStr = String.valueOf(widthHeightRatio);
        widthMeasureSpec = MeasureSpec.makeMeasureSpec(widthSize, MeasureSpec.EXACTLY);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(widthSize * ratioStr.charAt(0) / ratioStr.charAt(1), MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        /**
         * 如果自己不在最后加上super.onMeasure(widthMeasureSpec, heightMeasureSpec);
         * 就要自己调用下面这个方法，自己加上下面这个方法的好处是自己能自定义getMeasureSize的规则，如果有需要就要，
         * 他这个方法的作用就是设置实际大小。
         * setMeasuredDimension(getMeasureSize(widthMeasureSpec, true), getMeasureSize(heightMeasureSpec, false));
         */
    }

    /**
     * onMeasure传入的widthMeasureSpec和heightMeasureSpec不是一般的尺寸数值，而是将模式和尺寸组合在一起的数值。
     * 我们需要通过int mode = MeasureSpec.getMode(widthMeasureSpec)得到模式，
     * 用int size = MeasureSpec.getSize(widthMeasureSpec)得到尺寸。
     * <p/>
     * mode共有三种情况，取值分别为MeasureSpec.UNSPECIFIED, MeasureSpec.EXACTLY, MeasureSpec.AT_MOST。
     * <p/>
     * MeasureSpec.EXACTLY是精确尺寸，当我们将控件的layout_width或layout_height指定为具体数值时如andorid:layout_width="50dip"，
     * 或者为FILL_PARENT是，都是控件大小已经确定的情况，都是精确尺寸。
     * <p/>
     * MeasureSpec.AT_MOST是最大尺寸，当控件的layout_width或layout_height指定为WRAP_CONTENT时，
     * 控件大小一般随着控件的子空间或内容进行变化，此时控件尺寸只要不超过父控件允许的最大尺寸即可。
     * 因此，此时的mode是AT_MOST，size给出了父控件允许的最大尺寸。
     * <p/>
     * MeasureSpec.UNSPECIFIED是未指定尺寸，这种情况不多，一般都是父控件是AdapterView，通过measure方法传入的模式。
     */
    private int getMeasureSize(int measureSpec, boolean isWidth) {
        int realSize = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.EXACTLY:
                if (isWidth) {
                    realSize = getLeft() + getRight() + size + getPaddingLeft() + getPaddingBottom();
                } else {
                    realSize = getTop() + getBottom() + size + getPaddingTop() + getPaddingBottom();
                }
        }
        return realSize;
    }
}
