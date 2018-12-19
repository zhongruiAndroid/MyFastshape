package com.test.fastshape;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.github.fastshape.MyLinearLayout;
import com.github.fastshape.bean.BaseHelper;

public class TestLinearlayoutActivity extends AppCompatActivity {

    private MyLinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_linearlayout);

        ll = findViewById(R.id.ll);
    }

    public void viewClick(View view) {
        ll.clearAttr()
                .setShapeType(BaseHelper.shapeType_rectangle)
                .setRadius(50)
                .setSolidColor(Color.parseColor("#87ceeb"))
                .setAll_line(true)
                .setBorderWidth(10)
                .setBottomLeftRadius(20)
                .setBottomRightRadius(30)
                .setTopLeftRadius(40)
                .setTopRightRadius(50)
                .setShapeType(BaseHelper.shapeType_rectangle)
                .setBorderDashWidth(10)
                .setBorderDashGap(20)
                .setGradientAngle(BaseHelper.angle_0)
                .setGradientType(BaseHelper.gradientType_radial)
                .setGradientStartColor(Color.BLACK)
                .setGradientEndColor(Color.RED)
                .setGradientCenterColor(Color.GREEN)
                .setGradientCenterX(0.5f)
                .setGradientCenterY(0.5f)
                .setGradientRadius(140)
                .setPressColor(Color.GRAY)
                .setDrawable_press(getDrawable(R.drawable.check_select))
                .setDrawable_normal(getDrawable(R.drawable.check_normal))
                .complete();
    }
}
