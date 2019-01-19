package com.test.fastshape.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.test.fastshape.R;

public class LinearLayoutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linearlayout);

        ViewFragment fragment = ViewFragment.newInstance(ViewFragment.type_linearlayout);
        getSupportFragmentManager().beginTransaction().add(R.id.fl_fragment,fragment).commitAllowingStateLoss();
    }
}
