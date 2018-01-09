package com.example.administrator.classromapplication.model;

import cn.bmob.v3.BmobInstallation;

/**
 * Created by Administrator on 2017/11/29.
 */

public class Installation extends BmobInstallation {
    private boolean isRoot;
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isRoot() {
        return isRoot;
    }

    public void setRoot(boolean root) {
        isRoot = root;
    }
}
