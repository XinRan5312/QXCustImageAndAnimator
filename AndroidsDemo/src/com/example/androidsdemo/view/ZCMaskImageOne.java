package com.example.androidsdemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.example.androidsdemo.R;

/**
 * Created by houqixin on 2016/12/8.
 * 带Mask的图片
 */
public class ZCMaskImageOne extends ImageView {
    private int mImageSource = 0;
    private int mMaskSource = 0;
    private RuntimeException mException;

    public ZCMaskImageOne(Context context) {
        this(context, null);
    }

    public ZCMaskImageOne(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ZCMaskImageOne(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.MaskImageOne, 0, 0);
        mImageSource = a.getResourceId(R.styleable.MaskImageOne_src_resource_id, 0);
        mMaskSource = a.getResourceId(R.styleable.MaskImageOne_mask_resource_id, 0);

        if (mImageSource == 0 || mMaskSource == 0) {
            mException = new IllegalArgumentException(a.getPositionDescription() +
                    ": The content attribute is required and must refer to a valid image.");
        }

        if (mException != null)
            throw mException;
        /**
         * 主要代码实现
         */
        //获取图片的资源文件
        Bitmap original = BitmapFactory.decodeResource(getResources(), mImageSource);
        //获取遮罩层图片
        Bitmap mask = BitmapFactory.decodeResource(getResources(), mMaskSource);
        Bitmap result = Bitmap.createBitmap(mask.getWidth(), mask.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas mCanvas = new Canvas(result);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP));//叠加重复的部分，显示下面的
        mCanvas.drawBitmap(original, 0, 0, null);//在画布上画原图
        mCanvas.drawBitmap(mask, 0, 0, paint);//在画布上画mask
        paint.setXfermode(null);
        setImageBitmap(result);
        setScaleType(ScaleType.CENTER);
        a.recycle();
    }
}
