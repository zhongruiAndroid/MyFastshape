package com.github.fastshape.inter;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2018/6/29.
 */

public interface OnDrawInter {
    void onRefreshPaint(Canvas canvas,int paddingLeft,int paddingTop,int paddingRight,int paddingBottom,int w, int h);
    void onSizeChanged(int paddingLeft,int paddingTop,int paddingRight,int paddingBottom,int w, int h, int oldw, int oldh);
    void dispatchDrawEnd(int saveLayer,Canvas canvas);
    boolean onTouchEvent(MotionEvent event);
}
