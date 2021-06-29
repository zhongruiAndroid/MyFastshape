package com.github.fastshape.viewhelper;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.github.fastshape.R;
import com.github.fastshape.inter.CompleteInter;

/***
 *   created by zhongrui on 2018/12/31
 *   MyCheckBox,MyRadioButton
 */
public class ThirdHelper extends SecondHelper {
    protected PorterDuff.Mode colorFilter = PorterDuff.Mode.SRC_ATOP;
    /*设置normal和check状态的drawable*/
    protected Drawable normal_drawable_left;
    protected Drawable checked_drawable_left;
    protected int normal_drawable_left_color;
    protected int checked_drawable_left_color;

    protected Drawable normal_drawable_top;
    protected Drawable checked_drawable_top;
    protected int normal_drawable_top_color;
    protected int checked_drawable_top_color;

    protected Drawable normal_drawable_right;
    protected Drawable checked_drawable_right;
    protected int normal_drawable_right_color;
    protected int checked_drawable_right_color;

    protected Drawable normal_drawable_bottom;
    protected Drawable checked_drawable_bottom;
    protected int normal_drawable_bottom_color;
    protected int checked_drawable_bottom_color;

    protected Drawable normal_drawable;
    protected Drawable checked_drawable;
    protected int normal_drawable_color;
    protected int checked_drawable_color;

    /*setCompoundDrawables宽高*/
    protected int button_width;
    protected int button_height;


    /*设置normal和check状态的文字颜色*/
    protected int normal_textColor;
    protected int checked_textColor;

    /*设置drawable到边框的偏移量*/
    protected int padding_left;
    protected int padding_top;
    protected int padding_right;
    protected int padding_bottom;


    /*
     * 设置button所在方向left,top,right,bottom
     * 设置此属性需要:android:button="@null"
     * */
    private int textDefaultColor = Color.GRAY;


    public ThirdHelper(CompleteInter completeInter) {
        super(completeInter);
    }

    public void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray viewNormal = context.obtainStyledAttributes(attrs, R.styleable.FastShapeAttr, defStyleAttr, 0);
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
        normal_drawable_left = viewNormal.getDrawable(R.styleable.FastShapeAttr_normal_drawable_left);
        checked_drawable_left = viewNormal.getDrawable(R.styleable.FastShapeAttr_checked_drawable_left);

        normal_drawable_top = viewNormal.getDrawable(R.styleable.FastShapeAttr_normal_drawable_top);
        checked_drawable_top = viewNormal.getDrawable(R.styleable.FastShapeAttr_checked_drawable_top);

        normal_drawable_right = viewNormal.getDrawable(R.styleable.FastShapeAttr_normal_drawable_right);
        checked_drawable_right = viewNormal.getDrawable(R.styleable.FastShapeAttr_checked_drawable_right);

        normal_drawable_bottom = viewNormal.getDrawable(R.styleable.FastShapeAttr_normal_drawable_bottom);
        checked_drawable_bottom = viewNormal.getDrawable(R.styleable.FastShapeAttr_checked_drawable_bottom);

        normal_drawable = viewNormal.getDrawable(R.styleable.FastShapeAttr_normal_drawable);
        checked_drawable = viewNormal.getDrawable(R.styleable.FastShapeAttr_checked_drawable);


        normal_drawable_left_color = viewNormal.getColor(R.styleable.FastShapeAttr_normal_drawable_left_color, def_color);
        checked_drawable_left_color = viewNormal.getColor(R.styleable.FastShapeAttr_checked_drawable_left_color, def_color);

        normal_drawable_top_color = viewNormal.getColor(R.styleable.FastShapeAttr_normal_drawable_top_color, def_color);
        checked_drawable_top_color = viewNormal.getColor(R.styleable.FastShapeAttr_checked_drawable_top_color, def_color);

        normal_drawable_right_color = viewNormal.getColor(R.styleable.FastShapeAttr_normal_drawable_right_color, def_color);
        checked_drawable_right_color = viewNormal.getColor(R.styleable.FastShapeAttr_checked_drawable_right_color, def_color);

        normal_drawable_bottom_color = viewNormal.getColor(R.styleable.FastShapeAttr_normal_drawable_bottom_color, def_color);
        checked_drawable_bottom_color = viewNormal.getColor(R.styleable.FastShapeAttr_checked_drawable_bottom_color, def_color);

        normal_drawable_color = viewNormal.getColor(R.styleable.FastShapeAttr_normal_drawable_color, def_color);
        checked_drawable_color = viewNormal.getColor(R.styleable.FastShapeAttr_checked_drawable_color, def_color);

        int filter = viewNormal.getInt(R.styleable.FastShapeAttr_colorFilter, -1);
        this.colorFilter = setPorterDuff(filter);


        normal_textColor = viewNormal.getColor(R.styleable.FastShapeAttr_normal_textColor, textDefaultColor);//this.getTextColors().getDefaultColor()
        checked_textColor = viewNormal.getColor(R.styleable.FastShapeAttr_checked_textColor, textDefaultColor);


        button_width = (int) viewNormal.getDimension(R.styleable.FastShapeAttr_button_width, 0);
        button_height = (int) viewNormal.getDimension(R.styleable.FastShapeAttr_button_height, 0);


