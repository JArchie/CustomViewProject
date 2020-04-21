package com.jarchie.customview.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.animation.DecelerateInterpolator;

import com.jarchie.customview.R;
import com.jarchie.customview.view.QQStepView;

/**
 * 作者: 乔布奇
 * 日期: 2020-04-19 13:11
 * 邮箱: jarchie520@gmail.com
 * 描述: 主Activity
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final QQStepView stepView = findViewById(R.id.stepView);
        //属性动画 后面会说
        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0, 12000);
        valueAnimator.setDuration(1200);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentStep = (float) animation.getAnimatedValue();
                stepView.setCurrentStep((int) currentStep);
            }
        });
        valueAnimator.start();
    }
}
