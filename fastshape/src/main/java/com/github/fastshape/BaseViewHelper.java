package com.github.fastshape;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.fastshape.inter.OnDrawInter;
import com.github.fastshape.inter.ViewHelperInter;

/**
 * Created by windows10 on 2018/3/28.
 */

public class BaseViewHelper extends Helper implements OnDrawInter {
    /**[1]无press效果,全边框,gradientDrawableNormal*/
    /**[2]无press效果,部分边框,layerDrawableNormal*/
    /**
     * view最后设置的背景
     * [3]有press效果,全边框,selector[gradientDrawablePress,gradientDrawableNormal]
     */
//    protected StateListDrawable stateListDrawableForShape;
//    protected GradientDrawable gradientDrawablePress;
//    protected GradientDrawable gradientDrawableNormal;

    /**
     * view最后设置的背景
     * [4]有press效果,部分边框,selector[layerDrawableNormal,layerDrawablePress]
     */
//    protected StateListDrawable stateListDrawableForLayer;
//    protected LayerDrawable layerDrawablePress;//layerGradientDrawablePress+layerDrawable
//    protected LayerDrawable layerDrawableNormal;//layerGradientDrawableNormal+layerDrawable


//    protected GradientDrawable layerDrawable;//最底层
//    protected GradientDrawable layerGradientDrawableNormal;//最上层
//    protected GradientDrawable layerGradientDrawablePress;//最上层

    private ViewHelperInter viewHelperInter;
    private View view;
    public BaseViewHelper() {
        this(null);
    }
    public BaseViewHelper(ViewHelperInter inter) {
        viewHelperInter=inter;
    }
    public BaseViewHelper(View view,ViewHelperInter inter) {
        viewHelperInter=inter;
        this.view=view;
    }
    public void complete(){
        if(viewHelperInter!=null){
            viewHelperInter.onComplete();
            if(needInvalidate&&view!=null){
                view.invalidate();
                needInvalidate=false;
            }
        }
    }

    public static final int shapeType_rectangle=GradientDrawable.RECTANGLE;
    public static final int shapeType_oval=GradientDrawable.OVAL;
    public static final int shapeType_line=GradientDrawable.LINE;
    public static final int shapeType_ring=GradientDrawable.RING;
    /***
     *"rectangle"	填充包含视图的矩形。这是默认形状。
     *"oval"	适应包含视图尺寸的椭圆形状。
     *"line"	跨越包含视图宽度的水平线。此形状需要 <stroke> 元素定义线宽。
     *"ring"	环形。
     */
    protected Integer shapeType=GradientDrawable.RECTANGLE;
    protected boolean useLevel;
    /*** 是否显示部分边框*/
    protected boolean isPartBorder;

    /*** 是否显示边框*/
    protected boolean allLine;
    /***左边框*/
    protected boolean leftLine;
    /*** 顶部边框*/
    protected boolean topLine;
    /*** 右边框*/
    protected boolean rightLine;
    /***底部边框*/
    protected boolean bottomLine;
    /***边框宽度*/
    protected float borderWidth;
    /***填充色*/
    protected int solidColor;
    /***触摸颜色*/
    protected int pressColor;
    /***边框颜色*/
    protected int borderColor;
    /***虚线长度*/
    protected float borderDashWidth;
    /***虚线间隔*/
    protected float borderDashGap;

    /***左上圆角半径*/
    protected float topLeftRadius;
    /***右上圆角半径*/
    protected float topRightRadius;
    /***左下圆角半径*/
    protected float bottomLeftRadius;
    /***右下圆角半径*/
    protected float bottomRightRadius;
    /***部分边框宽度*/
    protected int[] partBorderWidth;

    /*直接设置图片为背景*/
    protected Drawable drawable_normal;
    protected Drawable drawable_press;


