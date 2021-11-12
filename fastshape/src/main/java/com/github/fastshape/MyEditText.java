package com.github.fastshape;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.github.fastshape.inter.CompleteInter;
import com.github.fastshape.viewhelper.FourthHelper;
import com.github.fastshape.viewhelper.SetBackgroundUtil;

/**
 * Created by Administrator on 2016/9/6.
 */
public class MyEditText extends EditText {
    public static final int show_type_hidden = 0;
    public static final int show_type_delete = 1;
    public static final int show_type_look = 2;
    /*****************************clearIcon*******************************/
    /***清除文本内容的icon*/

    protected int iconShowType = show_type_hidden;
    /*设置清除按钮drawable(点击清除内容)*/
    protected Drawable iconDeleteDrawable;

    /*设置清除按钮宽度,只设置其中一个属性自动适配另外一个属性*/
    protected int icon_width;
    protected int icon_height;

    protected Drawable iconLookCloseDrawable;
    protected Drawable iconLookOpenDrawable;
    protected boolean iconLookAlwaysShow = false;


    public OnDeleteListener onRightListener;
    public OnLookListener onLookListener;


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

        iconDeleteDrawable = typedArray.getDrawable(R.styleable.FastShapeAttr_iconDeleteDrawable);
        iconLookCloseDrawable = typedArray.getDrawable(R.styleable.FastShapeAttr_iconLookCloseDrawable);
        iconLookOpenDrawable = typedArray.getDrawable(R.styleable.FastShapeAttr_iconLookOpenDrawable);
        if (iconDeleteDrawable != null) {
            iconShowType = show_type_delete;
        }
        iconShowType = typedArray.getInt(R.styleable.FastShapeAttr_iconShowType, iconShowType);
        icon_width = (int) typedArray.getDimension(R.styleable.FastShapeAttr_icon_width, 0);
        icon_height = (int) typedArray.getDimension(R.styleable.FastShapeAttr_icon_height, 0);
        iconLookAlwaysShow = typedArray.getBoolean(R.styleable.FastShapeAttr_iconLookAlwaysShow, false);

        if (iconShowType == show_type_look) {
            if (iconLookCloseDrawable == null || iconLookOpenDrawable == null) {
                iconShowType = 0;
            }
        }
        typedArray.recycle();

        /*设置清除icon*/
        setIconDeleteDrawable(iconDeleteDrawable);
        /*设置隐藏密码状态icon*/
        setIconLookCloseDrawable(iconLookCloseDrawable);
        /*设置显示密码状态icon*/
        setIconLookOpenDrawable(iconLookOpenDrawable);

