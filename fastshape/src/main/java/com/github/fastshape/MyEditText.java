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
import com.github.fastshape.viewhelper.FourthHelper;
import com.github.fastshape.viewhelper.SetBackgroundUtil;

/**
 * Created by Administrator on 2016/9/6.
 */
public class MyEditText extends AppCompatEditText {

    /*****************************clearIcon*******************************/
    /***清除文本内容的icon*/
    public boolean focusFlag;
    private boolean hiddenClearIcon;
    /*设置清除按钮drawable(点击清除内容)*/
    protected Drawable clearIconDrawable;
    /*设置清除按钮宽度,只设置其中一个属性自动适配另外一个属性*/
    protected int clearIcon_width;
    protected int clearIcon_height;

    public MyEditText.OnRightListener onRightListener;


    private FourthHelper viewHelper;

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
            @Override
            public boolean isEditMode() {
                return isInEditMode();
            }
        });
        init(attrs);
    }

    public FourthHelper getViewHelper() {
        return viewHelper;
    }

    /*public void setViewHelper(SecondHelper baseHelper) {
        this.viewHelper = baseHelper;
    }*/
    public void init(AttributeSet attrs) {
        TypedArray typedArray = viewHelper.initCompat(getContext(), attrs, R.attr.MyEditTextStyle);

        clearIconDrawable = typedArray.getDrawable(R.styleable.FastShapeAttr_clearIconDrawable);
        hiddenClearIcon = typedArray.getBoolean(R.styleable.FastShapeAttr_hiddenClearIcon, false);
        clearIcon_width = (int) typedArray.getDimension(R.styleable.FastShapeAttr_clearIcon_width, 0);
        clearIcon_height = (int) typedArray.getDimension(R.styleable.FastShapeAttr_clearIcon_height, 0);

        typedArray.recycle();

        setClearIconDrawable(clearIconDrawable);
        // 默认设置隐藏图标
        setClearIconVisible(false);
        // 设置焦点改变的监听
        setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                focusFlag = hasFocus;
                if (hasFocus) {
                    setClearIconVisible(getText().length() > 0);
                } else {
                    setClearIconVisible(false);
                }
            }
        });
        // 设置输入框里面内容发生改变的监听
        addTextChangedListener(getWatcher());

        Drawable background = getBackground();

        if (background instanceof ColorDrawable && background != null) {
            SetBackgroundUtil.setCompoundDrawables(this, viewHelper);
        } else {
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
            SetBackgroundUtil.setCompoundDrawables(this, viewHelper);
        }
    }


    /*****************************clearIcon*******************************/

    public interface OnRightListener {
        boolean clickRight();
    }

    public void setOnRightListener(MyEditText.OnRightListener onRightListener) {
        this.onRightListener = onRightListener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (getCompoundDrawables()[2] != null) {
                boolean touchable = event.getX() > (getWidth() - getTotalPaddingRight()) && (event.getX() < ((getWidth() - getPaddingRight())));
                if (touchable) {
                    if (this.onRightListener == null || this.onRightListener.clickRight() == false) {
                        this.setText("");
                    }
                }
            }
        }
        return super.onTouchEvent(event);
    }

    /**
     * 设置清除图标的显示与隐藏，调用setCompoundDrawables为EditText绘制上去
     *
     * @param visible
     */
    private void setClearIconVisible(boolean visible) {
        Drawable right = (visible && !hiddenClearIcon) ? clearIconDrawable : null;
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], right, getCompoundDrawables()[3]);
    }

    /**
     * 设置光标位置
     *
     * @param text
     * @param type
     */
    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
        if (!TextUtils.isEmpty(text)) {
            try {
                setSelection(text.length());
            } catch (Exception e) {
                Log.e("Exception", "输入字符长度超出限制");
            }
        }
    }

    /**
     * 设置是否显示(隐藏)清除文本内容的icon(默认false-显示)
     *
     * @param isHiddenClear true隐藏   false显示
     */
    public MyEditText setHiddenClearIcon(boolean isHiddenClear) {
        this.hiddenClearIcon = isHiddenClear;
        return this;
    }

    public boolean isHiddenClearIcon() {
        return hiddenClearIcon;
    }

    public Drawable getClearIconDrawable() {
        return clearIconDrawable;
    }
    public int getClearIcon_width() {
        return clearIcon_width;
    }

    public MyEditText setClearIcon_width(int clearIcon_width) {
        this.clearIcon_width = clearIcon_width;
        if(clearIconDrawable!=null){
            refreshIconBounds(this.clearIcon_width,this.clearIcon_height);
        }
        return this;
    }

    public int getClearIcon_height() {
        return clearIcon_height;
    }

    public MyEditText setClearIcon_height(int clearIcon_height) {
        this.clearIcon_height = clearIcon_height;
        if(clearIconDrawable!=null){
            refreshIconBounds(this.clearIcon_width,this.clearIcon_height);
        }
        return this;
    }

    /**
     * 设置清除文本内容的icon
     *
     * @param clearDrawable
     */
    public MyEditText setClearIconDrawable(@DrawableRes int clearDrawable) {
        return setClearIconDrawable(ContextCompat.getDrawable(getContext(), clearDrawable));
    }

    /**
     * 设置清除文本内容的icon
     *
     * @param clearIcon
     */
    public MyEditText setClearIconDrawable(Drawable clearIcon) {
        /*if(isInEditMode()){
            return;
        }*/
        if (clearIcon != null) {
            clearIconDrawable = clearIcon;
        } else {
            clearIconDrawable = getCompoundDrawables()[2];
        }

        if (clearIconDrawable == null) {
            clearIconDrawable = ContextCompat.getDrawable(getContext(), R.drawable.textclear);
        }

        refreshIconBounds(getClearIcon_width(), getClearIcon_height());
//        this.setCompoundDrawablePadding(dip2px(getContext(), 5));
//        this.setPadding(0,0,15,0);

        return this;
    }

    private void refreshIconBounds(int clearIcon_width, int clearIcon_height) {
        /*图片宽高*/
        int width = clearIconDrawable.getIntrinsicWidth();
        int height = clearIconDrawable.getIntrinsicHeight();
        int w=clearIcon_width;
        int h=clearIcon_height;
        if (w > 0 && h > 0) {

        } else if (w > 0) {
            h = (int) SetBackgroundUtil.chuFa(SetBackgroundUtil.chengFa(w, height), width);
        } else if (h > 0) {
            w = (int) SetBackgroundUtil.chuFa(SetBackgroundUtil.chengFa(h, width), height);
        } else {
            w = width;
            h = height;
        }

        clearIconDrawable.setBounds(0, 0, w, h);
    }

    private TextWatcher getWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (focusFlag) {
                    setClearIconVisible(s.length() > 0);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }
}

