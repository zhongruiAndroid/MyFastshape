package com.test.fastshape.test;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

import com.github.fastshape.MyLinearLayout;
import com.github.fastshape.newbean.FirstHelper;
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
    private int type;

    LinearLayout ll_content;
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

    private MyLinearLayout ll;

    float radiusScale = 1.5f;

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

        initData(view);
    }

    private void initData(View view) {
        View itemView = null;
        if (type == type_linearlayout) {
            itemView = LayoutInflater.from(getActivity()).inflate(R.layout.activity_linearlayout_item, null);
            ll = itemView.findViewById(R.id.ll);

            FirstHelper viewHelper = ll.getViewHelper();
            float topLeftRadius = viewHelper.getTopLeftRadius();
            float topRightRadius = viewHelper.getTopRightRadius();
            float bottomLeftRadius = viewHelper.getBottomLeftRadius();
            float bottomRightRadius = viewHelper.getBottomRightRadius();
            float borderWidth = viewHelper.getBorderWidth();
            float borderDashWidth = viewHelper.getBorderDashWidth();
            float borderDashGap = viewHelper.getBorderDashGap();
            boolean left_line = viewHelper.isLeft_line();
            boolean top_line = viewHelper.isTop_line();
            boolean right_line = viewHelper.isRight_line();
            boolean bottom_line = viewHelper.isBottom_line();
            int shapeType = viewHelper.getShapeType();
            int gradientType = viewHelper.getGradientType();
            int pressColor = viewHelper.getPressColor();
            int solidColor = viewHelper.getSolidColor();
            int borderColor = viewHelper.getBorderColor();
            int gradientStartColor = viewHelper.getGradientStartColor();
            int gradientCenterColor = viewHelper.getGradientCenterColor();
            int gradientEndColor = viewHelper.getGradientEndColor();
            int gradientAngle = viewHelper.getGradientAngle();
            float gradientCenterX = viewHelper.getGradientCenterX();
            float gradientCenterY = viewHelper.getGradientCenterY();
            float gradientRadius = viewHelper.getGradientRadius();

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
            }else if(gradientType == FirstHelper.gradientType_none){
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

        }
        if (itemView != null) {
            ll_content = view.findViewById(R.id.ll_content);
            ll_content.addView(itemView, 0);
        }
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.sbRadiusTopLeft:
                if (type == type_linearlayout) {
                    ll.getViewHelper().setTopLeftRadius(progress * radiusScale).complete();
                } else {

                }
                break;
            case R.id.sbRadiusTopRight:
                if (type == type_linearlayout) {
                    ll.getViewHelper().setTopRightRadius(progress * radiusScale).complete();
                } else {

                }
                break;
            case R.id.sbRadiusBottomLeft:
                if (type == type_linearlayout) {
                    ll.getViewHelper().setBottomLeftRadius(progress * radiusScale).complete();
                } else {

                }
                break;
            case R.id.sbRadiusBottomRight:
                if (type == type_linearlayout) {
                    ll.getViewHelper().setBottomRightRadius(progress * radiusScale).complete();
                } else {

                }
                break;
            case R.id.sbBorderWidth:
                if (type == type_linearlayout) {
                    ll.getViewHelper().setBorderWidth(progress).complete();
                } else {

                }
                break;
            case R.id.sbBorderDashWidth:
                if (type == type_linearlayout) {
                    ll.getViewHelper().setBorderDashWidth(progress).complete();
                } else {

                }
                break;
            case R.id.sbBorderDashGap:
                if (type == type_linearlayout) {
                    ll.getViewHelper().setBorderDashGap(progress).complete();
                } else {

                }
                break;
            case R.id.sbGradientRadius:
                if (type == type_linearlayout) {
                    ll.getViewHelper().setGradientRadius(progress * 2).complete();
                } else {

                }
                break;
            case R.id.sbGradientCenterX:
                if (type == type_linearlayout) {
                    float x = 1f * progress / 100;
                    ll.getViewHelper().setGradientCenterX(x).complete();
                } else {

                }
                break;
            case R.id.sbGradientCenterY:
                if (type == type_linearlayout) {
                    float y = 1f * progress / 100;
                    ll.getViewHelper().setGradientCenterY(y).complete();
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
                ll.getViewHelper().setLeft_line(isChecked).complete();
                break;
            case R.id.cbTopLine:
                ll.getViewHelper().setTop_line(isChecked).complete();
                break;
            case R.id.cbRightLine:
                ll.getViewHelper().setRight_line(isChecked).complete();
                break;
            case R.id.cbBottomLine:
                ll.getViewHelper().setBottom_line(isChecked).complete();
                break;
            case R.id.rbShapeType1:
                if (isChecked) {
                    ll.getViewHelper().setShapeType(FirstHelper.shapeType_rectangle).complete();
                }
                break;
            case R.id.rbShapeType2:
                if (isChecked) {
                    ll.getViewHelper().setShapeType(FirstHelper.shapeType_oval).complete();
                }
                break;
            case R.id.rbShapeType3:
                if (isChecked) {
                    ll.getViewHelper().setShapeType(FirstHelper.shapeType_line).complete();
                }
                break;
            case R.id.rbGradientType1:
                if (isChecked) {
                    ll.getViewHelper().setGradientType(FirstHelper.gradientType_linear).complete();
                }
                break;
            case R.id.rbGradientType2:
                if (isChecked) {
                    ll.getViewHelper().setGradientType(FirstHelper.gradientType_radial).complete();
                }
                break;
            case R.id.rbGradientType3:
                if (isChecked) {
                    ll.getViewHelper().setGradientType(FirstHelper.gradientType_sweep).complete();
                }
                break;
            case R.id.rbGradientTypeNone:
                if (isChecked) {
                    ll.getViewHelper().setGradientType(FirstHelper.gradientType_none).complete();
                }
                break;
            case R.id.rbAngle0:
                if (isChecked) {
                    selectButton.setChecked(false);
                    ll.getViewHelper().setGradientAngle(FirstHelper.angle_0).complete();
                    selectButton = rbAngle0;
                }
                break;
            case R.id.rbAngle45:
                if (isChecked) {
                    selectButton.setChecked(false);
                    ll.getViewHelper().setGradientAngle(FirstHelper.angle_45).complete();
                    selectButton = rbAngle45;
                }
                break;
            case R.id.rbAngle90:
                if (isChecked) {
                    selectButton.setChecked(false);
                    ll.getViewHelper().setGradientAngle(FirstHelper.angle_90).complete();
                    selectButton = rbAngle90;
                }
                break;
            case R.id.rbAngle135:
                if (isChecked) {
                    selectButton.setChecked(false);
                    ll.getViewHelper().setGradientAngle(FirstHelper.angle_135).complete();
                    selectButton = rbAngle135;
                }
                break;
            case R.id.rbAngle180:
                if (isChecked) {
                    selectButton.setChecked(false);
                    ll.getViewHelper().setGradientAngle(FirstHelper.angle_180).complete();
                    selectButton = rbAngle180;
                }
                break;
            case R.id.rbAngle225:
                if (isChecked) {
                    selectButton.setChecked(false);
                    ll.getViewHelper().setGradientAngle(FirstHelper.angle_225).complete();
                    selectButton = rbAngle225;
                }
                break;
            case R.id.rbAngle270:
                if (isChecked) {
                    selectButton.setChecked(false);
                    ll.getViewHelper().setGradientAngle(FirstHelper.angle_270).complete();
                    selectButton = rbAngle270;
                }
                break;
            case R.id.rbAngle315:
                if (isChecked) {
                    selectButton.setChecked(false);
                    ll.getViewHelper().setGradientAngle(FirstHelper.angle_315).complete();
                    selectButton = rbAngle315;
                }
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
                ll.getViewHelper().setPressColor(selectColor).complete();
                break;
            case R.id.tvSolidColor:
                tvSolidColor.setBackgroundColor(selectColor);
                ll.getViewHelper().setSolidColor(selectColor).complete();
                break;
            case R.id.tvBorderColor:
                tvBorderColor.setBackgroundColor(selectColor);
                ll.getViewHelper().setBorderColor(selectColor).complete();
                break;
            case R.id.tvStartColor:
                tvStartColor.setBackgroundColor(selectColor);
                ll.getViewHelper().setGradientStartColor(selectColor).complete();
                break;
            case R.id.tvCenterColor:
                tvCenterColor.setBackgroundColor(selectColor);
                ll.getViewHelper().setGradientCenterColor(selectColor).complete();
                break;
            case R.id.tvEndColor:
                tvEndColor.setBackgroundColor(selectColor);
                ll.getViewHelper().setGradientEndColor(selectColor).complete();
                break;
        }
    }
}
