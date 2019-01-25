package com.github.fastshape.newbean;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.IntDef;
import android.util.AttributeSet;

import com.github.fastshape.R;
import com.github.fastshape.inter.CompleteInter;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/***
 *   created by zhongrui on 2018/12/31
 *   MyCheckBox,MyRadioButton
 */
public class ThirdHelper extends SecondHelper {
    /*设置normal和check状态的drawable*/
    protected Drawable normal_drawable;
    protected Drawable checked_drawable;

    /*设置normal和check状态的文字颜色*/
    protected int normal_textColor;
    protected int checked_textColor;

    /*
     * 设置button所在方向left,top,right,bottom
     * 设置此属性需要:android:button="@null"
     * */
    protected int drawable_direction;

    private int textDefaultColor=Color.GRAY;

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

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({DEFAULT,LEFT,TOP,RIGHT,BOTTOM})
    public @interface direction{}

    public ThirdHelper(CompleteInter completeInter) {
        super(completeInter);
    }
    public void init(Context context, AttributeSet attrs ) {
        TypedArray viewNormal = context.obtainStyledAttributes(attrs, R.styleable.FastShapeAttr,defStyleAttr,0);
        /*第一部分公共属性*/
        publicFirstAttr(viewNormal);

        /*第二部分公共属性*/
        publicSecondAttr(viewNormal);

        /*第三部分公共属性*/
        publicThirdAttr(viewNormal);

        /*裁剪属性 viewGroup属性*/
//        clipAttr(viewNormal);
        viewNormal.recycle();
    }

    public void setDefaultTextColor(@ColorInt int color){
        textDefaultColor=color;
    }
    protected void publicThirdAttr(TypedArray viewNormal) {
        normal_drawable =  viewNormal.getDrawable(R.styleable.FastShapeAttr_normal_drawable );
        checked_drawable =   viewNormal.getDrawable(R.styleable.FastShapeAttr_checked_drawable);
        normal_textColor =  viewNormal.getColor(R.styleable.FastShapeAttr_normal_textColor,textDefaultColor);//this.getTextColors().getDefaultColor()
        checked_textColor =  viewNormal.getColor(R.styleable.FastShapeAttr_checked_textColor,textDefaultColor);
        drawable_direction = viewNormal.getInt(R.styleable.FastShapeAttr_drawable_direction,DEFAULT);
    }

    public Drawable getNormal_drawable() {
        return normal_drawable;
    }

    public ThirdHelper setNormal_drawable(Drawable normal_drawable) {
        this.normal_drawable = normal_drawable;
        return this;
    }

    public Drawable getChecked_drawable() {
        return checked_drawable;
    }

    public ThirdHelper setChecked_drawable(Drawable checked_drawable) {
        this.checked_drawable = checked_drawable;
        return this;
    }

    public int getNormal_textColor() {
        return normal_textColor;
    }

    public ThirdHelper setNormal_textColor(int normal_textColor) {
        this.normal_textColor = normal_textColor;
        return this;
    }

    public int getChecked_textColor() {
        return checked_textColor;
    }

    public ThirdHelper setChecked_textColor(int checked_textColor) {
        this.checked_textColor = checked_textColor;
        return this;
    }

    public int getDrawable_direction() {
        return drawable_direction;
    }

    public ThirdHelper setDrawable_direction(@direction int drawable_direction) {
        this.drawable_direction = drawable_direction;
        return this;
    }
}
