package com.jarchie.customview.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.jarchie.customview.R;
import com.jarchie.customview.databinding.ActivityTextviewLayoutBinding;
import com.jarchie.customview.viewmodel.CommonFinishViewModel;

/**
 * 作者：created by Jarchie
 * 时间：2020/4/22 14:54:31
 * 邮箱：jarchie520@gmail.com
 * 说明：展示自定义TextView的效果
 */
public class TextViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityTextviewLayoutBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_textview_layout);
        CommonFinishViewModel viewModel = new CommonFinishViewModel(this);
        binding.setViewModel(viewModel);
    }
}
