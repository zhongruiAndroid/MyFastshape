package com.github.fastshape.viewhelper;

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

import com.github.fastshape.inter.ClipInter;
import com.github.fastshape.inter.CompleteInter;

/***
 *   created by zhongrui on 2018/12/29
 */
public class ClipHelper implements ClipInter<ClipHelper> {
    protected CompleteInter completeInter;
    /*是否启用裁剪*/
    protected boolean clipSwitch;
    /*是否裁剪背景*/
    protected boolean clipBg = true;
    /*裁剪成圆形*/
    protected boolean clipIsCircle = false;
    /*裁剪之后的区域点击，true:只有剩余部分有点击事件，false:所有区域有点击事件*/
    protected boolean clipIsAreaClick = true;
    /*裁剪时是否忽略padding,true:忽略，false：不忽略*/
    protected boolean clipIgnorePadding = true;
    protected float clipTopLeftRadius;
    protected float clipTopRightRadius;
    protected float clipBottomLeftRadius;
    protected float clipBottomRightRadius;
    /*裁剪边框宽度*/
    protected float clipBorderWidth;
    /*裁剪边框颜色*/
    protected int clipBorderColor;
    /*裁剪虚线长度*/
    protected float clipBorderDashWidth;
    /*裁剪虚线之间的间隔*/
    protected float clipBorderDashGap;
    /*裁剪时虚线背景*/
    protected int clipBorderDashBgColor;

    /*裁剪虚线起始点*/
    protected int clipBorderPhase;


    public Paint clipPaint;
    protected Paint clipBorderPaint;
    protected Paint clipBorderDashBgPaint;
    protected Paint clipClearPaint;

    public Path clipPath;
    private Path tempPath;
    protected Path clipBorderPath;
    protected Region viewRegion;
    protected Region clickRegion;

