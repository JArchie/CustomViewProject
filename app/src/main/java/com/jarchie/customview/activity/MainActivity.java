package com.jarchie.customview.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.jarchie.customview.R;
import com.jarchie.customview.adapter.MainAdapter;
import com.jarchie.customview.bean.MainBean;
import com.jarchie.customview.databinding.ActivityMainBinding;
import com.jarchie.customview.viewmodel.MainViewModel;

import java.util.List;

/**
 * 作者: 乔布奇
 * 日期: 2020-04-19 13:11
 * 邮箱: jarchie520@gmail.com
 * 描述: 主Activity
 */
public class MainActivity extends AppCompatActivity implements MainViewModel.DataListener {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        MainViewModel mainViewModel = new MainViewModel(this);
        binding.setViewModel(mainViewModel);
    }

    @Override
    public void loadMainData(List<MainBean> list) {
        MainAdapter mAdapter = new MainAdapter(this, list);
        binding.mRecycler.setAdapter(mAdapter);
        binding.mRecycler.setLayoutManager(new LinearLayoutManager(this));
    }
}
