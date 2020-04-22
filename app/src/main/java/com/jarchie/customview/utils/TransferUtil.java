package com.jarchie.customview.utils;

import android.util.TypedValue;

import com.jarchie.customview.app.BaseApp;

/**
 * 作者: 乔布奇
 * 日期: 2020-04-22 20:57
 * 邮箱: jarchie520@gmail.com
 * 描述: 转换工具类
 */
public class TransferUtil {

    //sp转px
    public static int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, BaseApp.getContext().getResources().getDisplayMetrics());
    }

}
