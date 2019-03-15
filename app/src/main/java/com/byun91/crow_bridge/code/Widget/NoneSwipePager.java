package com.byun91.crow_bridge.code.Widget;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by dwp on 2017. 3. 19..
 */

public class NoneSwipePager extends ViewPager {
    private boolean enabled = false;

    public NoneSwipePager(Context context) {
        super(context);
    }

    public NoneSwipePager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (enabled) {
            return super.onInterceptTouchEvent(ev);
        }
        else {
            if (MotionEventCompat.getActionMasked(ev) == MotionEvent.ACTION_MOVE) {
            }
            else {
                if (super.onInterceptTouchEvent(ev)) {
                    super.onTouchEvent(ev);
                }
            }
            return false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (enabled) {
            return super.onTouchEvent(e);
        } else {
            return MotionEventCompat.getActionMasked(e) != MotionEvent.ACTION_MOVE && super.onTouchEvent(e);
        }
    }

    public void setPagingEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
