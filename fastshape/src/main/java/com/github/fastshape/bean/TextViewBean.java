package com.github.fastshape.bean;

/**
 * Created by Administrator on 2018/7/3.
 */

public class TextViewBean extends BaseBean{
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


    public int getLeft_width() {
        return left_width;
    }

    public void setLeft_width(int left_width) {
        this.left_width = left_width;
    }

    public int getLeft_height() {
        return left_height;
    }

    public void setLeft_height(int left_height) {
        this.left_height = left_height;
    }

    public int getTop_width() {
        return top_width;
    }

    public void setTop_width(int top_width) {
        this.top_width = top_width;
    }

    public int getTop_height() {
        return top_height;
    }

    public void setTop_height(int top_height) {
        this.top_height = top_height;
    }

    public int getRight_width() {
        return right_width;
    }

    public void setRight_width(int right_width) {
        this.right_width = right_width;
    }

    public int getRight_height() {
        return right_height;
    }

    public void setRight_height(int right_height) {
        this.right_height = right_height;
    }

    public int getBottom_width() {
        return bottom_width;
    }

    public void setBottom_width(int bottom_width) {
        this.bottom_width = bottom_width;
    }

    public int getBottom_height() {
        return bottom_height;
    }

    public void setBottom_height(int bottom_height) {
        this.bottom_height = bottom_height;
    }
}
