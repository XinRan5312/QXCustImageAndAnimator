package com.example.androidsdemo;

import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by houqixin on 2016/12/15.
 */
public class ViewTreeObserverActivity extends QxBaseActivity implements View.OnClickListener,
        ViewTreeObserver.OnTouchModeChangeListener,
        ViewTreeObserver.OnGlobalLayoutListener,
        ViewTreeObserver.OnPreDrawListener,
        ViewTreeObserver.OnGlobalFocusChangeListener{

    private TextView tv_show ;
    private ViewTreeObserver vto ;
    private View all ;
    private EditText ed1 ;
    private EditText ed2 ;
    private TextView tv_display ;
    private Button button ;
    private boolean btnClicked ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super .onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tree_observer );
        tv_show = (TextView) this .findViewById(R.id. tv_show );
        all = this .findViewById(R.id. full_screen );  // 得到整个屏幕对象 ， 因为顶层 layout 的 width 和 height 都是 fill_parent

        vto =all .getViewTreeObserver(); // 通过 getViewTreeObserver 获得 ViewTreeObserver 对象，谁得到就观察谁
        tv_display = (TextView) this .findViewById(R.id. tv_display );
        ed1 = (EditText) this .findViewById(R.id. ed_enter1 );
        ed2 = (EditText) this .findViewById(R.id. ed_enter2 );
        button = (Button) this .findViewById(R.id. button );
        button .setOnClickListener( this );
        /**
         * 我们知道在oncreate中View.getWidth和View.getHeight无法获得一个view的高度和宽度，这是因为View组件布局要在onResume回调后完成。
         * 所以现在需要使用getViewTreeObserver().addOnGlobalLayoutListener()来获得宽度或者高度。这是获得一个view的宽度和高度的方法之一。

             OnGlobalLayoutListener 是ViewTreeObserver的内部类，当一个视图树的布局发生改变时，可以被ViewTreeObserver监听到，
             这是一个注册监听视图树的观察者(observer)，在视图树的全局事件改变时得到通知。ViewTreeObserver不能直接实例化，
             而是通过getViewTreeObserver()获得。
         */
        vto .addOnTouchModeChangeListener( this ); // 用于监听 Touch 和非 Touch 模式的转换
        vto .addOnGlobalFocusChangeListener(this );  // 用于监听焦点的变化
        vto .addOnPreDrawListener( this );       // 用于在屏幕上画 View 之前，要做什么额外的工作
        vto .addOnGlobalLayoutListener( this ); // 用于监听布局之类的变化，比如某个空间消失了,或者是动态得到某个布局的宽高

    }
    // onTouchModeChanged 是接口 ViewTreeObserver.OnTouchModeChangeListener
    // 中定义的方法。
    @Override
    public void onTouchModeChanged( boolean isInTouchMode) {
        if (isInTouchMode) tv_show .setText( "In touch mode" );

        else tv_show .setText( "Not in touch mode" );

    }
    // onGlobalLayout 是接口 ViewTreeObserver.OnGlobalLayoutListener
    // 中定义的方法。
    // Callback method to be invokedwhen the global layout state or the
    // visibility of views within the view treechanges
    @Override
    public void onGlobalLayout() {
        if ( btnClicked ) {
            if (! ed2 .isShown())
                ed1 .setText( " 第二个 EditText 不见了 " );
            else
                ed1 .setText( " 第二个 EditText 出来了 " );
        }
    }
    // onPreDraw 是接口 ViewTreeObserver.OnPreDrawListener
    // 中定义的方法。
    @Override
    public boolean onPreDraw() {
        // 在屏幕上画出 ed1 控件之间 ， 给它增加一个提示 ， 并改变其字体大小
        ed1 .setHint( " 在 onPreDraw 方法中增加一个提示信息 " );
        ed1 .setTextSize(( float ) 20.0);
        //return false;   // Return true to proceed with the current drawing pass, or falseto cancel.
        return true ;       // 如果此处不返回 true ， 则整个界面不能完整显示。

    }
    // onGlobalFocusChanged 是接口 ViewTreeObserver.OnGlobalFocusChangeListener
    // 中定义的方法。
    // 焦点发生变化时，会触发这个方法的执行
    @Override
    public void onGlobalFocusChanged(View oldFocus, View newFocus) {
        if (oldFocus != null && newFocus!= null ) {
            tv_display .setText( "Focus /nFROM:/t" + oldFocus.toString() + "/n  TO:/t" + newFocus.toString());
        }
    }
    @Override
    public void onClick(View v) {
        // 改变 ed2 的可见性 ， 会触发 onGlobalLayout 方法的执行
        btnClicked = true ;
        if (v.getId() == R.id. button ) {
            if ( ed2 .isShown())
                ed2 .setVisibility(View. INVISIBLE );
            else
                ed2 .setVisibility(View. VISIBLE );
        }
    }
}