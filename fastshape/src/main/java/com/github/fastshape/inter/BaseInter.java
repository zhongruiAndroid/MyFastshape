package com.github.fastshape.inter;

import android.graphics.drawable.Drawable;

import com.github.fastshape.MyLinearLayout;
import com.github.fastshape.bean.BaseBean;

/**
 * @createBy Administrator
 * @time 2018-12-17 16:35
 */
public interface BaseInter<T> {
    T clearAttr();

    Drawable getDrawable_normal();

    T setDrawable_normal(Drawable drawable_normal);

    Drawable getDrawable_press();

    T setDrawable_press(Drawable drawable_press);

    int getPressColor();

    T setPressColor(int pressColor);

    boolean isAll_line();

    T setAll_line(boolean all_line);

    boolean isLeft_line();

    T setLeft_line(boolean left_line);

    boolean isTop_line();

    T setTop_line(boolean top_line);

    boolean isRight_line();

    T setRight_line(boolean right_line);

    boolean isBottom_line();

    T setBottom_line(boolean bottom_line);

    int getShapeType();

    T setShapeType(int shapeType);

    float getBorderWidth();

    T setBorderWidth(float borderWidth);

    int getBorderColor();

    T setBorderColor(int borderColor);

    float getBorderDashWidth();

    T setBorderDashWidth(float borderDashWidth);

    float getBorderDashGap();

    T setBorderDashGap(float borderDashGap);

    int getSolidColor();

    T setSolidColor(int solidColor);

    T setRadius(float topLeftRadius, float topRightRadius, float bottomRightRadius, float bottomLeftRadius);

    T setRadius(float radius);


    float getTopLeftRadius();

    T setTopLeftRadius(float topLeftRadius);

    float getTopRightRadius();

    T setTopRightRadius(float topRightRadius);

    float getBottomLeftRadius();

    T setBottomLeftRadius(float bottomLeftRadius);

    float getBottomRightRadius();

    T setBottomRightRadius(float bottomRightRadius);

    int getGradientType();

    T setGradientType(int gradientType);

    int getGradientAngle();

    T setGradientAngle(int gradientAngle);

    float getGradientCenterX();

    T setGradientCenterX(float gradientCenterX);

    float getGradientCenterY();

    T setGradientCenterY(float gradientCenterY);

    int getGradientStartColor();

    T setGradientStartColor(int gradientStartColor);

    int getGradientCenterColor();

    T setGradientCenterColor(int gradientCenterColor);

    int getGradientEndColor();

    T setGradientEndColor(int gradientEndColor);

    float getGradientRadius();

    T setGradientRadius(float gradientRadius);
}
