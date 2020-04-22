package com.jarchie.customview.bean;

/**
 * 作者：created by Jarchie
 * 时间：2020/4/22 10:39:05
 * 邮箱：jarchie520@gmail.com
 * 说明：主页列表实体
 */
public class MainBean {
    private String name;
    private int position;

    public MainBean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
