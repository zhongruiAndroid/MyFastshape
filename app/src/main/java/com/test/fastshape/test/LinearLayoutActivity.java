package com.test.fastshape.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.test.fastshape.R;

public class LinearLayoutActivity extends AppCompatActivity {
    FrameLayout fl_fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linearlayout);

        fl_fragment=findViewById(R.id.fl_fragment);

        ViewFragment fragment = ViewFragment.newInstance(ViewFragment.type_linearlayout);
        getSupportFragmentManager().beginTransaction().add(R.id.fl_fragment,fragment).commitAllowingStateLoss();
    }
}
