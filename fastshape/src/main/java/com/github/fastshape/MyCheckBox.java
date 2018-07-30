package com.github.fastshape;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.AttributeSet;

import com.github.fastshape.inter.ViewHelperInter;

/**
 * Created by Administrator on 2016/8/29.
 */
public class MyCheckBox extends AppCompatCheckBox {

   private CheckViewHelper viewHelper;

    public MyCheckBox(Context context) {
        super(context);
            init(null);
    }
    public MyCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
            init(attrs);
    }
    public MyCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
            init(attrs);
    }

    public CheckViewHelper getViewHelper() {
        return viewHelper;
    }

    public void setViewHelper(CheckViewHelper viewHelper) {
        this.viewHelper = viewHelper;
    }

    private void init(AttributeSet attrs){
        viewHelper=new CheckViewHelper(new ViewHelperInter() {
            @Override
            public void onComplete() {
                complete();
            }
        });
        if(attrs==null){
            return;
        }
        TypedArray viewNormal = this.getContext().obtainStyledAttributes(attrs, R.styleable.MyCheckBox);
        viewHelper.normal_drawable = viewNormal.getDrawable(R.styleable.MyCheckBox_normal_drawable);
        viewHelper.checked_drawable = viewNormal.getDrawable(R.styleable.MyCheckBox_checked_drawable);

        viewHelper.normal_textColor = viewNormal.getColor(R.styleable.MyCheckBox_normal_textColor, this.getTextColors().getDefaultColor());
        viewHelper.checked_textColor = viewNormal.getColor(R.styleable.MyCheckBox_checked_textColor,this.getTextColors().getDefaultColor());

        viewHelper.left_width = (int) viewNormal.getDimension(R.styleable.MyCheckBox_left_width, -1);
        viewHelper.left_height = (int) viewNormal.getDimension(R.styleable.MyCheckBox_left_height,-1);


        viewHelper.top_width =  (int) viewNormal.getDimension(R.styleable.MyCheckBox_top_width, -1);
        viewHelper.top_height = (int) viewNormal.getDimension(R.styleable.MyCheckBox_top_height,-1);


        viewHelper.right_width =  (int) viewNormal.getDimension(R.styleable.MyCheckBox_right_width, -1);
        viewHelper.right_height = (int) viewNormal.getDimension(R.styleable.MyCheckBox_right_height,-1);


        viewHelper.bottom_width =  (int) viewNormal.getDimension(R.styleable.MyCheckBox_bottom_width, -1);
        viewHelper.bottom_height = (int) viewNormal.getDimension(R.styleable.MyCheckBox_bottom_height,-1);



        viewHelper.drawable_direction = viewNormal.getInteger(R.styleable.MyCheckBox_drawable_direction, CheckViewHelper.DEFAULT);
        viewNormal.recycle();

        complete();
    }

    /**
     * 设置各个自定义属性之后调用此方法设置ButtonDrawable
     * 这里有必要说明一下,为什么设置属性了还需要调用这个方法才能生效?
     * 这个方法是将代码设置的各个属性收集生成一个Drawable,然后将它设置为ButtonDrawable,简单点这个方法就是用来设置背景的,等价于setButtonDrawable方法
     */
    public void complete() {
        if (viewHelper != null) {
            viewHelper.viewComplete(this);
        }
    }


}
