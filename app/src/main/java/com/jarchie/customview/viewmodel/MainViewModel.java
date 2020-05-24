package com.jarchie.customview.viewmodel;

import com.jarchie.customview.bean.MainBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：created by Jarchie
 * 时间：2020/4/22 10:35:12
 * 邮箱：jarchie520@gmail.com
 * 说明：主页面的数据处理类
 */
public class MainViewModel {

    private DataListener mDataListener;
    private List<MainBean> mList = new ArrayList<>();

    public MainViewModel(DataListener listener){
        this.mDataListener = listener;
        loadDatas();
    }

    //初始化数据
    private void loadDatas() {
        mList.clear();
        MainBean bean1 = new MainBean("自定义TextView");
        MainBean bean2 = new MainBean("仿QQ运动步数进度效果");
        MainBean bean3 = new MainBean("滑动字体变色效果");
        mList.add(bean1);
        mList.add(bean2);
        mList.add(bean3);
        mDataListener.loadMainData(mList);
    }

    //数据返回的回调接口
    public interface DataListener{
        void loadMainData(List<MainBean> list);
    }

}
