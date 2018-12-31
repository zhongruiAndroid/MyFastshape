package com.github.fastshape.newbean;

import android.graphics.drawable.Drawable;

import com.github.fastshape.inter.CompleteInter;

/***
 *   created by zhongrui on 2018/12/31
 *   MyCheckBox,MyRadioButton
 */
public class ThirdHelper extends SecondHelper {
    /*设置normal和check状态的drawable*/
    protected Drawable normal_drawable;
    protected Drawable checked_drawable;

    /*设置normal和check状态的文字颜色*/
    protected Drawable normal_textColor;
    protected Drawable checked_textColor;

    /*
     * 设置button所在方向left,top,right,bottom
     * 设置此属性需要:android:button="@null"
     * */
    protected Drawable drawable_direction;

    public ThirdHelper(CompleteInter completeInter) {
        super(completeInter);
    }
}
