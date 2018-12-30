package com.github.fastshape;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PathEffect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.github.fastshape.bean.BaseHelper;
import com.github.fastshape.inter.BaseInter;
import com.github.fastshape.inter.ClipInter;


/**
 * Created by Administrator on 2016/9/6.
 */
public class MyLinearLayout extends LinearLayout implements BaseInter<MyLinearLayout>,ClipInter<MyLinearLayout> {
    private BaseHelper baseHelper;
    private int saveLayerCount;
    public MyLinearLayout(Context context) {
        this(context, null);
    }

    public MyLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, BaseHelper.defStyleAttr);
    }

    public MyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs, defStyleAttr);
    }

    public void init(AttributeSet attrs, int defStyleAttr) {
        baseHelper = new BaseHelper();
        Drawable background = getBackground();
        if (background != null) {
            return;
        }
        baseHelper.init(getContext(), attrs, defStyleAttr);

        /**
         * 设置各个自定义属性之后调用此方法设置background
         * 这里有必要说明一下,为什么设置属性了还需要调用这个方法才能生效?
         * 这个方法是将代码设置的各个属性收集生成一个Drawable,然后将它设置为background,简单点这个方法就是用来设置背景的,等价于setBackground方法
         */
        complete();
    }

    /**
     * 设置各个自定义属性之后调用此方法设置background
     * 这里有必要说明一下,为什么设置属性了还需要调用这个方法才能生效?
     * 这个方法是将代码设置的各个属性收集生成一个Drawable,然后将它设置为background,简单点这个方法就是用来设置背景的,等价于setBackground方法
     */
    public void complete() {
        baseHelper.viewComplete(this);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (baseHelper.clipHelper != null) {
            baseHelper.clipHelper.onSizeChanged();
            baseHelper.clipHelper.onRefreshPaint(getPaddingLeft(),
                    getPaddingTop(),
                    getPaddingRight(),
                    getPaddingBottom(), getWidth(), getHeight());
        }
    }

    @Override
    public void draw(Canvas canvas) {
        if (baseHelper.clipHelper != null&&baseHelper.clipHelper.clipBg) {
            canvas.save();
            canvas.clipPath(baseHelper.clipHelper.clipPath);
            super.draw(canvas);
            canvas.restore();
        } else {
            super.draw(canvas);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        if (baseHelper.clipHelper != null) {
            saveLayerCount = canvas.saveLayer(new RectF(0, 0, canvas.getWidth(), canvas.getHeight()), null, Canvas.ALL_SAVE_FLAG);
        }
        super.dispatchDraw(canvas);

        if (baseHelper.clipHelper != null) {
            baseHelper.clipHelper.dispatchDrawEnd(saveLayerCount, canvas);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            if (baseHelper.clipHelper != null && baseHelper.clipHelper.clipIsAreaClick) {
                if (baseHelper.clipHelper.onTouchEvent(ev) == false) {//如果这个地方返回true会导致点击事件失效
                    return false;
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public MyLinearLayout clearAttr() {
        baseHelper.clearAttr();
        return this;
    }

    @Override
    public Drawable getDrawable_normal() {
        return baseHelper.getDrawable_normal();
    }

    @Override
    public MyLinearLayout setDrawable_normal(Drawable drawable_normal) {
        baseHelper.setDrawable_normal(drawable_normal);
        return this;

    }

    @Override
    public Drawable getDrawable_press() {
        return baseHelper.getDrawable_press();
    }

    @Override
    public MyLinearLayout setDrawable_press(Drawable drawable_press) {
        baseHelper.setDrawable_press(drawable_press);
        return this;
    }

    @Override
    public int getPressColor() {
        return baseHelper.getPressColor();
    }

    @Override
    public MyLinearLayout setPressColor(int pressColor) {
        baseHelper.setPressColor(pressColor);
        return this;
    }

    @Override
    public boolean isAll_line() {
        return baseHelper.isAll_line();
    }

    @Override
    public MyLinearLayout setAll_line(boolean all_line) {
        baseHelper.setAll_line(all_line);
        return this;
    }

    @Override
    public boolean isLeft_line() {
        return baseHelper.isLeft_line();
    }

    @Override
    public MyLinearLayout setLeft_line(boolean left_line) {
        baseHelper.setLeft_line(left_line);
        return this;
    }

    @Override
    public boolean isTop_line() {
        return baseHelper.isTop_line();
    }

    @Override
    public MyLinearLayout setTop_line(boolean top_line) {
        baseHelper.setTop_line(top_line);
        return this;
    }

    @Override
    public boolean isRight_line() {
        return baseHelper.isRight_line();
    }

    @Override
    public MyLinearLayout setRight_line(boolean right_line) {
        baseHelper.setRight_line(right_line);
        return this;
    }

    @Override
    public boolean isBottom_line() {
        return baseHelper.isBottom_line();
    }

    @Override
    public MyLinearLayout setBottom_line(boolean bottom_line) {
        baseHelper.setBottom_line(bottom_line);
        return this;
    }

    @Override
    public int getShapeType() {
        return baseHelper.getShapeType();
    }

    @Override
    public MyLinearLayout setShapeType(@BaseHelper.shapeType int shapeType) {
        baseHelper.setShapeType(shapeType);
        return this;
    }

    @Override
    public float getBorderWidth() {
        return baseHelper.getBorderWidth();
    }

    @Override
    public MyLinearLayout setBorderWidth(float borderWidth) {
        baseHelper.setBorderWidth(borderWidth);
        return this;
    }

    @Override
    public int getBorderColor() {
        return baseHelper.getBorderColor();
    }

    @Override
    public MyLinearLayout setBorderColor(int borderColor) {
        baseHelper.setBorderColor(borderColor);
        return this;
    }

    @Override
    public float getBorderDashWidth() {
        return baseHelper.getBorderDashWidth();
    }

    @Override
    public MyLinearLayout setBorderDashWidth(float borderDashWidth) {
        baseHelper.setBorderDashWidth(borderDashWidth);
        return this;
    }

    @Override
    public float getBorderDashGap() {
        return baseHelper.getBorderDashGap();
    }

    @Override
    public MyLinearLayout setBorderDashGap(float borderDashGap) {
        baseHelper.setBorderDashGap(borderDashGap);
        return this;
    }

    @Override
    public MyLinearLayout setSolidColor(int solidColor) {
        baseHelper.setSolidColor(solidColor);
        return this;
    }


    @Override
    public MyLinearLayout setRadius(float topLeftRadius, float topRightRadius, float bottomRightRadius, float bottomLeftRadius) {
        baseHelper.setRadius(topLeftRadius, topRightRadius, bottomRightRadius, bottomLeftRadius);
        return this;
    }

    @Override
    public MyLinearLayout setRadius(float radius) {
        baseHelper.setRadius(radius);
        return this;
    }

    @Override
    public float getTopLeftRadius() {
        return baseHelper.getTopLeftRadius();
    }

    @Override
    public MyLinearLayout setTopLeftRadius(float topLeftRadius) {
        baseHelper.setTopLeftRadius(topLeftRadius);
        return this;
    }

    @Override
    public float getTopRightRadius() {
        return baseHelper.getTopRightRadius();
    }

    @Override
    public MyLinearLayout setTopRightRadius(float topRightRadius) {
        baseHelper.setTopRightRadius(topRightRadius);
        return this;
    }

    @Override
    public float getBottomLeftRadius() {
        return baseHelper.getBottomLeftRadius();
    }

    @Override
    public MyLinearLayout setBottomLeftRadius(float bottomLeftRadius) {
        baseHelper.setBottomLeftRadius(bottomLeftRadius);
        return this;
    }

    @Override
    public float getBottomRightRadius() {
        return baseHelper.getBottomRightRadius();
    }

    @Override
    public MyLinearLayout setBottomRightRadius(float bottomRightRadius) {
        baseHelper.setBottomRightRadius(bottomRightRadius);
        return this;
    }

    @Override
    public int getGradientType() {
        return baseHelper.getGradientType();
    }

    @Override
    public MyLinearLayout setGradientType(@BaseHelper.gradientType int gradientType) {
        baseHelper.setGradientType(gradientType);
        return this;
    }

    @Override
    public int getGradientAngle() {
        return baseHelper.getGradientAngle();
    }

    @Override
    public MyLinearLayout setGradientAngle(@BaseHelper.angleType int gradientAngle) {
        baseHelper.setGradientAngle(gradientAngle);
        return this;
    }

    @Override
    public float getGradientCenterX() {
        return baseHelper.getGradientCenterX();
    }

    @Override
    public MyLinearLayout setGradientCenterX(float gradientCenterX) {
        baseHelper.setGradientCenterX(gradientCenterX);
        return this;
    }

    @Override
    public float getGradientCenterY() {
        return baseHelper.getGradientCenterY();
    }

    @Override
    public MyLinearLayout setGradientCenterY(float gradientCenterY) {
        baseHelper.setGradientCenterY(gradientCenterY);
        return this;
    }

    @Override
    public int getGradientStartColor() {
        return baseHelper.getGradientStartColor();
    }

    @Override
    public MyLinearLayout setGradientStartColor(int gradientStartColor) {
        baseHelper.setGradientStartColor(gradientStartColor);
        return this;
    }

    @Override
    public int getGradientCenterColor() {
        return baseHelper.getGradientCenterColor();
    }

    @Override
    public MyLinearLayout setGradientCenterColor(int gradientCenterColor) {
        baseHelper.setGradientCenterColor(gradientCenterColor);
        return this;
    }

    @Override
    public int getGradientEndColor() {
        return baseHelper.getGradientEndColor();
    }

    @Override
    public MyLinearLayout setGradientEndColor(int gradientEndColor) {
        baseHelper.setGradientEndColor(gradientEndColor);
        return this;
    }

    @Override
    public float getGradientRadius() {
        return baseHelper.getGradientRadius();
    }

    @Override
    public MyLinearLayout setGradientRadius(float gradientRadius) {
        baseHelper.setGradientRadius(gradientRadius);
        return this;
    }


    /*******************************************clip*********************************************/
    public void completeClip() {
        if (baseHelper.clipHelper != null) {
            baseHelper.clipHelper.onRefreshPaint(getPaddingLeft(),
                    getPaddingTop(),
                    getPaddingRight(),
                    getPaddingBottom(), getWidth(), getHeight());
            invalidate();
        }
    }

    @Override
    public boolean isClipBg() {
        return baseHelper.clipHelper.isClipBg();
    }

    @Override
    public MyLinearLayout setClipBg(boolean clipBg) {
        baseHelper.clipHelper.setClipBg(clipBg);
        return this;
    }

    @Override
    public boolean isClipIsCircle() {
        return baseHelper.clipHelper.isClipIsCircle();
    }

    @Override
    public MyLinearLayout setClipIsCircle(boolean clipIsCircle) {
        baseHelper.clipHelper.setClipIsCircle(clipIsCircle);
        return this;
    }

    @Override
    public boolean isClipIsAreaClick() {
        return baseHelper.clipHelper.isClipIsAreaClick();
    }

    @Override
    public MyLinearLayout setClipIsAreaClick(boolean clipIsAreaClick) {
        baseHelper.clipHelper.setClipIsAreaClick(clipIsAreaClick);
        return this;
    }

    @Override
    public boolean isClipIgnorePadding() {
        return baseHelper.clipHelper.isClipIgnorePadding();
    }

    @Override
    public MyLinearLayout setClipIgnorePadding(boolean clipIgnorePadding) {
        baseHelper.clipHelper.setClipIgnorePadding(clipIgnorePadding);
        return this;
    }

    @Override
    public float getClipTopLeftRadius() {
        return baseHelper.clipHelper.getClipTopLeftRadius();
    }

    @Override
    public MyLinearLayout setClipTopLeftRadius(float clipTopLeftRadius) {
        baseHelper.clipHelper.setClipTopLeftRadius(clipTopLeftRadius);
        return this;
    }

    @Override
    public float getClipTopRightRadius() {
        return baseHelper.clipHelper.getClipTopRightRadius();
    }

    @Override
    public MyLinearLayout setClipTopRightRadius(float clipTopRightRadius) {
        baseHelper.clipHelper.setClipTopRightRadius(clipTopRightRadius);
        return this;
    }

    @Override
    public float getClipBottomLeftRadius() {
        return baseHelper.clipHelper.getClipBottomLeftRadius();
    }

    @Override
    public MyLinearLayout setClipBottomLeftRadius(float clipBottomLeftRadius) {
        baseHelper.clipHelper.setClipBottomLeftRadius(clipBottomLeftRadius);
        return this;
    }

    @Override
    public float getClipBottomRightRadius() {
        return baseHelper.clipHelper.getClipBottomRightRadius();
    }

    @Override
    public MyLinearLayout setClipBottomRightRadius(float clipBottomRightRadius) {
        baseHelper.clipHelper.setClipBottomRightRadius(clipBottomRightRadius);
        return this;
    }

    @Override
    public float getClipBorderWidth() {
        return baseHelper.clipHelper.getClipBorderWidth();
    }

    @Override
    public MyLinearLayout setClipBorderWidth(float clipBorderWidth) {
        baseHelper.clipHelper.setClipBorderWidth(clipBorderWidth);
        return this;
    }

    @Override
    public int getClipBorderColor() {
        return baseHelper.clipHelper.getClipBorderColor();
    }

    @Override
    public MyLinearLayout setClipBorderColor(int clipBorderColor) {
        baseHelper.clipHelper.setClipBorderColor(clipBorderColor);
        return this;
    }

    @Override
    public float getClipBorderDashWidth() {
        return baseHelper.clipHelper.getClipBorderDashWidth();
    }

    @Override
    public MyLinearLayout setClipBorderDashWidth(float clipBorderDashWidth) {
        baseHelper.clipHelper.setClipBorderDashWidth(clipBorderDashWidth);
        return this;
    }

    @Override
    public float getClipBorderDashGap() {
        return baseHelper.clipHelper.getClipBorderDashGap();
    }

    @Override
    public MyLinearLayout setClipBorderDashGap(float clipBorderDashGap) {
        baseHelper.clipHelper.setClipBorderDashGap(clipBorderDashGap);
        return this;
    }

    @Override
    public int getClipBorderDashBgColor() {
        return baseHelper.clipHelper.getClipBorderDashBgColor();
    }

    @Override
    public MyLinearLayout setClipBorderDashBgColor(int clipBorderDashBgColor) {
        baseHelper.clipHelper.setClipBorderDashBgColor(clipBorderDashBgColor);
        return this;
    }

    @Override
    public int getClipBorderPhase() {
        return baseHelper.clipHelper.getClipBorderPhase();
    }

    @Override
    public MyLinearLayout setClipBorderPhase(int clipBorderPhase) {
        baseHelper.clipHelper.setClipBorderPhase(clipBorderPhase);
        return this;
    }

    @Override
    public Shader getShader() {
        return baseHelper.clipHelper.getShader();
    }

    @Override
    public MyLinearLayout setShader(Shader shader) {
        baseHelper.clipHelper.setShader(shader);
        return this;
    }

    @Override
    public PathEffect getPathEffect() {
        return baseHelper.clipHelper.getPathEffect();
    }

    @Override
    public MyLinearLayout setPathEffect(PathEffect pathEffect) {
        baseHelper.clipHelper.setPathEffect(pathEffect);
        return this;
    }
}
