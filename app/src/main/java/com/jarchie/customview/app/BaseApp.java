package com.jarchie.customview.app;

import android.app.Application;
import android.content.Context;

/**
 * 作者: 乔布奇
 * 日期: 2020-04-22 20:59
 * 邮箱: jarchie520@gmail.com
 * 描述:
 */
public class BaseApp extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext(){
        return mContext;
    }
}