        padding_left = (int) viewNormal.getDimension(R.styleable.FastShapeAttr_padding_left, 0);
        padding_top = (int) viewNormal.getDimension(R.styleable.FastShapeAttr_padding_top, 0);
        padding_right = (int) viewNormal.getDimension(R.styleable.FastShapeAttr_padding_right, 0);
        padding_bottom = (int) viewNormal.getDimension(R.styleable.FastShapeAttr_padding_bottom, 0);


    }

    private PorterDuff.Mode setPorterDuff(int colorFilter) {
        switch (colorFilter) {
            default:
            case -1:
                return PorterDuff.Mode.SRC_ATOP;
            case 0:
                return PorterDuff.Mode.CLEAR;
            case 1:
                return PorterDuff.Mode.SRC;
            case 2:
                return PorterDuff.Mode.DST;
            case 3:
                return PorterDuff.Mode.SRC_OVER;
            case 4:
                return PorterDuff.Mode.DST_OVER;
            case 5:
                return PorterDuff.Mode.SRC_IN;
            case 6:
                return PorterDuff.Mode.DST_IN;
            case 7:
                return PorterDuff.Mode.SRC_OUT;
            case 8:
                return PorterDuff.Mode.DST_OUT;
            case 9:
                return PorterDuff.Mode.SRC_ATOP;
            case 10:
                return PorterDuff.Mode.DST_ATOP;
            case 11:
                return PorterDuff.Mode.XOR;
            case 16:
                return PorterDuff.Mode.DARKEN;
            case 17:
                return PorterDuff.Mode.LIGHTEN;
            case 13:
                return PorterDuff.Mode.MULTIPLY;
            case 14:
                return PorterDuff.Mode.SCREEN;
            case 12:
                return PorterDuff.Mode.ADD;
            case 15:
                return PorterDuff.Mode.OVERLAY;
        }
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

    public int getNormal_drawable_color() {
        return normal_drawable_color;
    }

    public ThirdHelper setNormal_drawable_color(int normal_drawable_color) {
        this.normal_drawable_color = normal_drawable_color;
        return this;
    }

    public int getChecked_drawable_color() {
        return checked_drawable_color;
    }

    public ThirdHelper setChecked_drawable_color(int checked_drawable_color) {
        this.checked_drawable_color = checked_drawable_color;
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

    public int getButton_width() {
        return button_width;
    }

    public ThirdHelper setButton_width(int button_width) {
        this.button_width = button_width;
        return this;
    }

    public int getButton_height() {
        return button_height;
    }

    public ThirdHelper setButton_height(int button_height) {
        this.button_height = button_height;
        return this;
    }

    public int getChecked_textColor() {
        return checked_textColor;
    }

    public ThirdHelper setChecked_textColor(int checked_textColor) {
        this.checked_textColor = checked_textColor;
        return this;
    }

    public int getNormal_drawable_left_color() {
        return normal_drawable_left_color;
    }

    public ThirdHelper setNormal_drawable_left_color(int normal_drawable_left_color) {
        this.normal_drawable_left_color = normal_drawable_left_color;
        return this;
    }

    public int getChecked_drawable_left_color() {
        return checked_drawable_left_color;
    }

    public ThirdHelper setChecked_drawable_left_color(int checked_drawable_left_color) {
        this.checked_drawable_left_color = checked_drawable_left_color;
        return this;
    }

    public int getNormal_drawable_top_color() {
        return normal_drawable_top_color;
    }

    public ThirdHelper setNormal_drawable_top_color(int normal_drawable_top_color) {
        this.normal_drawable_top_color = normal_drawable_top_color;
        return this;
    }

    public int getChecked_drawable_top_color() {
        return checked_drawable_top_color;
    }

    public ThirdHelper setChecked_drawable_top_color(int checked_drawable_top_color) {
        this.checked_drawable_top_color = checked_drawable_top_color;
        return this;
    }

    public int getNormal_drawable_right_color() {
        return normal_drawable_right_color;
    }

    public ThirdHelper setNormal_drawable_right_color(int normal_drawable_right_color) {
        this.normal_drawable_right_color = normal_drawable_right_color;
        return this;
    }

    public int getChecked_drawable_right_color() {
        return checked_drawable_right_color;
    }

    public ThirdHelper setChecked_drawable_right_color(int checked_drawable_right_color) {
        this.checked_drawable_right_color = checked_drawable_right_color;
        return this;
    }

    public int getNormal_drawable_bottom_color() {
        return normal_drawable_bottom_color;
    }

    public ThirdHelper setNormal_drawable_bottom_color(int normal_drawable_bottom_color) {
        this.normal_drawable_bottom_color = normal_drawable_bottom_color;
        return this;
    }

    public int getChecked_drawable_bottom_color() {
        return checked_drawable_bottom_color;
    }

    public ThirdHelper setChecked_drawable_bottom_color(int checked_drawable_bottom_color) {
        this.checked_drawable_bottom_color = checked_drawable_bottom_color;
        return this;
    }

    public PorterDuff.Mode getColorFilter() {
        return colorFilter;
    }

    public void setColorFilter(PorterDuff.Mode colorFilter) {
        this.colorFilter = colorFilter;
    }
}
