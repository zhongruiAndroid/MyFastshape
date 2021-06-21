package com.test.fastshape.test;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.test.fastshape.R;

public class ButtonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewgroup);

        ViewFragment fragment = ViewFragment.newInstance(ViewFragment.type_button);
        getSupportFragmentManager().beginTransaction().add(R.id.fl_fragment,fragment).commitAllowingStateLoss();
    }
}
