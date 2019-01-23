package com.github.fastshape;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.github.fastshape.bean.BaseHelper;
import com.github.fastshape.inter.CompleteInter;
import com.github.fastshape.newbean.FirstHelper;
import com.github.fastshape.newbean.SecondHelper;
import com.github.fastshape.newbean.SetBackgroundUtil;


/**
 * Created by Administrator on 2016/8/29.
 */
public class MyButton extends AppCompatButton  {
    private SecondHelper viewHelper;
    private int saveLayerCount;
    public MyButton(Context context) {
        this(context, null);
    }

    public MyButton(Context context, AttributeSet attrs) {
        this(context, attrs, FirstHelper.defStyleAttr);
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        viewHelper = new SecondHelper(new CompleteInter() {
            @Override
            public void complete() {
                MyButton.this.complete();
            }
            @Override
            public void completeClip() {
                MyButton.this.completeClip();
            }

            @Override
            public void resetClip() {
                MyButton.this.resetClip();
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
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (viewHelper != null&& viewHelper.getClipSwitch()) {
            viewHelper.onSizeChanged();
            viewHelper.onRefreshPaint(getPaddingLeft(),
                    getPaddingTop(),
                    getPaddingRight(),
                    getPaddingBottom(), getWidth(), getHeight());
        }
    }

    @Override
    public void draw(Canvas canvas) {
        if (viewHelper != null&& viewHelper.isClipBg()&& viewHelper.getClipSwitch()) {
            canvas.save();
            canvas.clipPath(viewHelper.clipPath);
            super.draw(canvas);
            canvas.restore();
        } else {
            super.draw(canvas);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        if (viewHelper != null&& viewHelper.getClipSwitch()) {
            saveLayerCount = canvas.saveLayer(new RectF(0, 0, canvas.getWidth(), canvas.getHeight()), null, Canvas.ALL_SAVE_FLAG);
        }
        super.dispatchDraw(canvas);

        if (viewHelper != null&& viewHelper.getClipSwitch()) {
            viewHelper.dispatchDrawEnd(saveLayerCount, canvas);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            if (viewHelper != null && viewHelper.getClipIsAreaClick()&& viewHelper.getClipSwitch()) {
                if (viewHelper.onTouchEvent(ev) == false) {//如果这个地方返回true会导致点击事件失效
                    return false;
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }



    /*******************************************clip*********************************************/
    public void completeClip() {
        if (viewHelper != null&& viewHelper.getClipSwitch()) {
            if(viewHelper.clipPaint==null){
                viewHelper.onSizeChanged();
            }
            viewHelper.onRefreshPaint(getPaddingLeft(),
                    getPaddingTop(),
                    getPaddingRight(),
                    getPaddingBottom(), getWidth(), getHeight());
            invalidate();
        }
    }
    public void resetClip() {
        if (viewHelper != null) {
            viewHelper.setClipSwitch(false);
            invalidate();
        }
    }
}