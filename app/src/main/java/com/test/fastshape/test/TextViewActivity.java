package com.test.fastshape.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.test.fastshape.R;

public class TextViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textview);


        ViewFragment fragment = ViewFragment.newInstance(ViewFragment.type_textview);
        getSupportFragmentManager().beginTransaction().add(R.id.fl_fragment,fragment).commitAllowingStateLoss();
    }
}