    /***整型。渐变的角度（度）。0 为从左到右，90 为从上到上。必须是 45 的倍数。默认值为 0。*/
    protected Integer angle=0;
    /***浮点型。渐变中心的相对 X 轴位置 (0 - 1.0)。*/
    protected float centerX;
    /*浮点型。渐变中心的相对 Y 轴位置 (0 - 1.0)。*/
    protected float centerY;
    /*颜色。起始颜色，表示为十六进制值或颜色资源。*/
    protected int startColor;
    /*颜色。起始颜色与结束颜色之间的可选颜色，以十六进制值或颜色资源表示。*/
    protected int centerColor;
    /*颜色。结束颜色，表示为十六进制值或颜色资源。*/
    protected int endColor;
    /*浮点型。渐变的半径。仅在 android:type="radial" 时适用。*/
    protected float gradientRadius;


    public static final int gradientType_linear=GradientDrawable.LINEAR_GRADIENT;
    public static final int gradientType_radial=GradientDrawable.RADIAL_GRADIENT;
    public static final int gradientType_sweep=GradientDrawable.SWEEP_GRADIENT;
    /**关键字。要应用的渐变图案的类型。有效值为：
     * "linear"	线性渐变。这是默认值。
     *" radial"	径向渐变。起始颜色为中心颜色。
     * "sweep"	流线型渐变。
     */
    protected Integer gradientType=GradientDrawable.LINEAR_GRADIENT;



    public BaseViewHelper setShapeType(Integer shapeType) {
        this.shapeType = shapeType;
        return this;
    }

    public BaseViewHelper setUseLevel(boolean useLevel) {
        this.useLevel = useLevel;
        return this;
    }

    public BaseViewHelper setAllLine(boolean allLine) {
        this.allLine = allLine;
        return this;
    }

    public BaseViewHelper setLeftLine(boolean leftLine) {
        this.leftLine = leftLine;
        return this;
    }

    public BaseViewHelper setTopLine(boolean topLine) {
        this.topLine = topLine;
        return this;
    }

    public BaseViewHelper setRightLine(boolean rightLine) {
        this.rightLine = rightLine;
        return this;
    }

    public BaseViewHelper setBottomLine(boolean bottomLine) {
        this.bottomLine = bottomLine;
        return this;
    }

    public BaseViewHelper setBorderWidth(float borderWidth) {
        this.borderWidth = borderWidth;
        return this;
    }

    public BaseViewHelper setSolidColor(@ColorInt int solidColor) {
        this.solidColor = solidColor;
        return this;
    }

    public BaseViewHelper setPressColor(@ColorInt int pressColor) {
        this.pressColor = pressColor;
        return this;
    }

    public BaseViewHelper setBorderColor(@ColorInt int borderColor) {
        this.borderColor = borderColor;
        return this;
    }

    public BaseViewHelper setBorderDashWidth(float borderDashWidth) {
        this.borderDashWidth = borderDashWidth;
        return this;
    }

    public BaseViewHelper setBorderDashGap(float borderDashGap) {
        this.borderDashGap = borderDashGap;
        return this;
    }

    public BaseViewHelper setRadius(float radius) {
        setTopLeftRadius(radius);
        setTopRightRadius(radius);
        setBottomRightRadius(radius);
        setBottomLeftRadius(radius);
        return this;
    }

    public BaseViewHelper setTopLeftRadius(float topLeftRadius) {
        this.topLeftRadius = topLeftRadius;
        return this;
    }

    public BaseViewHelper setTopRightRadius(float topRightRadius) {
        this.topRightRadius = topRightRadius;
        return this;
    }

    public BaseViewHelper setBottomLeftRadius(float bottomLeftRadius) {
        this.bottomLeftRadius = bottomLeftRadius;
        return this;
    }

    public BaseViewHelper setBottomRightRadius(float bottomRightRadius) {
        this.bottomRightRadius = bottomRightRadius;
        return this;
    }

    public BaseViewHelper setDrawable_normal(Drawable drawable_normal) {
        this.drawable_normal = drawable_normal;
        return this;
    }

    public BaseViewHelper setDrawable_press(Drawable drawable_press) {
        this.drawable_press = drawable_press;
        return this;
    }

    public BaseViewHelper setAngle(Integer angle) {
        this.angle = angle;
        return this;
    }

    public BaseViewHelper setCenterX(float centerX) {
        this.centerX = centerX;
        return this;
    }

    public BaseViewHelper setCenterY(float centerY) {
        this.centerY = centerY;
        return this;
    }

    public BaseViewHelper setStartColor(int startColor) {
        this.startColor = startColor;
        return this;
    }

