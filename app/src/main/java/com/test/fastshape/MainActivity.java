package com.test.fastshape;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;

import com.github.fastshape.MyCheckBox;
import com.test.fastshape.test.ShapeTestListActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        findViewById(R.id.tvLook).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             /*   Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                startActivity(new Intent(MainActivity.this, ShapeTestListActivity.class));
//                startActivity(new Intent(MainActivity.this, TestLinearlayoutActivity.class));


            }
        });
        CheckBox box=null;
        MyCheckBox  test = findViewById(R.id.test);
        test.setTextColor(0);
        test.setBackgroundColor(Color.BLACK);
//        box.setButtonDrawable();
    }
}
