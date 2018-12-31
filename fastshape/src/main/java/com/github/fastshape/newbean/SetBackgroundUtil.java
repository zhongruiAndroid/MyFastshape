package com.github.fastshape.newbean;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.view.View;
import android.widget.TextView;

import java.math.BigDecimal;

import static com.github.fastshape.newbean.FirstHelper.gradientType_radial;

/***
 *   created by zhongrui on 2018/12/31
 */
public class SetBackgroundUtil {


    public static<T extends FirstHelper>void viewComplete(View myView,T firstHelper) {
        if (firstHelper.drawable_normal != null) {
            StateListDrawable stateListDrawableForLayer = new StateListDrawable();
            stateListDrawableForLayer.addState(new int[]{-android.R.attr.state_pressed}, firstHelper.drawable_normal);
            stateListDrawableForLayer.addState(new int[]{android.R.attr.state_pressed}, firstHelper.drawable_press);
            stateListDrawableForLayer.addState(new int[]{}, firstHelper.drawable_normal);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                myView.setBackground(stateListDrawableForLayer);
            } else {
                myView.setBackgroundDrawable(stateListDrawableForLayer);
            }
            return;
        }

        if (firstHelper.all_line || (!firstHelper.left_line && !firstHelper.top_line && !firstHelper.right_line && !firstHelper.bottom_line)) {
            firstHelper.isPartBorder = false;
        }
        if (!firstHelper.all_line && (firstHelper.left_line || firstHelper.top_line || firstHelper.right_line || firstHelper.bottom_line)) {
            firstHelper.isPartBorder = true;
        }

        //设置虚线需要设置layertype
        if (firstHelper.shapeType == firstHelper.shapeType_line && myView.getLayerType() == View.LAYER_TYPE_NONE) {
            myView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        //是否是全边框
        if (!firstHelper.isPartBorder) {
            //是否设置pressColor
            if (firstHelper.pressColor == Color.TRANSPARENT) {
                //无部分边框，无presscolor
                noPartBorderNoPressColor(myView,firstHelper);
            } else {
                //无部分边框，有presscolor
                noPartBorderHasPressColor(myView,firstHelper);
            }
        } else {
            //是否设置pressColor
            if (firstHelper.pressColor == Color.TRANSPARENT) {
                //有部分边框，无presscolor
                hasPartBorderNoPressColor(myView,firstHelper);
            } else {
                //有部分边框，有presscolor
                hasPartBorderHasPressColor(myView,firstHelper);
            }
        }
        /*if (myView instanceof MyButton) {
            setCompoundDrawables((TextView) myView,secondHelper);
        } else if(myView instanceof MyTextView) {
            setCompoundDrawables((TextView)myView,secondHelper);
        }else if(myView instanceof MyCheckBox){
            setCompoundDrawables((TextView)myView,secondHelper);
        }else if(myView instanceof MyRadioButton){
            setCompoundDrawables((TextView)myView,secondHelper);
        }*/
    }

    public static<T extends SecondHelper>void setCompoundDrawables(TextView myView,T secondHelper) {
        Drawable drawable0 = myView.getCompoundDrawables()[0];
        Drawable drawable1 = myView.getCompoundDrawables()[1];
        Drawable drawable2 = myView.getCompoundDrawables()[2];
        Drawable drawable3 = myView.getCompoundDrawables()[3];

        if (drawable0 != null) {
            int width = drawable0.getIntrinsicWidth();
            int height = drawable0.getIntrinsicHeight();
            drawable0.setBounds(0, 0, getLeftWH(width, height,secondHelper)[0], getLeftWH(width, height,secondHelper)[1]);
        }
        if (drawable1 != null) {
            int width = drawable1.getIntrinsicWidth();
            int height = drawable1.getIntrinsicHeight();
            drawable1.setBounds(0, 0, getTopWH(width, height,secondHelper)[0], getTopWH(width, height,secondHelper)[1]);
        }
        if (drawable2 != null) {
            int width = drawable2.getIntrinsicWidth();
            int height = drawable2.getIntrinsicHeight();
            drawable2.setBounds(0, 0, getRightWH(width, height,secondHelper)[0], getRightWH(width, height,secondHelper)[1]);
        }
        if (drawable3 != null) {
            int width = drawable3.getIntrinsicWidth();
            int height = drawable3.getIntrinsicHeight();
            drawable3.setBounds(0, 0, getBottomWH(width, height,secondHelper)[0], getBottomWH(width, height,secondHelper)[1]);
        }


        myView.setCompoundDrawables(drawable0, drawable1, drawable2, drawable3);
    }

