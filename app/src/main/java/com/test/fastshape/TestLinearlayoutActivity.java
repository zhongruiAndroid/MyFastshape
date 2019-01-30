package com.test.fastshape;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.fastshape.MyEditText;
import com.github.fastshape.MyLinearLayout;
import com.github.fastshape.MyTextView;
import com.github.fastshape.viewhelper.FirstHelper;
import com.github.fastshape.viewhelper.SecondHelper;

public class TestLinearlayoutActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_linearlayout);

        MyTextView textView=new MyTextView(this);
        SecondHelper viewHelper = textView.getViewHelper();
        viewHelper.clearShapeAttr();//清除所有属性,按照实际情况使用
        viewHelper.setSolidColor(ContextCompat.getColor(this,R.color.white));
        viewHelper.setRadius(20);//px
        viewHelper.setShapeType(SecondHelper.shapeType_rectangle);
        viewHelper.complete();//或者textView.complete();

        MyEditText editText=new MyEditText(this);
        //设置清除icon
//        editText.setClearIconDrawable(R.drawable.yourResId);
        //只设置icon高度(此时宽度自适应)
        editText.setClearIcon_height(30);//px
        //是否隐藏icon
        editText.setHiddenClearIcon(true);
        //给icon设置新的点击事件，false:没有消费事件,继续清除text,true:消费事件,不清除text
        editText.setOnRightListener(new MyEditText.OnRightListener() {
            @Override
            public boolean clickRight() {
                return false;
            }
        });
    }

}
