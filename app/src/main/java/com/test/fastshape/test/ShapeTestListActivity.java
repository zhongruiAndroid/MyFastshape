package com.test.fastshape.test;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.fastshape.R;
import com.test.fastshape.TestEditTextActivity;
import com.test.fastshape.TestLinearlayoutActivity;

import java.util.ArrayList;
import java.util.List;

public class ShapeTestListActivity extends AppCompatActivity {
    RecyclerView rv;
    private ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_shape_list);
        List<Class> actList=new ArrayList<>();
        actList.add(LinearLayoutActivity.class);
        actList.add(FrameLayoutActivity.class);
        actList.add(RelativeLayoutActivity.class);
        actList.add(TextViewActivity.class);
        actList.add(ButtonActivity.class);
        actList.add(EditTextActivity.class);
        actList.add(RadioButtonActivity.class);
        actList.add(CheckBoxActivity.class);
        actList.add(ImageViewActivity.class);
        actList.add(TestEditTextActivity.class);
//        actList.add(TestLinearlayoutActivity.class);
//
        List<String > itemList=new ArrayList<>();
        itemList.add("LinearLayout");
        itemList.add("FrameLayout");
        itemList.add("RelativeLayout");
        itemList.add("TextView");
        itemList.add("Button");
        itemList.add("EditText");
        itemList.add("RadioButton");
        itemList.add("CheckBox");
        itemList.add("ImageView");
        itemList.add("测试EditText");
//        itemList.add("测试Theme");

        adapter = new ListAdapter(this);
        adapter.setActList(actList);
        adapter.setItemList(itemList);

        rv=findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setNestedScrollingEnabled(false);
        rv.setAdapter(adapter);

    }
}
