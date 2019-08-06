package com.lxr.property_animation;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> mDatas;

    private ImageView iv;

    private ValueAnimator disappearAnimator;// 消失动画
    private ValueAnimator appearAnimator;// 出现动画
    private float originX = 0;// iv的原始x坐标
    private float ivWidth = 0;// iv的宽度
    private float screenWidth;// 屏幕宽度

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatas = new ArrayList<>();

        iv = findViewById(R.id.iv);

        screenWidth = Utils.getScreenWidth(this);

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
                // 获取iv的坐标以及宽高
                if (0 == originX) {
                    originX = iv.getX();
                    ivWidth = iv.getWidth();
                }

                switch (newState) {
                    case RecyclerView.SCROLL_STATE_DRAGGING:// 滚动中
                    case RecyclerView.SCROLL_STATE_SETTLING:// 飞翔中
                        // 消失动画的基本属性（从iv当前的x坐标一直到出了屏幕右侧一半）
                        if (disappearAnimator == null) {
                            disappearAnimator = ValueAnimator.ofFloat(originX, (float) (screenWidth - ivWidth / 2.0));
                            disappearAnimator.setDuration(400);// 动画持续时间
                            disappearAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                @Override
                                public void onAnimationUpdate(ValueAnimator animation) {// Value更新事件
                                    float curValue = (float) animation.getAnimatedValue();
                                    iv.setX(curValue);
                                }
                            });
                        }

                        // 如果此时出现动画已开始，则取消
                        if (appearAnimator != null && appearAnimator.isStarted()) {
                            appearAnimator.cancel();
                        }

                        // 如果消失动画还未开始执行并且iv的位置在原始位置，则执行
                        if (!disappearAnimator.isStarted() && originX == iv.getX()) {
                            disappearAnimator.start();
                        }
                        break;
                    case RecyclerView.SCROLL_STATE_IDLE:// 停止滚动
                        // 出现动画的基本属性（从屏幕右侧一半到原始位置）
                        if (appearAnimator == null) {
                            appearAnimator = ValueAnimator.ofFloat((float) (screenWidth - ivWidth / 2.0), originX);
                            appearAnimator.setDuration(400);// 动画持续时间
                            appearAnimator.setStartDelay(700);// 延迟时间
                            appearAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                @Override
                                public void onAnimationUpdate(ValueAnimator animation) {// Value更新事件
                                    float curValue = (float) animation.getAnimatedValue();
                                    iv.setX(curValue);
                                }
                            });
                        }

                        // 如果出现动画还未开始执行，则执行
                        if (!appearAnimator.isStarted()) {
                            appearAnimator.start();
                        }
                        break;
                }
            }
        });
    }

}
