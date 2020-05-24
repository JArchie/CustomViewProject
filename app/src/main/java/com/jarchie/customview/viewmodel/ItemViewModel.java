package com.jarchie.customview.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.jarchie.customview.activity.ColorTrackTVActivity;
import com.jarchie.customview.activity.QQStepActivity;
import com.jarchie.customview.activity.TextViewActivity;
import com.jarchie.customview.bean.MainBean;

/**
 * 作者：created by Jarchie
 * 时间：2020/4/22 10:53:05
 * 邮箱：jarchie520@gmail.com
 * 说明：列表Item的ViewModel
 */
public class ItemViewModel {
    private Context mContext;
    private MainBean mainBean;

    public ItemViewModel(Context context, MainBean bean) {
        this.mContext = context;
        this.mainBean = bean;
    }

    //设置数据
    public void setData(MainBean bean) {
        this.mainBean = bean;
    }

    //获取列表Item显示的文本
    public String getName() {
        return TextUtils.isEmpty(mainBean.getName()) ? "暂无" : mainBean.getName();
    }

    //列表Item的点击事件
    public void onItemClick() {
        switch (mainBean.getPosition()) {
            case 0:
                mContext.startActivity(new Intent(mContext, TextViewActivity.class));
                break;
            case 1:
                mContext.startActivity(new Intent(mContext, QQStepActivity.class));
                break;
            case 2:
                mContext.startActivity(new Intent(mContext, ColorTrackTVActivity.class));
                break;
        }
    }

}
