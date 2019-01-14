package com.github.fastshape.bean;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.support.annotation.IntDef;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.github.fastshape.Helper;
import com.github.fastshape.MyButton;
import com.github.fastshape.MyCheckBox;
import com.github.fastshape.MyRadioButton;
import com.github.fastshape.MyTextView;
import com.github.fastshape.R;
import com.github.fastshape.inter.BaseInter;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Administrator on 2018/7/3.
 */

public class BaseHelper extends Helper implements BaseInter<BaseHelper> {
    public static final int defStyleAttr=R.attr.fastshapeStyle;
    public ClipHelper clipHelper;

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
    public static final int shapeType_rectangle=GradientDrawable.RECTANGLE;
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

    public BaseHelper() {
        clipHelper=new ClipHelper();
    }

    public void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray viewNormal = context.obtainStyledAttributes(attrs, R.styleable.FastShapeAttr,defStyleAttr,0);

        publicFirstAttr(viewNormal);

        clipAttr(viewNormal);

        viewNormal.recycle();
    }

    /*裁剪属性*/
    protected void clipAttr(TypedArray viewNormal) {
        if(viewNormal.getBoolean(R.styleable.FastShapeAttr_clipSwitch, false)==false){
            clipHelper=null;
            return;
        }
        float clipRadius = viewNormal.getDimension(R.styleable.FastShapeAttr_clipRadius, 0);
        if (clipRadius > 0) {
            clipHelper.clipTopLeftRadius = clipRadius;
            clipHelper.clipTopRightRadius = clipRadius;
            clipHelper.clipBottomLeftRadius = clipRadius;
            clipHelper.clipBottomRightRadius = clipRadius;
        } else {
            clipHelper.clipTopLeftRadius = viewNormal.getDimension(R.styleable.FastShapeAttr_clipTopLeftRadius, 0);
            clipHelper.clipTopRightRadius = viewNormal.getDimension(R.styleable.FastShapeAttr_clipTopRightRadius, 0);
            clipHelper.clipBottomLeftRadius = viewNormal.getDimension(R.styleable.FastShapeAttr_clipBottomLeftRadius, 0);
            clipHelper.clipBottomRightRadius = viewNormal.getDimension(R.styleable.FastShapeAttr_clipBottomRightRadius, 0);
        }
        clipHelper.clipBg = viewNormal.getBoolean(R.styleable.FastShapeAttr_clipBg,true);
        clipHelper.clipIgnorePadding = viewNormal.getBoolean(R.styleable.FastShapeAttr_clipIgnorePadding,true);
        clipHelper.clipIsCircle = viewNormal.getBoolean(R.styleable.FastShapeAttr_clipIsCircle, false);
        clipHelper.clipIsAreaClick = viewNormal.getBoolean(R.styleable.FastShapeAttr_clipIsAreaClick, true);
        clipHelper.clipBorderWidth = viewNormal.getDimension(R.styleable.FastShapeAttr_clipBorderWidth, 0);
        clipHelper.clipBorderColor = viewNormal.getColor(R.styleable.FastShapeAttr_clipBorderColor, Color.WHITE);
        clipHelper.clipBorderDashWidth = viewNormal.getDimension(R.styleable.FastShapeAttr_clipBorderDashWidth, 0);
        clipHelper.clipBorderDashGap = viewNormal.getDimension(R.styleable.FastShapeAttr_clipBorderDashGap, 0);
        clipHelper.clipBorderDashBgColor = viewNormal.getColor(R.styleable.FastShapeAttr_clipBorderDashBgColor, Color.TRANSPARENT);
        clipHelper.clipBorderPhase = viewNormal.getInt(R.styleable.FastShapeAttr_clipBorderPhase, 0);
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

        gradientType = viewNormal.getInteger(R.styleable.FastShapeAttr_gradientType, -1);
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

    public BaseHelper clearAttr() {
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

    public void viewComplete(View myView) {
        if (drawable_normal != null) {
            StateListDrawable stateListDrawableForLayer = new StateListDrawable();
            stateListDrawableForLayer.addState(new int[]{-android.R.attr.state_pressed}, drawable_normal);
            stateListDrawableForLayer.addState(new int[]{android.R.attr.state_pressed}, drawable_press);
            stateListDrawableForLayer.addState(new int[]{}, drawable_normal);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                myView.setBackground(stateListDrawableForLayer);
            } else {
                myView.setBackgroundDrawable(stateListDrawableForLayer);
            }
            return;
        }

        if (left_line && top_line && right_line && bottom_line) {
            isPartBorder = false;
        }else if(left_line==false && top_line==false && right_line==false && bottom_line==false){
            isPartBorder = false;
        }else {
            isPartBorder = true;
        }

        //设置虚线需要设置layertype
        if (shapeType == shapeType_line && myView.getLayerType() == View.LAYER_TYPE_NONE) {
            myView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        //是否是全边框
        if (!isPartBorder) {
            //是否设置pressColor
            if (pressColor == Color.TRANSPARENT) {
                //无部分边框，无presscolor
                noPartBorderNoPressColor(myView);
            } else {
                //无部分边框，有presscolor
                noPartBorderHasPressColor(myView);
            }
        } else {
            //是否设置pressColor
            if (pressColor == Color.TRANSPARENT) {
                //有部分边框，无presscolor
                hasPartBorderNoPressColor(myView);
            } else {
                //有部分边框，有presscolor
                hasPartBorderHasPressColor(myView);
            }
        }
        if (myView instanceof MyButton) {
            setCompoundDrawables((TextView) myView);
        } else if(myView instanceof MyTextView) {
            setCompoundDrawables((TextView)myView);
        }else if(myView instanceof MyCheckBox){
            setCompoundDrawables((TextView)myView);
        }else if(myView instanceof MyRadioButton){
            setCompoundDrawables((TextView)myView);
        }
    }

    private void setCompoundDrawables(TextView myView) {
        Drawable drawable0 = myView.getCompoundDrawables()[0];
        Drawable drawable1 = myView.getCompoundDrawables()[1];
        Drawable drawable2 = myView.getCompoundDrawables()[2];
        Drawable drawable3 = myView.getCompoundDrawables()[3];

        if (drawable0 != null) {
            int width = drawable0.getIntrinsicWidth();
            int height = drawable0.getIntrinsicHeight();
            drawable0.setBounds(0, 0, getLeftWH(width, height)[0], getLeftWH(width, height)[1]);
        }
        if (drawable1 != null) {
            int width = drawable1.getIntrinsicWidth();
            int height = drawable1.getIntrinsicHeight();
            drawable1.setBounds(0, 0, getTopWH(width, height)[0], getTopWH(width, height)[1]);
        }
        if (drawable2 != null) {
            int width = drawable2.getIntrinsicWidth();
            int height = drawable2.getIntrinsicHeight();
            drawable2.setBounds(0, 0, getRightWH(width, height)[0], getRightWH(width, height)[1]);
        }
        if (drawable3 != null) {
            int width = drawable3.getIntrinsicWidth();
            int height = drawable3.getIntrinsicHeight();
            drawable3.setBounds(0, 0, getBottomWH(width, height)[0], getBottomWH(width, height)[1]);
        }


        myView.setCompoundDrawables(drawable0, drawable1, drawable2, drawable3);
    }


    private void noPartBorderNoPressColor(View myView) {
        GradientDrawable gradientDrawableNormal = getNoPartBorderNoPressColorGradientDrawable(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            myView.setBackground(gradientDrawableNormal);
        } else {
            myView.setBackgroundDrawable(gradientDrawableNormal);
        }
    }

    private void noPartBorderHasPressColor(View myView) {

        GradientDrawable gradientDrawableNormal = getNoPartBorderNoPressColorGradientDrawable(false);
        GradientDrawable gradientDrawablePress = getNoPartBorderNoPressColorGradientDrawable(false);
        gradientDrawablePress.setColor(pressColor);


        StateListDrawable stateListDrawableForShape = new StateListDrawable();
        stateListDrawableForShape.addState(new int[]{-android.R.attr.state_pressed}, gradientDrawableNormal);
        stateListDrawableForShape.addState(new int[]{android.R.attr.state_pressed}, gradientDrawablePress);
        stateListDrawableForShape.addState(new int[]{}, gradientDrawableNormal);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            myView.setBackground(stateListDrawableForShape);
        } else {
            myView.setBackgroundDrawable(stateListDrawableForShape);
        }
    }

    private void hasPartBorderNoPressColor(View myView) {
        setBorderWidthForPartBorder();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            myView.setBackground(getHasPartBorderNoPressColorLayerDrawableNormal());
        } else {
            myView.setBackgroundDrawable(getHasPartBorderNoPressColorLayerDrawableNormal());
        }

    }

    private void hasPartBorderHasPressColor(View myView) {
        setBorderWidthForPartBorder();


        GradientDrawable gradientDrawable = getHasPartBorderNoPressColorGradientDrawable();
        GradientDrawable gradientDrawableNormal = getHasPartBorderNoPressColorGradientDrawableNormal();


        GradientDrawable gradientDrawablePress = getHasPartBorderNoPressColorGradientDrawable();
        GradientDrawable gradientDrawableNormalPress = getHasPartBorderNoPressColorGradientDrawableNormal();
        gradientDrawableNormalPress.setColor(pressColor);

        LayerDrawable layerDrawableNormal = new LayerDrawable(new Drawable[]{gradientDrawable, gradientDrawableNormal});
        layerDrawableNormal.setLayerInset(1, partBorderWidth[0], partBorderWidth[1], partBorderWidth[2], partBorderWidth[3]);//第一层的偏移量

        LayerDrawable layerDrawablePress = new LayerDrawable(new Drawable[]{gradientDrawablePress, gradientDrawableNormalPress});
        layerDrawablePress.setLayerInset(1, partBorderWidth[0], partBorderWidth[1], partBorderWidth[2], partBorderWidth[3]);//第一层的偏移量


        StateListDrawable stateListDrawableForLayer = new StateListDrawable();
        stateListDrawableForLayer.addState(new int[]{-android.R.attr.state_pressed}, layerDrawableNormal);
        stateListDrawableForLayer.addState(new int[]{android.R.attr.state_pressed}, layerDrawablePress);
        stateListDrawableForLayer.addState(new int[]{}, layerDrawableNormal);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            myView.setBackground(stateListDrawableForLayer);
        } else {
            myView.setBackgroundDrawable(stateListDrawableForLayer);
        }
    }

    public GradientDrawable getHasPartBorderNoPressColorGradientDrawable() {
        //底层
        GradientDrawable layerDrawable = new GradientDrawable();
        layerDrawable.setShape(shapeType);

        layerDrawable.setStroke((int) borderWidth, borderColor, borderDashWidth, borderDashGap);
        layerDrawable.setColor(solidColor);


        float[] fourRadius = new float[]{topLeftRadius, topLeftRadius, topRightRadius, topRightRadius, bottomRightRadius, bottomRightRadius, bottomLeftRadius, bottomLeftRadius};
        layerDrawable.setCornerRadii(fourRadius);

        return layerDrawable;
    }

    public GradientDrawable getHasPartBorderNoPressColorGradientDrawableNormal() {
        //顶层
        GradientDrawable layerGradientDrawableNormal = new GradientDrawable();
        layerGradientDrawableNormal.setShape(shapeType);

        if (solidColor == getTransparentColor()) {//透明
            layerGradientDrawableNormal.setColor(Color.parseColor("#ffffffff"));//白色
        } else {
            layerGradientDrawableNormal.setColor(solidColor);
        }


        float[] fourRadius = new float[]{topLeftRadius, topLeftRadius, topRightRadius, topRightRadius, bottomRightRadius, bottomRightRadius, bottomLeftRadius, bottomLeftRadius};
        layerGradientDrawableNormal.setCornerRadii(fourRadius);

        return layerGradientDrawableNormal;
    }

    public LayerDrawable getHasPartBorderNoPressColorLayerDrawableNormal() {
        GradientDrawable layerDrawable = getHasPartBorderNoPressColorGradientDrawable();
        GradientDrawable layerGradientDrawableNormal = getHasPartBorderNoPressColorGradientDrawableNormal();

        Drawable[] layers = new Drawable[2];
        layers[0] = layerDrawable;
        layers[1] = layerGradientDrawableNormal;

        LayerDrawable layerDrawableNormal = new LayerDrawable(layers);

        layerDrawableNormal.setLayerInset(1, partBorderWidth[0], partBorderWidth[1], partBorderWidth[2], partBorderWidth[3]);//第一层的偏移量
        return layerDrawableNormal;
    }

    protected void setBorderWidthForPartBorder() {
        partBorderWidth = new int[]{0, 0, 0, 0};
        if (left_line) {
            if (borderWidth == 0) {
                borderWidth = 1;
            }
            partBorderWidth[0] = (int) borderWidth;
            if (borderColor == getTransparentColor()) {
                borderColor = getDefBorderColor();
            }
        }
        if (top_line) {
            if (borderWidth == 0) {
                borderWidth = 1;
            }
            partBorderWidth[1] = (int) borderWidth;
            if (borderColor == getTransparentColor()) {
                borderColor = getDefBorderColor();
            }
        }
        if (right_line) {
            if (borderWidth == 0) {
                borderWidth = 1;
            }
            partBorderWidth[2] = (int) borderWidth;
            if (borderColor == getTransparentColor()) {
                borderColor = getDefBorderColor();
            }
        }
        if (bottom_line) {
            if (borderWidth == 0) {
                borderWidth = 1;
            }
            partBorderWidth[3] = (int) borderWidth;
            if (borderColor == getTransparentColor()) {
                borderColor = getDefBorderColor();
            }
        }
    }

    public GradientDrawable getNoPartBorderNoPressColorGradientDrawable(boolean isSetGradientType) {
        GradientDrawable gradientDrawableNormal = new GradientDrawable();

        gradientDrawableNormal.setShape(shapeType);

        if (borderWidth >0) {
            if (borderColor == Color.TRANSPARENT) {
                borderColor = getDefBorderColor();
            }
        }
        gradientDrawableNormal.setStroke((int) borderWidth, borderColor, borderDashWidth, borderDashGap);

        gradientDrawableNormal.setColor(solidColor);


        float[] fourRadius = new float[]{
                topLeftRadius, topLeftRadius,
                topRightRadius, topRightRadius,
                bottomRightRadius, bottomRightRadius,
                bottomLeftRadius, bottomLeftRadius};
        gradientDrawableNormal.setCornerRadii(fourRadius);
        if (isSetGradientType) {
            setDrawableGradientType(gradientDrawableNormal);
        }

        return gradientDrawableNormal;
    }

    private void setDrawableGradientType(GradientDrawable gradientDrawableNormal) {
        if (gradientType != -1) {
            /*gradient属性*/
            gradientDrawableNormal.setGradientCenter(gradientCenterX, gradientCenterY);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                gradientDrawableNormal.setOrientation(getViewOrientation(gradientAngle));
            }

            if (gradientCenterColor != 0) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    gradientDrawableNormal.setColors(new int[]{gradientStartColor, gradientCenterColor, gradientEndColor});
                }
            } else if (gradientStartColor != 0 || gradientEndColor != 0) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    gradientDrawableNormal.setColors(new int[]{gradientStartColor, gradientEndColor});
                }
            }
            if (gradientType == gradientType_radial) {
                gradientDrawableNormal.setGradientRadius(gradientRadius);
                gradientDrawableNormal.setUseLevel(false);
            }
            gradientDrawableNormal.setGradientType(gradientType);
        }

    }

    public GradientDrawable.Orientation getViewOrientation(int angle) {
        GradientDrawable.Orientation orientation = GradientDrawable.Orientation.LEFT_RIGHT;
        switch (angle) {
            case 0:
                orientation = GradientDrawable.Orientation.LEFT_RIGHT;
                break;
            case 45:
                orientation = GradientDrawable.Orientation.BL_TR;
                break;
            case 90:
                orientation = GradientDrawable.Orientation.BOTTOM_TOP;
                break;
            case 135:
                orientation = GradientDrawable.Orientation.BR_TL;
                break;
            case 180:
                orientation = GradientDrawable.Orientation.RIGHT_LEFT;
                break;
            case 225:
                orientation = GradientDrawable.Orientation.TR_BL;
                break;
            case 270:
                orientation = GradientDrawable.Orientation.TOP_BOTTOM;
                break;
            case 315:
                orientation = GradientDrawable.Orientation.TL_BR;
                break;
        }
        return orientation;
    }



    /**
     * 默认边框颜色-灰色
     *
     * @return
     */
    protected int getDefBorderColor() {
        return Color.parseColor("#E2E2E2");
    }

    /**
     * 透明
     *
     * @return
     */
    protected int getDefColor() {
        return Color.parseColor("#00000000");
    }

    protected int getTransparentColor() {
        return Color.parseColor("#00000000");
    }



    public Drawable getDrawable_normal() {
        return drawable_normal;
    }

    public BaseHelper setDrawable_normal(Drawable drawable_normal) {
        this.drawable_normal = drawable_normal;
        return this;
    }

    public Drawable getDrawable_press() {
        return drawable_press;
    }

    public BaseHelper setDrawable_press(Drawable drawable_press) {
        this.drawable_press = drawable_press;
        return this;
    }

    public int getPressColor() {
        return pressColor;
    }

    public BaseHelper setPressColor(int pressColor) {
        this.pressColor = pressColor;
        return this;
    }



    public boolean isLeft_line() {
        return left_line;
    }

    public BaseHelper setLeft_line(boolean left_line) {
        this.left_line = left_line;
        return this;
    }

    public boolean isTop_line() {
        return top_line;
    }

    public BaseHelper setTop_line(boolean top_line) {
        this.top_line = top_line;
        return this;
    }

    public boolean isRight_line() {
        return right_line;
    }

    public BaseHelper setRight_line(boolean right_line) {
        this.right_line = right_line;
        return this;
    }

    public boolean isBottom_line() {
        return bottom_line;
    }

    public BaseHelper setBottom_line(boolean bottom_line) {
        this.bottom_line = bottom_line;
        return this;
    }

    public int getShapeType() {
        return shapeType;
    }

    public BaseHelper setShapeType(@shapeType int shapeType) {
        this.shapeType = shapeType;
        return this;
    }

    public float getBorderWidth() {
        return borderWidth;
    }

    public BaseHelper setBorderWidth(float borderWidth) {
        this.borderWidth = borderWidth;
        return this;
    }

    public int getBorderColor() {
        return borderColor;
    }

    public BaseHelper setBorderColor(int borderColor) {
        this.borderColor = borderColor;
        return this;
    }

    public float getBorderDashWidth() {
        return borderDashWidth;
    }

    public BaseHelper setBorderDashWidth(float borderDashWidth) {
        this.borderDashWidth = borderDashWidth;
        return this;
    }

    public float getBorderDashGap() {
        return borderDashGap;
    }

    public BaseHelper setBorderDashGap(float borderDashGap) {
        this.borderDashGap = borderDashGap;
        return this;
    }

    public int getSolidColor() {
        return solidColor;
    }

    public BaseHelper setSolidColor(int solidColor) {
        this.solidColor = solidColor;
        return this;
    }


    public BaseHelper setRadius(float topLeftRadius, float topRightRadius, float bottomRightRadius, float bottomLeftRadius) {
        setTopLeftRadius(topLeftRadius);
        setTopRightRadius(topRightRadius);
        setBottomRightRadius(bottomRightRadius);
        setBottomLeftRadius(bottomLeftRadius);
        return this;
    }
    public BaseHelper setRadius(float radius) {
        return setRadius(radius,radius,radius,radius);
    }

    public float getTopLeftRadius() {
        return topLeftRadius;
    }

    public BaseHelper setTopLeftRadius(float topLeftRadius) {
        this.topLeftRadius = topLeftRadius;
        return this;
    }

    public float getTopRightRadius() {
        return topRightRadius;
    }

    public BaseHelper setTopRightRadius(float topRightRadius) {
        this.topRightRadius = topRightRadius;
        return this;
    }

    public float getBottomLeftRadius() {
        return bottomLeftRadius;
    }

    public BaseHelper setBottomLeftRadius(float bottomLeftRadius) {
        this.bottomLeftRadius = bottomLeftRadius;
        return this;
    }

    public float getBottomRightRadius() {
        return bottomRightRadius;
    }

    public BaseHelper setBottomRightRadius(float bottomRightRadius) {
        this.bottomRightRadius = bottomRightRadius;
        return this;
    }

    public int getGradientType() {
        return gradientType;
    }

    public BaseHelper setGradientType(@gradientType int gradientType) {
        this.gradientType = gradientType;
        return this;
    }

    public int getGradientAngle() {
        return gradientAngle;
    }

    public BaseHelper setGradientAngle(@angleType int gradientAngle) {
        this.gradientAngle = gradientAngle;
        return this;
    }

    public float getGradientCenterX() {
        return gradientCenterX;
    }

    public BaseHelper setGradientCenterX(float gradientCenterX) {
        this.gradientCenterX = gradientCenterX;
        return this;
    }

    public float getGradientCenterY() {
        return gradientCenterY;
    }

    public BaseHelper setGradientCenterY(float gradientCenterY) {
        this.gradientCenterY = gradientCenterY;
        return this;
    }

    public int getGradientStartColor() {
        return gradientStartColor;
    }

    public BaseHelper setGradientStartColor(int gradientStartColor) {
        this.gradientStartColor = gradientStartColor;
        return this;
    }

    public int getGradientCenterColor() {
        return gradientCenterColor;
    }

    public BaseHelper setGradientCenterColor(int gradientCenterColor) {
        this.gradientCenterColor = gradientCenterColor;
        return this;
    }

    public int getGradientEndColor() {
        return gradientEndColor;
    }

    public BaseHelper setGradientEndColor(int gradientEndColor) {
        this.gradientEndColor = gradientEndColor;
        return this;
    }

    public float getGradientRadius() {
        return gradientRadius;
    }

    public BaseHelper setGradientRadius(float gradientRadius) {
        this.gradientRadius = gradientRadius;
        return this;
    }
}
