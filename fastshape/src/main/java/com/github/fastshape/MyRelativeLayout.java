package com.github.fastshape;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.github.fastshape.inter.ViewHelperInter;


/**
 * Created by Administrator on 2016/9/6.
 */
public class MyRelativeLayout extends RelativeLayout {

    private BaseViewHelper viewHelper;


    public MyRelativeLayout(Context context) {
        super(context);
        init(null);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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
        viewHelper = new BaseViewHelper(this, new ViewHelperInter() {
            @Override
            public void onComplete() {
                complete();
            }
        });
        initData();
        if (attrs == null) {
            return;
        }
        TypedArray viewNormal = this.getContext().obtainStyledAttributes(attrs, R.styleable.MyRelativeLayout);

        setAttrForDraw(viewNormal);


        Drawable background = getBackground();
        if (background != null) {
            viewNormal.recycle();
            return;
        }
        Drawable drawable_normal = viewNormal.getDrawable(R.styleable.MyRelativeLayout_drawable_normal);
        Drawable drawable_press = viewNormal.getDrawable(R.styleable.MyRelativeLayout_drawable_press);

        if (drawable_normal != null || drawable_press != null) {
            viewHelper.drawable_normal = drawable_normal;
            viewHelper.drawable_press = drawable_press;
            if (drawable_normal == null) {
                viewHelper.drawable_normal = drawable_press;
            }
            if (drawable_press == null) {
                viewHelper.drawable_press = drawable_normal;
            }
            viewNormal.recycle();
            complete();
            return;
        }

        viewHelper.pressColor = viewNormal.getColor(R.styleable.MyRelativeLayout_pressColor, viewHelper.getTransparentColor());
        viewHelper.allLine = viewNormal.getBoolean(R.styleable.MyRelativeLayout_all_line, false);
        viewHelper.leftLine = viewNormal.getBoolean(R.styleable.MyRelativeLayout_left_line, false);
        viewHelper.topLine = viewNormal.getBoolean(R.styleable.MyRelativeLayout_top_line, false);
        viewHelper.rightLine = viewNormal.getBoolean(R.styleable.MyRelativeLayout_right_line, false);
        viewHelper.bottomLine = viewNormal.getBoolean(R.styleable.MyRelativeLayout_bottom_line, false);
        if (viewHelper.leftLine && viewHelper.topLine && viewHelper.rightLine && viewHelper.bottomLine) {
            viewHelper.allLine = true;
        }
        if (!viewHelper.allLine && (viewHelper.leftLine || viewHelper.topLine || viewHelper.rightLine || viewHelper.bottomLine)) {
            viewHelper.isPartBorder = true;
        }

        viewHelper.shapeType = viewNormal.getInteger(R.styleable.MyRelativeLayout_shapeType, viewHelper.shapeType_rectangle);
        viewHelper.borderWidth = viewNormal.getDimension(R.styleable.MyRelativeLayout_borderWidth, 0);
        viewHelper.borderColor = viewNormal.getColor(R.styleable.MyRelativeLayout_borderColor, viewHelper.getTransparentColor());
        viewHelper.borderDashWidth = viewNormal.getDimension(R.styleable.MyRelativeLayout_borderDashWidth, 0);
        viewHelper.borderDashGap = viewNormal.getDimension(R.styleable.MyRelativeLayout_borderDashGap, 0);


        viewHelper.solidColor = viewNormal.getColor(R.styleable.MyRelativeLayout_solidColor, viewHelper.getTransparentColor());

        float radius = viewNormal.getDimension(R.styleable.MyRelativeLayout_radius, 0);
        if (radius > 0) {
            viewHelper.topLeftRadius = radius;
            viewHelper.topRightRadius = radius;
            viewHelper.bottomLeftRadius = radius;
            viewHelper.bottomRightRadius = radius;
        } else {
            viewHelper.topLeftRadius = viewNormal.getDimension(R.styleable.MyRelativeLayout_topLeftRadius, 0);
            viewHelper.topRightRadius = viewNormal.getDimension(R.styleable.MyRelativeLayout_topRightRadius, 0);
            viewHelper.bottomLeftRadius = viewNormal.getDimension(R.styleable.MyRelativeLayout_bottomLeftRadius, 0);
            viewHelper.bottomRightRadius = viewNormal.getDimension(R.styleable.MyRelativeLayout_bottomRightRadius, 0);
        }

        viewHelper.gradientType = viewNormal.getInteger(R.styleable.MyRelativeLayout_gradientType, -1);
        if (viewHelper.gradientType != -1) {
            viewHelper.angle = viewNormal.getInteger(R.styleable.MyRelativeLayout_gradientAngle, 0);
            viewHelper.centerX = viewNormal.getFloat(R.styleable.MyRelativeLayout_gradientCenterX, 0.5f);
            viewHelper.centerY = viewNormal.getFloat(R.styleable.MyRelativeLayout_gradientCenterY, 0.5f);

            viewHelper.startColor = viewNormal.getColor(R.styleable.MyRelativeLayout_gradientStartColor, 0);
            viewHelper.centerColor = viewNormal.getColor(R.styleable.MyRelativeLayout_gradientCenterColor, 0);
            viewHelper.endColor = viewNormal.getColor(R.styleable.MyRelativeLayout_gradientEndColor, 0);

            viewHelper.gradientRadius = viewNormal.getDimension(R.styleable.MyRelativeLayout_gradientRadius, 40);
        }


        viewNormal.recycle();

        /**
         * 设置各个自定义属性之后调用此方法设置background
         * 这里有必要说明一下,为什么设置属性了还需要调用这个方法才能生效?
         * 这个方法是将代码设置的各个属性收集生成一个Drawable,然后将它设置为background,简单点这个方法就是用来设置背景的,等价于setBackground方法
         */
        complete();
    }

