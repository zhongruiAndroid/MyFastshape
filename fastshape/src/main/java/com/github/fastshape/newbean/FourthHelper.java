package com.github.fastshape.newbean;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.github.fastshape.R;
import com.github.fastshape.inter.CompleteInter;

/***
 *   created by zhongrui on 2018/12/31
 *   MyEditText
 */
public class FourthHelper extends SecondHelper{
    /*设置清除按钮drawable(点击清除内容)*/
    protected Drawable clearIconDrawable;
    /*设置清除按钮是否隐藏,默认false*/
    protected boolean hiddenClearIcon;
    /*设置清除按钮宽度,只设置其中一个属性自动适配另外一个属性*/
    protected int clearIcon_width;
    protected int clearIcon_height;

    public FourthHelper(CompleteInter completeInter) {
        super(completeInter);
    }

    public void init(Context context, AttributeSet attrs , int defStyleAttr) {
        TypedArray viewNormal = context.obtainStyledAttributes(attrs, R.styleable.FastShapeAttr,defStyleAttr,0);
        /*第一部分公共属性*/
        publicFirstAttr(viewNormal);

        /*第二部分公共属性*/
        publicSecondAttr(viewNormal);

        /*第四部分公共属性*/
        publicFourthAttr(viewNormal);

        /*裁剪属性 viewGroup属性*/
//        clipAttr(viewNormal);

        viewNormal.recycle();
    }

    protected void publicFourthAttr(TypedArray viewNormal) {
        clearIconDrawable =  viewNormal.getDrawable(R.styleable.FastShapeAttr_clearIconDrawable);
        hiddenClearIcon =  viewNormal.getBoolean(R.styleable.FastShapeAttr_hiddenClearIcon,false);

        clearIcon_width =  (int) viewNormal.getDimension(R.styleable.FastShapeAttr_clearIcon_width, -1);
        clearIcon_height = (int) viewNormal.getDimension(R.styleable.FastShapeAttr_clearIcon_height,-1);
    }

    @Deprecated
    public void completeClip() {
    }

    @Deprecated
    public void resetClip() {
    }

    public Drawable getClearIconDrawable() {
        return clearIconDrawable;
    }

    public FourthHelper setClearIconDrawable(Drawable clearIconDrawable) {
        this.clearIconDrawable = clearIconDrawable;
        return this;
    }

    public boolean isHiddenClearIcon() {
        return hiddenClearIcon;
    }

    public FourthHelper setHiddenClearIcon(boolean hiddenClearIcon) {
        this.hiddenClearIcon = hiddenClearIcon;
        return this;
    }

    public int getClearIcon_width() {
        return clearIcon_width;
    }

    public FourthHelper setClearIcon_width(int clearIcon_width) {
        this.clearIcon_width = clearIcon_width;
        return this;
    }

    public int getClearIcon_height() {
        return clearIcon_height;
    }

    public FourthHelper setClearIcon_height(int clearIcon_height) {
        this.clearIcon_height = clearIcon_height;
        return this;
    }
}
