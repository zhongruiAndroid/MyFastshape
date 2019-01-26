package com.github.fastshape.newbean;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.github.fastshape.R;
import com.github.fastshape.inter.CompleteInter;

/***
 *   created by zhongrui on 2018/12/31
 *   MyCheckBox,MyRadioButton
 */
public class ThirdHelper extends SecondHelper {
    /*设置normal和check状态的drawable*/
    protected Drawable normal_drawable_left;
    protected Drawable checked_drawable_left;

    protected Drawable normal_drawable_top;
    protected Drawable checked_drawable_top;

    protected Drawable normal_drawable_right;
    protected Drawable checked_drawable_right;

    protected Drawable normal_drawable_bottom;
    protected Drawable checked_drawable_bottom;

    /*设置normal和check状态的文字颜色*/
    protected int normal_textColor;
    protected int checked_textColor;

    /*
     * 设置button所在方向left,top,right,bottom
     * 设置此属性需要:android:button="@null"
     * */
    private int textDefaultColor=Color.GRAY;


    public ThirdHelper(CompleteInter completeInter) {
        super(completeInter);
    }
    public void init(Context context, AttributeSet attrs ) {
        TypedArray viewNormal = context.obtainStyledAttributes(attrs, R.styleable.FastShapeAttr,R.attr.fastshapeStyle,0);
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


    protected void publicThirdAttr(TypedArray viewNormal) {
       normal_drawable_left=  viewNormal.getDrawable(R.styleable.FastShapeAttr_normal_drawable_left);
       checked_drawable_left =   viewNormal.getDrawable(R.styleable.FastShapeAttr_checked_drawable_left);

       normal_drawable_top=  viewNormal.getDrawable(R.styleable.FastShapeAttr_normal_drawable_top);
       checked_drawable_top =   viewNormal.getDrawable(R.styleable.FastShapeAttr_checked_drawable_top);

       normal_drawable_right=  viewNormal.getDrawable(R.styleable.FastShapeAttr_normal_drawable_right);
       checked_drawable_right =   viewNormal.getDrawable(R.styleable.FastShapeAttr_checked_drawable_right);

       normal_drawable_bottom=  viewNormal.getDrawable(R.styleable.FastShapeAttr_normal_drawable_bottom);
       checked_drawable_bottom =   viewNormal.getDrawable(R.styleable.FastShapeAttr_checked_drawable_bottom);




        normal_textColor =  viewNormal.getColor(R.styleable.FastShapeAttr_normal_textColor,textDefaultColor);//this.getTextColors().getDefaultColor()
        checked_textColor =  viewNormal.getColor(R.styleable.FastShapeAttr_checked_textColor,textDefaultColor);
    }

    public Drawable getNormal_drawable_left() {
        return normal_drawable_left;
    }

    public ThirdHelper setNormal_drawable_left(Drawable normal_drawable_left) {
        this.normal_drawable_left = normal_drawable_left;
        return this;
    }

    public Drawable getChecked_drawable_left() {
        return checked_drawable_left;
    }

    public ThirdHelper setChecked_drawable_left(Drawable checked_drawable_left) {
        this.checked_drawable_left = checked_drawable_left;
        return this;
    }

    public Drawable getNormal_drawable_top() {
        return normal_drawable_top;
    }

    public ThirdHelper setNormal_drawable_top(Drawable normal_drawable_top) {
        this.normal_drawable_top = normal_drawable_top;
        return this;
    }

    public Drawable getChecked_drawable_top() {
        return checked_drawable_top;
    }

    public ThirdHelper setChecked_drawable_top(Drawable checked_drawable_top) {
        this.checked_drawable_top = checked_drawable_top;
        return this;
    }

    public Drawable getNormal_drawable_right() {
        return normal_drawable_right;
    }

    public ThirdHelper setNormal_drawable_right(Drawable normal_drawable_right) {
        this.normal_drawable_right = normal_drawable_right;
        return this;
    }

    public Drawable getChecked_drawable_right() {
        return checked_drawable_right;
    }

    public ThirdHelper setChecked_drawable_right(Drawable checked_drawable_right) {
        this.checked_drawable_right = checked_drawable_right;
        return this;
    }

    public Drawable getNormal_drawable_bottom() {
        return normal_drawable_bottom;
    }

    public ThirdHelper setNormal_drawable_bottom(Drawable normal_drawable_bottom) {
        this.normal_drawable_bottom = normal_drawable_bottom;
        return this;
    }

    public Drawable getChecked_drawable_bottom() {
        return checked_drawable_bottom;
    }

    public ThirdHelper setChecked_drawable_bottom(Drawable checked_drawable_bottom) {
        this.checked_drawable_bottom = checked_drawable_bottom;
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
}
