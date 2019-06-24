package com.github.fastshape;

import android.content.Context;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.AttributeSet;

import com.github.fastshape.inter.CompleteInter;
import com.github.fastshape.viewhelper.SetBackgroundUtil;
import com.github.fastshape.viewhelper.ThirdHelper;

/**
 * Created by Administrator on 2016/8/29.
 */
public class MyRadioButton extends AppCompatRadioButton {
    private ThirdHelper viewHelper;
    public MyRadioButton(Context context) {
        super(context);
        initHelper(null);
    }

    public MyRadioButton(Context context, AttributeSet attrs) {
        super(context,attrs);
        initHelper(attrs);
    }

    public MyRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initHelper(attrs);
    }

    private void initHelper(AttributeSet attrs) {
        viewHelper = new ThirdHelper(new CompleteInter() {
            @Override
            public void complete() {
                MyRadioButton.this.complete();
            }
            @Override
            public void completeClip() {
            }
            @Override
            public void resetClip() {
            }
            @Override
            public boolean isEditMode() {
                return isInEditMode();
            }
        });
        init(attrs);
    }

    public ThirdHelper getViewHelper() {
        return viewHelper;
    }

    /*public void setViewHelper(ThirdHelper baseHelper) {
        this.viewHelper = baseHelper;
    }*/

    public void init(AttributeSet attrs ) {
        viewHelper.init(getContext(), attrs ,R.attr.MyRadioButtonStyle);
        if (getBackground() == null) {
            complete();
        }else{
            SetBackgroundUtil.setCompoundDrawables(this,viewHelper);
        }
    }

    /**
     * 设置各个自定义属性之后调用此方法设置background
     * 这里有必要说明一下,为什么设置属性了还需要调用这个方法才能生效?
     * 这个方法是将代码设置的各个属性收集生成一个Drawable,然后将它设置为background,简单点这个方法就是用来设置背景的,等价于setBackground方法
     */
    public void complete() {
        if (viewHelper != null) {
            SetBackgroundUtil.viewComplete(this, viewHelper);
            SetBackgroundUtil.setCompoundDrawables(this,viewHelper);
        }
    }

}