    public BaseViewHelper setCenterColor(@ColorInt int centerColor) {
        this.centerColor = centerColor;
        return this;
    }

    public BaseViewHelper setEndColor(@ColorInt int endColor) {
        this.endColor = endColor;
        return this;
    }

    public BaseViewHelper setGradientRadius(float gradientRadius) {
        this.gradientRadius = gradientRadius;
        return this;
    }

    public BaseViewHelper setGradientType(Integer gradientType) {
        this.gradientType = gradientType;
        return this;
    }

    public BaseViewHelper clearAttribute(){
        this.drawable_normal=null;
        this.drawable_press=null;

        this.pressColor=getTransparentColor();
        this.allLine=false;
        this.leftLine=false;
        this.topLine=false;
        this.rightLine=false;
        this.bottomLine=false;
        this.shapeType=shapeType_rectangle;
        this.borderWidth=0;
        this.borderColor=getTransparentColor();
        this.borderDashWidth=0;
        this.borderDashGap=0;
        this.solidColor=getTransparentColor();
        this.topLeftRadius=0;
        this.topRightRadius=0;
        this.bottomLeftRadius=0;
        this.bottomRightRadius=0;
        this.gradientType=-1;
        this.angle=0;
        this.centerX=0.5f;
        this.centerY=0.5f;
        this.startColor=0;
        this.centerColor=0;
        this.endColor=0;
        this.gradientRadius=40;

        return this;
    }

    public Integer getShapeType() {
        return shapeType;
    }

    public boolean isUseLevel() {
        return useLevel;
    }

    public boolean isAllLine() {
        return allLine;
    }

    public boolean isLeftLine() {
        return leftLine;
    }

    public boolean isTopLine() {
        return topLine;
    }

    public boolean isRightLine() {
        return rightLine;
    }

    public boolean isBottomLine() {
        return bottomLine;
    }

    public float getBorderWidth() {
        return borderWidth;
    }

    public int getSolidColor() {
        return solidColor;
    }

    public int getPressColor() {
        return pressColor;
    }

    public int getBorderColor() {
        return borderColor;
    }

    public float getBorderDashWidth() {
        return borderDashWidth;
    }

    public float getBorderDashGap() {
        return borderDashGap;
    }



    public float getTopLeftRadius() {
        return topLeftRadius;
    }

    public float getTopRightRadius() {
        return topRightRadius;
    }

    public float getBottomLeftRadius() {
        return bottomLeftRadius;
    }

    public float getBottomRightRadius() {
        return bottomRightRadius;
    }

    public Drawable getDrawable_normal() {
        return drawable_normal;
    }

    public Drawable getDrawable_press() {
        return drawable_press;
    }

    public Integer getAngle() {
        return angle;
    }

    public float getCenterX() {
        return centerX;
    }

    public float getCenterY() {
        return centerY;
    }

    public int getStartColor() {
        return startColor;
    }

    public int getCenterColor() {
        return centerColor;
    }

    public int getEndColor() {
        return endColor;
    }

    public float getGradientRadius() {
        return gradientRadius;
    }

    public Integer getGradientType() {
        return gradientType;
    }

