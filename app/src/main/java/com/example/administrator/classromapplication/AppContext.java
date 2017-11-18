package com.example.administrator.classromapplication;

import android.app.Application;

import com.tool.util.ToastHelp;

import cn.bmob.v3.Bmob;

/**
 * Created by Administrator on 2017/11/10.
 */

public class AppContext extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ToastHelp.init(this);
        //第一：默认初始化
        Bmob.initialize(this, "1cbadab6af8477e06659d7db77716363");
    }
}
