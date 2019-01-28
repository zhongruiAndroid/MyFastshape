package com.github.fastshape.newbean;

import android.content.Context;
import android.content.res.TypedArray;
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
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.IntDef;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.github.fastshape.R;
import com.github.fastshape.bean.ClipHelper;
import com.github.fastshape.inter.CompleteInter;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.github.fastshape.newbean.SetBackgroundUtil.getTransparentColor;

/***
 *   created by zhongrui on 2018/12/31
 *
 *   MyRelativeLayout，MyLinearLayout，MyFrameLayout，MyTextView，MyEditText，MyButton公共属性
 */
public class ImageHelper {
    ClipHelper te;
    /*是否裁剪成圆形*/
    protected boolean isCircle;
    /*是否只有剩下的部分具有点击事件*/
    protected boolean isAreaClick = true;
    protected boolean ignorePadding = true;

    /*边框宽度*/
    protected float borderWidth;
    /*边框颜色*/
    protected int borderColor;
    /*边框虚线长度*/
    protected float borderDashWidth;
    /*边框虚线间隔距离*/
    protected float borderDashGap;
    protected int borderDashBgColor = Color.TRANSPARENT;
    protected int borderPhase;

    /*左上方圆角*/
    protected float topLeftRadius;
    /*右上方圆角*/
    protected float topRightRadius;
    /*左下方圆角*/
    protected float bottomLeftRadius;
    /*右下方圆角*/
    protected float bottomRightRadius;


    protected CompleteInter completeInter;


    /*******************裁剪*********************/

    public Paint clipPaint;
    protected  Paint clipBorderPaint;
    protected  Paint clipBorderDashBgPaint;
    protected  Paint clipClearPaint;

    public Path clipPath;
    protected  Path clipBorderPath;
    protected Region viewRegion;
    protected  Region clickRegion;

    protected Shader shader;
    protected PathEffect pathEffect;

    public void complete() {
        if(completeInter!=null){
            completeInter.complete();
        }
    }

    public ImageHelper(CompleteInter completeInter) {
        this.completeInter = completeInter;
    }

    public void init(Context context, AttributeSet attrs) {
        TypedArray viewNormal = context.obtainStyledAttributes(attrs, R.styleable.MyImageView, R.attr.fastshapeStyle, 0);

        isCircle = viewNormal.getBoolean(R.styleable.MyImageView_isCircle, false);
        isAreaClick = viewNormal.getBoolean(R.styleable.MyImageView_isAreaClick, true);
        ignorePadding = viewNormal.getBoolean(R.styleable.MyImageView_ignorePadding, true);
        borderDashBgColor = viewNormal.getColor(R.styleable.MyImageView_borderDashBgColor, Color.TRANSPARENT);
        borderPhase = viewNormal.getInt(R.styleable.MyImageView_borderPhase, 0);

        borderWidth = viewNormal.getDimension(R.styleable.FastShapeAttr_borderWidth, 0);
        borderColor = viewNormal.getColor(R.styleable.FastShapeAttr_borderColor, Color.TRANSPARENT);
        borderDashWidth = viewNormal.getDimension(R.styleable.FastShapeAttr_borderDashWidth, 0);
        borderDashGap = viewNormal.getDimension(R.styleable.FastShapeAttr_borderDashGap, 0);


        float radius = viewNormal.getDimension(R.styleable.FastShapeAttr_radius, 0);
        if (radius > 0) {
            topLeftRadius = radius;
            topRightRadius = radius;
            bottomLeftRadius = radius;
            bottomRightRadius = radius;
        } else {
            topLeftRadius = viewNormal.getDimension(R.styleable.FastShapeAttr_topLeftRadius, 0);
            topRightRadius = viewNormal.getDimension(R.styleable.FastShapeAttr_topRightRadius, 0);
            bottomLeftRadius = viewNormal.getDimension(R.styleable.FastShapeAttr_bottomLeftRadius, 0);
            bottomRightRadius = viewNormal.getDimension(R.styleable.FastShapeAttr_bottomRightRadius, 0);
        }

        viewNormal.recycle();
    }

