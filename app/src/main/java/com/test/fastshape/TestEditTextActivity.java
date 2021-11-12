package com.test.fastshape;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatSeekBar;
import android.text.InputType;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import com.github.fastshape.MyEditText;

public class TestEditTextActivity extends AppCompatActivity {

    private MyEditText et;
    private RadioGroup rg;
    private View btChangeDeleteDrawable;
    private View btLookDeleteDrawable;
    private AppCompatCheckBox cbShowEye;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_edit_text);
        et = findViewById(R.id.et);
        cbShowEye = findViewById(R.id.cbShowEye);
        cbShowEye.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                et.setIconLookAlwaysShow(isChecked);
            }
        });
        btChangeDeleteDrawable = findViewById(R.id.btChangeDeleteDrawable);
        btChangeDeleteDrawable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et.setIconDeleteDrawable(R.drawable.delete2);
            }
        });

        btLookDeleteDrawable = findViewById(R.id.btLookDeleteDrawable);
        btLookDeleteDrawable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et.setIconLookDrawable(R.drawable.look_no2,R.drawable.look_yes2);
            }
        });
        rg = findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbHidden:
                        et.setIconShowType(MyEditText.show_type_hidden);
                    break;
                    case R.id.rbDelete:
                        et.setIconShowType(MyEditText.show_type_delete);
                        break;
                    case R.id.rbLook:
                        et.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        et.setIconShowType(MyEditText.show_type_look);
                        break;
                }
            }
        });

        AppCompatSeekBar sb = findViewById(R.id.sb);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                et.setIcon_width(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
