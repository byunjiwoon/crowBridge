package com.byun91.crow_bridge.code.Widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by dwp on 2017. 3. 19..
 */

public class ResizingViewPager extends ViewPager {

    private View child;

    public ResizingViewPager(Context context) {
        super(context);
    }

    public ResizingViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = 0;
        if(child!=null){
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            int h = child.getMeasuredHeight();
            if(h > height) height = h;

            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setChildHeight(View v){
        child = v;
        this.requestLayout();
    }

    /** mwKim 170816 **/
    private OnDispatchTouchListener listener;
    private boolean isSwiping = true;

    public void setOnDispatchTouchListener(OnDispatchTouchListener listener) {
        this.listener = listener;
    }

    public interface OnDispatchTouchListener {
        public abstract void onTouch();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if(listener != null) listener.onTouch();
                break;
            case MotionEvent.ACTION_UP:
                if(listener != null) listener.onTouch();
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (this.isSwiping) {
            return super.onTouchEvent(event);
        }

        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (this.isSwiping) {
            return super.onInterceptTouchEvent(event);
        }

        return false;
    }

    public void setSwipingEnabled(boolean isSwiping) {
        this.isSwiping = isSwiping;
    }
}
