package com.github.fastshape.viewhelper;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.github.fastshape.R;
import com.github.fastshape.inter.CompleteInter;

/***
 *   created by zhongrui on 2018/12/31
 *   MyEditText
 */
public class FourthHelper extends SecondHelper{

    public FourthHelper(CompleteInter completeInter) {
        super(completeInter);
    }
    public TypedArray initCompat(Context context, AttributeSet attrs ,int defStyleAttr) {
        TypedArray viewNormal = context.obtainStyledAttributes(attrs, R.styleable.FastShapeAttr,defStyleAttr,0);
        /*第一部分公共属性*/
        publicFirstAttr(viewNormal);

        /*第二部分公共属性*/
        publicSecondAttr(viewNormal);

        /*裁剪属性 viewGroup属性*/
//        clipAttr(viewNormal);
        return viewNormal;
    }
}
