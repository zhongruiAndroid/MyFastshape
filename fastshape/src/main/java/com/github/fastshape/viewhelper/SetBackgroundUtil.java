package com.github.fastshape.viewhelper;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;


import java.math.BigDecimal;

import static com.github.fastshape.viewhelper.FirstHelper.gradientType_radial;

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

            /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                myView.setBackground(stateListDrawableForLayer);
            } else {
                myView.setBackgroundDrawable(stateListDrawableForLayer);
            }*/
            myView.setBackground(stateListDrawableForLayer);
            return;
        }
        if (firstHelper.left_line && firstHelper.top_line && firstHelper.right_line && firstHelper.bottom_line) {
            firstHelper.isPartBorder = false;
        }else if(firstHelper.left_line==false && firstHelper.top_line==false && firstHelper.right_line==false && firstHelper.bottom_line==false){
            firstHelper.isPartBorder = false;
        }else {
            firstHelper.isPartBorder = true;
        }



        //设置虚线需要设置layertype
        if (firstHelper.shapeType == firstHelper.shapeType_line && myView.getLayerType() == View.LAYER_TYPE_NONE) {
            if(myView instanceof ImageView){
                //ImageView不做处理
            }else{
                myView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            }
        }else{
            myView.setLayerType(View.LAYER_TYPE_NONE, null);
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

    public static <T extends SecondHelper>void setCompoundDrawables(TextView myView,T secondHelper) {
        boolean leftFlag=secondHelper.getLeft_width()>0||secondHelper.getLeft_height()>0;
        boolean topFlag=secondHelper.getTop_width()>0||secondHelper.getTop_height()>0;
        boolean rightFlag=secondHelper.getRight_width()>0||secondHelper.getRight_height()>0;
        boolean bottomFlag=secondHelper.getBottom_width()>0||secondHelper.getBottom_height()>0;

        if(leftFlag==false&&topFlag==false&&rightFlag==false&&bottomFlag==false){
            return;
        }

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
    public static <T extends ThirdHelper>void setCompoundDrawables(CompoundButton myView,T thirdHelper) {
        setLeftDrawable(myView,thirdHelper);
        setTopDrawable(myView,thirdHelper);
        setRightDrawable(myView,thirdHelper);
        setBottomDrawable(myView,thirdHelper);

        /*if(thirdHelper.normal_drawable !=null&& thirdHelper.checked_drawable !=null){
            StateListDrawable stateListDrawable = new StateListDrawable();
            stateListDrawable.addState(new int[]{android.R.attr.state_checked}, thirdHelper.checked_drawable);
            stateListDrawable.addState(new int[]{}, thirdHelper.normal_drawable);

            Drawable drawable0 = myView.getCompoundDrawables()[0];
            Drawable drawable1 = myView.getCompoundDrawables()[1];
            Drawable drawable2 = myView.getCompoundDrawables()[2];
            Drawable drawable3 = myView.getCompoundDrawables()[3];

            if(drawable0!=null){
                int width=drawable0.getIntrinsicWidth();
                int height=drawable0.getIntrinsicHeight();
                drawable0.setBounds(0,0,getLeftWH(width,height,thirdHelper)[0],getLeftWH(width,height,thirdHelper)[1]);
            }
            if(drawable1!=null){
                int width=drawable1.getIntrinsicWidth();
                int height=drawable1.getIntrinsicHeight();
                drawable1.setBounds(0,0,getTopWH(width,height,thirdHelper)[0],getTopWH(width,height,thirdHelper)[1]);
            }
            if(drawable2!=null){
                int width=drawable2.getIntrinsicWidth();
                int height=drawable2.getIntrinsicHeight();
                drawable2.setBounds(0,0,getRightWH(width,height,thirdHelper)[0],getRightWH(width,height,thirdHelper)[1]);
            }
            if(drawable3!=null){
                int width=drawable3.getIntrinsicWidth();
                int height=drawable3.getIntrinsicHeight();
                drawable3.setBounds(0,0,getBottomWH(width,height,thirdHelper)[0],getBottomWH(width,height,thirdHelper)[1]);
            }

            int w=stateListDrawable.getIntrinsicWidth();
            int h=stateListDrawable.getIntrinsicHeight();
            switch (thirdHelper.drawable_direction){
                case ThirdHelper.DEFAULT:
                    myView.setButtonDrawable(stateListDrawable);
                    break;
                case ThirdHelper.LEFT:
                    stateListDrawable.setBounds(0,0,getLeftWH(w,h,thirdHelper)[0],getLeftWH(w,h,thirdHelper)[1]);
                    myView.setCompoundDrawables(stateListDrawable,drawable1,drawable2,drawable3);
                    break;
                case ThirdHelper.TOP:
                    stateListDrawable.setBounds(0,0,getTopWH(w,h,thirdHelper)[0],getTopWH(w,h,thirdHelper)[1]);
                    myView.setCompoundDrawables(drawable0,stateListDrawable,drawable2,drawable3);
                    break;
                case ThirdHelper.RIGHT:
                    stateListDrawable.setBounds(0,0,getRightWH(w,h,thirdHelper)[0],getRightWH(w,h,thirdHelper)[1]);
                    myView.setCompoundDrawables(drawable0,drawable1,stateListDrawable,drawable3);
                    break;
                case ThirdHelper.BOTTOM:
                    stateListDrawable.setBounds(0,0,getBottomWH(w,h,thirdHelper)[0],getBottomWH(w,h,thirdHelper)[1]);
                    myView.setCompoundDrawables(drawable0,drawable1,drawable2,stateListDrawable);
                    break;
            }
        }*/

        int [][]colorState=new int[2][];
        int []myColor=new int[]{thirdHelper.checked_textColor, thirdHelper.normal_textColor};
        colorState[0]=new int[]{android.R.attr.state_checked};
        colorState[1]=new int[]{};
        ColorStateList colorStateList=new ColorStateList(colorState,myColor);
        myView.setTextColor(colorStateList);
    }

    private static <T extends ThirdHelper> void setBottomDrawable(CompoundButton myView, T thirdHelper) {
        Drawable drawable0 = myView.getCompoundDrawables()[0];
        setNullColorFilter(drawable0);

        Drawable drawable1 = myView.getCompoundDrawables()[1];
        setNullColorFilter(drawable1);

        Drawable drawable2 = myView.getCompoundDrawables()[2];
        setNullColorFilter(drawable2);

//            Drawable drawable3 = myView.getCompoundDrawables()[3];
        if(thirdHelper.normal_drawable_bottom !=null&& thirdHelper.checked_drawable_bottom !=null){
            /*设置颜色过滤*/
            if(thirdHelper.checked_drawable_bottom_color!=thirdHelper.def_color){
                thirdHelper.checked_drawable_bottom.mutate().setColorFilter(thirdHelper.checked_drawable_bottom_color,thirdHelper.colorFilter);
            }else{
                thirdHelper.checked_drawable_bottom.mutate().clearColorFilter();
            }
            if(thirdHelper.normal_drawable_bottom_color!=thirdHelper.def_color){
                thirdHelper.normal_drawable_bottom.mutate().setColorFilter(thirdHelper.normal_drawable_bottom_color,thirdHelper.colorFilter);
            }else{
                thirdHelper.normal_drawable_bottom.mutate().clearColorFilter();
            }


            StateListDrawable stateListDrawable = new StateListDrawable();
            stateListDrawable.addState(new int[]{android.R.attr.state_checked}, thirdHelper.checked_drawable_bottom);
            stateListDrawable.addState(new int[]{}, thirdHelper.normal_drawable_bottom);



            int w=stateListDrawable.getIntrinsicWidth();
            int h=stateListDrawable.getIntrinsicHeight();
            stateListDrawable.setBounds(0,0-thirdHelper.padding_bottom,getBottomWH(w,h,thirdHelper)[0],getBottomWH(w,h,thirdHelper)[1]-thirdHelper.padding_bottom);
            myView.setCompoundDrawables(drawable0,drawable1,drawable2,stateListDrawable);
        }else{
            myView.setCompoundDrawables(drawable0,drawable1,drawable2,null);
        }
    }

    private static <T extends ThirdHelper> void setRightDrawable(CompoundButton myView, T thirdHelper) {
        Drawable drawable0 = myView.getCompoundDrawables()[0];
        setNullColorFilter(drawable0);

        Drawable drawable1 = myView.getCompoundDrawables()[1];
        setNullColorFilter(drawable1);

//            Drawable drawable2 = myView.getCompoundDrawables()[2];
        Drawable drawable3 = myView.getCompoundDrawables()[3];
        setNullColorFilter(drawable3);

        if(thirdHelper.normal_drawable_right !=null&& thirdHelper.checked_drawable_right !=null){
            /*设置颜色过滤*/
            if(thirdHelper.checked_drawable_right_color!=thirdHelper.def_color){
                thirdHelper.checked_drawable_right.mutate().setColorFilter(thirdHelper.checked_drawable_right_color,thirdHelper.colorFilter);
            }else{
                thirdHelper.checked_drawable_right.mutate().clearColorFilter();
            }
            if(thirdHelper.normal_drawable_right_color!=thirdHelper.def_color){
                thirdHelper.normal_drawable_right.mutate().setColorFilter(thirdHelper.normal_drawable_right_color,thirdHelper.colorFilter);
            }else{
                thirdHelper.normal_drawable_right.mutate().clearColorFilter();
            }


            StateListDrawable stateListDrawable = new StateListDrawable();
            stateListDrawable.addState(new int[]{android.R.attr.state_checked}, thirdHelper.checked_drawable_right);
            stateListDrawable.addState(new int[]{}, thirdHelper.normal_drawable_right);



            int w=stateListDrawable.getIntrinsicWidth();
            int h=stateListDrawable.getIntrinsicHeight();
            stateListDrawable.setBounds(0-thirdHelper.padding_right,0,getRightWH(w,h,thirdHelper)[0]-thirdHelper.padding_right,getRightWH(w,h,thirdHelper)[1]);
            myView.setCompoundDrawables(drawable0,drawable1,stateListDrawable,drawable3);
        }else{
            myView.setCompoundDrawables(drawable0,drawable1,null,drawable3);
        }
    }

    private static <T extends ThirdHelper> void setTopDrawable(CompoundButton myView, T thirdHelper) {
        Drawable drawable0 = myView.getCompoundDrawables()[0];
        setNullColorFilter(drawable0);

//            Drawable drawable1 = myView.getCompoundDrawables()[1];
        Drawable drawable2 = myView.getCompoundDrawables()[2];
        setNullColorFilter(drawable2);

        Drawable drawable3 = myView.getCompoundDrawables()[3];
        setNullColorFilter(drawable3);

        if(thirdHelper.normal_drawable_top !=null&& thirdHelper.checked_drawable_top !=null){
            /*设置颜色过滤*/
            if(thirdHelper.checked_drawable_top_color!=thirdHelper.def_color){
                thirdHelper.checked_drawable_top.mutate().setColorFilter(thirdHelper.checked_drawable_top_color,thirdHelper.colorFilter);
            }else{
                thirdHelper.checked_drawable_top.mutate().clearColorFilter();
            }
            if(thirdHelper.normal_drawable_top_color!=thirdHelper.def_color){
                thirdHelper.normal_drawable_top.mutate().setColorFilter(thirdHelper.normal_drawable_top_color,thirdHelper.colorFilter);
            }else{
                thirdHelper.normal_drawable_top.mutate().clearColorFilter();
            }

            StateListDrawable stateListDrawable = new StateListDrawable();
            stateListDrawable.addState(new int[]{android.R.attr.state_checked}, thirdHelper.checked_drawable_top);
            stateListDrawable.addState(new int[]{}, thirdHelper.normal_drawable_top);



            int w=stateListDrawable.getIntrinsicWidth();
            int h=stateListDrawable.getIntrinsicHeight();
            stateListDrawable.setBounds(0,thirdHelper.padding_top+0,getTopWH(w,h,thirdHelper)[0],thirdHelper.padding_top+getTopWH(w,h,thirdHelper)[1]);
            myView.setCompoundDrawables(drawable0,stateListDrawable,drawable2,drawable3);
        }else{
            myView.setCompoundDrawables(drawable0,null,drawable2,drawable3);
        }
    }

    private static <T extends ThirdHelper> void setLeftDrawable(CompoundButton myView, T thirdHelper) {
        Drawable drawable1 = myView.getCompoundDrawables()[1];
        setNullColorFilter(drawable1);

        Drawable drawable2 = myView.getCompoundDrawables()[2];
        setNullColorFilter(drawable2);

        Drawable drawable3 = myView.getCompoundDrawables()[3];
        setNullColorFilter(drawable3);

        if(thirdHelper.normal_drawable_left !=null&& thirdHelper.checked_drawable_left !=null){
            if(thirdHelper.checked_drawable_left_color!=thirdHelper.def_color){
                thirdHelper.checked_drawable_left.mutate().setColorFilter(thirdHelper.checked_drawable_left_color,thirdHelper.colorFilter);
            }else{
                thirdHelper.checked_drawable_left.mutate().clearColorFilter();
            }
            if(thirdHelper.normal_drawable_left_color!=thirdHelper.def_color){
                thirdHelper.normal_drawable_left.mutate().setColorFilter(thirdHelper.normal_drawable_left_color,thirdHelper.colorFilter);
            }else{
                thirdHelper.normal_drawable_left.mutate().clearColorFilter();
            }
            StateListDrawable stateListDrawable = new StateListDrawable();
            stateListDrawable.addState(new int[]{android.R.attr.state_checked}, thirdHelper.checked_drawable_left);
            stateListDrawable.addState(new int[]{}, thirdHelper.normal_drawable_left);

//            Drawable drawable0 = myView.getCompoundDrawables()[0];


            int w=stateListDrawable.getIntrinsicWidth();
            int h=stateListDrawable.getIntrinsicHeight();
            stateListDrawable.setBounds(thirdHelper.padding_left+0,0,thirdHelper.padding_left+getLeftWH(w,h,thirdHelper)[0],getLeftWH(w,h,thirdHelper)[1]);
            myView.setCompoundDrawables(stateListDrawable,drawable1,drawable2,drawable3);
        }else{
            myView.setCompoundDrawables(null,drawable1,drawable2,drawable3);
        }
    }
    private static <T extends ThirdHelper> void setButtonDrawable(CompoundButton myView, T thirdHelper) {
        Drawable drawable1 = myView.getCompoundDrawables()[1];
        setNullColorFilter(drawable1);

        Drawable drawable2 = myView.getCompoundDrawables()[2];
        setNullColorFilter(drawable2);

        Drawable drawable3 = myView.getCompoundDrawables()[3];
        setNullColorFilter(drawable3);

        if(thirdHelper.normal_drawable_left !=null&& thirdHelper.checked_drawable_left !=null){
            if(thirdHelper.checked_drawable_left_color!=thirdHelper.def_color){
                thirdHelper.checked_drawable_left.mutate().setColorFilter(thirdHelper.checked_drawable_left_color,thirdHelper.colorFilter);
            }else{
                thirdHelper.checked_drawable_left.mutate().clearColorFilter();
            }
            if(thirdHelper.normal_drawable_left_color!=thirdHelper.def_color){
                thirdHelper.normal_drawable_left.mutate().setColorFilter(thirdHelper.normal_drawable_left_color,thirdHelper.colorFilter);
            }else{
                thirdHelper.normal_drawable_left.mutate().clearColorFilter();
            }
            StateListDrawable stateListDrawable = new StateListDrawable();
            stateListDrawable.addState(new int[]{android.R.attr.state_checked}, thirdHelper.checked_drawable_left);
            stateListDrawable.addState(new int[]{}, thirdHelper.normal_drawable_left);

//            Drawable drawable0 = myView.getCompoundDrawables()[0];


            int w=stateListDrawable.getIntrinsicWidth();
            int h=stateListDrawable.getIntrinsicHeight();
            stateListDrawable.setBounds(thirdHelper.padding_left+0,0,thirdHelper.padding_left+getLeftWH(w,h,thirdHelper)[0],getLeftWH(w,h,thirdHelper)[1]);
            myView.setCompoundDrawables(stateListDrawable,drawable1,drawable2,drawable3);
        }else{
            myView.setCompoundDrawables(null,drawable1,drawable2,drawable3);
        }
    }
    private static void setNullColorFilter(Drawable drawable){
        if (drawable != null) {
//            drawable.setColorFilter(null);
        }
    }
    public static<T extends FirstHelper>void noPartBorderNoPressColor(View myView,T firstHelper) {
        GradientDrawable gradientDrawableNormal = getNoPartBorderNoPressColorGradientDrawable(true,firstHelper);

        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            myView.setBackground(gradientDrawableNormal);
        } else {
            myView.setBackgroundDrawable(gradientDrawableNormal);
        }*/


        myView.setBackground(gradientDrawableNormal);
    }

    public static<T extends FirstHelper>void noPartBorderHasPressColor(View myView,T firstHelper) {

        GradientDrawable gradientDrawableNormal = getNoPartBorderNoPressColorGradientDrawable(false,firstHelper);
        GradientDrawable gradientDrawablePress = getNoPartBorderNoPressColorGradientDrawable(false,firstHelper);
        gradientDrawablePress.setColor(firstHelper.pressColor);


        StateListDrawable stateListDrawableForShape = new StateListDrawable();
        stateListDrawableForShape.addState(new int[]{-android.R.attr.state_pressed}, gradientDrawableNormal);
        stateListDrawableForShape.addState(new int[]{android.R.attr.state_pressed}, gradientDrawablePress);
        stateListDrawableForShape.addState(new int[]{}, gradientDrawableNormal);

        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            myView.setBackground(stateListDrawableForShape);
        } else {
            myView.setBackgroundDrawable(stateListDrawableForShape);
        }*/
        myView.setBackground(stateListDrawableForShape);
    }

    public static<T extends FirstHelper>void hasPartBorderNoPressColor(View myView,T firstHelper) {
        setBorderWidthForPartBorder(firstHelper);

        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            myView.setBackground(getHasPartBorderNoPressColorLayerDrawableNormal(firstHelper));
        } else {
            myView.setBackgroundDrawable(getHasPartBorderNoPressColorLayerDrawableNormal(firstHelper));
        }*/
        myView.setBackground(getHasPartBorderNoPressColorLayerDrawableNormal(firstHelper));

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


       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            myView.setBackground(stateListDrawableForLayer);
        } else {
            myView.setBackgroundDrawable(stateListDrawableForLayer);
        }*/
        myView.setBackground(stateListDrawableForLayer);
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

        if (firstHelper.borderWidth >0) {
            if (firstHelper.borderColor == Color.TRANSPARENT) {
                firstHelper.borderColor = getDefBorderColor();
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
        if(secondHelper.left_width>0&&secondHelper.left_height>0){
            return new int[]{secondHelper.left_width,secondHelper.left_height};
        }else if(secondHelper.left_width>0){
            return new int[]{secondHelper.left_width, (int) chuFa(chengFa(secondHelper.left_width,height),width)};
        }else if(secondHelper.left_height>0){
            return new int[]{secondHelper.left_height, (int) chuFa(chengFa(secondHelper.left_height,width),height)};
        }else{
            return new int[]{width,height};
        }
    }
    protected static<T extends SecondHelper>int[] getTopWH(int width,int height,T secondHelper){
        if(secondHelper.top_width>0&&secondHelper.top_height>0){
            return new int[]{secondHelper.top_width,secondHelper.top_height};
        }else if(secondHelper.top_width>0){
            return new int[]{secondHelper.top_width, (int) chuFa(chengFa(secondHelper.top_width,height),width)};
        }else if(secondHelper.top_height>0){
            return new int[]{secondHelper.top_height, (int) chuFa(chengFa(secondHelper.top_height,width),height)};
        }else{
            return new int[]{width,height};
        }
    }
    protected static<T extends SecondHelper>int[] getRightWH(int width,int height,T secondHelper){
        if(secondHelper.right_width>0&&secondHelper.right_height>0){
            return new int[]{secondHelper.right_width,secondHelper.right_height};
        }else if(secondHelper.right_width>0){
            return new int[]{secondHelper.right_width, (int) chuFa(chengFa(secondHelper.right_width,height),width)};
        }else if(secondHelper.right_height>0){
            return new int[]{secondHelper.right_height, (int) chuFa(chengFa(secondHelper.right_height,width),height)};
        }else{
            return new int[]{width,height};
        }
    }
    protected static<T extends SecondHelper>int[] getBottomWH(int width,int height,T secondHelper){
        if(secondHelper.bottom_width>0&&secondHelper.bottom_height>0){
            return new int[]{secondHelper.bottom_width,secondHelper.bottom_height};
        }else if(secondHelper.bottom_width>0){
            return new int[]{secondHelper.bottom_width, (int) chuFa(chengFa(secondHelper.bottom_width,height),width)};
        }else if(secondHelper.bottom_height>0){
            return new int[]{secondHelper.bottom_height, (int) chuFa(chengFa(secondHelper.bottom_height,width),height)};
        }else{
            return new int[]{width,height};
        }
    }

    public static double chuFa(double d1,double d2) {
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
    public static double chengFa(double d1,double d2){
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
