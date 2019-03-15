package com.byun91.crow_bridge.code.Widget;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by dwpark on 2017. 8. 12..
 */

public class TouchInterceptPager extends ViewPager {

    private static final int OFF_SET = 10;
    private float preX, preY;

    public TouchInterceptPager(Context context) {
        super(context);
    }

    public TouchInterceptPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        final int action = ev.getAction() & MotionEventCompat.ACTION_MASK;

        switch (action){
            case MotionEvent.ACTION_DOWN:
                preX = ev.getX();
                preY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float y = ev.getY();
                if((preY > y+5 || preY < y-5)){

                }else{
                    float x = ev.getX();
                    if((x - OFF_SET <= preX && preX <= x+ OFF_SET)){
                        return false;
                    }else{
                        return true;
                    }
                }
        }
        return super.onInterceptTouchEvent(ev);
    }
}
