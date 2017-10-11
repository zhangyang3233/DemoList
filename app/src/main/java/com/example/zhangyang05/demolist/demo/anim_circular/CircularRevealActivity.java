package com.example.zhangyang05.demolist.demo.anim_circular;

import android.animation.Animator;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;

import com.example.zhangyang05.demolist.R;
import com.example.zhangyang05.demolist.base.BaseActivityCompat;

/**
 * 揭示动画
 */
public class CircularRevealActivity extends BaseActivityCompat implements View.OnClickListener{
    static boolean isFirst = false;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circular_reveal);
        setupView();
    }

    private void setupView() {
        button = (Button) findViewById(R.id.btn);
        button.setOnClickListener(this);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Animator createAnimation(View v, Boolean isFirst) {

        Animator animator;

        if (isFirst) {
            animator = ViewAnimationUtils.createCircularReveal(
                    v,// 操作的视图
                    0,// 动画开始的中心点X
                    0,// 动画开始的中心点Y
                    (float) Math.sqrt(v.getWidth()*v.getWidth() + v.getHeight()*v.getHeight()),// 动画开始半径
                    0);// 动画结束半径
        } else {
            animator = ViewAnimationUtils.createCircularReveal(
                    v,// 操作的视图
                    0,// 动画开始的中心点X
                    0,// 动画开始的中心点Y
                    0,// 动画开始半径
                    (float) Math.sqrt(v.getWidth()*v.getWidth() + v.getHeight()*v.getHeight()));// 动画结束半径
        }

        animator.setInterpolator(new DecelerateInterpolator());
        animator.setDuration(500);
        return animator;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        createAnimation(v, isFirst).start();
        isFirst = !isFirst;
    }
}