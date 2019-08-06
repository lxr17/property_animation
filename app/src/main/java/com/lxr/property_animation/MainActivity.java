package com.lxr.property_animation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatas = new ArrayList<>();

        // 设置rv属性
        RecyclerView rv = findViewById(R.id.rv);
        rv.setAdapter(new Adapter(mDatas));
        rv.setLayoutManager(new LinearLayoutManager(this));

        // 设置数据
        for (int i = 1; i < 61; i++) {
            mDatas.add("这是第" + i + "条数据数据数据数据数据数据数据数据数据数据数据数据数据数据。");
        }
    }
}
