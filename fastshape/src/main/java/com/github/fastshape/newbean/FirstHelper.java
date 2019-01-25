package com.github.fastshape.newbean;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.IntDef;
import android.util.AttributeSet;

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
public class FirstHelper extends ClipHelper{
    public static final int defStyleAttr=R.attr.fastshapeStyle;

    /*设置正常状态背景和press状态背景,覆盖其他所有属性*/
    protected Drawable drawable_normal;
    protected Drawable drawable_press;

    /*设置press颜色,设置了点击事件才生效*/
    protected int pressColor;

    /*显示左边框*/
    protected boolean left_line;
    /*显示上边框*/
    protected boolean top_line;
    /*显示右边框*/
    protected boolean right_line;
    /*显示底边框*/
    protected boolean bottom_line;

    /*
     * rectangle(默认):矩形
     * oval:椭圆
     * line:线
     * */
    protected int shapeType;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({shapeType_rectangle,shapeType_oval,shapeType_line})
    public @interface shapeType{};
    public static final int shapeType_rectangle= GradientDrawable.RECTANGLE;
    public static final int shapeType_oval=GradientDrawable.OVAL;
    public static final int shapeType_line=GradientDrawable.LINE;

    /*边框宽度*/
    protected float borderWidth;
    /*边框颜色*/
    protected int borderColor;
    /*边框虚线长度*/
    protected float borderDashWidth;
    /*边框虚线间隔距离*/
    protected float borderDashGap;
    /*view填充色(相当于背景色)*/
    protected int solidColor;
    /***部分边框宽度*/
    protected int[] partBorderWidth;
    /*左上方圆角*/
    protected float topLeftRadius;
    /*右上方圆角*/
    protected float topRightRadius;
    /*左下方圆角*/
    protected float bottomLeftRadius;
    /*右下方圆角*/
    protected float bottomRightRadius;

    /*
     * 渐变类型
     * linear:线性渐变
     * radial:放射渐变
     * sweep:扫描性渐变
     * */
    protected int gradientType;
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({gradientType_linear,gradientType_radial,gradientType_sweep,gradientType_none})
    public @interface gradientType{};
    public static final int gradientType_none=-1;
    public static final int gradientType_linear=0;
    public static final int gradientType_radial=1;
    public static final int gradientType_sweep=2;


    /*渐变角度*/
    protected int gradientAngle;
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({angle_0,angle_45,angle_90,angle_135,angle_180,angle_225,angle_270,angle_315})
    public  @interface angleType{};
    public static final int angle_0=0;
    public static final int angle_45=45;
    public static final int angle_90=90;
    public static final int angle_135=135;
    public static final int angle_180=180;
    public static final int angle_225=225;
    public static final int angle_270=270;
    public static final int angle_315=315;


    /*pressColor覆盖 gradient*/
    /*渐变的X轴起始位置,范围0~1,默认0.5*/
    protected float gradientCenterX;
    /*渐变的Y轴起始位置,范围0~1,默认0.5*/
    protected float gradientCenterY;
    /*渐变起始颜色*/
    protected int gradientStartColor;
    /*渐变中间颜色*/
    protected int gradientCenterColor;
    /*渐变结束颜色*/
    protected int gradientEndColor;
    /*渐变半径,gradientType="radial"适用,默认40*/
    protected float gradientRadius;


    protected boolean isPartBorder;


    public void complete() {
        if(completeInter!=null){
            completeInter.complete();
        }
    }
    public FirstHelper(CompleteInter completeInter) {
        this.completeInter = completeInter;
    }
    public void init(Context context, AttributeSet attrs) {
        TypedArray viewNormal = context.obtainStyledAttributes(attrs, R.styleable.FastShapeAttr,R.attr.fastshapeStyle,0);

        publicFirstAttr(viewNormal);

        clipAttr(viewNormal);

        viewNormal.recycle();
    }