    public static<T extends FirstHelper>void noPartBorderNoPressColor(View myView,T firstHelper) {
        GradientDrawable gradientDrawableNormal = getNoPartBorderNoPressColorGradientDrawable(true,firstHelper);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            myView.setBackground(gradientDrawableNormal);
        } else {
            myView.setBackgroundDrawable(gradientDrawableNormal);
        }
    }

    public static<T extends FirstHelper>void noPartBorderHasPressColor(View myView,T firstHelper) {

        GradientDrawable gradientDrawableNormal = getNoPartBorderNoPressColorGradientDrawable(false,firstHelper);
        GradientDrawable gradientDrawablePress = getNoPartBorderNoPressColorGradientDrawable(false,firstHelper);
        gradientDrawablePress.setColor(firstHelper.pressColor);


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

    public static<T extends FirstHelper>void hasPartBorderNoPressColor(View myView,T firstHelper) {
        setBorderWidthForPartBorder(firstHelper);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            myView.setBackground(getHasPartBorderNoPressColorLayerDrawableNormal(firstHelper));
        } else {
            myView.setBackgroundDrawable(getHasPartBorderNoPressColorLayerDrawableNormal(firstHelper));
        }

    }

    public static<T extends FirstHelper>void hasPartBorderHasPressColor(View myView,T firstHelper) {
        setBorderWidthForPartBorder(firstHelper);


        GradientDrawable gradientDrawable = getHasPartBorderNoPressColorGradientDrawable(firstHelper);
        GradientDrawable gradientDrawableNormal = getHasPartBorderNoPressColorGradientDrawableNormal(firstHelper);


        GradientDrawable gradientDrawablePress = getHasPartBorderNoPressColorGradientDrawable(firstHelper);
        GradientDrawable gradientDrawableNormalPress = getHasPartBorderNoPressColorGradientDrawableNormal(firstHelper);
        gradientDrawableNormalPress.setColor(firstHelper.pressColor);

        LayerDrawable layerDrawableNormal = new LayerDrawable(new Drawable[]{gradientDrawable, gradientDrawableNormal});
        layerDrawableNormal.setLayerInset(1, firstHelper.partBorderWidth[0], firstHelper.partBorderWidth[1], firstHelper.partBorderWidth[2], firstHelper.partBorderWidth[3]);//第一层的偏移量

        LayerDrawable layerDrawablePress = new LayerDrawable(new Drawable[]{gradientDrawablePress, gradientDrawableNormalPress});
        layerDrawablePress.setLayerInset(1, firstHelper.partBorderWidth[0], firstHelper.partBorderWidth[1], firstHelper.partBorderWidth[2], firstHelper.partBorderWidth[3]);//第一层的偏移量


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

    public static <T extends FirstHelper>GradientDrawable getHasPartBorderNoPressColorGradientDrawable(T firstHelper) {
        //底层
        GradientDrawable layerDrawable = new GradientDrawable();
        layerDrawable.setShape(firstHelper.shapeType);

        layerDrawable.setStroke((int) firstHelper.borderWidth, firstHelper.borderColor, firstHelper.borderDashWidth, firstHelper.borderDashGap);
        layerDrawable.setColor(firstHelper.solidColor);


        float[] fourRadius = new float[]{firstHelper.topLeftRadius, firstHelper.topLeftRadius, firstHelper.topRightRadius, firstHelper.topRightRadius, firstHelper.bottomRightRadius , firstHelper.bottomRightRadius , firstHelper.bottomLeftRadius , firstHelper.bottomLeftRadius};
        layerDrawable.setCornerRadii(fourRadius);

        return layerDrawable;
    }

    public static<T extends FirstHelper> GradientDrawable getHasPartBorderNoPressColorGradientDrawableNormal(T firstHelper) {
        //顶层
        GradientDrawable layerGradientDrawableNormal = new GradientDrawable();
        layerGradientDrawableNormal.setShape(firstHelper.shapeType);

        if (firstHelper.solidColor == getTransparentColor()) {//透明
            layerGradientDrawableNormal.setColor(Color.parseColor("#ffffffff"));//白色
        } else {
            layerGradientDrawableNormal.setColor(firstHelper.solidColor);
        }


        float[] fourRadius = new float[]{firstHelper.topLeftRadius, firstHelper.topLeftRadius, firstHelper.topRightRadius, firstHelper.topRightRadius, firstHelper.bottomRightRadius, firstHelper.bottomRightRadius, firstHelper.bottomLeftRadius, firstHelper.bottomLeftRadius};
        layerGradientDrawableNormal.setCornerRadii(fourRadius);

        return layerGradientDrawableNormal;
    }

    public static<T extends FirstHelper> LayerDrawable getHasPartBorderNoPressColorLayerDrawableNormal(T firstHelper) {
        GradientDrawable layerDrawable = getHasPartBorderNoPressColorGradientDrawable(firstHelper);
        GradientDrawable layerGradientDrawableNormal = getHasPartBorderNoPressColorGradientDrawableNormal(firstHelper);

        Drawable[] layers = new Drawable[2];
        layers[0] = layerDrawable;
        layers[1] = layerGradientDrawableNormal;

        LayerDrawable layerDrawableNormal = new LayerDrawable(layers);

        layerDrawableNormal.setLayerInset(1, firstHelper.partBorderWidth[0], firstHelper.partBorderWidth[1], firstHelper.partBorderWidth[2], firstHelper.partBorderWidth[3]);//第一层的偏移量
        return layerDrawableNormal;
    }

    protected static<T extends FirstHelper> void setBorderWidthForPartBorder(T firstHelper) {
        int[] partBorderWidth = new int[]{0, 0, 0, 0};
        if (firstHelper.left_line) {
            if (firstHelper.borderWidth == 0) {
                firstHelper.borderWidth = 1;
            }
            partBorderWidth[0] = (int) firstHelper.borderWidth;
            if (firstHelper.borderColor == getTransparentColor()) {
                firstHelper.borderColor = getDefBorderColor();
            }
        }
        if (firstHelper.top_line) {
            if (firstHelper.borderWidth == 0) {
                firstHelper.borderWidth = 1;
            }
            partBorderWidth[1] = (int) firstHelper.borderWidth;
            if (firstHelper.borderColor == getTransparentColor()) {
                firstHelper.borderColor = getDefBorderColor();
            }
        }
        if (firstHelper.right_line) {
            if (firstHelper.borderWidth == 0) {
                firstHelper.borderWidth = 1;
            }
            partBorderWidth[2] = (int) firstHelper.borderWidth;
            if (firstHelper.borderColor == getTransparentColor()) {
                firstHelper.borderColor = getDefBorderColor();
            }
        }
        if (firstHelper.bottom_line) {
            if (firstHelper.borderWidth == 0) {
                firstHelper.borderWidth = 1;
            }
            partBorderWidth[3] = (int) firstHelper.borderWidth;
            if (firstHelper.borderColor == getTransparentColor()) {
                firstHelper.borderColor = getDefBorderColor();
            }
        }
        firstHelper.partBorderWidth=partBorderWidth;
    }

    public static<T extends FirstHelper>GradientDrawable getNoPartBorderNoPressColorGradientDrawable(boolean isSetGradientType,T firstHelper) {
        GradientDrawable gradientDrawableNormal = new GradientDrawable();

        gradientDrawableNormal.setShape(firstHelper.shapeType);

        if (firstHelper.all_line) {
            if (firstHelper.borderColor == Color.TRANSPARENT) {
                firstHelper.borderColor = getDefBorderColor();
            }
            if (firstHelper.borderWidth <= 0) {
                firstHelper.borderWidth = 1;
            }
        }
        gradientDrawableNormal.setStroke((int) firstHelper.borderWidth, firstHelper.borderColor, firstHelper.borderDashWidth, firstHelper.borderDashGap);

        gradientDrawableNormal.setColor(firstHelper.solidColor);


        float[] fourRadius = new float[]{
                firstHelper.topLeftRadius, firstHelper.topLeftRadius,
                firstHelper.topRightRadius, firstHelper.topRightRadius,
                firstHelper.bottomRightRadius, firstHelper.bottomRightRadius,
                firstHelper.bottomLeftRadius, firstHelper.bottomLeftRadius};
        gradientDrawableNormal.setCornerRadii(fourRadius);
        if (isSetGradientType) {
            setDrawableGradientType(gradientDrawableNormal,firstHelper);
        }

        return gradientDrawableNormal;
    }

    public static<T extends FirstHelper>void setDrawableGradientType(GradientDrawable gradientDrawableNormal,T firstHelper) {
        if (firstHelper.gradientType != -1) {
            /*gradient属性*/
            gradientDrawableNormal.setGradientCenter(firstHelper.gradientCenterX, firstHelper.gradientCenterY);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                gradientDrawableNormal.setOrientation(getViewOrientation(firstHelper.gradientAngle));
            }

            if (firstHelper.gradientCenterColor != 0) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    gradientDrawableNormal.setColors(new int[]{firstHelper.gradientStartColor, firstHelper.gradientCenterColor, firstHelper.gradientEndColor});
                }
            } else if (firstHelper.gradientStartColor != 0 || firstHelper.gradientEndColor != 0) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    gradientDrawableNormal.setColors(new int[]{firstHelper.gradientStartColor, firstHelper.gradientEndColor});
                }
            }
            if (firstHelper.gradientType == gradientType_radial) {
                gradientDrawableNormal.setGradientRadius(firstHelper.gradientRadius);
                gradientDrawableNormal.setUseLevel(false);
            }
            gradientDrawableNormal.setGradientType(firstHelper.gradientType);
        }

    }

    public static GradientDrawable.Orientation getViewOrientation(int angle) {
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


    protected static<T extends SecondHelper>int[] getLeftWH(int width,int height,T secondHelper){
        if(secondHelper.left_width!=-1&&secondHelper.left_height!=-1){
            return new int[]{secondHelper.left_width,secondHelper.left_height};
        }else if(secondHelper.left_width!=-1){
            return new int[]{secondHelper.left_width, (int) chuFa(chengFa(secondHelper.left_width,height),width)};
        }else if(secondHelper.left_height!=-1){
            return new int[]{secondHelper.left_height, (int) chuFa(chengFa(secondHelper.left_height,width),height)};
        }else{
            return new int[]{width,height};
        }
    }
    protected static<T extends SecondHelper>int[] getTopWH(int width,int height,T secondHelper){
        if(secondHelper.top_width!=-1&&secondHelper.top_height!=-1){
            return new int[]{secondHelper.top_width,secondHelper.top_height};
        }else if(secondHelper.top_width!=-1){
            return new int[]{secondHelper.top_width, (int) chuFa(chengFa(secondHelper.top_width,height),width)};
        }else if(secondHelper.top_height!=-1){
            return new int[]{secondHelper.top_height, (int) chuFa(chengFa(secondHelper.top_height,width),height)};
        }else{
            return new int[]{width,height};
        }
    }
    protected static<T extends SecondHelper>int[] getRightWH(int width,int height,T secondHelper){
        if(secondHelper.right_width!=-1&&secondHelper.right_height!=-1){
            return new int[]{secondHelper.right_width,secondHelper.right_height};
        }else if(secondHelper.right_width!=-1){
            return new int[]{secondHelper.right_width, (int) chuFa(chengFa(secondHelper.right_width,height),width)};
        }else if(secondHelper.right_height!=-1){
            return new int[]{secondHelper.right_height, (int) chuFa(chengFa(secondHelper.right_height,width),height)};
        }else{
            return new int[]{width,height};
        }
    }
    protected static<T extends SecondHelper>int[] getBottomWH(int width,int height,T secondHelper){
        if(secondHelper.bottom_width!=-1&&secondHelper.bottom_height!=-1){
            return new int[]{secondHelper.bottom_width,secondHelper.bottom_height};
        }else if(secondHelper.bottom_width!=-1){
            return new int[]{secondHelper.bottom_width, (int) chuFa(chengFa(secondHelper.bottom_width,height),width)};
        }else if(secondHelper.bottom_height!=-1){
            return new int[]{secondHelper.bottom_height, (int) chuFa(chengFa(secondHelper.bottom_height,width),height)};
        }else{
            return new int[]{width,height};
        }
    }

    protected static double chuFa(double d1,double d2) {
        return chuFa(d1,d2,2);
    }
    private static double chuFa(double d1,double d2,int scale) {
        //  当然在此之前，你要判断分母是否为0，
        //  为0你可以根据实际需求做相应的处理
        try {
            if(d2==0){
                throw new Exception("分母不能为0");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        BigDecimal bd1 = new BigDecimal(Double.toString(d1));
        BigDecimal bd2 = new BigDecimal(Double.toString(d2));
        return bd1.divide
                (bd2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    protected static double chengFa(double d1,double d2){
        BigDecimal bd1 = new BigDecimal(d1);
        BigDecimal bd2 = new BigDecimal(d2);
        return round(bd1.multiply(bd2).doubleValue());
    }
    protected static double round(double value) {
        return round(value,2, BigDecimal.ROUND_HALF_UP);
    }
    protected static double round(double value, int scale,int roundingMode) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(scale, roundingMode);
        double d = bd.doubleValue();
        bd = null;
        return d;
    }

    /**
     * 默认边框颜色-灰色
     *
     * @return
     */
    protected static int getDefBorderColor() {
        return Color.parseColor("#E2E2E2");
    }

    protected static int getTransparentColor() {
        return Color.TRANSPARENT;
    }


}
