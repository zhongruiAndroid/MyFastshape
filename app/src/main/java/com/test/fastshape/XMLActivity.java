package com.test.fastshape;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Toast;

import com.github.fastshape.BaseViewHelper;
import com.github.fastshape.MyLinearLayout;
import com.github.fastshape.MyTextView;

public class XMLActivity extends AppCompatActivity {


    CheckBox cb_regin_click;
    CheckBox cb_isCircle;
    CheckBox cb_border_color;
    CheckBox cb_dash_bg;

    SeekBar sb_radius;
    SeekBar sb_border_width;
    SeekBar sb_dash_width;
    SeekBar sb_dash_gap;
    SeekBar sb_dash_phase;
    private BaseViewHelper viewHelper;
    private Toast toast;

    private MyLinearLayout ll_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setTheme(R.style.AppTheme_NoActionBar2);
        setContentView(R.layout.xmlactivity_xml);
        MyTextView t=new MyTextView(this);
//        t.getViewHelper().setClipRadius(1);


        ll_view = (MyLinearLayout) findViewById(R.id.ll_view);
        ll_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMsg("click");
            }
        });
//        viewHelper = ll_view.getViewHelper();

        cb_regin_click = (CheckBox) findViewById(R.id.cb_regin_click);
        cb_regin_click.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                viewHelper.setClipIsAreaClick(isChecked).complete();
            }
        });

        cb_isCircle = (CheckBox) findViewById(R.id.cb_isCircle);
        cb_isCircle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                viewHelper.setClipIsCircle(isChecked);
                viewHelper.setRadius(100).complete();
            }
        });

        cb_border_color = (CheckBox) findViewById(R.id.cb_border_color);
        cb_border_color.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    viewHelper.setClipBorderColor(ContextCompat.getColor(XMLActivity.this, R.color.blue)).complete();
                } else {
                    viewHelper.setClipBorderColor(Color.BLACK).complete();
                }
            }
        });

        cb_dash_bg = (CheckBox) findViewById(R.id.cb_dash_bg);
        cb_dash_bg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    viewHelper.setClipBorderDashBgColor(Color.WHITE).complete();
                }else{
                    viewHelper.setClipBorderDashBgColor(Color.TRANSPARENT).complete();
                }
            }
        });


        sb_radius = (SeekBar) findViewById(R.id.sb_radius);
        sb_border_width = (SeekBar) findViewById(R.id.sb_border_width);
        sb_dash_width = (SeekBar) findViewById(R.id.sb_dash_width);
        sb_dash_gap = (SeekBar) findViewById(R.id.sb_dash_gap);
        sb_dash_phase = (SeekBar) findViewById(R.id.sb_dash_phase);

        sb_radius.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                  viewHelper.setClipRadius(progress).complete();
//                viewHelper.setClipTopLeftRadius(progress).complete();
//                viewHelper.setClipTopRightRadius(progress).complete();
//                viewHelper.setClipBottomRightRadius(progress).complete();
//                viewHelper.setClipBottomLeftRadius(progress).complete();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sb_border_width.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                viewHelper.setClipBorderWidth(progress).complete();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        sb_dash_width.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress>=0&progress<sb_dash_width.getMax()){
                    viewHelper.setClipBorderDashWidth(progress==0?1:progress).complete();
                }else{
                    viewHelper.setClipBorderDashWidth(0).complete();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        sb_dash_gap.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                viewHelper.setClipBorderDashGap(progress).complete();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        sb_dash_phase.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                viewHelper.setClipBorderPhase(progress).complete();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void bgclick(View view) {
        showMsg("bgclick");
    }

    public void showMsg(String msg) {
        if (toast == null) {
            toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        }
        toast.show();

    }
}
