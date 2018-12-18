package com.github.fastshape.inter;

import android.graphics.drawable.Drawable;

/**
 * @createBy Administrator
 * @time 2018-12-17 16:36
 */
public interface EditTextInter {

    public Drawable getClearIconDrawable() ;

    public void setClearIconDrawable(Drawable clearIconDrawable) ;

    public boolean isHiddenClearIcon();

    public void setHiddenClearIcon(boolean hiddenClearIcon);

    public int getClearIcon_width() ;

    public void setClearIcon_width(int clearIcon_width);

    public int getClearIcon_height();

    public void setClearIcon_height(int clearIcon_height);
}
