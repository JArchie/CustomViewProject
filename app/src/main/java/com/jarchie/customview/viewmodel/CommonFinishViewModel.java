package com.jarchie.customview.viewmodel;

import android.app.Activity;
import android.content.Context;

/**
 * 作者：created by Jarchie
 * 时间：2020/4/22 15:03:05
 * 邮箱：jarchie520@gmail.com
 * 说明：公共的关闭页面Model
 */
public class CommonFinishViewModel {
    private Context mContext;

    public CommonFinishViewModel(Context context) {
        this.mContext = context;
    }

    public void finishActivity() {
        ((Activity) mContext).finish();
    }
}
