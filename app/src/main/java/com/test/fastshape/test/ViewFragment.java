package com.test.fastshape.test;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatSeekBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.fastshape.MyCheckBox;
import com.github.fastshape.MyFrameLayout;
import com.github.fastshape.MyLinearLayout;
import com.github.fastshape.MyRadioButton;
import com.github.fastshape.MyRelativeLayout;
import com.github.fastshape.MyTextView;
import com.github.fastshape.newbean.FirstHelper;
import com.github.fastshape.newbean.SecondHelper;
import com.github.fastshape.newbean.ThirdHelper;
import com.skydoves.colorpickerview.ColorPickerView;
import com.skydoves.colorpickerview.listeners.ColorListener;
import com.skydoves.colorpickerview.sliders.AlphaSlideBar;
import com.skydoves.colorpickerview.sliders.BrightnessSlideBar;
import com.test.fastshape.R;

public class ViewFragment extends Fragment implements SeekBar.OnSeekBarChangeListener
        , CompoundButton.OnCheckedChangeListener, View.OnClickListener {
    public static final int type_linearlayout = 1;
    public static final int type_framelayout = 2;
    public static final int type_relativelayout = 3;
    public static final int type_textview = 4;
    public static final int type_checkview = 5;
    public static final int type_radioview = 6;
    private int type;

    LinearLayout ll_content;
    LinearLayout llClip;
    AppCompatSeekBar sbRadiusTopLeft;
    AppCompatSeekBar sbRadiusTopRight;
    AppCompatSeekBar sbRadiusBottomLeft;
    AppCompatSeekBar sbRadiusBottomRight;
    AppCompatSeekBar sbAllRadius;
    AppCompatSeekBar sbBorderWidth;
    AppCompatSeekBar sbBorderDashWidth;
    AppCompatSeekBar sbBorderDashGap;
    CheckBox cbLeftLine;
    CheckBox cbTopLine;
    CheckBox cbRightLine;
    CheckBox cbBottomLine;
    RadioGroup rgShapeType;
    RadioButton rbShapeType1;
    RadioButton rbShapeType2;
    RadioButton rbShapeType3;
    RadioGroup rgGradientType;
    RadioButton rbGradientType1;
    RadioButton rbGradientType2;
    RadioButton rbGradientType3;
    RadioButton rbGradientTypeNone;
    TextView tvPressColor;
    TextView tvSolidColor;
    TextView tvBorderColor;
    TextView tvStartColor;
    TextView tvCenterColor;
    TextView tvEndColor;
    RadioButton selectButton;
    RadioButton rbAngle0;
    RadioButton rbAngle45;
    RadioButton rbAngle90;
    RadioButton rbAngle135;
    RadioButton rbAngle180;
    RadioButton rbAngle225;
    RadioButton rbAngle270;
    RadioButton rbAngle315;
    AppCompatSeekBar sbGradientCenterX;
    AppCompatSeekBar sbGradientCenterY;
    AppCompatSeekBar sbGradientRadius;
    AppCompatSeekBar sbPadding;

    private MyLinearLayout ll;
    private MyFrameLayout fl;
    private MyRelativeLayout rl;
    private MyTextView tv;
    private MyCheckBox cb;
    private MyRadioButton rb;
    FirstHelper firstHelper;
    SecondHelper secondHelper;
    ThirdHelper thirdHelper;
    float radiusScale = 1.5f;

    /**********************裁剪**********************/
    CheckBox cbClipSwitch;
    CheckBox cbClipBg;
    CheckBox cbClipIsCircle;
    CheckBox cbClipIgnorePadding;
    CheckBox cbClipIsAreaClick;
    AppCompatSeekBar sbClipRadiusTopLeft;
    AppCompatSeekBar sbClipRadiusTopRight;
    AppCompatSeekBar sbClipRadiusBottomLeft;
    AppCompatSeekBar sbClipRadiusBottomRight;
    AppCompatSeekBar sbClipAllRadius;
    AppCompatSeekBar sbClipBorderWidth;
    AppCompatSeekBar sbClipBorderDashWidth;
    AppCompatSeekBar sbClipBorderDashGap;
    TextView tvClipBorderColor;
    TextView tvClipDashBgColor;
    AppCompatSeekBar sbClipBorderPhase;

    /***************************MyTextView***************************/
    LinearLayout llTextView;
    CheckBox cbSetDrawable;
    AppCompatSeekBar sbLeftWidth;
    AppCompatSeekBar sbLeftHeight;
    AppCompatSeekBar sbTopWidth;
    AppCompatSeekBar sbTopHeight;
    AppCompatSeekBar sbRightWidth;
    AppCompatSeekBar sbRightHeight;
    AppCompatSeekBar sbBottomWidth;
    AppCompatSeekBar sbBottomHeight;

    /***************************MyCheckBox***************************/
    LinearLayout llCheckBox;
    CheckBox cbSetLeftDrawable;
    CheckBox cbSetTopDrawable;
    CheckBox cbSetRightDrawable;
    CheckBox cbSetBottomDrawable;

    public ViewFragment() {
    }

    public static ViewFragment newInstance(int type) {
        ViewFragment fragment = new ViewFragment();
        Bundle args = new Bundle();
        args.putInt("type", type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getInt("type");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ll_content = view.findViewById(R.id.ll_content);
        llClip = view.findViewById(R.id.llClip);
        llTextView = view.findViewById(R.id.llTextView);
        llCheckBox = view.findViewById(R.id.llCheckBox);

        sbRadiusTopLeft = view.findViewById(R.id.sbRadiusTopLeft);
        sbRadiusTopLeft.setOnSeekBarChangeListener(this);

        sbRadiusTopRight = view.findViewById(R.id.sbRadiusTopRight);
        sbRadiusTopRight.setOnSeekBarChangeListener(this);

        sbRadiusBottomLeft = view.findViewById(R.id.sbRadiusBottomLeft);
        sbRadiusBottomLeft.setOnSeekBarChangeListener(this);

        sbRadiusBottomRight = view.findViewById(R.id.sbRadiusBottomRight);
        sbRadiusBottomRight.setOnSeekBarChangeListener(this);

        sbAllRadius = view.findViewById(R.id.sbAllRadius);
        sbAllRadius.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sbRadiusTopLeft.setProgress(progress);
                sbRadiusTopRight.setProgress(progress);
                sbRadiusBottomLeft.setProgress(progress);
                sbRadiusBottomRight.setProgress(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        sbBorderWidth = view.findViewById(R.id.sbBorderWidth);
        sbBorderWidth.setOnSeekBarChangeListener(this);

        sbBorderDashWidth = view.findViewById(R.id.sbBorderDashWidth);
        sbBorderDashWidth.setOnSeekBarChangeListener(this);

        sbBorderDashGap = view.findViewById(R.id.sbBorderDashGap);
        sbBorderDashGap.setOnSeekBarChangeListener(this);

        cbLeftLine = view.findViewById(R.id.cbLeftLine);
        cbLeftLine.setOnCheckedChangeListener(this);

        cbTopLine = view.findViewById(R.id.cbTopLine);
        cbTopLine.setOnCheckedChangeListener(this);

        cbRightLine = view.findViewById(R.id.cbRightLine);
        cbRightLine.setOnCheckedChangeListener(this);

        cbBottomLine = view.findViewById(R.id.cbBottomLine);
        cbBottomLine.setOnCheckedChangeListener(this);


        rgShapeType = view.findViewById(R.id.rgShapeType);
        rbShapeType1 = view.findViewById(R.id.rbShapeType1);
        rbShapeType1.setOnCheckedChangeListener(this);

        rbShapeType2 = view.findViewById(R.id.rbShapeType2);
        rbShapeType2.setOnCheckedChangeListener(this);

        rbShapeType3 = view.findViewById(R.id.rbShapeType3);
        rbShapeType3.setOnCheckedChangeListener(this);

        rgGradientType = view.findViewById(R.id.rgGradientType);
        rbGradientType1 = view.findViewById(R.id.rbGradientType1);
        rbGradientType1.setOnCheckedChangeListener(this);

        rbGradientType2 = view.findViewById(R.id.rbGradientType2);
        rbGradientType2.setOnCheckedChangeListener(this);

        rbGradientType3 = view.findViewById(R.id.rbGradientType3);
        rbGradientType3.setOnCheckedChangeListener(this);

        rbGradientTypeNone = view.findViewById(R.id.rbGradientTypeNone);
        rbGradientTypeNone.setOnCheckedChangeListener(this);

        tvPressColor = view.findViewById(R.id.tvPressColor);
        tvPressColor.setOnClickListener(this);

        tvSolidColor = view.findViewById(R.id.tvSolidColor);
        tvSolidColor.setOnClickListener(this);

        tvBorderColor = view.findViewById(R.id.tvBorderColor);
        tvBorderColor.setOnClickListener(this);

        tvStartColor = view.findViewById(R.id.tvStartColor);
        tvStartColor.setOnClickListener(this);

        tvCenterColor = view.findViewById(R.id.tvCenterColor);
        tvCenterColor.setOnClickListener(this);

        tvEndColor = view.findViewById(R.id.tvEndColor);
        tvEndColor.setOnClickListener(this);


        rbAngle0 = view.findViewById(R.id.rbAngle0);
        rbAngle0.setOnCheckedChangeListener(this);
//        selectButton = rbAngle0;
        rbAngle45 = view.findViewById(R.id.rbAngle45);
        rbAngle45.setOnCheckedChangeListener(this);

        rbAngle90 = view.findViewById(R.id.rbAngle90);
        rbAngle90.setOnCheckedChangeListener(this);

        rbAngle135 = view.findViewById(R.id.rbAngle135);
        rbAngle135.setOnCheckedChangeListener(this);

        rbAngle180 = view.findViewById(R.id.rbAngle180);
        rbAngle180.setOnCheckedChangeListener(this);

        rbAngle225 = view.findViewById(R.id.rbAngle225);
        rbAngle225.setOnCheckedChangeListener(this);

        rbAngle270 = view.findViewById(R.id.rbAngle270);
        rbAngle270.setOnCheckedChangeListener(this);

        rbAngle315 = view.findViewById(R.id.rbAngle315);
        rbAngle315.setOnCheckedChangeListener(this);

        sbGradientCenterX = view.findViewById(R.id.sbGradientCenterX);
        sbGradientCenterX.setOnSeekBarChangeListener(this);

        sbGradientCenterY = view.findViewById(R.id.sbGradientCenterY);
        sbGradientCenterY.setOnSeekBarChangeListener(this);

        sbGradientRadius = view.findViewById(R.id.sbGradientRadius);
        sbGradientRadius.setOnSeekBarChangeListener(this);

        sbPadding = view.findViewById(R.id.sbPadding);
        sbPadding.setOnSeekBarChangeListener(this);

        initClipView(view);

        initData(view);

    }

    private void initClipView(View view) {
        cbClipSwitch = view.findViewById(R.id.cbClipSwitch);
        cbClipSwitch.setOnCheckedChangeListener(this);

        cbClipBg = view.findViewById(R.id.cbClipBg);
        cbClipBg.setOnCheckedChangeListener(this);

        cbClipIsCircle = view.findViewById(R.id.cbClipIsCircle);
        cbClipIsCircle.setOnCheckedChangeListener(this);

        cbClipIgnorePadding = view.findViewById(R.id.cbClipIgnorePadding);
        cbClipIgnorePadding.setOnCheckedChangeListener(this);

        cbClipIsAreaClick = view.findViewById(R.id.cbClipIsAreaClick);
        cbClipIsAreaClick.setOnCheckedChangeListener(this);

        sbClipRadiusTopLeft = view.findViewById(R.id.sbClipRadiusTopLeft);
        sbClipRadiusTopLeft.setOnSeekBarChangeListener(this);

        sbClipRadiusTopRight = view.findViewById(R.id.sbClipRadiusTopRight);
        sbClipRadiusTopRight.setOnSeekBarChangeListener(this);

        sbClipRadiusBottomLeft = view.findViewById(R.id.sbClipRadiusBottomLeft);
        sbClipRadiusBottomLeft.setOnSeekBarChangeListener(this);

        sbClipRadiusBottomRight = view.findViewById(R.id.sbClipRadiusBottomRight);
        sbClipRadiusBottomRight.setOnSeekBarChangeListener(this);

        sbClipAllRadius = view.findViewById(R.id.sbClipAllRadius);
        sbClipAllRadius.setOnSeekBarChangeListener(this);

        sbClipBorderWidth = view.findViewById(R.id.sbClipBorderWidth);
        sbClipBorderWidth.setOnSeekBarChangeListener(this);

        sbClipBorderDashWidth = view.findViewById(R.id.sbClipBorderDashWidth);
        sbClipBorderDashWidth.setOnSeekBarChangeListener(this);

        sbClipBorderDashGap = view.findViewById(R.id.sbClipBorderDashGap);
        sbClipBorderDashGap.setOnSeekBarChangeListener(this);

        tvClipDashBgColor = view.findViewById(R.id.tvClipDashBgColor);
        tvClipDashBgColor.setOnClickListener(this);

        tvClipBorderColor = view.findViewById(R.id.tvClipBorderColor);
        tvClipBorderColor.setOnClickListener(this);

        sbClipBorderPhase = view.findViewById(R.id.sbClipBorderPhase);
        sbClipBorderPhase.setOnSeekBarChangeListener(this);

    }

    private void initData(View view) {
        View itemView = null;
        if (type == type_linearlayout) {
            itemView = LayoutInflater.from(getActivity()).inflate(R.layout.activity_linearlayout_item, null);
            ll = itemView.findViewById(R.id.ll);
            firstHelper = ll.getViewHelper();
            getViewData();
            llClip.setVisibility(View.VISIBLE);
            llTextView.setVisibility(View.GONE);
        } else if (type == type_framelayout) {
            itemView = LayoutInflater.from(getActivity()).inflate(R.layout.activity_framelayout_item, null);
            fl = itemView.findViewById(R.id.fl);
            firstHelper = fl.getViewHelper();
            getViewData();
            llClip.setVisibility(View.VISIBLE);
            llTextView.setVisibility(View.GONE);
        } else if (type == type_relativelayout) {
            itemView = LayoutInflater.from(getActivity()).inflate(R.layout.activity_relativelayout_item, null);
            rl = itemView.findViewById(R.id.rl);
            firstHelper = rl.getViewHelper();
            getViewData();
            llClip.setVisibility(View.VISIBLE);
            llTextView.setVisibility(View.GONE);
        } else if (type == type_textview) {
            getTextViewId(view);
            itemView = LayoutInflater.from(getActivity()).inflate(R.layout.activity_textview_item, null);
            tv = itemView.findViewById(R.id.tv);
            firstHelper = tv.getViewHelper();
            secondHelper = tv.getViewHelper();
            getViewData();
            llClip.setVisibility(View.GONE);
            llTextView.setVisibility(View.VISIBLE);
            llCheckBox.setVisibility(View.GONE);
        } else if (type == type_checkview) {
            getTextViewId(view);
            getCheckBoxId(view);
            itemView = LayoutInflater.from(getActivity()).inflate(R.layout.activity_checkbox_item, null);
            cb = itemView.findViewById(R.id.cb);
            cb.setChecked(true);
            firstHelper = cb.getViewHelper();
            secondHelper = cb.getViewHelper();
            thirdHelper = cb.getViewHelper();

            getViewData();

            llClip.setVisibility(View.GONE);
            llTextView.setVisibility(View.VISIBLE);
            llCheckBox.setVisibility(View.VISIBLE);
        }else if (type == type_radioview) {
            getTextViewId(view);
            getCheckBoxId(view);
            itemView = LayoutInflater.from(getActivity()).inflate(R.layout.activity_radio_item, null);
            rb = itemView.findViewById(R.id.rb);
            rb.setChecked(true);
            firstHelper = rb.getViewHelper();
            secondHelper = rb.getViewHelper();
            thirdHelper = rb.getViewHelper();

            getViewData();

            llClip.setVisibility(View.GONE);
            llTextView.setVisibility(View.VISIBLE);
            llCheckBox.setVisibility(View.VISIBLE);
        }


        if (itemView != null) {
            ll_content.addView(itemView, 0);
        }
    }

    private void getCheckBoxId(View view) {


        cbSetLeftDrawable = view.findViewById(R.id.cbSetLeftDrawable);
        cbSetLeftDrawable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    thirdHelper.setChecked_drawable_left(ContextCompat.getDrawable(getActivity(),R.drawable.check_select))
                            .setNormal_drawable_left(ContextCompat.getDrawable(getActivity(),R.drawable.normal)).complete();
                }else{
                    thirdHelper.setChecked_drawable_left(null).complete();
                }
            }
        });

        cbSetTopDrawable = view.findViewById(R.id.cbSetTopDrawable);
        cbSetTopDrawable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    thirdHelper.setChecked_drawable_top(ContextCompat.getDrawable(getActivity(),R.drawable.check_select))
                            .setNormal_drawable_top(ContextCompat.getDrawable(getActivity(),R.drawable.normal)).complete();
                }else{
                    thirdHelper.setChecked_drawable_top(null).complete();
                }
            }
        });

        cbSetRightDrawable = view.findViewById(R.id.cbSetRightDrawable);
        cbSetRightDrawable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    thirdHelper.setChecked_drawable_right(ContextCompat.getDrawable(getActivity(),R.drawable.check_select))
                            .setNormal_drawable_right(ContextCompat.getDrawable(getActivity(),R.drawable.normal)).complete();
                }else{
                    thirdHelper.setChecked_drawable_right(null).complete();
                }
            }
        });

        cbSetBottomDrawable = view.findViewById(R.id.cbSetBottomDrawable);
        cbSetBottomDrawable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    thirdHelper.setChecked_drawable_bottom(ContextCompat.getDrawable(getActivity(),R.drawable.check_select))
                            .setNormal_drawable_bottom(ContextCompat.getDrawable(getActivity(),R.drawable.normal)).complete();
                }else{
                    thirdHelper.setChecked_drawable_bottom(null).complete();
                }
            }
        });
    }

    private void getTextViewId(View view) {
        cbSetDrawable = view.findViewById(R.id.cbSetDrawable);
        if(type==type_checkview||type==type_radioview){
            cbSetDrawable.setVisibility(View.GONE);
        }
        cbSetDrawable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Drawable drawable1 = ContextCompat.getDrawable(getActivity(), R.drawable.select);
                    Drawable drawable2 = ContextCompat.getDrawable(getActivity(), R.drawable.select);
                    Drawable drawable3 = ContextCompat.getDrawable(getActivity(), R.drawable.select);
                    Drawable drawable4 = ContextCompat.getDrawable(getActivity(), R.drawable.select);
                    tv.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable1, drawable2, drawable3, drawable4);
                } else {
                    tv.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, null);
                }
            }
        });
        sbLeftWidth = view.findViewById(R.id.sbLeftWidth);
        sbLeftWidth.setOnSeekBarChangeListener(this);

        sbLeftHeight = view.findViewById(R.id.sbLeftHeight);
        sbLeftHeight.setOnSeekBarChangeListener(this);

        sbTopWidth = view.findViewById(R.id.sbTopWidth);
        sbTopWidth.setOnSeekBarChangeListener(this);

        sbTopHeight = view.findViewById(R.id.sbTopHeight);
        sbTopHeight.setOnSeekBarChangeListener(this);

        sbRightWidth = view.findViewById(R.id.sbRightWidth);
        sbRightWidth.setOnSeekBarChangeListener(this);

        sbRightHeight = view.findViewById(R.id.sbRightHeight);
        sbRightHeight.setOnSeekBarChangeListener(this);

        sbBottomWidth = view.findViewById(R.id.sbBottomWidth);
        sbBottomWidth.setOnSeekBarChangeListener(this);

        sbBottomHeight = view.findViewById(R.id.sbBottomHeight);
        sbBottomHeight.setOnSeekBarChangeListener(this);

    }

    private void getViewData() {
        float topLeftRadius = firstHelper.getTopLeftRadius();
        float topRightRadius = firstHelper.getTopRightRadius();
        float bottomLeftRadius = firstHelper.getBottomLeftRadius();
        float bottomRightRadius = firstHelper.getBottomRightRadius();
        float borderWidth = firstHelper.getBorderWidth();
        float borderDashWidth = firstHelper.getBorderDashWidth();
        float borderDashGap = firstHelper.getBorderDashGap();
        boolean left_line = firstHelper.isLeft_line();
        boolean top_line = firstHelper.isTop_line();
        boolean right_line = firstHelper.isRight_line();
        boolean bottom_line = firstHelper.isBottom_line();
        int shapeType = firstHelper.getShapeType();
        int gradientType = firstHelper.getGradientType();
        int pressColor = firstHelper.getPressColor();
        int solidColor = firstHelper.getSolidColor();
        int borderColor = firstHelper.getBorderColor();
        int gradientStartColor = firstHelper.getGradientStartColor();
        int gradientCenterColor = firstHelper.getGradientCenterColor();
        int gradientEndColor = firstHelper.getGradientEndColor();
        int gradientAngle = firstHelper.getGradientAngle();
        float gradientCenterX = firstHelper.getGradientCenterX();
        float gradientCenterY = firstHelper.getGradientCenterY();
        float gradientRadius = firstHelper.getGradientRadius();

        sbRadiusTopLeft.setProgress((int) ((int) topLeftRadius / radiusScale));
        sbRadiusTopRight.setProgress((int) ((int) topRightRadius / radiusScale));
        sbRadiusBottomLeft.setProgress((int) ((int) bottomLeftRadius / radiusScale));
        sbRadiusBottomRight.setProgress((int) ((int) bottomRightRadius / radiusScale));
        sbBorderWidth.setProgress((int) borderWidth);
        sbBorderDashWidth.setProgress((int) borderDashWidth);
        sbBorderDashGap.setProgress((int) borderDashGap);
        cbLeftLine.setChecked(left_line);
        cbTopLine.setChecked(top_line);
        cbRightLine.setChecked(right_line);
        cbBottomLine.setChecked(bottom_line);
        if (shapeType == FirstHelper.shapeType_rectangle) {
            rbShapeType1.setChecked(true);
        } else if (shapeType == FirstHelper.shapeType_oval) {
            rbShapeType2.setChecked(true);
        } else if (shapeType == FirstHelper.shapeType_line) {
            rbShapeType3.setChecked(true);
        }

        if (gradientType == FirstHelper.gradientType_linear) {
            rbGradientType1.setChecked(true);
        } else if (gradientType == FirstHelper.gradientType_radial) {
            rbGradientType2.setChecked(true);
        } else if (gradientType == FirstHelper.gradientType_sweep) {
            rbGradientType3.setChecked(true);
        } else if (gradientType == FirstHelper.gradientType_none) {
            rbGradientTypeNone.setChecked(true);
        }


        tvPressColor.setBackgroundColor(pressColor);
        tvSolidColor.setBackgroundColor(solidColor);
        tvBorderColor.setBackgroundColor(borderColor);
        tvStartColor.setBackgroundColor(gradientStartColor);
        tvCenterColor.setBackgroundColor(gradientCenterColor);
        tvEndColor.setBackgroundColor(gradientEndColor);
        switch (gradientAngle) {
            case FirstHelper.angle_0:
//                    selectButton.setChecked(false);
                selectButton = rbAngle0;
                selectButton.setChecked(true);
                break;
            case FirstHelper.angle_45:
//                    selectButton.setChecked(false);
                selectButton = rbAngle45;
                selectButton.setChecked(true);
                break;
            case FirstHelper.angle_90:
//                    selectButton.setChecked(false);
                selectButton = rbAngle90;
                selectButton.setChecked(true);
                break;
            case FirstHelper.angle_135:
//                    selectButton.setChecked(false);
                selectButton = rbAngle135;
                selectButton.setChecked(true);
                break;
            case FirstHelper.angle_180:
//                    selectButton.setChecked(false);
                selectButton = rbAngle180;
                selectButton.setChecked(true);
                break;
            case FirstHelper.angle_225:
//                    selectButton.setChecked(false);
                selectButton = rbAngle225;
                selectButton.setChecked(true);
                break;
            case FirstHelper.angle_270:
//                    selectButton.setChecked(false);
                selectButton = rbAngle270;
                selectButton.setChecked(true);
                break;
            case FirstHelper.angle_315:
//                    selectButton.setChecked(false);
                selectButton = rbAngle0;
                selectButton.setChecked(true);
                break;
        }
        sbGradientCenterX.setProgress((int) (gradientCenterX * 100));
        sbGradientCenterY.setProgress((int) (gradientCenterY * 100));
        sbGradientRadius.setProgress((int) (gradientRadius / 2));


        boolean clipSwitch = firstHelper.getClipSwitch();
        boolean clipBg = firstHelper.isClipBg();
        boolean clipIsCircle = firstHelper.getClipIsCircle();
        boolean clipIgnorePadding = firstHelper.isClipIgnorePadding();
        boolean clipIsAreaClick = firstHelper.getClipIsAreaClick();
        float clipTopLeftRadius = firstHelper.getClipTopLeftRadius();
        float clipTopRightRadius = firstHelper.getClipTopRightRadius();
        float clipBottomLeftRadius = firstHelper.getClipBottomLeftRadius();
        float clipBottomRightRadius = firstHelper.getClipBottomRightRadius();
        float clipBorderWidth = firstHelper.getClipBorderWidth();
        float clipBorderDashWidth = firstHelper.getClipBorderDashWidth();
        float clipBorderDashGap = firstHelper.getClipBorderDashGap();
        int clipBorderColor = firstHelper.getClipBorderColor();
        int clipBorderDashBgColor = firstHelper.getClipBorderDashBgColor();
        int clipBorderPhase = firstHelper.getClipBorderPhase();

        cbClipSwitch.setChecked(clipSwitch);
        cbClipBg.setChecked(clipBg);
        cbClipIsCircle.setChecked(clipIsCircle);
        cbClipIgnorePadding.setChecked(clipIgnorePadding);
        cbClipIsAreaClick.setChecked(clipIsAreaClick);
        sbClipRadiusTopLeft.setProgress((int) (clipTopLeftRadius / radiusScale));
        sbClipRadiusTopRight.setProgress((int) (clipTopRightRadius / radiusScale));
        sbClipRadiusBottomLeft.setProgress((int) (clipBottomLeftRadius / radiusScale));
        sbClipRadiusBottomRight.setProgress((int) (clipBottomRightRadius / radiusScale));
        sbClipBorderWidth.setProgress((int) clipBorderWidth);
        sbClipBorderDashWidth.setProgress((int) clipBorderDashWidth);
        sbClipBorderDashGap.setProgress((int) clipBorderDashGap);
        tvClipBorderColor.setBackgroundColor(clipBorderColor);
        tvClipDashBgColor.setBackgroundColor(clipBorderDashBgColor);
        sbClipBorderPhase.setProgress(clipBorderPhase);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.sbRadiusTopLeft:
                if (firstHelper != null) {
                    firstHelper.setTopLeftRadius(progress * radiusScale).complete();
                } else {

                }
                break;
            case R.id.sbRadiusTopRight:
                if (firstHelper != null) {
                    firstHelper.setTopRightRadius(progress * radiusScale).complete();
                } else {

                }
                break;
            case R.id.sbRadiusBottomLeft:
                if (firstHelper != null) {
                    firstHelper.setBottomLeftRadius(progress * radiusScale).complete();
                } else {

                }
                break;
            case R.id.sbRadiusBottomRight:
                if (firstHelper != null) {
                    firstHelper.setBottomRightRadius(progress * radiusScale).complete();
                } else {

                }
                break;
            case R.id.sbBorderWidth:
                if (firstHelper != null) {
                    firstHelper.setBorderWidth(progress).complete();
                } else {

                }
                break;
            case R.id.sbBorderDashWidth:
                if (firstHelper != null) {
                    firstHelper.setBorderDashWidth(progress).complete();
                } else {

                }
                break;
            case R.id.sbBorderDashGap:
                if (firstHelper != null) {
                    firstHelper.setBorderDashGap(progress).complete();
                } else {

                }
                break;
            case R.id.sbGradientRadius:
                if (firstHelper != null) {
                    firstHelper.setGradientRadius(progress * 2).complete();
                } else {

                }
                break;
            case R.id.sbPadding:
                if (type == type_linearlayout) {
                    ll.setPadding(progress, progress, progress, progress);
                } else if (type == type_framelayout) {
                    fl.setPadding(progress, progress, progress, progress);
                } else if (type == type_relativelayout) {
                    rl.setPadding(progress, progress, progress, progress);
                }
                break;
            case R.id.sbGradientCenterX:
                if (firstHelper != null) {
                    float x = 1f * progress / 100;
                    firstHelper.setGradientCenterX(x).complete();
                } else {

                }
                break;
            case R.id.sbGradientCenterY:
                if (firstHelper != null) {
                    float y = 1f * progress / 100;
                    firstHelper.setGradientCenterY(y).complete();
                } else {

                }
                break;
            case R.id.sbClipRadiusTopLeft:
                if (firstHelper != null) {
                    firstHelper.setClipTopLeftRadius(progress * radiusScale).completeClip();
                } else {

                }
                break;
            case R.id.sbClipRadiusTopRight:
                if (firstHelper != null) {
                    firstHelper.setClipTopRightRadius(progress * radiusScale).completeClip();
                } else {

                }
                break;
            case R.id.sbClipRadiusBottomLeft:
                if (firstHelper != null) {
                    firstHelper.setClipBottomLeftRadius(progress * radiusScale).completeClip();
                } else {

                }
                break;
            case R.id.sbClipRadiusBottomRight:
                if (firstHelper != null) {
                    firstHelper.setClipBottomRightRadius(progress * radiusScale).completeClip();
                } else {

                }
                break;
            case R.id.sbClipAllRadius:
                if (firstHelper != null) {
                    sbClipRadiusTopLeft.setProgress(progress);
                    sbClipRadiusTopRight.setProgress(progress);
                    sbClipRadiusBottomLeft.setProgress(progress);
                    sbClipRadiusBottomRight.setProgress(progress);
                } else {

                }
                break;
            case R.id.sbClipBorderWidth:
                if (firstHelper != null) {
                    firstHelper.setClipBorderWidth(progress).completeClip();
                } else {

                }
                break;
            case R.id.sbClipBorderDashWidth:
                if (firstHelper != null) {
                    firstHelper.setClipBorderDashWidth(progress).completeClip();
                } else {

                }
                break;
            case R.id.sbClipBorderDashGap:
                if (firstHelper != null) {
                    firstHelper.setClipBorderDashGap(progress).completeClip();
                } else {

                }
                break;
            case R.id.sbClipBorderPhase:
                if (firstHelper != null) {
                    firstHelper.setClipBorderPhase(progress).completeClip();
                } else {

                }
                break;
            case R.id.sbLeftWidth:
                if (secondHelper != null) {
                    secondHelper.setLeft_width(progress).complete();
                } else {

                }
                break;
            case R.id.sbLeftHeight:
                if (secondHelper != null) {
                    secondHelper.setLeft_height(progress).complete();
                } else {

                }
                break;
            case R.id.sbTopWidth:
                if (secondHelper != null) {
                    secondHelper.setTop_width(progress).complete();
                }else{

                }
                break;
            case R.id.sbTopHeight:
                if (secondHelper != null) {
                    secondHelper.setTop_height(progress).complete();
                } else {

                }
                break;
            case R.id.sbRightWidth:
                if (secondHelper != null) {
                    secondHelper.setRight_width(progress).complete();
                } else {

                }
                break;
            case R.id.sbRightHeight:
                if (secondHelper != null) {
                    secondHelper.setRight_height(progress).complete();
                } else {

                }
                break;
            case R.id.sbBottomWidth:
                if (secondHelper != null) {
                    secondHelper.setBottom_width(progress).complete();
                } else {

                }
                break;
            case R.id.sbBottomHeight:
                if (secondHelper != null) {
                    secondHelper.setBottom_height(progress).complete();
                } else {

                }
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.cbLeftLine:
                firstHelper.setLeft_line(isChecked).complete();
                break;
            case R.id.cbTopLine:
                firstHelper.setTop_line(isChecked).complete();
                break;
            case R.id.cbRightLine:
                firstHelper.setRight_line(isChecked).complete();
                break;
            case R.id.cbBottomLine:
                firstHelper.setBottom_line(isChecked).complete();
                break;
            case R.id.rbShapeType1:
                if (isChecked) {
                    firstHelper.setShapeType(FirstHelper.shapeType_rectangle).complete();
                }
                break;
            case R.id.rbShapeType2:
                if (isChecked) {
                    firstHelper.setShapeType(FirstHelper.shapeType_oval).complete();
                }
                break;
            case R.id.rbShapeType3:
                if (isChecked) {
                    firstHelper.setShapeType(FirstHelper.shapeType_line).complete();
                }
                break;
            case R.id.rbGradientType1:
                if (isChecked) {
                    firstHelper.setGradientType(FirstHelper.gradientType_linear).complete();
                }
                break;
            case R.id.rbGradientType2:
                if (isChecked) {
                    firstHelper.setGradientType(FirstHelper.gradientType_radial).complete();
                }
                break;
            case R.id.rbGradientType3:
                if (isChecked) {
                    firstHelper.setGradientType(FirstHelper.gradientType_sweep).complete();
                }
                break;
            case R.id.rbGradientTypeNone:
                if (isChecked) {
                    firstHelper.setGradientType(FirstHelper.gradientType_none).complete();
                }
                break;
            case R.id.rbAngle0:
                if (isChecked) {
                    selectButton.setChecked(false);
                    firstHelper.setGradientAngle(FirstHelper.angle_0).complete();
                    selectButton = rbAngle0;
                }
                break;
            case R.id.rbAngle45:
                if (isChecked) {
                    selectButton.setChecked(false);
                    firstHelper.setGradientAngle(FirstHelper.angle_45).complete();
                    selectButton = rbAngle45;
                }
                break;
            case R.id.rbAngle90:
                if (isChecked) {
                    selectButton.setChecked(false);
                    firstHelper.setGradientAngle(FirstHelper.angle_90).complete();
                    selectButton = rbAngle90;
                }
                break;
            case R.id.rbAngle135:
                if (isChecked) {
                    selectButton.setChecked(false);
                    firstHelper.setGradientAngle(FirstHelper.angle_135).complete();
                    selectButton = rbAngle135;
                }
                break;
            case R.id.rbAngle180:
                if (isChecked) {
                    selectButton.setChecked(false);
                    firstHelper.setGradientAngle(FirstHelper.angle_180).complete();
                    selectButton = rbAngle180;
                }
                break;
            case R.id.rbAngle225:
                if (isChecked) {
                    selectButton.setChecked(false);
                    firstHelper.setGradientAngle(FirstHelper.angle_225).complete();
                    selectButton = rbAngle225;
                }
                break;
            case R.id.rbAngle270:
                if (isChecked) {
                    selectButton.setChecked(false);
                    firstHelper.setGradientAngle(FirstHelper.angle_270).complete();
                    selectButton = rbAngle270;
                }
                break;
            case R.id.rbAngle315:
                if (isChecked) {
                    selectButton.setChecked(false);
                    firstHelper.setGradientAngle(FirstHelper.angle_315).complete();
                    selectButton = rbAngle315;
                }
                break;
            case R.id.cbClipSwitch:
                firstHelper.setClipSwitch(isChecked).completeClip();
                if (isChecked == false) {
                    firstHelper.resetClip();
                }
                break;
            case R.id.cbClipBg:
                firstHelper.setClipBg(isChecked).completeClip();
                break;
            case R.id.cbClipIsCircle:
                firstHelper.setClipIsCircle(isChecked).completeClip();
                break;
            case R.id.cbClipIgnorePadding:
                firstHelper.setClipIgnorePadding(isChecked).completeClip();
                break;
            case R.id.cbClipIsAreaClick:
                firstHelper.setClipIsAreaClick(isChecked).completeClip();
                break;
        }
    }

    @Override
    public void onClick(View v) {
        showSelectColorDialog(v.getId());
    }

    public int selectColor = Color.TRANSPARENT;

    private void showSelectColorDialog(final int viewId) {
        final Dialog dialog = new Dialog(getActivity());
        View view = View.inflate(getActivity(), R.layout.dialog_select_color, null);
        ColorPickerView colorPickerView = view.findViewById(R.id.colorPickerView);
        colorPickerView.setColorListener(new ColorListener() {
            @Override
            public void onColorSelected(int color, boolean fromUser) {
                selectColor = color;
            }
        });
        BrightnessSlideBar brightnessSlide = view.findViewById(R.id.brightnessSlide);
        AlphaSlideBar alphaSlideBar = view.findViewById(R.id.alphaSlideBar);
        Button btCancel = view.findViewById(R.id.btCancel);
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        Button btDefault = view.findViewById(R.id.btDefault);
        btDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectColor = Color.TRANSPARENT;
                setColor(viewId);
                dialog.dismiss();
            }
        });
        Button btSure = view.findViewById(R.id.btSure);
        btSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setColor(viewId);
                dialog.dismiss();
            }
        });

        colorPickerView.attachAlphaSlider(alphaSlideBar);
        colorPickerView.attachBrightnessSlider(brightnessSlide);
        dialog.setContentView(view);
        dialog.show();
    }

    public void setColor(final int viewId) {
        switch (viewId) {
            case R.id.tvPressColor:
                tvPressColor.setBackgroundColor(selectColor);
                firstHelper.setPressColor(selectColor).complete();
                break;
            case R.id.tvSolidColor:
                tvSolidColor.setBackgroundColor(selectColor);
                firstHelper.setSolidColor(selectColor).complete();
                break;
            case R.id.tvBorderColor:
                tvBorderColor.setBackgroundColor(selectColor);
                firstHelper.setBorderColor(selectColor).complete();
                break;
            case R.id.tvStartColor:
                tvStartColor.setBackgroundColor(selectColor);
                firstHelper.setGradientStartColor(selectColor).complete();
                break;
            case R.id.tvCenterColor:
                tvCenterColor.setBackgroundColor(selectColor);
                firstHelper.setGradientCenterColor(selectColor).complete();
                break;
            case R.id.tvEndColor:
                tvEndColor.setBackgroundColor(selectColor);
                firstHelper.setGradientEndColor(selectColor).complete();
                break;
            case R.id.tvClipDashBgColor:
                tvClipDashBgColor.setBackgroundColor(selectColor);
                firstHelper.setClipBorderDashBgColor(selectColor).completeClip();
                break;
            case R.id.tvClipBorderColor:
                tvClipBorderColor.setBackgroundColor(selectColor);
                firstHelper.setClipBorderColor(selectColor).completeClip();
                break;
        }
    }
}
