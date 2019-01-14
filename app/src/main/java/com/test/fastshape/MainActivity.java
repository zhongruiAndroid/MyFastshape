package com.test.fastshape;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.github.fastshape.BaseViewHelper;
import com.github.fastshape.MyLinearLayout;
import com.github.fastshape.MyTextView;
import com.test.fastshape.test.ShapeTestListActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                startActivity(new Intent(MainActivity.this, ShapeTestListActivity.class));
//                startActivity(new Intent(MainActivity.this,TestLinearlayoutActivity.class));
//            }
//        });

            }

            public void codeSet() {
                MyTextView textView = new MyTextView(MainActivity.this);
                BaseViewHelper viewHelper = textView.getViewHelper();
                //viewHelper.clearAttribute();清除所有属性,按照实际情况使用
                viewHelper.setAllLine(true);
                viewHelper.setSolidColor(ContextCompat.getColor(MainActivity.this, R.color.white));
                viewHelper.setRadius(20);
                viewHelper.setShapeType(BaseViewHelper.shapeType_rectangle);
                viewHelper.complete();//或者textView.complete();

                MyLinearLayout linearLayout = new MyLinearLayout(MainActivity.this);
                linearLayout.getViewHelper().clearAttr()
                        .setGradientType(BaseViewHelper.gradientType_linear)
                        .setSolidColor(ContextCompat.getColor(MainActivity.this, R.color.white))
                        .setRadius(20)
                        .complete();
        /*linearLayout.getViewHelper()
                .clearAttribute()
                .setAllLine(true)
                .setGradientType(BaseViewHelper.gradientType_linear)
                .setSolidColor(ContextCompat.getColor(this,R.color.white))
                .setRadius(20)
                .complete();//或者linearLayout.complete();*/

            }
        });
    }
}
