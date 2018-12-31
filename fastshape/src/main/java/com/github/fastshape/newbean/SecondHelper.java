package com.github.fastshape.newbean;

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
}
