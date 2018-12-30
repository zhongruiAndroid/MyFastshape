package com.github.fastshape.bean;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Shader;
import android.view.MotionEvent;

/***
 *   created by zhongrui on 2018/12/29
 */
public class ClipHelper {
    /*是否裁剪背景*/
    public boolean clipBg=true;
    /*裁剪成圆形*/
    public boolean clipIsCircle = false;
    /*裁剪之后的区域点击，true:只有剩余部分有点击事件，false:所有区域有点击事件*/
    public boolean clipIsAreaClick = true;
    /*裁剪时是否忽略padding,true:忽略，false：不忽略*/
    public boolean clipIgnorePadding = true;
    public float clipTopLeftRadius;
    public float clipTopRightRadius;
    public float clipBottomLeftRadius;
    public float clipBottomRightRadius;
    /*裁剪边框宽度*/
    public float clipBorderWidth;
    /*裁剪边框颜色*/
    public int clipBorderColor;
    /*裁剪虚线长度*/
    public float clipBorderDashWidth;
    /*裁剪虚线之间的间隔*/
    public float clipBorderDashGap;
    /*裁剪时虚线背景*/
    public int clipBorderDashBgColor;

    /*裁剪虚线起始点*/
    public int clipBorderPhase;


    public  Paint clipPaint;
    public  Paint clipBorderPaint;
    public  Paint clipBorderDashBgPaint;
    public  Paint clipClearPaint;

    public  Path clipPath;
    public  Path clipBorderPath;
    public  Region viewRegion;
    public  Region clickRegion;

    public  Shader shader;
    public PathEffect pathEffect;


    public void onSizeChanged() {
        clickRegion = new Region();

        clipPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        clipPaint.setStyle(Paint.Style.FILL);

        clipBorderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        clipBorderPaint.setStyle(Paint.Style.STROKE);

        clipBorderDashBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        clipBorderDashBgPaint.setStyle(Paint.Style.STROKE);

        clipClearPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        clipClearPaint.setStyle(Paint.Style.STROKE);
        clipClearPaint.setColor(Color.WHITE);


    }

    public void onRefreshPaint(int paddingLeft, int paddingTop, int paddingRight, int paddingBottom, int w, int h) {
        int left = paddingLeft;
        int top = paddingTop;
        int right = paddingRight;
        int bottom = paddingBottom;
        if (clipIgnorePadding) {
            left = 0;
            top = 0;
            right = 0;
            bottom = 0;
        }

        viewRegion = new Region(0, 0, w, h);

        clipPaint.setColor(Color.WHITE);
//        clipPaint.setFilterBitmap(false);
        clipPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));

        clipBorderPaint.setColor(clipBorderColor);
        clipBorderPaint.setStrokeWidth(clipBorderWidth * 2);

        clipBorderDashBgPaint.setColor(clipBorderDashBgColor);
        clipBorderDashBgPaint.setStrokeWidth(clipBorderWidth * 2);

        clipClearPaint.setStrokeWidth(clipBorderWidth * 2);


        if (shader != null) {
            clipBorderPaint.setShader(shader);
        }else{
            clipBorderPaint.setShader(null);
        }
        if (clipBorderDashWidth > 0 && clipBorderDashGap > 0) {
            pathEffect = new DashPathEffect(new float[]{clipBorderDashWidth, clipBorderDashGap}, clipBorderPhase);
            clipBorderPaint.setPathEffect(pathEffect);
        } else {
            clipBorderPaint.setPathEffect(null);
        }

        clipPath = new Path();
        clipBorderPath = new Path();
        float[] radius = new float[8];

        radius[0] = clipTopLeftRadius;
        radius[1] = clipTopLeftRadius;

        radius[2] = clipTopRightRadius;
        radius[3] = clipTopRightRadius;

        radius[4] = clipBottomRightRadius;
        radius[5] = clipBottomRightRadius;

        radius[6] = clipBottomLeftRadius;
        radius[7] = clipBottomLeftRadius;

        RectF rectF = new RectF(left, top, w - right, h - bottom);
        if (clipIsCircle) {
            int centerX = (w - left - right) / 2 + left;
            int centerY = (h - top - bottom) / 2 + top;

            int circleRadius = (w - left - right) > (h - top - bottom) ? (h - top - bottom) / 2 : (w - left - right) / 2;
            clipPath.addCircle(centerX, centerY, circleRadius, Path.Direction.CW);
            clipBorderPath.addCircle(centerX, centerY, circleRadius, Path.Direction.CW);
        } else {
            clipPath.addRoundRect(rectF, radius, Path.Direction.CW);
            clipBorderPath.addRoundRect(rectF, radius, Path.Direction.CW);
        }

        clickRegion.setPath(clipPath, viewRegion);
        clipPath.moveTo(0, 0);
        clipPath.moveTo(w, h);
    }

    public void dispatchDrawEnd(int saveLayerCount, Canvas canvas) {
        if (clipBorderWidth > 0) {

            /*如果边框有透明度或者是绘制虚线就执行以下代码*/
            if(Color.alpha(clipBorderColor)<255|| pathEffect !=null){
                /*绘制半透明边框作用*/
                clipClearPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
                canvas.drawPath(clipBorderPath, clipClearPaint);

                clipClearPaint.setXfermode(null);
            }

            /*绘制虚线背景*/
            if(clipBorderDashBgColor!=Color.TRANSPARENT&&pathEffect !=null){
                canvas.drawPath(clipBorderPath, clipBorderDashBgPaint);
            }
            clipBorderPaint.setColor(clipBorderColor);
            canvas.drawPath(clipBorderPath, clipBorderPaint);
        }
        //通过setXfermode裁剪出需要显示部分区域
        canvas.drawPath(clipPath, clipPaint);

        canvas.restoreToCount(saveLayerCount);
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (clipIsAreaClick) {
            if (!clickRegion.contains((int) event.getX(), (int) event.getY())) {
                return false;
            }
        }
        return true;
    }
}