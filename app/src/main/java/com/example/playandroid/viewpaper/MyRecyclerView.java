package com.example.playandroid.viewpaper;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class MyRecyclerView extends RecyclerView {
    public MyRecyclerView(@NonNull Context context) {
        super(context);
    }

    public MyRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //告诉父控件不要拦截子控件的滑动


        float x = ev.getX();
        switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_DOWN://按下
                if (getParent() != null) {
                    getParent().requestDisallowInterceptTouchEvent(true);   //让事件不再分发
                }
                break;

            case MotionEvent.ACTION_MOVE://移动
                if (getParent() != null) {
                    getParent().requestDisallowInterceptTouchEvent(true);   //让事件不再分发
                }
                break;

            case MotionEvent.ACTION_CANCEL://非人工操作
                if (getParent() != null) {
                    getParent().requestDisallowInterceptTouchEvent(true);   //让事件不再分发
                }
                break;

            case MotionEvent.ACTION_UP://抬起
                if (getParent() != null) {
                    getParent().requestDisallowInterceptTouchEvent(true);   //让事件不再分发
                }
                break;
        }

        return true;
    }
}
