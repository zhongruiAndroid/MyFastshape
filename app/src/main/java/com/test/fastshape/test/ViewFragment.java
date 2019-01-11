package com.test.fastshape.test;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSeekBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.test.fastshape.R;

public class ViewFragment extends Fragment {
    public static final int type_linearlayout=1;
    public static final int type_framelayout=2;
    public static final int type_relativelayout=3;
    public static final int type_textview=4;
    private int type;

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
    CheckBox cbAllLine;
    RadioGroup rgShapeType;
    RadioButton rbShapeType1;
    RadioButton rbShapeType2;
    RadioButton rbShapeType3;
    RadioGroup rgGradientType;
    RadioButton rbGradientType1;
    RadioButton rbGradientType2;
    RadioButton rbGradientType3;
    TextView tvPressColor;
    TextView tvSolidColor;
    TextView tvBorderColor;
    TextView tvStartColor;
    TextView tvCenterColor;
    TextView tvEndColor;
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




    public ViewFragment() {
    }
    public static ViewFragment newInstance(int type) {
        ViewFragment fragment = new ViewFragment();
        Bundle args = new Bundle();
        args.putInt("type",type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            type=getArguments().getInt("type");
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
        sbRadiusTopLeft=view.findViewById(R.id.sbRadiusTopLeft);
        sbRadiusTopRight=view.findViewById(R.id.sbRadiusTopRight);
        sbRadiusBottomLeft=view.findViewById(R.id.sbRadiusBottomLeft);
        sbRadiusBottomRight=view.findViewById(R.id.sbRadiusBottomRight);
        sbAllRadius=view.findViewById(R.id.sbAllRadius);
        sbBorderWidth=view.findViewById(R.id.sbBorderWidth);
        sbBorderDashWidth=view.findViewById(R.id.sbBorderDashWidth);
        sbBorderDashGap=view.findViewById(R.id.sbBorderDashGap);
        cbLeftLine=view.findViewById(R.id.cbLeftLine);
        cbTopLine=view.findViewById(R.id.cbTopLine);
        cbRightLine=view.findViewById(R.id.cbRightLine);
        cbBottomLine=view.findViewById(R.id.cbBottomLine);
        cbAllLine=view.findViewById(R.id.cbAllLine);
        rgShapeType=view.findViewById(R.id.rgShapeType);
        rbShapeType1=view.findViewById(R.id.rbShapeType1);
        rbShapeType2=view.findViewById(R.id.rbShapeType2);
        rbShapeType3=view.findViewById(R.id.rbShapeType3);
        rgGradientType=view.findViewById(R.id.rgGradientType);
        rbGradientType1=view.findViewById(R.id.rbGradientType1);
        rbGradientType2=view.findViewById(R.id.rbGradientType2);
        rbGradientType3=view.findViewById(R.id.rbGradientType3);
        tvPressColor=view.findViewById(R.id.tvPressColor);
        tvSolidColor=view.findViewById(R.id.tvSolidColor);
        tvBorderColor=view.findViewById(R.id.tvBorderColor);
        tvStartColor=view.findViewById(R.id.tvStartColor);
        tvCenterColor=view.findViewById(R.id.tvCenterColor);
        tvEndColor=view.findViewById(R.id.tvEndColor);
        rbAngle0=view.findViewById(R.id.rbAngle0);
        rbAngle45=view.findViewById(R.id.rbAngle45);
        rbAngle90=view.findViewById(R.id.rbAngle90);
        rbAngle135=view.findViewById(R.id.rbAngle135);
        rbAngle180=view.findViewById(R.id.rbAngle180);
        rbAngle225=view.findViewById(R.id.rbAngle225);
        rbAngle270=view.findViewById(R.id.rbAngle270);
        rbAngle315=view.findViewById(R.id.rbAngle315);
        sbGradientCenterX=view.findViewById(R.id.sbGradientCenterX);
        sbGradientCenterY=view.findViewById(R.id.sbGradientCenterY);
        sbGradientRadius=view.findViewById(R.id.sbGradientRadius);

    }
}
