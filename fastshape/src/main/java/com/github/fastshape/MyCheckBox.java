package com.github.fastshape;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.AttributeSet;

import com.github.fastshape.inter.CompleteInter;
import com.github.fastshape.inter.ViewHelperInter;
import com.github.fastshape.newbean.FirstHelper;
import com.github.fastshape.newbean.SecondHelper;
import com.github.fastshape.newbean.SetBackgroundUtil;
import com.github.fastshape.newbean.ThirdHelper;

/**
 * Created by Administrator on 2016/8/29.
 */
public class MyCheckBox extends AppCompatCheckBox  {
    private ThirdHelper viewHelper;
    public MyCheckBox(Context context) {
        this(context, null);
    }

    public MyCheckBox(Context context, AttributeSet attrs) {
        this(context, attrs, FirstHelper.defStyleAttr);
    }

    public MyCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        viewHelper = new ThirdHelper(new CompleteInter() {
            @Override
            public void complete() {
                MyCheckBox.this.complete();
            }
            @Override
            public void completeClip() {
            }
            @Override
            public void resetClip() {
            }
        });
        init(attrs, defStyleAttr);
    }

    public SecondHelper getViewHelper() {
        return viewHelper;
    }

    /*public void setViewHelper(SecondHelper baseHelper) {
        this.viewHelper = baseHelper;
    }*/

    public void init(AttributeSet attrs, int defStyleAttr) {
        Drawable background = getBackground();
        if (background != null) {
            return;
        }
        viewHelper.setDefaultTextColor(this.getCurrentTextColor());
        viewHelper.init(getContext(), attrs, defStyleAttr);
        complete();
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
