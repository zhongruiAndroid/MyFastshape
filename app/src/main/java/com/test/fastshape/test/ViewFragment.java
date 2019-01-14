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
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.fastshape.MyLinearLayout;
import com.test.fastshape.R;

public class ViewFragment extends Fragment implements SeekBar.OnSeekBarChangeListener,CompoundButton.OnCheckedChangeListener {
    public static final int type_linearlayout=1;
    public static final int type_framelayout=2;
    public static final int type_relativelayout=3;
    public static final int type_textview=4;
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
    private MyLinearLayout ll;


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
        View itemView=null;
        if(type==type_linearlayout){
            itemView = LayoutInflater.from(getActivity()).inflate(R.layout.activity_linearlayout_item, null);
            ll = itemView.findViewById(R.id.ll);
        }
        if(itemView!=null){
            ll_content=view.findViewById(R.id.ll_content);
            ll_content.addView(itemView,0);
        }
        sbRadiusTopLeft=view.findViewById(R.id.sbRadiusTopLeft);
        sbRadiusTopLeft.setOnSeekBarChangeListener(this);

        sbRadiusTopRight=view.findViewById(R.id.sbRadiusTopRight);
        sbRadiusTopRight.setOnSeekBarChangeListener(this);

        sbRadiusBottomLeft=view.findViewById(R.id.sbRadiusBottomLeft);
        sbRadiusBottomLeft.setOnSeekBarChangeListener(this);

        sbRadiusBottomRight=view.findViewById(R.id.sbRadiusBottomRight);
        sbRadiusBottomRight.setOnSeekBarChangeListener(this);

        sbAllRadius=view.findViewById(R.id.sbAllRadius);
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

        sbBorderWidth=view.findViewById(R.id.sbBorderWidth);
        sbBorderWidth.setOnSeekBarChangeListener(this);

        sbBorderDashWidth=view.findViewById(R.id.sbBorderDashWidth);
        sbBorderDashWidth.setOnSeekBarChangeListener(this);

        sbBorderDashGap=view.findViewById(R.id.sbBorderDashGap);
        sbBorderDashGap.setOnSeekBarChangeListener(this);

        cbLeftLine=view.findViewById(R.id.cbLeftLine);
        cbLeftLine.setOnCheckedChangeListener(this);

        cbTopLine=view.findViewById(R.id.cbTopLine);
        cbTopLine.setOnCheckedChangeListener(this);

        cbRightLine=view.findViewById(R.id.cbRightLine);
        cbRightLine.setOnCheckedChangeListener(this);

        cbBottomLine=view.findViewById(R.id.cbBottomLine);
        cbBottomLine.setOnCheckedChangeListener(this);

        cbAllLine=view.findViewById(R.id.cbAllLine);
        cbAllLine.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ll.getViewHelper().complete();
                cbLeftLine.setChecked(isChecked);
                cbTopLine.setChecked(isChecked);
                cbRightLine.setChecked(isChecked);
                cbBottomLine.setChecked(isChecked);
            }
        });

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
    float radiusScale=1.5f;
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()){
            case R.id.sbRadiusTopLeft:
                if(type==type_linearlayout){
                    ll.getViewHelper().setTopLeftRadius(progress*radiusScale).complete();
                }else{

                }
            break;
            case R.id.sbRadiusTopRight:
                if(type==type_linearlayout){
                    ll.getViewHelper().setTopRightRadius(progress*radiusScale).complete();
                }else{

                }
                break;
            case R.id.sbRadiusBottomLeft:
                if(type==type_linearlayout){
                    ll.getViewHelper().setBottomLeftRadius(progress*radiusScale).complete();
                }else{

                }
                break;
            case R.id.sbRadiusBottomRight:
                if(type==type_linearlayout){
                    ll.getViewHelper().setBottomRightRadius(progress*radiusScale).complete();
                }else{

                }
                break;
            case R.id.sbBorderWidth:
                if(type==type_linearlayout){
                    ll.getViewHelper().setBorderWidth(progress).complete();
                }else{

                }
                break;
            case R.id.sbBorderDashWidth:
                if(type==type_linearlayout){
                    ll.getViewHelper().setBorderDashWidth(progress).complete();
                }else{

                }
                break;
            case R.id.sbBorderDashGap:
                if(type==type_linearlayout){
                    ll.getViewHelper().setBorderDashGap(progress).complete();
                }else{

                }
                break;
        }
    }

    private void changLL(float progress) {
        ll.getViewHelper().setTopLeftRadius(progress).complete();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
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
        }
    }
}