    /*裁剪属性*/
    protected void clipAttr(TypedArray viewNormal) {
        clipSwitch=viewNormal.getBoolean(R.styleable.FastShapeAttr_clipSwitch, false);
        float clipRadius = viewNormal.getDimension(R.styleable.FastShapeAttr_clipRadius, 0);
        if (clipRadius > 0) {
            clipTopLeftRadius = clipRadius;
            clipTopRightRadius = clipRadius;
            clipBottomLeftRadius = clipRadius;
            clipBottomRightRadius = clipRadius;
        } else {
            clipTopLeftRadius = viewNormal.getDimension(R.styleable.FastShapeAttr_clipTopLeftRadius, 0);
            clipTopRightRadius = viewNormal.getDimension(R.styleable.FastShapeAttr_clipTopRightRadius, 0);
            clipBottomLeftRadius = viewNormal.getDimension(R.styleable.FastShapeAttr_clipBottomLeftRadius, 0);
            clipBottomRightRadius = viewNormal.getDimension(R.styleable.FastShapeAttr_clipBottomRightRadius, 0);
        }
        clipBg = viewNormal.getBoolean(R.styleable.FastShapeAttr_clipBg,true);
        clipIgnorePadding = viewNormal.getBoolean(R.styleable.FastShapeAttr_clipIgnorePadding,true);
        clipIsCircle = viewNormal.getBoolean(R.styleable.FastShapeAttr_clipIsCircle, false);
        clipIsAreaClick = viewNormal.getBoolean(R.styleable.FastShapeAttr_clipIsAreaClick, true);
        clipBorderWidth = viewNormal.getDimension(R.styleable.FastShapeAttr_clipBorderWidth, 0);
        clipBorderColor = viewNormal.getColor(R.styleable.FastShapeAttr_clipBorderColor, Color.WHITE);
        clipBorderDashWidth = viewNormal.getDimension(R.styleable.FastShapeAttr_clipBorderDashWidth, 0);
        clipBorderDashGap = viewNormal.getDimension(R.styleable.FastShapeAttr_clipBorderDashGap, 0);
        clipBorderDashBgColor = viewNormal.getColor(R.styleable.FastShapeAttr_clipBorderDashBgColor, Color.TRANSPARENT);
        clipBorderPhase = viewNormal.getInt(R.styleable.FastShapeAttr_clipBorderPhase, 0);
    }
    /*第一部分公共属性*/
    protected void publicFirstAttr(TypedArray viewNormal) {
        Drawable drawable_normal = viewNormal.getDrawable(R.styleable.FastShapeAttr_drawable_normal);
        Drawable drawable_press = viewNormal.getDrawable(R.styleable.FastShapeAttr_drawable_press);

        if (drawable_normal != null || drawable_press != null) {
            this.drawable_normal = drawable_normal;
            this.drawable_press = drawable_press;
            if (drawable_normal == null) {
                this.drawable_normal = drawable_press;
            }
            if (drawable_press == null) {
                this.drawable_press = drawable_normal;
            }
            return ;
        }

        pressColor = viewNormal.getColor(R.styleable.FastShapeAttr_pressColor, Color.TRANSPARENT);
        left_line = viewNormal.getBoolean(R.styleable.FastShapeAttr_left_line, false);
        top_line = viewNormal.getBoolean(R.styleable.FastShapeAttr_top_line, false);
        right_line = viewNormal.getBoolean(R.styleable.FastShapeAttr_right_line, false);
        bottom_line = viewNormal.getBoolean(R.styleable.FastShapeAttr_bottom_line, false);

        if (left_line && top_line && right_line && bottom_line) {
            isPartBorder = false;
        }else if(left_line==false && top_line==false && right_line==false && bottom_line==false){
            isPartBorder = false;
        }else {
            isPartBorder = true;
        }

        shapeType = viewNormal.getInteger(R.styleable.FastShapeAttr_shapeType, shapeType_rectangle);
        borderWidth = viewNormal.getDimension(R.styleable.FastShapeAttr_borderWidth, 0);
        borderColor = viewNormal.getColor(R.styleable.FastShapeAttr_borderColor,Color.TRANSPARENT);
        borderDashWidth = viewNormal.getDimension(R.styleable.FastShapeAttr_borderDashWidth, 0);
        borderDashGap = viewNormal.getDimension(R.styleable.FastShapeAttr_borderDashGap, 0);


        solidColor = viewNormal.getColor(R.styleable.FastShapeAttr_solidColor, Color.TRANSPARENT);

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

        gradientType = viewNormal.getInteger(R.styleable.FastShapeAttr_gradientType, gradientType_none);
        if (gradientType != -1) {
            gradientAngle = viewNormal.getInteger(R.styleable.FastShapeAttr_gradientAngle, 0);
            gradientCenterX = viewNormal.getFloat(R.styleable.FastShapeAttr_gradientCenterX, 0.5f);
            gradientCenterY = viewNormal.getFloat(R.styleable.FastShapeAttr_gradientCenterY, 0.5f);

            gradientStartColor = viewNormal.getColor(R.styleable.FastShapeAttr_gradientStartColor, 0);
            gradientCenterColor = viewNormal.getColor(R.styleable.FastShapeAttr_gradientCenterColor, 0);
            gradientEndColor = viewNormal.getColor(R.styleable.FastShapeAttr_gradientEndColor, 0);

            gradientRadius = viewNormal.getDimension(R.styleable.FastShapeAttr_gradientRadius, 40);
        }
    }