    public void onRefreshPaint(int paddingLeft, int paddingTop, int paddingRight, int paddingBottom, int w, int h) {
        int left = paddingLeft;
        int top = paddingTop;
        int right = paddingRight;
        int bottom = paddingBottom;
        if (ignorePadding) {
            left = 0;
            top = 0;
            right = 0;
            bottom = 0;
        }

        viewRegion = new Region(0, 0, w, h);

        clipPaint.setColor(Color.WHITE);
//        clipPaint.setFilterBitmap(false);
        clipPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));

        clipBorderPaint.setColor(borderColor);
        clipBorderPaint.setStrokeWidth(borderWidth * 2);

        clipBorderDashBgPaint.setColor(borderDashBgColor);
        clipBorderDashBgPaint.setStrokeWidth(borderWidth * 2);

        clipClearPaint.setStrokeWidth(borderWidth * 2);


        if (shader != null) {
            clipBorderPaint.setShader(shader);
        }else{
            clipBorderPaint.setShader(null);
        }
        if (borderDashWidth > 0 && borderDashGap > 0) {
            pathEffect = new DashPathEffect(new float[]{borderDashWidth, borderDashGap},borderPhase);
            clipBorderPaint.setPathEffect(pathEffect);
        } else {
            clipBorderPaint.setPathEffect(null);
        }

        clipPath = new Path();
        clipBorderPath = new Path();
        float[] radius = new float[8];

        radius[0] = topLeftRadius;
        radius[1] = topLeftRadius;

        radius[2] = topRightRadius;
        radius[3] = topRightRadius;

        radius[4] = bottomRightRadius;
        radius[5] = bottomRightRadius;

        radius[6] = bottomLeftRadius;
        radius[7] = bottomLeftRadius;

        RectF rectF = new RectF(left, top, w - right, h - bottom);
        if (isCircle) {
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

    public void dispatchDraw(Canvas canvas) {
        if (borderWidth > 0) {

            /*如果边框有透明度或者是绘制虚线就执行以下代码*/
            if(Color.alpha(borderColor)<255|| pathEffect !=null){
                /*绘制半透明边框作用*/
                clipClearPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
                canvas.drawPath(clipBorderPath, clipClearPaint);

                clipClearPaint.setXfermode(null);
            }

            /*绘制虚线背景*/
            if(borderDashBgColor!=Color.TRANSPARENT&&pathEffect !=null){
                canvas.drawPath(clipBorderPath, clipBorderDashBgPaint);
            }
            clipBorderPaint.setColor(borderColor);
            canvas.drawPath(clipBorderPath, clipBorderPaint);
        }
        //通过setXfermode裁剪出需要显示部分区域
        canvas.drawPath(clipPath, clipPaint);

    }

    public boolean onTouchEvent(MotionEvent event) {
        if (isAreaClick) {
            if (!clickRegion.contains((int) event.getX(), (int) event.getY())) {
                return false;
            }
        }
        return true;
    }


    public ImageHelper clearAttr() {
        this.isCircle = false;
        this.isAreaClick = true;
        this.ignorePadding = true;
        this.borderWidth = 0;
        this.borderColor = getTransparentColor();
        this.borderDashWidth = 0;
        this.borderDashGap = 0;
        this.topLeftRadius = 0;
        this.topRightRadius = 0;
        this.bottomLeftRadius = 0;
        this.bottomRightRadius = 0;
        this.borderDashBgColor = getTransparentColor();
        this.borderPhase = 0;
        return this;
    }



    public float getBorderWidth() {
        return borderWidth;
    }

    public ImageHelper setBorderWidth(float borderWidth) {
        this.borderWidth = borderWidth;
        return this;
    }

    public int getBorderColor() {
        return borderColor;
    }

    public ImageHelper setBorderColor(int borderColor) {
        this.borderColor = borderColor;
        return this;
    }

    public float getBorderDashWidth() {
        return borderDashWidth;
    }

    public ImageHelper setBorderDashWidth(float borderDashWidth) {
        this.borderDashWidth = borderDashWidth;
        return this;
    }

    public float getBorderDashGap() {
        return borderDashGap;
    }

    public ImageHelper setBorderDashGap(float borderDashGap) {
        this.borderDashGap = borderDashGap;
        return this;
    }


    public ImageHelper setRadius(float topLeftRadius, float topRightRadius, float bottomRightRadius, float bottomLeftRadius) {
        setTopLeftRadius(topLeftRadius);
        setTopRightRadius(topRightRadius);
        setBottomRightRadius(bottomRightRadius);
        setBottomLeftRadius(bottomLeftRadius);
        return this;
    }

    public ImageHelper setRadius(float radius) {
        return setRadius(radius, radius, radius, radius);
    }

    public float getTopLeftRadius() {
        return topLeftRadius;
    }

    public ImageHelper setTopLeftRadius(float topLeftRadius) {
        this.topLeftRadius = topLeftRadius;
        return this;
    }

    public float getTopRightRadius() {
        return topRightRadius;
    }

    public ImageHelper setTopRightRadius(float topRightRadius) {
        this.topRightRadius = topRightRadius;
        return this;
    }

    public float getBottomLeftRadius() {
        return bottomLeftRadius;
    }

    public ImageHelper setBottomLeftRadius(float bottomLeftRadius) {
        this.bottomLeftRadius = bottomLeftRadius;
        return this;
    }

    public float getBottomRightRadius() {
        return bottomRightRadius;
    }

    public ImageHelper setBottomRightRadius(float bottomRightRadius) {
        this.bottomRightRadius = bottomRightRadius;
        return this;
    }


    public boolean isCircle() {
        return isCircle;
    }

    public ImageHelper setCircle(boolean circle) {
        isCircle = circle;
        return this;
    }

    public boolean isAreaClick() {
        return isAreaClick;
    }

    public ImageHelper setAreaClick(boolean areaClick) {
        isAreaClick = areaClick;
        return this;
    }

    public boolean isIgnorePadding() {
        return ignorePadding;
    }

    public ImageHelper setIgnorePadding(boolean ignorePadding) {
        this.ignorePadding = ignorePadding;
        return this;
    }

    public int getBorderDashBgColor() {
        return borderDashBgColor;
    }

    public ImageHelper setBorderDashBgColor(int borderDashBgColor) {
        this.borderDashBgColor = borderDashBgColor;
        return this;
    }

    public int getBorderPhase() {
        return borderPhase;
    }

    public ImageHelper setBorderPhase(int borderPhase) {
        this.borderPhase = borderPhase;
        return this;
    }
}
