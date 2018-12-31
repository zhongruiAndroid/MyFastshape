package com.github.fastshape.newbean;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/***
 *   created by zhongrui on 2018/12/31
 *
 *   MyRelativeLayout，MyLinearLayout，MyFrameLayout，MyTextView，MyEditText，MyButton公共属性
 */
public class FirstHelper {
    /*设置正常状态背景和press状态背景,覆盖其他所有属性*/
    protected Drawable drawable_normal;
    protected Drawable drawable_press;

    /*设置press颜色,设置了点击事件才生效*/
    protected int pressColor;

    /*显示四个边框*/
    protected boolean all_line;
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
    @IntDef({gradientType_linear,gradientType_radial,gradientType_sweep})
    public @interface gradientType{};
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


    private boolean isPartBorder;





}
