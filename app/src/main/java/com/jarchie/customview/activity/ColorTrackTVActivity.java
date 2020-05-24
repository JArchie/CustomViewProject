package com.jarchie.customview.activity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.jarchie.customview.R;
import com.jarchie.customview.databinding.ActivityColorTrackTvLayoutBinding;
import com.jarchie.customview.fragment.ItemFragment;
import com.jarchie.customview.view.ColorTrackTextView;
import com.jarchie.customview.viewmodel.CommonFinishViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: 乔布奇
 * 日期: 2020-05-24 20:30
 * 邮箱: jarchie520@gmail.com
 * 描述: 字体变色效果
 */
public class ColorTrackTVActivity extends AppCompatActivity {
    private static final String TAG = ColorTrackTVActivity.class.getSimpleName();
    private String[] items = {"关注", "推荐", "两会", "头条", "抗疫", "南京"};
    private List<ColorTrackTextView> mIndicators;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityColorTrackTvLayoutBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_color_track_tv_layout);
        CommonFinishViewModel viewModel = new CommonFinishViewModel(this);
        binding.setViewModel(viewModel);
        mIndicators = new ArrayList<>();
        initIndicators(binding);
        initViewPager(binding);
    }

    //初始化ViewPager
    private void initViewPager(ActivityColorTrackTvLayoutBinding binding) {
        binding.mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return ItemFragment.newInstance(items[position]);
            }

            @Override
            public int getCount() {
                return items.length;
            }
        });
        binding.mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //position：代表当前的位置  positionOffset：代表滚动的0-1百分比
                if (position == mIndicators.size() - 1) return;
                ColorTrackTextView left = mIndicators.get(position);
                left.setDirection(ColorTrackTextView.Direction.RIGHT_TO_LEFT);
                left.setCurrentProgress(1 - positionOffset);
                ColorTrackTextView right = mIndicators.get(position + 1);
                right.setDirection(ColorTrackTextView.Direction.LEFT_TO_RIGHT);
                right.setCurrentProgress(positionOffset);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //初始化指示器
    private void initIndicators(ActivityColorTrackTvLayoutBinding binding) {
        for (String item : items) {
            //动态添加颜色跟踪的TextView
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.weight = 1;
            ColorTrackTextView colorTrackTextView = new ColorTrackTextView(this);
            //设置颜色
            colorTrackTextView.setChangeColor(getResources().getColor(R.color.colorAccent));
            colorTrackTextView.setTextSize(30);
            colorTrackTextView.setText(item);
            colorTrackTextView.setLayoutParams(params);
            //把新的加入LinearLayout容器
            binding.mIndicatorView.addView(colorTrackTextView);
            //加入集合
            mIndicators.add(colorTrackTextView);
        }
    }
}