        // 默认设置隐藏图标
        setIconVisible(false);
        // 设置焦点改变的监听
        setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    setIconVisible(getText().length() > 0);
                } else {
                    setIconVisible(false);
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

    public interface OnDeleteListener {
        boolean onClick();
    }

    public interface OnLookListener {
        boolean onClick();
    }

    public void setOnRightListener(OnDeleteListener onRightListener) {
        this.onRightListener = onRightListener;
    }

    public void setOnLookListener(OnLookListener onLookListener) {
        this.onLookListener = onLookListener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (getCompoundDrawables()[2] != null) {
                boolean touchable = event.getX() > (getWidth() - getTotalPaddingRight()) && (event.getX() < ((getWidth() - getPaddingRight())));
                if (touchable) {
                    if (isShowDeleteIcon()) {
                        if (this.onRightListener == null || !this.onRightListener.onClick()) {
                            this.setText("");
                        }
                    } else if (isShowLookIcon()) {
                        int inputType = getInputType();
                        if (onLookListener == null || !onLookListener.onClick()) {
                            if (!isVisiblePassword()) {
                                originInputType = inputType;
                                setInputType(originInputType | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                            } else {
                                setInputType(originInputType);
                            }
                        }
                    }
                }
            }
        }
        return super.onTouchEvent(event);
    }

    private int textWebPassword = 0x000000e1;
    private int textPassword = 0x00000081;
    private int textVisiblePassword = 0x00000091;
    private int numberPassword = 0x00000012;
    private int originInputType = textPassword;

    public boolean isVisiblePassword() {
        int inputType = getInputType();
        if ((inputType & InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
            return true;
        }
        if ((inputType & textWebPassword) == textWebPassword) {
            return false;
        }
        if ((inputType & textPassword) == textPassword) {
            return false;
        }
        if ((inputType & textVisiblePassword) == textVisiblePassword) {
            return false;
        }
        if ((inputType & numberPassword) == numberPassword) {
            return false;
        }
        return true;
    }

    /**
     * 设置清除图标的显示与隐藏，调用setCompoundDrawables为EditText绘制上去
     *
     * @param visible
     */
    private void setIconVisible(boolean visible) {
        Drawable drawable = null;
        if (visible) {
            if (isShowDeleteIcon()) {
                drawable = iconDeleteDrawable;
            } else if (isShowLookIcon()) {
//            if (inputType == 129) {       android:inputType="textPassword"
                if (!isVisiblePassword()) {
                    drawable = this.iconLookCloseDrawable;
                } else {
                    drawable = this.iconLookOpenDrawable;
                }
            }
        } else if (isShowLookIcon() && iconLookAlwaysShow) {
            if (!isVisiblePassword()) {
                drawable = this.iconLookCloseDrawable;
            } else {
                drawable = this.iconLookOpenDrawable;
            }
        }
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], drawable, getCompoundDrawables()[3]);
    }

    private boolean isShowDeleteIcon() {
        return iconShowType == show_type_delete;
    }

    private boolean isShowLookIcon() {
        return iconShowType == show_type_look;
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
            setSelection(text.length());
        }
    }

    public MyEditText setIconShowType(int iconShowType) {
        if (iconShowType != show_type_hidden && iconShowType != show_type_delete && iconShowType != show_type_look) {
            iconShowType = show_type_hidden;
        }
        this.iconShowType = iconShowType;
        setIconVisible(isFocused());
        return this;
    }


    public Drawable getIconDeleteDrawable() {
        return iconDeleteDrawable;
    }

    public int getIcon_width() {
        return icon_width;
    }

    public MyEditText setIcon_width(int icon_width) {
        if (this.icon_width == icon_width) {
            return this;
        }
        this.icon_width = icon_width;
        if (isShowDeleteIcon()) {
            refreshIconBounds(iconDeleteDrawable, this.icon_width, this.icon_height);
            setIconVisible(isFocused());
        } else if (isShowLookIcon()) {
            refreshIconBounds(iconLookCloseDrawable, this.icon_width, this.icon_height);
            refreshIconBounds(iconLookOpenDrawable, this.icon_width, this.icon_height);
            setIconVisible(isFocused());
        }
        return this;
    }

    public int getIcon_height() {
        return icon_height;
    }

    public MyEditText setIcon_height(int icon_height) {
        if (this.icon_width == icon_width) {
            return this;
        }
        this.icon_height = icon_height;
        if (isShowDeleteIcon()) {
            refreshIconBounds(iconDeleteDrawable, this.icon_width, this.icon_height);
            setIconVisible(isFocused());
        } else if (isShowLookIcon()) {
            refreshIconBounds(iconLookCloseDrawable, this.icon_width, this.icon_height);
            refreshIconBounds(iconLookOpenDrawable, this.icon_width, this.icon_height);
            setIconVisible(isFocused());
        }
        return this;
    }

    /**
     * 设置清除文本内容的icon
     *
     * @param clearDrawable
     */
    public MyEditText setIconDeleteDrawable(@DrawableRes int clearDrawable) {
        return setIconDeleteDrawable(ContextCompat.getDrawable(getContext(), clearDrawable));
    }

    /**
     * 设置清除文本内容的icon
     *
     * @param clearIcon
     */
    public MyEditText setIconDeleteDrawable(Drawable clearIcon) {
        /*if(isInEditMode()){
            return;
        }*/
        if (clearIcon != null) {
            iconDeleteDrawable = clearIcon;
        } else {
            iconDeleteDrawable = getCompoundDrawables()[2];
        }

        if (iconDeleteDrawable == null) {
            iconDeleteDrawable = ContextCompat.getDrawable(getContext(), R.drawable.textclear);
        }

        refreshIconBounds(getIcon_width(), getIcon_height());
//        this.setCompoundDrawablePadding(dip2px(getContext(), 5));
//        this.setPadding(0,0,15,0);

        setIconVisible(isFocused());
        return this;
    }

    public MyEditText setIconLookDrawable(@DrawableRes int close, @DrawableRes int open) {
        return setIconLookDrawable(ContextCompat.getDrawable(getContext(), close), ContextCompat.getDrawable(getContext(), open));
    }

    public MyEditText setIconLookDrawable(Drawable close, Drawable open) {
        if (close == null || open == null) {
            return this;
        }
        setIconLookCloseDrawable(close);
        setIconLookOpenDrawable(open);
        setIconVisible(isFocused());
        return this;
    }

    private void setIconLookCloseDrawable(Drawable icon) {
        if (icon != null) {
            iconLookCloseDrawable = icon;
        } else {
            iconLookCloseDrawable = getCompoundDrawables()[2];
        }
        refreshIconBounds(this.iconLookCloseDrawable, getIcon_width(), getIcon_height());
    }

    private void setIconLookOpenDrawable(Drawable icon) {
        if (icon != null) {
            iconLookOpenDrawable = icon;
        } else {
            iconLookOpenDrawable = getCompoundDrawables()[2];
        }
        refreshIconBounds(this.iconLookOpenDrawable, getIcon_width(), getIcon_height());
    }

    private void refreshIconBounds(int icon_width, int icon_height) {
        /*图片宽高*/
        refreshIconBounds(iconDeleteDrawable, icon_width, icon_height);
    }

    private void refreshIconBounds(Drawable drawable, int icon_width, int icon_height) {
        if (drawable == null) {
            return;
        }
        /*图片宽高*/
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        int w = icon_width;
        int h = icon_height;
        if (w > 0 && h > 0) {

        } else if (w > 0) {
            h = (int) SetBackgroundUtil.chuFa(SetBackgroundUtil.chengFa(w, height), width);
        } else if (h > 0) {
            w = (int) SetBackgroundUtil.chuFa(SetBackgroundUtil.chengFa(h, width), height);
        } else {
            w = width;
            h = height;
        }

        drawable.setBounds(0, 0, w, h);
    }

    public MyEditText setIconLookAlwaysShow(boolean iconLookAlwaysShow) {
        if (this.iconLookAlwaysShow != iconLookAlwaysShow) {
            this.iconLookAlwaysShow = iconLookAlwaysShow;
            setIconVisible(isFocused());
        }
        return this;
    }

    private TextWatcher getWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (isFocused()) {
                    setIconVisible(s.length() > 0);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }
}