    protected Shader shader;
    protected PathEffect pathEffect;


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
        clipPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));

        clipBorderPaint.setColor(clipBorderColor);
        clipBorderPaint.setStrokeWidth(clipBorderWidth * 2);

        clipBorderDashBgPaint.setColor(clipBorderDashBgColor);
        clipBorderDashBgPaint.setStrokeWidth(clipBorderWidth * 2);

        clipClearPaint.setStrokeWidth(clipBorderWidth * 2);


        if (shader != null) {
            clipBorderPaint.setShader(shader);
        } else {
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

        if(tempPath==null){
            tempPath = new Path();
        }else{
            tempPath.reset();
        }
        tempPath.addRect(new RectF(0,0,w,h),Path.Direction.CW);
        clipPath.op(tempPath, Path.Op.XOR);
    }

    /*裁剪背景*/
    public void clipBg( Canvas canvas) {
        //通过setXfermode裁剪出需要显示部分区域
        canvas.drawPath(clipPath, clipPaint);
    }
    /*裁剪内容*/
    public void dispatchDrawEnd(int saveLayerCount, Canvas canvas) {
        if (clipBorderWidth > 0) {

            /*如果边框有透明度或者是绘制虚线就执行以下代码*/
            if (Color.alpha(clipBorderColor) < 255 || pathEffect != null) {
                /*绘制半透明边框作用*/
                clipClearPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
                canvas.drawPath(clipBorderPath, clipClearPaint);

                clipClearPaint.setXfermode(null);
            }

            /*绘制虚线背景*/
            if (clipBorderDashBgColor != Color.TRANSPARENT && pathEffect != null) {
                canvas.drawPath(clipBorderPath, clipBorderDashBgPaint);
            }
            clipBorderPaint.setColor(clipBorderColor);
            canvas.drawPath(clipBorderPath, clipBorderPaint);
        }
        //通过setXfermode裁剪出需要显示部分区域
        canvas.drawPath(clipPath, clipPaint);
        if (saveLayerCount > 0) {
            canvas.restoreToCount(saveLayerCount);
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (clipIsAreaClick) {
            if (!clickRegion.contains((int) event.getX(), (int) event.getY())) {
                return false;
            }
        }
        return true;
    }


    public boolean isClipBg() {
        return clipBg;
    }

    public ClipHelper setClipBg(boolean clipBg) {
        this.clipBg = clipBg;
        return this;
    }

    public boolean getClipSwitch() {
        return clipSwitch;
    }

    public ClipHelper setClipSwitch(boolean clipSwitch) {
        this.clipSwitch = clipSwitch;
        return this;
    }

    public ClipHelper clearClipAttr() {
        clipBg = true;
        clipIsCircle = false;
        clipIsAreaClick = true;
        clipIgnorePadding = true;
        clipTopLeftRadius = 0;
        clipTopRightRadius = 0;
        clipBottomLeftRadius = 0;
        clipBottomRightRadius = 0;
        clipBorderWidth = 0;
        clipBorderColor = Color.TRANSPARENT;
        clipBorderDashWidth = 0;
        clipBorderDashGap = 0;
        clipBorderDashBgColor = Color.TRANSPARENT;
        clipBorderPhase=0;
        return this;
    }

    public boolean getClipIsCircle() {
        return clipIsCircle;
    }

    public ClipHelper setClipIsCircle(boolean clipIsCircle) {
        this.clipIsCircle = clipIsCircle;
        return this;
    }

    public boolean getClipIsAreaClick() {
        return clipIsAreaClick;
    }

    public ClipHelper setClipIsAreaClick(boolean clipIsAreaClick) {
        this.clipIsAreaClick = clipIsAreaClick;
        return this;
    }

    public boolean isClipIgnorePadding() {
        return clipIgnorePadding;
    }

    public ClipHelper setClipIgnorePadding(boolean clipIgnorePadding) {
        this.clipIgnorePadding = clipIgnorePadding;
        return this;
    }

    public float getClipTopLeftRadius() {
        return clipTopLeftRadius;
    }

    public ClipHelper setClipTopLeftRadius(float clipTopLeftRadius) {
        this.clipTopLeftRadius = clipTopLeftRadius;
        return this;
    }

    public float getClipTopRightRadius() {
        return clipTopRightRadius;
    }

    public ClipHelper setClipTopRightRadius(float clipTopRightRadius) {
        this.clipTopRightRadius = clipTopRightRadius;
        return this;
    }

    public float getClipBottomLeftRadius() {
        return clipBottomLeftRadius;
    }

    public ClipHelper setClipBottomLeftRadius(float clipBottomLeftRadius) {
        this.clipBottomLeftRadius = clipBottomLeftRadius;
        return this;
    }

    public float getClipBottomRightRadius() {
        return clipBottomRightRadius;
    }

    public ClipHelper setClipBottomRightRadius(float clipBottomRightRadius) {
        this.clipBottomRightRadius = clipBottomRightRadius;
        return this;
    }

    public float getClipBorderWidth() {
        return clipBorderWidth;
    }

    public ClipHelper setClipBorderWidth(float clipBorderWidth) {
        this.clipBorderWidth = clipBorderWidth;
        return this;
    }

    public int getClipBorderColor() {
        return clipBorderColor;
    }

    public ClipHelper setClipBorderColor(int clipBorderColor) {
        this.clipBorderColor = clipBorderColor;
        return this;
    }

    public float getClipBorderDashWidth() {
        return clipBorderDashWidth;
    }

    public ClipHelper setClipBorderDashWidth(float clipBorderDashWidth) {
        this.clipBorderDashWidth = clipBorderDashWidth;
        return this;
    }

    public float getClipBorderDashGap() {
        return clipBorderDashGap;
    }

    public ClipHelper setClipBorderDashGap(float clipBorderDashGap) {
        this.clipBorderDashGap = clipBorderDashGap;
        return this;
    }

    public int getClipBorderDashBgColor() {
        return clipBorderDashBgColor;
    }

    public ClipHelper setClipBorderDashBgColor(int clipBorderDashBgColor) {
        this.clipBorderDashBgColor = clipBorderDashBgColor;
        return this;
    }

    public int getClipBorderPhase() {
        return clipBorderPhase;
    }

    public ClipHelper setClipBorderPhase(int clipBorderPhase) {
        this.clipBorderPhase = clipBorderPhase;
        return this;
    }

    public Shader getShader() {
        return shader;
    }

    public ClipHelper setShader(Shader shader) {
        this.shader = shader;
        return this;
    }

    public PathEffect getPathEffect() {
        return pathEffect;
    }

    public ClipHelper setPathEffect(PathEffect pathEffect) {
        this.pathEffect = pathEffect;
        return this;
    }

    public void completeClip() {
        if (completeInter != null) {
            completeInter.completeClip();
        }
    }

    public void resetClip() {
        if (completeInter != null) {
            completeInter.resetClip();
        }
    }
}
