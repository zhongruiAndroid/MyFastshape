package com.github.fastshape.inter;

import android.graphics.PathEffect;
import android.graphics.Shader;

/***
 *   created by zhongrui on 2018/12/30
 */
public interface ClipInter<T> {
    boolean isClipBg();

    T setClipBg(boolean clipBg);

    boolean isClipIsCircle();

    T setClipIsCircle(boolean clipIsCircle);

    boolean isClipIsAreaClick();

    T setClipIsAreaClick(boolean clipIsAreaClick);

    boolean isClipIgnorePadding();

    T setClipIgnorePadding(boolean clipIgnorePadding);

    float getClipTopLeftRadius();

    T setClipTopLeftRadius(float clipTopLeftRadius);

    float getClipTopRightRadius();

    T setClipTopRightRadius(float clipTopRightRadius);

    float getClipBottomLeftRadius();

    T setClipBottomLeftRadius(float clipBottomLeftRadius);

    float getClipBottomRightRadius();

    T setClipBottomRightRadius(float clipBottomRightRadius);

    float getClipBorderWidth();
    

    T setClipBorderWidth(float clipBorderWidth);

    int getClipBorderColor();

    T setClipBorderColor(int clipBorderColor);

    float getClipBorderDashWidth();

    T setClipBorderDashWidth(float clipBorderDashWidth);

    float getClipBorderDashGap();

    T setClipBorderDashGap(float clipBorderDashGap);

    int getClipBorderDashBgColor();

    T setClipBorderDashBgColor(int clipBorderDashBgColor);

    int getClipBorderPhase();

    T setClipBorderPhase(int clipBorderPhase);

    Shader getShader();

    T setShader(Shader shader);

    PathEffect getPathEffect();

    T setPathEffect(PathEffect pathEffect);
}
