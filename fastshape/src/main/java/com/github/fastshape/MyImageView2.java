package com.github.fastshape;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.github.fastshape.inter.CompleteInter;
import com.github.fastshape.newbean.ImageHelper;

/**
 * Created by Administrator on 2017/7/24.
 */

public class MyImageView2 extends AppCompatImageView  {
    private ImageHelper viewHelper;
    private Drawable mDrawable;
    private int mDrawableWidth;
    private int mDrawableHeight;
    private Matrix mDrawMatrix;
    private int mPaddingTop;
    private int mPaddingLeft;
    private int mPaddingRight;
    private int mPaddingBottom;
    private boolean mCropToPadding;
    private int mScrollX;
    private int mScrollY;
    private int mRight;
    private int mLeft;
    private int mBottom;
    private int mTop;

    public MyImageView2(Context context) {
        super(context);
        initHelper(null);
    }
    public MyImageView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        initHelper(attrs);
    }
    public MyImageView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initHelper(attrs);
    }
    private void initHelper(AttributeSet attrs ) {
        viewHelper = new ImageHelper(new CompleteInter() {
            @Override
            public void complete() {
                MyImageView2.this.complete();
            }
            @Override
            public void completeClip() {
                MyImageView2.this.complete();
            }
            @Override
            public void resetClip() {
                MyImageView2.this.complete();
            }
        });
        init(attrs );
    }

    public ImageHelper getViewHelper() {
        return viewHelper;
    }

    public void init(AttributeSet attrs ) {
        viewHelper.init(getContext(), attrs );
    }



    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            mCropToPadding = getCropToPadding();
        }

        if (viewHelper != null) {
            viewHelper.onSizeChanged();
            viewHelper.onRefreshPaint(getPaddingLeft(),
                    getPaddingTop(),
                    getPaddingRight(),
                    getPaddingBottom(), getWidth(), getHeight());
        }
    }
    @Override
    public void draw(Canvas canvas) {
        if (viewHelper != null) {
            canvas.save();
            canvas.clipPath(viewHelper.clipPath);
            super.draw(canvas);
            canvas.restore();
        } else {
            super.draw(canvas);
        }
    }
    @Override
    protected void onDraw(Canvas canvas) {
        if (viewHelper != null) {
            canvas.save();
            int saveLayerCount = canvas.saveLayer(new RectF(0, 0, canvas.getWidth(), canvas.getHeight()), null, Canvas.ALL_SAVE_FLAG);
        }
        super.onDraw(canvas);
        if (viewHelper != null) {
            viewHelper.dispatchDraw( canvas);
            canvas.restore();
        }
    }

    /* @Override
        protected void onDraw(Canvas canvas) {
            *//*if (viewHelper != null&& viewHelper.getClipSwitch()) {
//            saveLayerCount = canvas.saveLayer(new RectF(0, 0, canvas.getWidth(), canvas.getHeight()), null, Canvas.ALL_SAVE_FLAG);
        }
        super.onDraw(canvas);
        if (viewHelper != null&& viewHelper.getClipSwitch()) {
            viewHelper.dispatchDrawEnd(0, canvas);
        }*//*
//        super.onDraw(canvas);

        mDrawable = getDrawable();
        mDrawableWidth = mDrawable.getIntrinsicWidth();
        mDrawableHeight = mDrawable.getIntrinsicHeight();
        mDrawMatrix = getImageMatrix();
        mPaddingTop = getPaddingTop();
        mPaddingLeft = getPaddingLeft();
        mPaddingRight = getPaddingRight();
        mPaddingBottom = getPaddingBottom();


        mScrollX = getScrollX();
        mScrollY = getScrollY();
        mRight = getRight();
        mLeft = getLeft();
        mBottom = getBottom();
        mTop = getTop();

        if (mDrawable == null) {
            return;
        }
        if (mDrawableWidth == 0 || mDrawableHeight == 0) {
            return;
        }
        int saveCount = canvas.getSaveCount();
        if (mDrawMatrix == null && mPaddingTop == 0 && mPaddingLeft == 0) {
            final int saveLayerCount= canvas.saveLayer(new RectF(0, 0, canvas.getWidth(), canvas.getHeight()), null, Canvas.ALL_SAVE_FLAG);
            mDrawable.draw(canvas);
            if (viewHelper != null) {
                viewHelper.dispatchDraw(canvas);
            }
            canvas.restoreToCount(saveLayerCount);
        } else {
            if (mCropToPadding) {
                final int scrollX = mScrollX;
                final int scrollY = mScrollY;
                canvas.clipRect(scrollX + mPaddingLeft, scrollY + mPaddingTop,
                        scrollX + mRight - mLeft - mPaddingRight,
                        scrollY + mBottom - mTop - mPaddingBottom);
            }

            canvas.translate(mPaddingLeft, mPaddingTop);

            if (mDrawMatrix != null) {
                canvas.concat(mDrawMatrix);
            }
            final int saveLayerCount= canvas.saveLayer(new RectF(0, 0, canvas.getWidth(), canvas.getHeight()), null, Canvas.ALL_SAVE_FLAG);
            mDrawable.draw(canvas);

            if (viewHelper != null) {
                viewHelper.dispatchDraw(canvas);
            }
            canvas.restoreToCount(saveLayerCount);
        }
        canvas.restoreToCount(saveCount);

    }*/
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            if (viewHelper != null && viewHelper.isAreaClick()) {
                if (viewHelper.onTouchEvent(ev) == false) {//如果这个地方返回true会导致点击事件失效
                    return false;
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }


    /*******************************************clip*********************************************/
    public void complete() {
        if (viewHelper != null) {
            if(viewHelper.clipPaint==null){
                viewHelper.onSizeChanged();
            }
            viewHelper.onRefreshPaint(getPaddingLeft(),
                    getPaddingTop(),
                    getPaddingRight(),
                    getPaddingBottom(), getWidth(), getHeight());
            invalidate();
        }
    }
}