    public FirstHelper clearAttr() {
        this.drawable_normal = null;
        this.drawable_press = null;

        this.pressColor = getTransparentColor();
        this.left_line = false;
        this.top_line = false;
        this.right_line = false;
        this.bottom_line = false;
        this.shapeType = shapeType_rectangle;
        this.borderWidth = 0;
        this.borderColor = getTransparentColor();
        this.borderDashWidth = 0;
        this.borderDashGap = 0;
        this.solidColor = getTransparentColor();
        this.topLeftRadius = 0;
        this.topRightRadius = 0;
        this.bottomLeftRadius = 0;
        this.bottomRightRadius = 0;
        this.gradientType = -1;
        this.gradientAngle = 0;
        this.gradientCenterX = 0.5f;
        this.gradientCenterY = 0.5f;
        this.gradientStartColor = 0;
        this.gradientCenterColor = 0;
        this.gradientEndColor = 0;
        this.gradientRadius = 40;
        return this;
    }

    public Drawable getDrawable_normal() {
        return drawable_normal;
    }

    public FirstHelper setDrawable_normal(Drawable drawable_normal) {
        this.drawable_normal = drawable_normal;
        return this;
    }

    public Drawable getDrawable_press() {
        return drawable_press;
    }

    public FirstHelper setDrawable_press(Drawable drawable_press) {
        this.drawable_press = drawable_press;
        return this;
    }

    public int getPressColor() {
        return pressColor;
    }

    public FirstHelper setPressColor(int pressColor) {
        this.pressColor = pressColor;
        return this;
    }



    public boolean isLeft_line() {
        return left_line;
    }

    public FirstHelper setLeft_line(boolean left_line) {
        this.left_line = left_line;
        return this;
    }

    public boolean isTop_line() {
        return top_line;
    }

    public FirstHelper setTop_line(boolean top_line) {
        this.top_line = top_line;
        return this;
    }

    public boolean isRight_line() {
        return right_line;
    }

    public FirstHelper setRight_line(boolean right_line) {
        this.right_line = right_line;
        return this;
    }

    public boolean isBottom_line() {
        return bottom_line;
    }

    public FirstHelper setBottom_line(boolean bottom_line) {
        this.bottom_line = bottom_line;
        return this;
    }

    public int getShapeType() {
        return shapeType;
    }

    public FirstHelper setShapeType(@FirstHelper.shapeType int shapeType) {
        this.shapeType = shapeType;
        return this;
    }

    public float getBorderWidth() {
        return borderWidth;
    }

