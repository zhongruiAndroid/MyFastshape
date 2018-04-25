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

import com.github.fastshape.inter.ViewHelperInter;

/**
 * Created by Administrator on 2016/9/6.
 */
public class MyEditText extends AppCompatEditText implements View.OnFocusChangeListener {


    private BaseViewHelper viewHelper;
    /***清除文本内容的icon*/
    private Drawable mClearDrawable;
    private boolean hasFoucs,isHiddenClear;
    public MyEditText.OnRightListener onRightListener;
    private int clearIcon_width;
    private int clearIcon_height;

    public interface OnRightListener{
        boolean clickRight();
    }
    public void setOnRightListener(MyEditText.OnRightListener onRightListener) {
        this.onRightListener = onRightListener;
    }
    public MyEditText(Context context) {
        super(context);
        init(null);
    }
    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }
    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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
        if (background instanceof ColorDrawable &&background!=null) {
            return;
        }
        TypedArray viewNormal = this.getContext().obtainStyledAttributes(attrs, R.styleable.MyEditText);
        Drawable drawable_normal = viewNormal.getDrawable(R.styleable.MyEditText_drawable_normal);
        Drawable drawable_press  = viewNormal.getDrawable(R.styleable.MyEditText_drawable_press);


        clearIcon_width =  (int) viewNormal.getDimension(R.styleable.MyEditText_clearIcon_width, -1);
        clearIcon_height = (int) viewNormal.getDimension(R.styleable.MyEditText_clearIcon_height,-1);

        isHiddenClear= viewNormal.getBoolean(R.styleable.MyEditText_hiddenClearIcon, false);
        Drawable clearIcon = viewNormal.getDrawable(R.styleable.MyEditText_clearIconDrawable);
        setRightDrawble(clearIcon);

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

        viewHelper.pressColor = viewNormal.getColor(R.styleable.MyEditText_pressColor, viewHelper.getTransparentColor());
        viewHelper.allLine = viewNormal.getBoolean(R.styleable.MyEditText_all_line, false);
        viewHelper.leftLine = viewNormal.getBoolean(R.styleable.MyEditText_left_line, false);
        viewHelper.topLine = viewNormal.getBoolean(R.styleable.MyEditText_top_line, false);
        viewHelper.rightLine = viewNormal.getBoolean(R.styleable.MyEditText_right_line, false);
        viewHelper.bottomLine = viewNormal.getBoolean(R.styleable.MyEditText_bottom_line, false);
        if(viewHelper.leftLine&&viewHelper.topLine&&viewHelper.rightLine&&viewHelper.bottomLine){
            viewHelper.allLine=true;
        }
        if(!viewHelper.allLine&&(viewHelper.leftLine||viewHelper.topLine||viewHelper.rightLine||viewHelper.bottomLine)){
            viewHelper.isPartBorder=true;
        }

        viewHelper.shapeType = viewNormal.getInteger(R.styleable.MyEditText_shapeType, viewHelper.shapeType_rectangle);
        viewHelper.borderWidth = viewNormal.getDimension(R.styleable.MyEditText_borderWidth, 0);
        viewHelper.borderColor = viewNormal.getColor(R.styleable.MyEditText_borderColor, viewHelper.getTransparentColor());
        viewHelper.borderDashWidth = viewNormal.getDimension(R.styleable.MyEditText_borderDashWidth, 0);
        viewHelper.borderDashGap = viewNormal.getDimension(R.styleable.MyEditText_borderDashGap, 0);


        viewHelper.solidColor = viewNormal.getColor(R.styleable.MyEditText_solidColor, viewHelper.getTransparentColor());

        viewHelper.radius = viewNormal.getDimension(R.styleable.MyEditText_radius, 0);
        if (viewHelper.radius <= 0) {
            viewHelper.topLeftRadius = viewNormal.getDimension(R.styleable.MyEditText_topLeftRadius, 0);
            viewHelper.topRightRadius = viewNormal.getDimension(R.styleable.MyEditText_topRightRadius, 0);
            viewHelper.bottomLeftRadius = viewNormal.getDimension(R.styleable.MyEditText_bottomLeftRadius, 0);
            viewHelper.bottomRightRadius = viewNormal.getDimension(R.styleable.MyEditText_bottomRightRadius, 0);
        }

        viewHelper.gradientType = viewNormal.getInteger(R.styleable.MyEditText_gradientType, -1);
        if(viewHelper.gradientType!=-1){
            viewHelper.angle = viewNormal.getInteger(R.styleable.MyEditText_gradientAngle, 0);
            viewHelper.centerX = viewNormal.getFloat(R.styleable.MyEditText_gradientCenterX, 0.5f);
            viewHelper.centerY = viewNormal.getFloat(R.styleable.MyEditText_gradientCenterY, 0.5f);

            viewHelper.startColor = viewNormal.getColor(R.styleable.MyEditText_gradientStartColor, 0);
            viewHelper.centerColor = viewNormal.getColor(R.styleable.MyEditText_gradientCenterColor, 0);
            viewHelper.endColor = viewNormal.getColor(R.styleable.MyEditText_gradientEndColor, 0);

            viewHelper.gradientRadius = viewNormal.getDimension(R.styleable.MyEditText_gradientRadius, 40);
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


    /***************************************************set方法****************************************************/
    /**
     * 设置清除文本内容的icon
     * @param clearDrawable
     */
    public void setClearDrawable(Drawable clearDrawable) {
//        this.mClearDrawable = clearDrawable;
        setRightDrawble(clearDrawable);
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

    private void setRightDrawble(Drawable clearIcon) {
        if(isInEditMode()){
            return;
        }
        if(clearIcon!=null){
            mClearDrawable=clearIcon;
        }else{
            mClearDrawable = getCompoundDrawables()[2];
        }

        if (mClearDrawable == null) {
//            mClearDrawable = getResources().getDrawable(R.drawable.textclear);
            mClearDrawable = ContextCompat.getDrawable(getContext(),R.drawable.textclear);
        }
        int width=mClearDrawable.getIntrinsicWidth();
        int height=mClearDrawable.getIntrinsicHeight();

        int w=mClearDrawable.getIntrinsicWidth();
        int h=mClearDrawable.getIntrinsicHeight();


        if(clearIcon_width!=-1&&clearIcon_height!=-1){
            w=clearIcon_width;
            h=clearIcon_height;
        }else if(clearIcon_width!=-1){
            w=clearIcon_width;
            h= (int) viewHelper.chuFa(viewHelper.chengFa(clearIcon_width,height),width);
        }else if(clearIcon_height!=-1){
            w=clearIcon_height;
            h= (int) viewHelper.chuFa(viewHelper.chengFa(clearIcon_height,width),height);
        }

        mClearDrawable.setBounds(0, 0, w, h);
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
        Drawable right = visible&&!isHiddenClear ? mClearDrawable : null;
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
    private int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    private int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
