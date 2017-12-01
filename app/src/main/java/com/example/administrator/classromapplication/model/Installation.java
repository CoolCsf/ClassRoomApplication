package com.example.administrator.classromapplication.model;

import cn.bmob.v3.BmobInstallation;

/**
 * Created by Administrator on 2017/11/29.
 */

public class Installation extends BmobInstallation {
    private boolean isRoot;

    public boolean isRoot() {
        return isRoot;
    }

    public void setRoot(boolean root) {
        isRoot = root;
    }
}
