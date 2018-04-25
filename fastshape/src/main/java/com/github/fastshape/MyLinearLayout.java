package com.github.fastshape;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.github.fastshape.inter.ViewHelperInter;


/**
 * Created by Administrator on 2016/9/6.
 */
public class MyLinearLayout extends LinearLayout {

    private BaseViewHelper viewHelper;


    public MyLinearLayout(Context context) {
        super(context);
        init(null);
    }

    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public MyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    public BaseViewHelper getViewHelper() {
        return viewHelper;
    }

    public void setViewHelper(BaseViewHelper viewHelper) {
        this.viewHelper = viewHelper;
    }

    private void init(AttributeSet attrs) {
        if (attrs == null) {
            return;
        }
        viewHelper = new BaseViewHelper(new ViewHelperInter() {
            @Override
            public void onComplete() {
                complete();
            }
        });
        Drawable background = getBackground();
        if (background != null) {
            return;
        }
        TypedArray viewNormal = this.getContext().obtainStyledAttributes(attrs, R.styleable.MyLinearLayout);
        Drawable drawable_normal = viewNormal.getDrawable(R.styleable.MyLinearLayout_drawable_normal);
        Drawable drawable_press  = viewNormal.getDrawable(R.styleable.MyLinearLayout_drawable_press);

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

        viewHelper.pressColor = viewNormal.getColor(R.styleable.MyLinearLayout_pressColor, viewHelper.getTransparentColor());
        viewHelper.allLine = viewNormal.getBoolean(R.styleable.MyLinearLayout_all_line, false);
        viewHelper.leftLine = viewNormal.getBoolean(R.styleable.MyLinearLayout_left_line, false);
        viewHelper.topLine = viewNormal.getBoolean(R.styleable.MyLinearLayout_top_line, false);
        viewHelper.rightLine = viewNormal.getBoolean(R.styleable.MyLinearLayout_right_line, false);
        viewHelper.bottomLine = viewNormal.getBoolean(R.styleable.MyLinearLayout_bottom_line, false);
        if(viewHelper.leftLine&&viewHelper.topLine&&viewHelper.rightLine&&viewHelper.bottomLine){
            viewHelper.allLine=true;
        }
        if(!viewHelper.allLine&&(viewHelper.leftLine||viewHelper.topLine||viewHelper.rightLine||viewHelper.bottomLine)){
            viewHelper.isPartBorder=true;
        }

        viewHelper.shapeType = viewNormal.getInteger(R.styleable.MyLinearLayout_shapeType, viewHelper.shapeType_rectangle);
        viewHelper.borderWidth = viewNormal.getDimension(R.styleable.MyLinearLayout_borderWidth, 0);
        viewHelper.borderColor = viewNormal.getColor(R.styleable.MyLinearLayout_borderColor, viewHelper.getTransparentColor());
        viewHelper.borderDashWidth = viewNormal.getDimension(R.styleable.MyLinearLayout_borderDashWidth, 0);
        viewHelper.borderDashGap = viewNormal.getDimension(R.styleable.MyLinearLayout_borderDashGap, 0);


        viewHelper.solidColor = viewNormal.getColor(R.styleable.MyLinearLayout_solidColor, viewHelper.getTransparentColor());

        viewHelper.radius = viewNormal.getDimension(R.styleable.MyLinearLayout_radius, 0);
        if (viewHelper.radius <= 0) {
            viewHelper.topLeftRadius = viewNormal.getDimension(R.styleable.MyLinearLayout_topLeftRadius, 0);
            viewHelper.topRightRadius = viewNormal.getDimension(R.styleable.MyLinearLayout_topRightRadius, 0);
            viewHelper.bottomLeftRadius = viewNormal.getDimension(R.styleable.MyLinearLayout_bottomLeftRadius, 0);
            viewHelper.bottomRightRadius = viewNormal.getDimension(R.styleable.MyLinearLayout_bottomRightRadius, 0);
        }

        viewHelper.gradientType = viewNormal.getInteger(R.styleable.MyLinearLayout_gradientType, -1);
        if(viewHelper.gradientType!=-1){
            viewHelper.angle = viewNormal.getInteger(R.styleable.MyLinearLayout_gradientAngle, 0);
            viewHelper.centerX = viewNormal.getFloat(R.styleable.MyLinearLayout_gradientCenterX, 0.5f);
            viewHelper.centerY = viewNormal.getFloat(R.styleable.MyLinearLayout_gradientCenterY, 0.5f);

            viewHelper.startColor = viewNormal.getColor(R.styleable.MyLinearLayout_gradientStartColor, 0);
            viewHelper.centerColor = viewNormal.getColor(R.styleable.MyLinearLayout_gradientCenterColor, 0);
            viewHelper.endColor = viewNormal.getColor(R.styleable.MyLinearLayout_gradientEndColor, 0);

            viewHelper.gradientRadius = viewNormal.getDimension(R.styleable.MyLinearLayout_gradientRadius, 40);
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
