package com.github.fastshape.newbean;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.github.fastshape.R;
import com.github.fastshape.inter.CompleteInter;

/***
 *   created by zhongrui on 2018/12/31
 *   MyTextView，MyButton，MyCheckBox，MyRadioButton公共属性
 */
public class SecondHelper extends FirstHelper{
    /*设置drawableLeft宽高,只设置其中一个属性自动适配另外一个属性*/
    protected int left_width;
    protected int left_height;
    /*设置drawableTop宽高,只设置其中一个属性自动适配另外一个属性*/
    protected int top_width;
    protected int top_height;
    /*设置drawableRight宽高,只设置其中一个属性自动适配另外一个属性*/
    protected int right_width;
    protected int right_height;
    /*设置drawableBottom宽高,只设置其中一个属性自动适配另外一个属性*/
    protected int bottom_width;
    protected int bottom_height;


    public SecondHelper(CompleteInter completeInter) {
        super(completeInter);
    }
    public void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray viewNormal = context.obtainStyledAttributes(attrs, R.styleable.FastShapeAttr,defStyleAttr,0);
        /*第一部分公共属性*/
        publicFirstAttr(viewNormal);

        /*第二部分公共属性*/
        publicSecondAttr(viewNormal);

        /*裁剪属性*/
        clipAttr(viewNormal);

        viewNormal.recycle();
    }

    /*第二部分公共属性*/
    protected void publicSecondAttr(TypedArray viewNormal) {
        left_width = (int) viewNormal.getDimension(R.styleable.FastShapeAttr_left_width, -1);
        left_height = (int) viewNormal.getDimension(R.styleable.FastShapeAttr_left_height,-1);


        top_width =  (int) viewNormal.getDimension(R.styleable.FastShapeAttr_top_width, -1);
        top_height = (int) viewNormal.getDimension(R.styleable.FastShapeAttr_top_height,-1);


        right_width =  (int) viewNormal.getDimension(R.styleable.FastShapeAttr_right_width, -1);
        right_height = (int) viewNormal.getDimension(R.styleable.FastShapeAttr_right_height,-1);


        bottom_width =  (int) viewNormal.getDimension(R.styleable.FastShapeAttr_bottom_width, -1);
        bottom_height = (int) viewNormal.getDimension(R.styleable.FastShapeAttr_bottom_height,-1);
    }

    @Deprecated
    public void completeClip() {
    }

    @Deprecated
    public void resetClip() {
    }
}