    public GradientDrawable.Orientation getViewOrientation (int angle){
        GradientDrawable.Orientation orientation= GradientDrawable.Orientation.LEFT_RIGHT;
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
    public GradientDrawable getNoPartBorderNoPressColorGradientDrawable(boolean isSetGradientType){
        GradientDrawable gradientDrawableNormal = new GradientDrawable();

        gradientDrawableNormal.setShape(shapeType);

        if (allLine) {
            if (borderColor == getTransparentColor()) {
                borderColor = getDefBorderColor();
            }
            if (borderWidth <= 0) {
                borderWidth = 1;
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
        if(isSetGradientType){
            setGradientType(gradientDrawableNormal);
        }

        return gradientDrawableNormal;
    }
    private void setGradientType(GradientDrawable gradientDrawableNormal){
        if (gradientType != -1) {
            /*gradient属性*/
            gradientDrawableNormal.setGradientCenter(centerX, centerY);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                gradientDrawableNormal.setOrientation(getViewOrientation(angle));
            }

            if (centerColor != 0) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    gradientDrawableNormal.setColors(new int[]{startColor, centerColor, endColor});
                }
            } else if (startColor != 0 || endColor != 0) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    gradientDrawableNormal.setColors(new int[]{startColor, endColor});
                }
            }
            if (gradientType == gradientType_radial) {
                gradientDrawableNormal.setGradientRadius(gradientRadius);
                gradientDrawableNormal.setUseLevel(false);
            }
            gradientDrawableNormal.setGradientType(gradientType);
        }

    }

    public GradientDrawable getHasPartBorderNoPressColorGradientDrawable(){
        //底层
        GradientDrawable layerDrawable=new GradientDrawable();
        layerDrawable.setShape(shapeType);

        layerDrawable.setStroke((int) borderWidth, borderColor, borderDashWidth, borderDashGap);
        layerDrawable.setColor(solidColor);


            float[] fourRadius= new float[]{topLeftRadius, topLeftRadius, topRightRadius, topRightRadius, bottomRightRadius, bottomRightRadius, bottomLeftRadius, bottomLeftRadius};
            layerDrawable.setCornerRadii(fourRadius);

        return layerDrawable;
    }
    public GradientDrawable getHasPartBorderNoPressColorGradientDrawableNormal(){
        //顶层
        GradientDrawable layerGradientDrawableNormal=new GradientDrawable();
        layerGradientDrawableNormal.setShape(shapeType);

        if(solidColor==getTransparentColor()){//透明
            layerGradientDrawableNormal.setColor(Color.parseColor("#ffffffff"));//白色
        }else{
            layerGradientDrawableNormal.setColor(solidColor);
        }


            float[] fourRadius= new float[]{topLeftRadius, topLeftRadius, topRightRadius, topRightRadius, bottomRightRadius, bottomRightRadius, bottomLeftRadius,bottomLeftRadius};
            layerGradientDrawableNormal.setCornerRadii(fourRadius);

        return layerGradientDrawableNormal;
    }

    public LayerDrawable getHasPartBorderNoPressColorLayerDrawableNormal(){
        GradientDrawable layerDrawable=getHasPartBorderNoPressColorGradientDrawable();
        GradientDrawable layerGradientDrawableNormal=getHasPartBorderNoPressColorGradientDrawableNormal();

        Drawable[] layers = new Drawable[2];
        layers[0] = layerDrawable;
        layers[1] = layerGradientDrawableNormal;

        LayerDrawable layerDrawableNormal=new LayerDrawable(layers);

        layerDrawableNormal.setLayerInset(1, partBorderWidth[0], partBorderWidth[1], partBorderWidth[2], partBorderWidth[3]);//第一层的偏移量
        return layerDrawableNormal;
    }

