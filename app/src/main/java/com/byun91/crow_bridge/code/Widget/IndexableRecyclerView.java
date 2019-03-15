package com.byun91.crow_bridge.code.Widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.si.simembers.Scroller.Indexer;
import com.si.simembers.Scroller.RecyclerViewIndexScroller;

public class IndexableRecyclerView extends RecyclerView {

    private   RecyclerViewIndexScroller mScroller;
    private boolean invisible;

    public IndexableRecyclerView(Context context) {
        super(context);
        init();
    }

    public IndexableRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public IndexableRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void init() {
        mScroller = new RecyclerViewIndexScroller(getContext(), this);
        addItemDecoration(itemDecoration);
    }

    public void setInvisibleIndexer(boolean invisibleIndexer) {
        this.invisible = invisibleIndexer;
        mScroller.setInvisible(invisibleIndexer);
    }

    public void setIndexTypeface(Typeface typeface){
        mScroller.setDefaultTypeface(typeface);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        return mScroller.onTouchEvent(e) || super.onTouchEvent(e);
    }

    @Override
    public boolean fling(int velocityX, int velocityY) {
        mScroller.show();
        return super.fling(velocityX, velocityY);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        return mScroller.contains(e.getX(), e.getY()) || super.onInterceptTouchEvent(e);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);

        if(adapter instanceof Indexer)
         mScroller.setAdapter(adapter);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mScroller.onSizeChanged(w, h, oldw, oldh);
        if(getChildCount() > 0 && !invisible) {
            if (h < oldh) {
                mScroller.setInvisible(true);
            } else {
                mScroller.setInvisible(false);
            }
        }
    }

    ItemDecoration itemDecoration = new ItemDecoration() {
        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, State state) {
            super.onDrawOver(c, parent, state);
            mScroller.draw(c);
        }
    };

    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);

        if (state == SCROLL_STATE_DRAGGING) {
          //  setInvisibleIndexer(false);
        }

    }




}