    private void initData() {
        viewHelper.clipBorderColor = Color.parseColor("#34e8a6");
        viewHelper.clipBorderDashBgColor = Color.WHITE;
    }

    private void setAttrForDraw(TypedArray viewNormal) {
        float clipRadius = viewNormal.getDimension(R.styleable.MyRelativeLayout_clipRadius, 0);
        if (clipRadius > 0) {
            viewHelper.clipTopLeftRadius = clipRadius;
            viewHelper.clipTopRightRadius = clipRadius;
            viewHelper.clipBottomLeftRadius = clipRadius;
            viewHelper.clipBottomRightRadius = clipRadius;
        } else {
            viewHelper.clipTopLeftRadius = viewNormal.getDimension(R.styleable.MyRelativeLayout_clipTopLeftRadius, 0);
            viewHelper.clipTopRightRadius = viewNormal.getDimension(R.styleable.MyRelativeLayout_clipTopRightRadius, 0);
            viewHelper.clipBottomLeftRadius = viewNormal.getDimension(R.styleable.MyRelativeLayout_clipBottomLeftRadius, 0);
            viewHelper.clipBottomRightRadius = viewNormal.getDimension(R.styleable.MyRelativeLayout_clipBottomRightRadius, 0);
        }

        viewHelper.clipIgnorePadding = viewNormal.getBoolean(R.styleable.MyRelativeLayout_clipIgnorePadding, false);
        viewHelper.clipIsCircle = viewNormal.getBoolean(R.styleable.MyRelativeLayout_clipIsCircle, false);
        viewHelper.clipIsAreaClick = viewNormal.getBoolean(R.styleable.MyRelativeLayout_clipIsAreaClick, true);
        viewHelper.clipBorderWidth = viewNormal.getDimension(R.styleable.MyRelativeLayout_clipBorderWidth, 0);
        viewHelper.clipBorderColor = viewNormal.getColor(R.styleable.MyRelativeLayout_clipBorderColor, Color.parseColor("#34e8a6"));
        viewHelper.clipBorderDashBgColor = viewNormal.getColor(R.styleable.MyRelativeLayout_clipBorderDashBgColor, Color.WHITE);
        viewHelper.clipBorderDashWidth = viewNormal.getDimension(R.styleable.MyRelativeLayout_clipBorderDashWidth, 0);
        viewHelper.clipBorderDashGap = viewNormal.getDimension(R.styleable.MyRelativeLayout_clipBorderDashGap, 0);

    }

    /**
     * 设置各个自定义属性之后调用此方法设置background
     * 这里有必要说明一下,为什么设置属性了还需要调用这个方法才能生效?
     * 这个方法是将代码设置的各个属性收集生成一个Drawable,然后将它设置为background,简单点这个方法就是用来设置背景的,等价于setBackground方法
     */
    public void complete() {
        viewHelper.viewComplete(this);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (viewHelper != null) {
            viewHelper.onSizeChanged(getPaddingLeft(),
                    getPaddingTop(),
                    getPaddingRight(),
                    getPaddingBottom(), w, h, oldw, oldh);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (viewHelper != null) {
            viewHelper.onRefreshPaint(canvas, getPaddingLeft(),
                    getPaddingTop(),
                    getPaddingRight(),
                    getPaddingBottom(), getWidth(), getHeight());
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        int saveLayer = canvas.saveLayer(new RectF(0, 0, canvas.getWidth(), canvas.getHeight()), null, Canvas.ALL_SAVE_FLAG);
        super.dispatchDraw(canvas);
        viewHelper.dispatchDrawEnd(saveLayer, canvas);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            if (viewHelper != null && viewHelper.clipIsAreaClick) {
                if (viewHelper.onTouchEvent(ev) == false) {//如果这个地方返回true会导致点击事件失效
                    return false;
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }


}
