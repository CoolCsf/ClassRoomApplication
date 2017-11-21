package com.example.administrator.classromapplication.viewmodel;

/**
 * Created by Administrator on 2017/11/19.
 */

public class ApplicationDetailHeadViewModel {
    private String applicationTime;
    private String applicationUser;
    private String useReason;
    private String useTime;

    public String getApplicationTime() {
        return applicationTime;
    }

    public void setApplicationTime(String applicationTime) {
        this.applicationTime = "申请时间 :" + applicationTime;
    }

    public String getApplicationUser() {
        return applicationUser;
    }

    public void setApplicationUser(String applicationUser) {
        this.applicationUser = "申请人 :" + applicationUser;
    }

    public String getUseReason() {
        return useReason;
    }

    public void setUseReason(String useReason) {
        this.useReason = "使用原因  :" + useReason;
    }

    public String getUseTime() {
        return useTime;
    }

    public void setUseTime(String useTime) {
        this.useTime = "使用时间  :" + useTime;
    }
}
