package com.github.fastshape.bean;

import android.graphics.drawable.Drawable;

/**
 * @createBy Administrator
 * @time 2018-12-17 15:46
 */
public class EditTextBean extends BaseBean {
    /*设置清除按钮drawable(点击清除内容)*/
    protected Drawable clearIconDrawable;
    /*设置清除按钮是否隐藏,默认false*/
    protected boolean hiddenClearIcon;
    /*设置清除按钮宽度,只设置其中一个属性自动适配另外一个属性*/
    protected int clearIcon_width;
    protected int clearIcon_height;


    public Drawable getClearIconDrawable() {
        return clearIconDrawable;
    }

    public void setClearIconDrawable(Drawable clearIconDrawable) {
        this.clearIconDrawable = clearIconDrawable;
    }

    public boolean isHiddenClearIcon() {
        return hiddenClearIcon;
    }

    public void setHiddenClearIcon(boolean hiddenClearIcon) {
        this.hiddenClearIcon = hiddenClearIcon;
    }

    public int getClearIcon_width() {
        return clearIcon_width;
    }

    public void setClearIcon_width(int clearIcon_width) {
        this.clearIcon_width = clearIcon_width;
    }

    public int getClearIcon_height() {
        return clearIcon_height;
    }

    public void setClearIcon_height(int clearIcon_height) {
        this.clearIcon_height = clearIcon_height;
    }
}
