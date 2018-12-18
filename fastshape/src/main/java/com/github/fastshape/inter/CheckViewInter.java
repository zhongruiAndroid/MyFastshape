package com.github.fastshape.inter;

import android.graphics.drawable.Drawable;

/**
 * @createBy Administrator
 * @time 2018-12-17 16:36
 */
public interface CheckViewInter {
    public Drawable getNormal_drawable() ;

    public void setNormal_drawable(Drawable normal_drawable);

    public Drawable getChecked_drawable() ;

    public void setChecked_drawable(Drawable checked_drawable);

    public Drawable getNormal_textColor() ;

    public void setNormal_textColor(Drawable normal_textColor);

    public Drawable getChecked_textColor();

    public void setChecked_textColor(Drawable checked_textColor) ;

    public Drawable getDrawable_direction();

    public void setDrawable_direction(Drawable drawable_direction);
}
