package com.test.fastshape;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.github.fastshape.MyLinearLayout;

public class TestLinearlayoutActivity extends AppCompatActivity {

    private MyLinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_linearlayout);

        ll = findViewById(R.id.ll);
    }

    public void viewClick(View view) {
        ll.clearAttr().setRadius(50).setBottomLeftRadius(70).setSolidColor(Color.BLUE).complete();
    }
}