/*******************************************complete***************************************************/
    /**
     * 设置各个自定义属性之后调用此方法设置background
     * 这里有必要说明一下,为什么设置属性了还需要调用这个方法才能生效?
     * 这个方法是将代码设置的各个属性收集生成一个Drawable,然后将它设置为background,简单点这个方法就是用来设置背景的,等价于setBackground方法
     */
    protected void viewComplete(View myView) {
        if(drawable_normal!=null){
            StateListDrawable stateListDrawableForLayer = new StateListDrawable();
            stateListDrawableForLayer.addState(new int[]{-android.R.attr.state_pressed}, drawable_normal);
            stateListDrawableForLayer.addState(new int[]{android.R.attr.state_pressed}, drawable_press);
            stateListDrawableForLayer.addState(new int[]{}, drawable_normal);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                myView.setBackground(stateListDrawableForLayer);
            }else{
                myView.setBackgroundDrawable(stateListDrawableForLayer);
            }
            return;
        }

        if(allLine||(!leftLine&&!topLine&&!rightLine&&!bottomLine)){
            isPartBorder=false;
        }
        if(!allLine&&(leftLine||topLine||rightLine||bottomLine)){
            isPartBorder=true;
        }

        //设置虚线需要设置layertype
        if(shapeType==shapeType_line&&myView.getLayerType()== View.LAYER_TYPE_NONE){
            myView.setLayerType(View.LAYER_TYPE_SOFTWARE,null);
        }
        //是否是全边框
        if (!isPartBorder) {
            //是否设置pressColor
            if (pressColor == getTransparentColor()) {
                //无部分边框，无presscolor
                noPartBorderNoPressColor(myView);
            } else {
                //无部分边框，有presscolor
                noPartBorderHasPressColor(myView);
            }
        } else {
            //是否设置pressColor
            if (pressColor == getTransparentColor()) {
                //有部分边框，无presscolor
                hasPartBorderNoPressColor(myView);
            } else {
                //有部分边框，有presscolor
                hasPartBorderHasPressColor(myView);
            }
        }
        if(myView instanceof MyButton){
            setCompoundDrawables((Button)myView);
        }else{
            setCompoundDrawables(myView);
        }
    }

    private void setCompoundDrawables(Button myView) {
        Drawable drawable0 = myView.getCompoundDrawables()[0];
        Drawable drawable1 = myView.getCompoundDrawables()[1];
        Drawable drawable2 = myView.getCompoundDrawables()[2];
        Drawable drawable3 = myView.getCompoundDrawables()[3];

        if(drawable0!=null){
            int width=drawable0.getIntrinsicWidth();
            int height=drawable0.getIntrinsicHeight();
            drawable0.setBounds(0,0,getLeftWH(width,height)[0],getLeftWH(width,height)[1]);
        }
        if(drawable1!=null){
            int width=drawable1.getIntrinsicWidth();
            int height=drawable1.getIntrinsicHeight();
            drawable1.setBounds(0,0,getTopWH(width,height)[0],getTopWH(width,height)[1]);
        }
        if(drawable2!=null){
            int width=drawable2.getIntrinsicWidth();
            int height=drawable2.getIntrinsicHeight();
            drawable2.setBounds(0,0,getRightWH(width,height)[0],getRightWH(width,height)[1]);
        }
        if(drawable3!=null){
            int width=drawable3.getIntrinsicWidth();
            int height=drawable3.getIntrinsicHeight();
            drawable3.setBounds(0,0,getBottomWH(width,height)[0],getBottomWH(width,height)[1]);
        }


        myView.setCompoundDrawables(drawable0,drawable1,drawable2,drawable3);
    }
    private void setCompoundDrawables(View view) {
        if(view instanceof TextView){
            TextView myView= (TextView) view;
            Drawable drawable0 = myView.getCompoundDrawables()[0];
            Drawable drawable1 = myView.getCompoundDrawables()[1];
            Drawable drawable2 = myView.getCompoundDrawables()[2];
            Drawable drawable3 = myView.getCompoundDrawables()[3];

            if(drawable0!=null){
                int width=drawable0.getIntrinsicWidth();
                int height=drawable0.getIntrinsicHeight();
                drawable0.setBounds(0,0,getLeftWH(width,height)[0],getLeftWH(width,height)[1]);
            }
            if(drawable1!=null){
                int width=drawable1.getIntrinsicWidth();
                int height=drawable1.getIntrinsicHeight();
                drawable1.setBounds(0,0,getTopWH(width,height)[0],getTopWH(width,height)[1]);
            }
            if(drawable2!=null){
                int width=drawable2.getIntrinsicWidth();
                int height=drawable2.getIntrinsicHeight();
                drawable2.setBounds(0,0,getRightWH(width,height)[0],getRightWH(width,height)[1]);
            }
            if(drawable3!=null){
                int width=drawable3.getIntrinsicWidth();
                int height=drawable3.getIntrinsicHeight();
                drawable3.setBounds(0,0,getBottomWH(width,height)[0],getBottomWH(width,height)[1]);
            }


            myView.setCompoundDrawables(drawable0,drawable1,drawable2,drawable3);
        }
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


        StateListDrawable  stateListDrawableForShape = new StateListDrawable();
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
        }else{
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

        LayerDrawable layerDrawableNormal=new LayerDrawable(new Drawable[]{gradientDrawable,gradientDrawableNormal});
        layerDrawableNormal.setLayerInset(1, partBorderWidth[0], partBorderWidth[1], partBorderWidth[2], partBorderWidth[3]);//第一层的偏移量

        LayerDrawable layerDrawablePress=new LayerDrawable(new Drawable[]{gradientDrawablePress,gradientDrawableNormalPress});
        layerDrawablePress.setLayerInset(1, partBorderWidth[0], partBorderWidth[1], partBorderWidth[2], partBorderWidth[3]);//第一层的偏移量


        StateListDrawable stateListDrawableForLayer = new StateListDrawable();
        stateListDrawableForLayer.addState(new int[]{-android.R.attr.state_pressed}, layerDrawableNormal);
        stateListDrawableForLayer.addState(new int[]{android.R.attr.state_pressed}, layerDrawablePress);
        stateListDrawableForLayer.addState(new int[]{}, layerDrawableNormal);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            myView.setBackground(stateListDrawableForLayer);
        }else{
            myView.setBackgroundDrawable(stateListDrawableForLayer);
        }
    }
