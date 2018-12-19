package com.github.fastshape.bean;

import android.graphics.drawable.Drawable;

import com.github.fastshape.inter.CheckViewInter;

/**
 * @createBy Administrator
 * @time 2018-12-17 15:47
 */
public class CheckViewHelper extends TextViewHelper implements CheckViewInter {
    /*设置normal和check状态的drawable*/
    protected Drawable normal_drawable;
    protected Drawable checked_drawable;

    /*设置normal和check状态的文字颜色*/
    protected Drawable normal_textColor;
    protected Drawable checked_textColor;

    /*
    * 设置button所在方向left,top,right,bottom
    * 设置此属性需要:android:button="@null"
    * */
    protected Drawable drawable_direction;


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

    public Drawable getNormal_textColor() {
        return normal_textColor;
    }

    public void setNormal_textColor(Drawable normal_textColor) {
        this.normal_textColor = normal_textColor;
    }

    public Drawable getChecked_textColor() {
        return checked_textColor;
    }

    public void setChecked_textColor(Drawable checked_textColor) {
        this.checked_textColor = checked_textColor;
    }

    public Drawable getDrawable_direction() {
        return drawable_direction;
    }

    public void setDrawable_direction(Drawable drawable_direction) {
        this.drawable_direction = drawable_direction;
    }
}
