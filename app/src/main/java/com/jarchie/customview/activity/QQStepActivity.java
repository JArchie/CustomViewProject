package com.jarchie.customview.activity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.animation.DecelerateInterpolator;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.jarchie.customview.R;
import com.jarchie.customview.databinding.ActivityQqstepLayoutBinding;
import com.jarchie.customview.viewmodel.CommonFinishViewModel;

/**
 * 作者：created by Jarchie
 * 时间：2020/4/22 17:02:49
 * 邮箱：jarchie520@gmail.com
 * 说明：QQ运动步数进度效果
 */
public class QQStepActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityQqstepLayoutBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_qqstep_layout);
        CommonFinishViewModel viewModel = new CommonFinishViewModel(this);
        binding.setViewModel(viewModel);
        //属性动画 后面会说
        binding.mStepView.setStepMax(18000);
        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0, 7890);
        valueAnimator.setDuration(1200);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentStep = (float) animation.getAnimatedValue();
                binding.mStepView.setCurrentStep((int) currentStep);
            }
        });
        valueAnimator.start();
    }
}
