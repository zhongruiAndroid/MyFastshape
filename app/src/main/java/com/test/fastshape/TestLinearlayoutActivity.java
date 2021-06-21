package com.test.fastshape;

import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.github.fastshape.MyCheckBox;
import com.github.fastshape.MyRadioButton;

public class TestLinearlayoutActivity extends AppCompatActivity {


    private MyCheckBox cb;
    private MyRadioButton rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_linearlayout);

        cb = findViewById(R.id.cb);
        rb = findViewById(R.id.rb);
        cb.setChecked(false);
        rb.setChecked(false);

        cb.postDelayed(new Runnable() {
            @Override
            public void run() {
                cb.getViewHelper()
                        .setChecked_drawable_left(ContextCompat.getDrawable(TestLinearlayoutActivity.this,R.drawable.select))
                        .setNormal_drawable_left(ContextCompat.getDrawable(TestLinearlayoutActivity.this,R.drawable.normal))
                        .setNormal_drawable_left_color(ContextCompat.getColor(TestLinearlayoutActivity.this,R.color.top_color))
                        .setChecked_drawable_left_color(ContextCompat.getColor(TestLinearlayoutActivity.this,R.color.red_point))

                        .setChecked_drawable_top(ContextCompat.getDrawable(TestLinearlayoutActivity.this,R.drawable.select))
                        .setNormal_drawable_top(ContextCompat.getDrawable(TestLinearlayoutActivity.this,R.drawable.normal))
                        .setNormal_drawable_top_color(ContextCompat.getColor(TestLinearlayoutActivity.this,R.color.red_point))
                        .setChecked_drawable_top_color(ContextCompat.getColor(TestLinearlayoutActivity.this,R.color.top_color))

                        .setChecked_drawable_right(ContextCompat.getDrawable(TestLinearlayoutActivity.this,R.drawable.select))
                        .setNormal_drawable_right(ContextCompat.getDrawable(TestLinearlayoutActivity.this,R.drawable.normal))
                        .setNormal_drawable_right_color(ContextCompat.getColor(TestLinearlayoutActivity.this,R.color.deeppink))
                        .setChecked_drawable_right_color(ContextCompat.getColor(TestLinearlayoutActivity.this,R.color.darkorange))

                        .setChecked_drawable_bottom(ContextCompat.getDrawable(TestLinearlayoutActivity.this,R.drawable.select))
                        .setNormal_drawable_bottom(ContextCompat.getDrawable(TestLinearlayoutActivity.this,R.drawable.normal))
                        .setNormal_drawable_bottom_color(ContextCompat.getColor(TestLinearlayoutActivity.this,R.color.greenyellow))
                        .setChecked_drawable_bottom_color(ContextCompat.getColor(TestLinearlayoutActivity.this,R.color.button_ensure))
                        .complete();

                        rb.getViewHelper()
                        .setChecked_drawable_left(ContextCompat.getDrawable(TestLinearlayoutActivity.this,R.drawable.select))
                        .setNormal_drawable_left(ContextCompat.getDrawable(TestLinearlayoutActivity.this,R.drawable.normal))
                        .setChecked_drawable_left_color(ContextCompat.getColor(TestLinearlayoutActivity.this,R.color.red_point))
                        .setNormal_drawable_left_color(ContextCompat.getColor(TestLinearlayoutActivity.this,R.color.top_color))

                        .setChecked_drawable_top(ContextCompat.getDrawable(TestLinearlayoutActivity.this,R.drawable.select))
                        .setNormal_drawable_top(ContextCompat.getDrawable(TestLinearlayoutActivity.this,R.drawable.normal))
                        .setChecked_drawable_top_color(ContextCompat.getColor(TestLinearlayoutActivity.this,R.color.top_color))
                        .setNormal_drawable_top_color(ContextCompat.getColor(TestLinearlayoutActivity.this,R.color.black))

                        .setChecked_drawable_right(ContextCompat.getDrawable(TestLinearlayoutActivity.this,R.drawable.select))
                        .setNormal_drawable_right(ContextCompat.getDrawable(TestLinearlayoutActivity.this,R.drawable.normal))
                        .setChecked_drawable_right_color(ContextCompat.getColor(TestLinearlayoutActivity.this,R.color.darkorange))
                        .setNormal_drawable_right_color(ContextCompat.getColor(TestLinearlayoutActivity.this,R.color.deeppink))

                        .setChecked_drawable_bottom(ContextCompat.getDrawable(TestLinearlayoutActivity.this,R.drawable.select))
                        .setNormal_drawable_bottom(ContextCompat.getDrawable(TestLinearlayoutActivity.this,R.drawable.normal))
                        .setChecked_drawable_bottom_color(ContextCompat.getColor(TestLinearlayoutActivity.this,R.color.blueviolet))
                        .setNormal_drawable_bottom_color(ContextCompat.getColor(TestLinearlayoutActivity.this,R.color.greenyellow))
                        .complete();
            }
        },1000);
        cb.postDelayed(new Runnable() {
            @Override
            public void run() {
                cb.setChecked(true);
                rb.setChecked(true);
            }
        },2000);
        cb.postDelayed(new Runnable() {
            @Override
            public void run() {
                cb.setChecked(false);
                rb.setChecked(false);
            }
        },3000);

    }

}