    public FirstHelper setBorderWidth(float borderWidth) {
        this.borderWidth = borderWidth;
        return this;
    }

    public int getBorderColor() {
        return borderColor;
    }

    public FirstHelper setBorderColor(int borderColor) {
        this.borderColor = borderColor;
        return this;
    }

    public float getBorderDashWidth() {
        return borderDashWidth;
    }

    public FirstHelper setBorderDashWidth(float borderDashWidth) {
        this.borderDashWidth = borderDashWidth;
        return this;
    }

    public float getBorderDashGap() {
        return borderDashGap;
    }

    public FirstHelper setBorderDashGap(float borderDashGap) {
        this.borderDashGap = borderDashGap;
        return this;
    }

    public int getSolidColor() {
        return solidColor;
    }

    public FirstHelper setSolidColor(int solidColor) {
        this.solidColor = solidColor;
        return this;
    }


    public FirstHelper setRadius(float topLeftRadius, float topRightRadius, float bottomRightRadius, float bottomLeftRadius) {
        setTopLeftRadius(topLeftRadius);
        setTopRightRadius(topRightRadius);
        setBottomRightRadius(bottomRightRadius);
        setBottomLeftRadius(bottomLeftRadius);
        return this;
    }
    public FirstHelper setRadius(float radius) {
        return setRadius(radius,radius,radius,radius);
    }

    public float getTopLeftRadius() {
        return topLeftRadius;
    }

    public FirstHelper setTopLeftRadius(float topLeftRadius) {
        this.topLeftRadius = topLeftRadius;
        return this;
    }

    public float getTopRightRadius() {
        return topRightRadius;
    }

    public FirstHelper setTopRightRadius(float topRightRadius) {
        this.topRightRadius = topRightRadius;
        return this;
    }

    public float getBottomLeftRadius() {
        return bottomLeftRadius;
    }

    public FirstHelper setBottomLeftRadius(float bottomLeftRadius) {
        this.bottomLeftRadius = bottomLeftRadius;
        return this;
    }

    public float getBottomRightRadius() {
        return bottomRightRadius;
    }

    public FirstHelper setBottomRightRadius(float bottomRightRadius) {
        this.bottomRightRadius = bottomRightRadius;
        return this;
    }

    public int getGradientType() {
        return gradientType;
    }

    public FirstHelper setGradientType(@FirstHelper.gradientType int gradientType) {
        this.gradientType = gradientType;
        return this;
    }

    public int getGradientAngle() {
        return gradientAngle;
    }

    public FirstHelper setGradientAngle(@FirstHelper.angleType int gradientAngle) {
        this.gradientAngle = gradientAngle;
        return this;
    }

    public float getGradientCenterX() {
        return gradientCenterX;
    }

    public FirstHelper setGradientCenterX(float gradientCenterX) {
        this.gradientCenterX = gradientCenterX;
        return this;
    }

    public float getGradientCenterY() {
        return gradientCenterY;
    }

    public FirstHelper setGradientCenterY(float gradientCenterY) {
        this.gradientCenterY = gradientCenterY;
        return this;
    }

    public int getGradientStartColor() {
        return gradientStartColor;
    }

    public FirstHelper setGradientStartColor(int gradientStartColor) {
        this.gradientStartColor = gradientStartColor;
        return this;
    }

    public int getGradientCenterColor() {
        return gradientCenterColor;
    }

    public FirstHelper setGradientCenterColor(int gradientCenterColor) {
        this.gradientCenterColor = gradientCenterColor;
        return this;
    }

    public int getGradientEndColor() {
        return gradientEndColor;
    }

    public FirstHelper setGradientEndColor(int gradientEndColor) {
        this.gradientEndColor = gradientEndColor;
        return this;
    }

    public float getGradientRadius() {
        return gradientRadius;
    }

    public FirstHelper setGradientRadius(float gradientRadius) {
        this.gradientRadius = gradientRadius;
        return this;
    }
    



}
