package com.github.fastshape;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.github.fastshape.inter.ViewHelperInter;

/**
 * Created by Administrator on 2016/9/6.
 */
public class MyTextView extends AppCompatTextView {

    private BaseViewHelper viewHelper;


    public MyTextView(Context context) {
        super(context);
        init(null);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @Override
    public void setTypeface(Typeface tf) {
        super.setTypeface(tf);
    }

    public BaseViewHelper getViewHelper() {
        return viewHelper;
    }

    public void setViewHelper(BaseViewHelper viewHelper) {
        this.viewHelper = viewHelper;
    }

    private void init(AttributeSet attrs) {
        viewHelper = new BaseViewHelper(new ViewHelperInter() {
            @Override
            public void onComplete() {
                complete();
            }
        });
        if (attrs == null) {
            return;
        }
        Drawable background = getBackground();
        if (background != null) {
            return;
        }
        TypedArray viewNormal = this.getContext().obtainStyledAttributes(attrs, R.styleable.MyTextView);
        Drawable drawable_normal = viewNormal.getDrawable(R.styleable.MyTextView_drawable_normal);
        Drawable drawable_press  = viewNormal.getDrawable(R.styleable.MyTextView_drawable_press);

        viewHelper.left_width = (int) viewNormal.getDimension(R.styleable.MyTextView_left_width,-1);
        viewHelper.left_height= (int) viewNormal.getDimension(R.styleable.MyTextView_left_height,-1);

        viewHelper.top_width = (int) viewNormal.getDimension(R.styleable.MyTextView_top_width,-1);
        viewHelper.top_height= (int) viewNormal.getDimension(R.styleable.MyTextView_top_height,-1);

        viewHelper.right_width = (int) viewNormal.getDimension(R.styleable.MyTextView_right_width,-1);
        viewHelper.right_height= (int) viewNormal.getDimension(R.styleable.MyTextView_right_height,-1);

        viewHelper.bottom_width = (int) viewNormal.getDimension(R.styleable.MyTextView_bottom_width,-1);
        viewHelper.bottom_height= (int) viewNormal.getDimension(R.styleable.MyTextView_bottom_height,-1);


        if(drawable_normal!=null||drawable_press!=null){
            viewHelper.drawable_normal=drawable_normal;
            viewHelper.drawable_press=drawable_press;
            if(drawable_normal==null){
                viewHelper.drawable_normal=drawable_press;
            }
            if(drawable_press==null){
                viewHelper.drawable_press=drawable_normal;
            }
            viewNormal.recycle();
            complete();
            return;
        }

        viewHelper.pressColor = viewNormal.getColor(R.styleable.MyTextView_pressColor, viewHelper.getTransparentColor());
        viewHelper.allLine = viewNormal.getBoolean(R.styleable.MyTextView_all_line, false);
        viewHelper.leftLine = viewNormal.getBoolean(R.styleable.MyTextView_left_line, false);
        viewHelper.topLine = viewNormal.getBoolean(R.styleable.MyTextView_top_line, false);
        viewHelper.rightLine = viewNormal.getBoolean(R.styleable.MyTextView_right_line, false);
        viewHelper.bottomLine = viewNormal.getBoolean(R.styleable.MyTextView_bottom_line, false);
        if(viewHelper.leftLine&&viewHelper.topLine&&viewHelper.rightLine&&viewHelper.bottomLine){
            viewHelper.allLine=true;
        }
        if(!viewHelper.allLine&&(viewHelper.leftLine||viewHelper.topLine||viewHelper.rightLine||viewHelper.bottomLine)){
            viewHelper.isPartBorder=true;
        }

        viewHelper.shapeType = viewNormal.getInteger(R.styleable.MyTextView_shapeType, viewHelper.shapeType_rectangle);
        viewHelper.borderWidth = viewNormal.getDimension(R.styleable.MyTextView_borderWidth, 0);
        viewHelper.borderColor = viewNormal.getColor(R.styleable.MyTextView_borderColor, viewHelper.getTransparentColor());
        viewHelper.borderDashWidth = viewNormal.getDimension(R.styleable.MyTextView_borderDashWidth, 0);
        viewHelper.borderDashGap = viewNormal.getDimension(R.styleable.MyTextView_borderDashGap, 0);


        viewHelper.solidColor = viewNormal.getColor(R.styleable.MyTextView_solidColor, viewHelper.getTransparentColor());

        float radius = viewNormal.getDimension(R.styleable.MyTextView_radius, 0);
        if (radius > 0) {
            viewHelper.topLeftRadius = radius;
            viewHelper.topRightRadius = radius;
            viewHelper.bottomLeftRadius = radius;
            viewHelper.bottomRightRadius = radius;
        } else {
            viewHelper.topLeftRadius = viewNormal.getDimension(R.styleable.MyTextView_topLeftRadius, 0);
            viewHelper.topRightRadius = viewNormal.getDimension(R.styleable.MyTextView_topRightRadius, 0);
            viewHelper.bottomLeftRadius = viewNormal.getDimension(R.styleable.MyTextView_bottomLeftRadius, 0);
            viewHelper.bottomRightRadius = viewNormal.getDimension(R.styleable.MyTextView_bottomRightRadius, 0);
        }

        viewHelper.gradientType = viewNormal.getInteger(R.styleable.MyTextView_gradientType, -1);
        if(viewHelper.gradientType!=-1){
            viewHelper.angle = viewNormal.getInteger(R.styleable.MyTextView_gradientAngle, 0);
            viewHelper.centerX = viewNormal.getFloat(R.styleable.MyTextView_gradientCenterX, 0.5f);
            viewHelper.centerY = viewNormal.getFloat(R.styleable.MyTextView_gradientCenterY, 0.5f);

            viewHelper.startColor = viewNormal.getColor(R.styleable.MyTextView_gradientStartColor, 0);
            viewHelper.centerColor = viewNormal.getColor(R.styleable.MyTextView_gradientCenterColor, 0);
            viewHelper.endColor = viewNormal.getColor(R.styleable.MyTextView_gradientEndColor, 0);

            viewHelper.gradientRadius = viewNormal.getDimension(R.styleable.MyTextView_gradientRadius, 40);
        }


        viewNormal.recycle();

        /**
         * 设置各个自定义属性之后调用此方法设置background
         * 这里有必要说明一下,为什么设置属性了还需要调用这个方法才能生效?
         * 这个方法是将代码设置的各个属性收集生成一个Drawable,然后将它设置为background,简单点这个方法就是用来设置背景的,等价于setBackground方法
         */
        complete();
    }

    /**
     * 设置各个自定义属性之后调用此方法设置background
     * 这里有必要说明一下,为什么设置属性了还需要调用这个方法才能生效?
     * 这个方法是将代码设置的各个属性收集生成一个Drawable,然后将它设置为background,简单点这个方法就是用来设置背景的,等价于setBackground方法
     */
    public void complete() {
        viewHelper.viewComplete(this);
    }
}
