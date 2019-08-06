package com.lxr.property_animation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> mDatas;

    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatas = new ArrayList<>();

        iv = findViewById(R.id.iv);

        // 设置rv属性
        RecyclerView rv = findViewById(R.id.rv);
        rv.setAdapter(new Adapter(mDatas));
        rv.setLayoutManager(new LinearLayoutManager(this));

        // 设置数据
        for (int i = 1; i < 61; i++) {
            mDatas.add("这是第" + i + "条数据数据数据数据数据数据数据数据数据数据数据数据数据数据。");
        }

        // 给rv加上滚动事件
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_DRAGGING:// 滚动中
                    case RecyclerView.SCROLL_STATE_SETTLING:// 飞翔中
                        iv.setVisibility(View.GONE);
                        break;
                    case RecyclerView.SCROLL_STATE_IDLE:// 停止滚动
                        iv.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
    }
}
