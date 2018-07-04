package com.github.fastshape;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.github.fastshape.inter.ViewHelperInter;

/**
 * Created by Administrator on 2017/7/24.
 */

public class MyImageView extends ImageView {
    private BaseViewHelper viewHelper;
    public MyImageView(Context context) {
        super(context);
        init(context,null);
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }
    public BaseViewHelper getViewHelper() {
        return viewHelper;
    }
    private void init(Context context, AttributeSet attrs) {
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
        TypedArray viewNormal = context.obtainStyledAttributes(attrs, R.styleable.MyImageView);

        setAttrForDraw(viewNormal);

/*        radius = viewNormal.getDimension(R.styleable.MyImageView_radius, 0);
        bottomRightRadius = viewNormal.getDimension(R.styleable.MyImageView_bottomRightRadius, 0);
        bottomLeftRadius = viewNormal.getDimension(R.styleable.MyImageView_bottomLeftRadius, 0);
        topRightRadius = viewNormal.getDimension(R.styleable.MyImageView_topRightRadius, 0);
        topLeftRadius = viewNormal.getDimension(R.styleable.MyImageView_topLeftRadius, 0);

        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));

        paintView = new Paint();
        paintView.setXfermode(null);*/
    }
    public void complete(){
        viewHelper.viewComplete(this);
    }
    private void initData() {
        viewHelper.clipBorderColor = Color.parseColor("#34e8a6");
        viewHelper.clipBorderDashBgColor = Color.WHITE;
    }
    private void setAttrForDraw(TypedArray viewNormal) {
        float clipRadius = viewNormal.getDimension(R.styleable.MyImageView_clipRadius, 0);
        if (clipRadius > 0) {
            viewHelper.clipTopLeftRadius = clipRadius;
            viewHelper.clipTopRightRadius = clipRadius;
            viewHelper.clipBottomLeftRadius = clipRadius;
            viewHelper.clipBottomRightRadius = clipRadius;
        } else {
            viewHelper.clipTopLeftRadius = viewNormal.getDimension(R.styleable.MyImageView_clipTopLeftRadius, 0);
            viewHelper.clipTopRightRadius = viewNormal.getDimension(R.styleable.MyImageView_clipTopRightRadius, 0);
            viewHelper.clipBottomLeftRadius = viewNormal.getDimension(R.styleable.MyImageView_clipBottomLeftRadius, 0);
            viewHelper.clipBottomRightRadius = viewNormal.getDimension(R.styleable.MyImageView_clipBottomRightRadius, 0);
        }

        viewHelper.clipIgnorePadding = viewNormal.getBoolean(R.styleable.MyImageView_clipIgnorePadding, false);
        viewHelper.clipIsCircle = viewNormal.getBoolean(R.styleable.MyImageView_clipIsCircle, false);
        viewHelper.clipIsAreaClick = viewNormal.getBoolean(R.styleable.MyImageView_clipIsAreaClick, true);
        viewHelper.clipBorderWidth = viewNormal.getDimension(R.styleable.MyImageView_clipBorderWidth, 0);
        viewHelper.clipBorderColor = viewNormal.getColor(R.styleable.MyImageView_clipBorderColor, Color.parseColor("#34e8a6"));
        viewHelper.clipBorderDashBgColor = viewNormal.getColor(R.styleable.MyImageView_clipBorderDashBgColor, Color.WHITE);
        viewHelper.clipBorderDashWidth = viewNormal.getDimension(R.styleable.MyImageView_clipBorderDashWidth, 0);
        viewHelper.clipBorderDashGap = viewNormal.getDimension(R.styleable.MyImageView_clipBorderDashGap, 0);

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
        if (viewHelper != null) {
            viewHelper.onRefreshPaint(canvas, getPaddingLeft(),
                    getPaddingTop(),
                    getPaddingRight(),
                    getPaddingBottom(), getWidth(), getHeight());
        }
        int saveLayer = canvas.saveLayer(new RectF(0, 0, canvas.getWidth(), canvas.getHeight()), null, Canvas.ALL_SAVE_FLAG);
        super.onDraw(canvas);
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
