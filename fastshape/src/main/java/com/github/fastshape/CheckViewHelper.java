package com.github.fastshape;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.widget.CompoundButton;

import com.github.fastshape.inter.ViewHelperInter;

/**
 * Created by windows10 on 2018/4/8.
 */

public class CheckViewHelper extends Helper{
    protected Drawable normal_drawable;
    protected Drawable checked_drawable;
    protected int normal_textColor;
    protected int checked_textColor;
    protected int drawable_direction;


    /***显示Drawable方向--默认*/
    public static final int DEFAULT=0;
    /***显示Drawable方向--左边*/
    public static final int LEFT=1;
    /***显示Drawable方向--顶部*/
    public static final int TOP=2;
    /***显示Drawable方向--右边*/
    public static final int RIGHT=3;
    /***显示Drawable方向--底部*/
    public static final int BOTTOM=4;


    private ViewHelperInter viewHelperInter;
    public CheckViewHelper() {
        this(null);
    }
    public CheckViewHelper(ViewHelperInter inter) {
        viewHelperInter=inter;
    }
    public void complete(){
        if(viewHelperInter!=null){
            viewHelperInter.onComplete();
        }
    }

    public Drawable getNormal_drawable() {
        return normal_drawable;
    }

    public void setNormal_drawable(Drawable normal_drawable) {
        this.normal_drawable = normal_drawable;
    }

    public Drawable getChecked_drawable() {
        return checked_drawable;
    }

    public void setChecked_drawable(Drawable checked_drawable) {
        this.checked_drawable = checked_drawable;
    }

    public int getNormal_textColor() {
        return normal_textColor;
    }

    public void setNormal_textColor(int normal_textColor) {
        this.normal_textColor = normal_textColor;
    }

    public int getChecked_textColor() {
        return checked_textColor;
    }

    public void setChecked_textColor(int checked_textColor) {
        this.checked_textColor = checked_textColor;
    }

    public int getDrawable_direction() {
        return drawable_direction;
    }

    public void setDrawable_direction(int drawable_direction) {
        this.drawable_direction = drawable_direction;
    }



    protected void viewComplete(CompoundButton myView) {
        if(normal_drawable !=null&& checked_drawable !=null){

            StateListDrawable stateListDrawable = new StateListDrawable();
            stateListDrawable.addState(new int[]{android.R.attr.state_checked}, checked_drawable);
            stateListDrawable.addState(new int[]{}, normal_drawable);

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

            int w=stateListDrawable.getIntrinsicWidth();
            int h=stateListDrawable.getIntrinsicHeight();
            switch (drawable_direction){
                case CheckViewHelper.DEFAULT:
                    myView.setButtonDrawable(stateListDrawable);
                    break;
                case CheckViewHelper.LEFT:
                    stateListDrawable.setBounds(0,0,getLeftWH(w,h)[0],getLeftWH(w,h)[1]);
                    myView.setCompoundDrawables(stateListDrawable,drawable1,drawable2,drawable3);
                    break;
                case CheckViewHelper.TOP:
                    stateListDrawable.setBounds(0,0,getTopWH(w,h)[0],getTopWH(w,h)[1]);
                    myView.setCompoundDrawables(drawable0,stateListDrawable,drawable2,drawable3);
                    break;
                case CheckViewHelper.RIGHT:
                    stateListDrawable.setBounds(0,0,getRightWH(w,h)[0],getRightWH(w,h)[1]);
                    myView.setCompoundDrawables(drawable0,drawable1,stateListDrawable,drawable3);
                    break;
                case CheckViewHelper.BOTTOM:
                    stateListDrawable.setBounds(0,0,getBottomWH(w,h)[0],getBottomWH(w,h)[1]);
                    myView.setCompoundDrawables(drawable0,drawable1,drawable2,stateListDrawable);
                    break;
            }
        }

        int [][]colorState=new int[2][];
        int []myColor=new int[]{checked_textColor, normal_textColor};
        colorState[0]=new int[]{android.R.attr.state_checked};
        colorState[1]=new int[]{};
        ColorStateList colorStateList=new ColorStateList(colorState,myColor);
        myView.setTextColor(colorStateList);
    }


}
