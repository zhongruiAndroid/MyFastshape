package com.github.fastshape;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.github.fastshape.bean.BaseBean;
import com.github.fastshape.inter.BaseInter;


/**
 * Created by Administrator on 2016/9/6.
 */
public class MyLinearLayout extends LinearLayout implements BaseInter<MyLinearLayout> {
    private BaseBean baseBean;
//    private BaseViewHelper viewHelper;

    public MyLinearLayout(Context context) {
        this(context,null);
    }

    public MyLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs,BaseBean.defStyleAttr);
    }

    public MyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init(attrs,defStyleAttr);
    }


    public void init(AttributeSet attrs,int defStyleAttr) {
        baseBean=new BaseBean();
        initData();
        Drawable background = getBackground();
        if (background != null) {
            return;
        }
        /*
        setAttrForDraw(viewNormal);
*/
        baseBean.init(getContext(),attrs,defStyleAttr);




        /**
         * 设置各个自定义属性之后调用此方法设置background
         * 这里有必要说明一下,为什么设置属性了还需要调用这个方法才能生效?
         * 这个方法是将代码设置的各个属性收集生成一个Drawable,然后将它设置为background,简单点这个方法就是用来设置背景的,等价于setBackground方法
         */
        complete();
    }

    private void initData() {
//        baseBean.clipBorderColor = Color.parseColor("#34e8a6");
//        baseBean.clipBorderDashBgColor = Color.WHITE;
    }

    private void setAttrForDraw(TypedArray viewNormal) {
       /* float clipRadius = viewNormal.getDimension(R.styleable.MyLinearLayout_clipRadius, 0);
        if (clipRadius > 0) {
            viewHelper.clipTopLeftRadius = clipRadius;
            viewHelper.clipTopRightRadius = clipRadius;
            viewHelper.clipBottomLeftRadius = clipRadius;
            viewHelper.clipBottomRightRadius = clipRadius;
        } else {
            viewHelper.clipTopLeftRadius = viewNormal.getDimension(R.styleable.MyLinearLayout_clipTopLeftRadius, 0);
            viewHelper.clipTopRightRadius = viewNormal.getDimension(R.styleable.MyLinearLayout_clipTopRightRadius, 0);
            viewHelper.clipBottomLeftRadius = viewNormal.getDimension(R.styleable.MyLinearLayout_clipBottomLeftRadius, 0);
            viewHelper.clipBottomRightRadius = viewNormal.getDimension(R.styleable.MyLinearLayout_clipBottomRightRadius, 0);
        }

        viewHelper.clipIgnorePadding = viewNormal.getBoolean(R.styleable.MyLinearLayout_clipIgnorePadding, false);
        viewHelper.clipIsCircle = viewNormal.getBoolean(R.styleable.MyLinearLayout_clipIsCircle, false);
        viewHelper.clipIsAreaClick = viewNormal.getBoolean(R.styleable.MyLinearLayout_clipIsAreaClick, true);
        viewHelper.clipBorderWidth = viewNormal.getDimension(R.styleable.MyLinearLayout_clipBorderWidth, 0);
        viewHelper.clipBorderColor = viewNormal.getColor(R.styleable.MyLinearLayout_clipBorderColor, Color.parseColor("#34e8a6"));
        viewHelper.clipBorderDashBgColor = viewNormal.getColor(R.styleable.MyLinearLayout_clipBorderDashBgColor, Color.WHITE);
        viewHelper.clipBorderDashWidth = viewNormal.getDimension(R.styleable.MyLinearLayout_clipBorderDashWidth, 0);
        viewHelper.clipBorderDashGap = viewNormal.getDimension(R.styleable.MyLinearLayout_clipBorderDashGap, 0);*/

    }

    /**
     * 设置各个自定义属性之后调用此方法设置background
     * 这里有必要说明一下,为什么设置属性了还需要调用这个方法才能生效?
     * 这个方法是将代码设置的各个属性收集生成一个Drawable,然后将它设置为background,简单点这个方法就是用来设置背景的,等价于setBackground方法
     */
    public void complete() {
        baseBean.viewComplete(this);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        /*if (viewHelper != null) {
            viewHelper.onSizeChanged(getPaddingLeft(),
                    getPaddingTop(),
                    getPaddingRight(),
                    getPaddingBottom(), w, h, oldw, oldh);
        }*/
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
       /* if (viewHelper != null) {
            viewHelper.onRefreshPaint(canvas, getPaddingLeft(),
                    getPaddingTop(),
                    getPaddingRight(),
                    getPaddingBottom(), getWidth(), getHeight());
        }*/
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        int saveLayer = canvas.saveLayer(new RectF(0, 0, canvas.getWidth(), canvas.getHeight()), null, Canvas.ALL_SAVE_FLAG);
        super.dispatchDraw(canvas);
//        viewHelper.dispatchDrawEnd(saveLayer, canvas);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        /*if (ev.getAction() == MotionEvent.ACTION_UP) {
            if (viewHelper != null && viewHelper.clipIsAreaClick) {
                if (viewHelper.onTouchEvent(ev) == false) {//如果这个地方返回true会导致点击事件失效
                    return false;
                }
            }
        }*/
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public MyLinearLayout clearAttr() {
        baseBean.clearAttr();
        return this;
    }

    @Override
    public Drawable getDrawable_normal() {
        return baseBean.getDrawable_normal();
    }

    @Override
    public MyLinearLayout setDrawable_normal(Drawable drawable_normal) {
        baseBean.setDrawable_normal(drawable_normal);
        return this;

    }

    @Override
    public Drawable getDrawable_press() {
        return baseBean.getDrawable_press();
    }

    @Override
    public MyLinearLayout setDrawable_press(Drawable drawable_press) {
        baseBean.setDrawable_press(drawable_press);
        return this;
    }

    @Override
    public int getPressColor() {
        return baseBean.getPressColor();
    }

    @Override
    public MyLinearLayout setPressColor(int pressColor) {
        baseBean.setPressColor(pressColor);
        return this;
    }

    @Override
    public boolean isAll_line() {
        return baseBean.isAll_line();
    }

    @Override
    public MyLinearLayout setAll_line(boolean all_line) {
        baseBean.setAll_line(all_line);
        return this;
    }

    @Override
    public boolean isLeft_line() {
        return baseBean.isLeft_line();
    }

    @Override
    public MyLinearLayout setLeft_line(boolean left_line) {
        baseBean.setLeft_line(left_line);
        return this;
    }

    @Override
    public boolean isTop_line() {
        return baseBean.isTop_line();
    }

    @Override
    public MyLinearLayout setTop_line(boolean top_line) {
        baseBean.setTop_line(top_line);
        return this;
    }

    @Override
    public boolean isRight_line() {
        return baseBean.isRight_line();
    }

    @Override
    public MyLinearLayout setRight_line(boolean right_line) {
        baseBean.setRight_line(right_line);
        return this;
    }

    @Override
    public boolean isBottom_line() {
        return baseBean.isBottom_line();
    }

    @Override
    public MyLinearLayout setBottom_line(boolean bottom_line) {
        baseBean.setBottom_line(bottom_line);
        return this;
    }

    @Override
    public int getShapeType() {
        return baseBean.getShapeType();
    }

    @Override
    public MyLinearLayout setShapeType(int shapeType) {
        baseBean.setShapeType(shapeType);
        return this;
    }

    @Override
    public float getBorderWidth() {
        return baseBean.getBorderWidth();
    }

    @Override
    public MyLinearLayout setBorderWidth(float borderWidth) {
        baseBean.setBorderWidth(borderWidth);
        return this;
    }

    @Override
    public int getBorderColor() {
        return baseBean.getBorderColor();
    }

    @Override
    public MyLinearLayout setBorderColor(int borderColor) {
        baseBean.setBorderColor(borderColor);
        return this;
    }

    @Override
    public float getBorderDashWidth() {
        return baseBean.getBorderDashWidth();
    }

    @Override
    public MyLinearLayout setBorderDashWidth(float borderDashWidth) {
        baseBean.setBorderDashWidth(borderDashWidth);
        return this;
    }

    @Override
    public float getBorderDashGap() {
        return baseBean.getBorderDashGap();
    }

    @Override
    public MyLinearLayout setBorderDashGap(float borderDashGap) {
        baseBean.setBorderDashGap(borderDashGap);
        return this;
    }

    @Override
    public MyLinearLayout setSolidColor(int solidColor) {
        baseBean.setSolidColor(solidColor);
        return this;
    }



    @Override
    public MyLinearLayout setRadius(float topLeftRadius, float topRightRadius, float bottomRightRadius, float bottomLeftRadius) {
        baseBean.setRadius(topLeftRadius,topRightRadius,bottomRightRadius,bottomLeftRadius);
        return this;
    }
    @Override
    public MyLinearLayout setRadius(float radius) {
        baseBean.setRadius(radius);
        return this;
    }
    @Override
    public float getTopLeftRadius() {
        return baseBean.getTopLeftRadius();
    }
    @Override
    public MyLinearLayout setTopLeftRadius(float topLeftRadius) {
        baseBean.setTopLeftRadius(topLeftRadius);
        return this;
    }

    @Override
    public float getTopRightRadius() {
        return baseBean.getTopRightRadius();
    }

    @Override
    public MyLinearLayout setTopRightRadius(float topRightRadius) {
        baseBean.setTopRightRadius(topRightRadius);
        return this;
    }

    @Override
    public float getBottomLeftRadius() {
        return baseBean.getBottomLeftRadius();
    }

    @Override
    public MyLinearLayout setBottomLeftRadius(float bottomLeftRadius) {
        baseBean.setBottomLeftRadius(bottomLeftRadius);
        return this;
    }

    @Override
    public float getBottomRightRadius() {
        return baseBean.getBottomRightRadius();
    }

    @Override
    public MyLinearLayout setBottomRightRadius(float bottomRightRadius) {
        baseBean.setBottomRightRadius(bottomRightRadius);
        return this;
    }

    @Override
    public int getGradientType() {
        return baseBean.getGradientType();
    }

    @Override
    public MyLinearLayout setGradientType(int gradientType) {
        baseBean.setGradientType(gradientType);
        return this;
    }

    @Override
    public int getGradientAngle() {
        return baseBean.getGradientAngle();
    }

    @Override
    public MyLinearLayout setGradientAngle(int gradientAngle) {
        baseBean.setGradientAngle(gradientAngle);
        return this;
    }

    @Override
    public float getGradientCenterX() {
        return baseBean.getGradientCenterX();
    }

    @Override
    public MyLinearLayout setGradientCenterX(float gradientCenterX) {
        baseBean.setGradientCenterX(gradientCenterX);
        return this;
    }

    @Override
    public float getGradientCenterY() {
        return baseBean.getGradientCenterY();
    }

    @Override
    public MyLinearLayout setGradientCenterY(float gradientCenterY) {
        baseBean.setGradientCenterY(gradientCenterY);
        return this;
    }

    @Override
    public int getGradientStartColor() {
        return baseBean.getGradientStartColor();
    }

    @Override
    public MyLinearLayout setGradientStartColor(int gradientStartColor) {
        baseBean.setGradientStartColor(gradientStartColor);
        return this;
    }

    @Override
    public int getGradientCenterColor() {
        return baseBean.getGradientCenterColor();
    }

    @Override
    public MyLinearLayout setGradientCenterColor(int gradientCenterColor) {
        baseBean.setGradientCenterColor(gradientCenterColor);
        return this;
    }

    @Override
    public int getGradientEndColor() {
        return baseBean.getGradientEndColor();
    }

    @Override
    public MyLinearLayout setGradientEndColor(int gradientEndColor) {
        baseBean.setGradientEndColor(gradientEndColor);
        return this;
    }

    @Override
    public float getGradientRadius() {
        return baseBean.getGradientRadius();
    }

    @Override
    public MyLinearLayout setGradientRadius(float gradientRadius) {
        baseBean.setGradientRadius(gradientRadius);
        return this;
    }


}
