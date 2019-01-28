package com.github.fastshape;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.github.fastshape.inter.CompleteInter;
import com.github.fastshape.inter.ViewHelperInter;
import com.github.fastshape.newbean.FourthHelper;
import com.github.fastshape.newbean.SetBackgroundUtil;
import com.github.fastshape.newbean.ThirdHelper;

/**
 * Created by Administrator on 2016/9/6.
 */
public class MyEditText extends AppCompatEditText implements View.OnFocusChangeListener {
    private FourthHelper viewHelper;

    /***清除文本内容的icon*/
    private boolean hasFoucs;
    private boolean isHiddenClear;
    public MyEditText.OnRightListener onRightListener;

    public interface OnRightListener{
        boolean clickRight();
    }
    public void setOnRightListener(MyEditText.OnRightListener onRightListener) {
        this.onRightListener = onRightListener;
    }
    public MyEditText(Context context) {
        super(context);
        initHelper(null);
    }
    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initHelper(attrs);
    }
    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initHelper(attrs);
    }
    public FourthHelper getViewHelper() {
        return viewHelper;
    }
    private void initHelper(AttributeSet attrs) {
        viewHelper = new FourthHelper(new CompleteInter() {
            @Override
            public void complete() {
                MyEditText.this.complete();
            }
            @Override
            public void completeClip() {
            }
            @Override
            public void resetClip() {
            }
        });
        init(attrs);
    }
    public void init(AttributeSet attrs ) {
        viewHelper.init(getContext(), attrs,R.attr.MyEditTextStyle);

        setClearDrawable(viewHelper.getClearIconDrawable());
        Drawable background = getBackground();

        if (background instanceof ColorDrawable &&background!=null) {
            SetBackgroundUtil.setCompoundDrawables(this,viewHelper);
        }else{
            complete();
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
    /***************************************************set方法****************************************************/
    /**
     * 设置清除文本内容的icon
     * @param clearDrawable
     */
    public void setClearDrawable(Drawable clearDrawable) {
//        this.mClearDrawable = clearDrawable;
        setRightDrawable(clearDrawable);
    }
    /**
     * 设置清除文本内容的icon
     * @param clearDrawable
     */
    public void setClearDrawable(@DrawableRes int clearDrawable) {
        setClearDrawable(getResources().getDrawable(clearDrawable));
    }
    /**
     * 设置是否显示(隐藏)清除文本内容的icon(默认false-显示)
     * @param isHiddenClear  true隐藏   false显示
     */
    public void setHiddenClearIcon(boolean isHiddenClear) {
        this.isHiddenClear = isHiddenClear;
    }

    private void setRightDrawable(Drawable clearIcon) {
        /*if(isInEditMode()){
            return;
        }*/
        if(clearIcon!=null){
            viewHelper.setClearIconDrawable(clearIcon);
        }else{
            viewHelper.setClearIconDrawable( getCompoundDrawables()[2]);
        }

        if (viewHelper.getClearIconDrawable() == null) {
//            mClearDrawable = getResources().getDrawable(R.drawable.textclear);
            viewHelper.setClearIconDrawable( ContextCompat.getDrawable(getContext(),R.drawable.textclear));
        }
        /*图片宽高*/
        int width=viewHelper.getClearIconDrawable().getIntrinsicWidth();
        int height=viewHelper.getClearIconDrawable().getIntrinsicHeight();

        /*属性设置宽高*/
        int w=viewHelper.getClearIcon_width();
        int h=viewHelper.getClearIcon_height();

        if(w>0&&h>0){

        }else if(w>0){
            h= (int) SetBackgroundUtil.chuFa(SetBackgroundUtil.chengFa(w,height),width);
        }else if(h>0){
            w= (int) SetBackgroundUtil.chuFa(SetBackgroundUtil.chengFa(h,width),height);
        }else{
            w=width;
            h=height;
        }

        viewHelper.getClearIconDrawable().setBounds(0, 0, w, h);
//        this.setCompoundDrawablePadding(dip2px(getContext(), 5));
//        this.setPadding(0,0,15,0);
        // 默认设置隐藏图标
        setClearIconVisible(false);
        // 设置焦点改变的监听
        setOnFocusChangeListener(this);
        // 设置输入框里面内容发生改变的监听
        addTextChangedListener(getWatcher());
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (getCompoundDrawables()[2] != null) {
                boolean touchable = event.getX() > (getWidth() - getTotalPaddingRight()) && (event.getX() < ((getWidth() - getPaddingRight())));
                if (touchable) {
                    if(this.onRightListener==null||this.onRightListener.clickRight()==false){
                        this.setText("");
                    }
                }
            }
        }
        return super.onTouchEvent(event);
    }
    /**
     * 设置清除图标的显示与隐藏，调用setCompoundDrawables为EditText绘制上去
     * @param visible
     */
    protected void setClearIconVisible(boolean visible) {
        Drawable right = (visible&&!isHiddenClear) ? viewHelper.getClearIconDrawable() : null;
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], right, getCompoundDrawables()[3]);
    }
    /**
     * 设置光标位置
     * @param text
     * @param type
     */
    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
        if(!TextUtils.isEmpty(text)){
            try {
                setSelection(text.length());
            }catch (Exception e){
                Log.e("Exception", "输入字符长度超出限制");
            }
        }
    }
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        this.hasFoucs = hasFocus;
        if (hasFocus) {
            setClearIconVisible(getText().length() > 0);
        } else {
            setClearIconVisible(false);
        }
    }
    private TextWatcher getWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (hasFoucs) {
                    setClearIconVisible(s.length() > 0);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }
}