/**********************************************************************************************/

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

    protected int getTransparentColor(){
        return Color.parseColor("#00000000");
    }

    protected void setBorderWidthForPartBorder() {
        partBorderWidth=new int[]{0,0,0,0};
        if(leftLine){
            if(borderWidth ==0){
                borderWidth =1;
            }
            partBorderWidth[0]=(int)borderWidth;
            if(borderColor==getTransparentColor()){
                borderColor=getDefBorderColor();
            }
        }
        if(topLine){
            if(borderWidth ==0){
                borderWidth =1;
            }
            partBorderWidth[1]=(int)borderWidth;
            if(borderColor==getTransparentColor()){
                borderColor=getDefBorderColor();
            }
        }
        if(rightLine){
            if(borderWidth ==0){
                borderWidth =1;
            }
            partBorderWidth[2]=(int)borderWidth;
            if(borderColor==getTransparentColor()){
                borderColor=getDefBorderColor();
            }
        }
        if(bottomLine){
            if(borderWidth ==0){
                borderWidth =1;
            }
            partBorderWidth[3]=(int)borderWidth;
            if(borderColor==getTransparentColor()){
                borderColor=getDefBorderColor();
            }
        }
    }

    /**********DrawHelper 裁剪**********/
    protected int errorLayerCount=-100;

    protected Paint clipPaint;
    protected Paint clipBorderPaint;
    protected Paint clipBorderDashBgPaint;

    protected Path clipPath;
    protected Path clipBorderPath;
    protected Region viewRegion;
    protected Region clickRegion;

    protected boolean clipIsCircle =false;
    protected boolean clipIsAreaClick =true;
    protected float clipTopLeftRadius;
    protected float clipTopRightRadius;
    protected float clipBottomLeftRadius;
    protected float clipBottomRightRadius;
    protected float clipBorderWidth;
    protected int clipBorderColor;
    protected float clipBorderDashWidth;
    protected float clipBorderDashGap;
    protected int clipBorderDashBgColor;
    protected float clipBorderPhase=1;

    protected Shader shader;
    private boolean needInvalidate;
    public boolean isClipIsCircle() {
        return clipIsCircle;
    }

    public BaseViewHelper setClipIsCircle(boolean clipIsCircle) {
        this.clipIsCircle = clipIsCircle;
        needInvalidate();
        return this;
    }

    private void needInvalidate() {
        if(needInvalidate==false){
            needInvalidate=true;
        }
    }

    public boolean isClipIsAreaClick() {
        return clipIsAreaClick;
    }

    public BaseViewHelper setClipIsAreaClick(boolean clipIsAreaClick) {
        this.clipIsAreaClick = clipIsAreaClick;
        needInvalidate();
        return this;
    }

    public BaseViewHelper setClipRadius(float clipRadius) {
        setClipTopLeftRadius(clipRadius);
        setClipTopRightRadius(clipRadius);
        setClipBottomRightRadius(clipRadius);
        setClipBottomLeftRadius(clipRadius);
        needInvalidate();
        return this;
    }

    public float getClipTopLeftRadius() {
        return clipTopLeftRadius;
    }

    public BaseViewHelper setClipTopLeftRadius(float clipTopLeftRadius) {
        this.clipTopLeftRadius = clipTopLeftRadius;
        needInvalidate();
        return this;
    }

    public float getClipTopRightRadius() {
        return clipTopRightRadius;
    }

    public BaseViewHelper setClipTopRightRadius(float clipTopRightRadius) {
        this.clipTopRightRadius = clipTopRightRadius;
        needInvalidate();
        return this;
    }

    public float getClipBottomLeftRadius() {
        return clipBottomLeftRadius;
    }

    public BaseViewHelper setClipBottomLeftRadius(float clipBottomLeftRadius) {
        this.clipBottomLeftRadius = clipBottomLeftRadius;
        needInvalidate();
        return this;
    }

    public float getClipBottomRightRadius() {
        return clipBottomRightRadius;
    }

    public BaseViewHelper setClipBottomRightRadius(float clipBottomRightRadius) {
        this.clipBottomRightRadius = clipBottomRightRadius;
        needInvalidate();
        return this;
    }

    public float getClipBorderWidth() {
        return clipBorderWidth;
    }

    public BaseViewHelper setClipBorderWidth(float clipBorderWidth) {
        this.clipBorderWidth = clipBorderWidth;
        needInvalidate();
        return this;
    }

    public int getClipBorderColor() {
        return clipBorderColor;
    }

    public BaseViewHelper setClipBorderColor(@ColorInt int clipBorderColor) {
        this.clipBorderColor = clipBorderColor;
        needInvalidate();
        return this;
    }

    public int getClipBorderDashBgColor() {
        return clipBorderDashBgColor;
    }

    public BaseViewHelper setClipBorderDashBgColor(@ColorInt int clipBorderDashBgColor) {
        this.clipBorderDashBgColor = clipBorderDashBgColor;
        needInvalidate();
        return this;
    }

    public float getClipBorderDashWidth() {
        return clipBorderDashWidth;
    }

    public BaseViewHelper setClipBorderDashWidth(float clipBorderDashWidth) {
        this.clipBorderDashWidth = clipBorderDashWidth;
        needInvalidate();
        return this;
    }

    public float getClipBorderDashGap() {
        return clipBorderDashGap;
    }

    public BaseViewHelper setClipBorderDashGap(float clipBorderDashGap) {
        this.clipBorderDashGap = clipBorderDashGap;
        needInvalidate();
        return this;
    }

    public Shader getShader() {
        return shader;
    }

    public BaseViewHelper setShader(Shader shader) {
        this.shader = shader;
        needInvalidate();
        return this;
    }

    public float getClipBorderPhase() {
        return clipBorderPhase;
    }

    public BaseViewHelper setClipBorderPhase(float clipBorderPhase) {
        this.clipBorderPhase = clipBorderPhase;
        needInvalidate();
        return this;
    }

    /**********DrawHelper 裁剪**********/

    @Override
    public void onSizeChanged(int paddingLeft,int paddingTop,int paddingRight,int paddingBottom,int w, int h, int oldw, int oldh) {

        clickRegion=new Region();

        clipPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        clipPaint.setStyle(Paint.Style.FILL);

        clipBorderPaint =new Paint(Paint.ANTI_ALIAS_FLAG);
        clipBorderPaint.setStyle(Paint.Style.STROKE);


        clipBorderDashBgPaint =new Paint(Paint.ANTI_ALIAS_FLAG);
        clipBorderDashBgPaint.setStyle(Paint.Style.STROKE);

    }

    @Override
    public void onRefreshPaint(Canvas canvas,int paddingLeft,int paddingTop,int paddingRight,int paddingBottom,int w, int h) {
        if(paddingLeft<clipBorderWidth/2){
            paddingLeft= (int) (clipBorderWidth/2);
        }
        if(paddingTop<clipBorderWidth/2){
            paddingTop= (int) (clipBorderWidth/2);
        }
        if(paddingRight<clipBorderWidth/2){
            paddingRight= (int) (clipBorderWidth/2);
        }
        if(paddingBottom<clipBorderWidth/2){
            paddingBottom= (int) (clipBorderWidth/2);
        }
        viewRegion=new Region(0,0,w,h);

        clipPaint.setColor(Color.WHITE);
        clipPaint.setFilterBitmap(false);
        clipPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));

        clipBorderPaint.setColor(clipBorderColor);
        clipBorderPaint.setStrokeWidth(clipBorderWidth);

        clipBorderDashBgPaint.setColor(clipBorderDashBgColor);
        clipBorderDashBgPaint.setStrokeWidth(clipBorderWidth);
       /* float gradientLeft=paddingLeft-borderWidth/2;
        float gradientTop=paddingTop-borderWidth/2;
        float gradientRigth=w-paddingRight+borderWidth/2;
        float gradientBottom=h-paddingBottom+borderWidth/2;*/
        /*LinearGradient linearGradient = new LinearGradient(gradientLeft,
                gradientTop,
                gradientRigth,
                gradientTop,
                new int[]{Color.BLUE,Color.GREEN},
                new float[]{0f,1f}, Shader.TileMode.CLAMP);
*/
//        int  X=(w-paddingLeft-paddingRight)/2+paddingLeft;
//        int  Y=(h-paddingTop-paddingBottom)/2+paddingTop;
//        SweepGradient sweepGradient=new SweepGradient(X,Y,new int[]{Color.BLUE,Color.GREEN},new float[]{0.7f,1f});
//        SweepGradient sweepGradient=new SweepGradient(X,Y,Color.BLUE,Color.GREEN);
//        RadialGradient radialGradient=new RadialGradient(X,Y,(w-paddingLeft-paddingRight)/2,Color.BLUE,Color.GREEN, Shader.TileMode.CLAMP);
        if(shader!=null){
            clipBorderPaint.setShader(shader);
        }
        if(clipBorderDashWidth>0&&clipBorderDashGap>0){
            clipBorderPaint.setPathEffect(new DashPathEffect(new float[]{clipBorderDashWidth,clipBorderDashGap},clipBorderPhase));
        }else{
            clipBorderPaint.setPathEffect(null);
        }

        clipPath=new Path();
        clipBorderPath =new Path();
        float[]radius=new float[8];

        radius[0]=clipTopLeftRadius;
        radius[1]=clipTopLeftRadius;

        radius[2]=clipTopRightRadius;
        radius[3]=clipTopRightRadius;

        radius[4]=clipBottomRightRadius;
        radius[5]=clipBottomRightRadius;

        radius[6]=clipBottomLeftRadius;
        radius[7]=clipBottomLeftRadius;

        RectF rectF=new RectF(paddingLeft,paddingTop,w-paddingRight,h-paddingBottom);
        if(clipIsCircle){
            int  centerX=(w-paddingLeft-paddingRight)/2+paddingLeft;
            int  centerY=(h-paddingTop-paddingBottom)/2+paddingTop;

            int  circleRadius=(w-paddingLeft-paddingRight)>(h-paddingTop-paddingBottom)?(h-paddingTop-paddingBottom)/2:(w-paddingLeft-paddingRight)/2;
            clipPath.addCircle(centerX,centerY,circleRadius, Path.Direction.CW);
            clipBorderPath.addCircle(centerX,centerY,circleRadius, Path.Direction.CW);
        }else{
            clipPath.addRoundRect(rectF,radius, Path.Direction.CW);
            clipBorderPath.addRoundRect(rectF,radius, Path.Direction.CW);
        }

        clickRegion.setPath(clipPath,viewRegion);
        clipPath.moveTo(0,0);
        clipPath.moveTo(w,h);
    }

    @Override
    public int dispatchDrawStart(Canvas canvas) {
        int saveLayer = canvas.saveLayer(new RectF(0, 0, canvas.getWidth(), canvas.getHeight()), null, Canvas.ALL_SAVE_FLAG);
        return saveLayer;
    }
    @Override
    public void dispatchDrawEnd(int saveLayer,Canvas canvas) {
        canvas.drawPath(clipPath,clipPaint);
        if(clipBorderWidth>0){
            if(clipBorderDashBgColor!=Color.TRANSPARENT){
                canvas.drawPath(clipBorderPath, clipBorderDashBgPaint);
            }
            canvas.drawPath(clipBorderPath, clipBorderPaint);
        }
        if(saveLayer!=errorLayerCount){
            canvas.restore();
//            canvas.restoreToCount(saveLayer);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(clipIsAreaClick){
            if(!clickRegion.contains((int)event.getX(),(int)event.getY())){
                return false;
            }
        }
        return true;
    }
}
