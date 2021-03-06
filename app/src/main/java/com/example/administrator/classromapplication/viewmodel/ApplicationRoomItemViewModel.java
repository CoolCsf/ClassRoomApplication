package com.example.administrator.classromapplication.viewmodel;

import com.example.administrator.classromapplication.model.ApplicationStatueEmun;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/11/19.
 */

public class ApplicationRoomItemViewModel implements Serializable {
    private String applicationTime;
    private String applicationUser;
    private String applicationStatus;
    private String useReason;
    private String userId;
    private String applicationId;
    private ApplicationViewModel viewModel;

    public ApplicationViewModel getViewModel() {
        return viewModel;
    }

    public void setViewModel(ApplicationViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getApplicationTime() {
        return "申请时间  " + applicationTime;
    }

    public void setApplicationTime(String applicationTime) {
        this.applicationTime = applicationTime;
    }

    public String getApplicationUser() {
        return "申请人 " + applicationUser;
    }

    public void setApplicationUser(String applicationUser) {
        this.applicationUser = applicationUser;
    }

    public String getApplicationStatus() {
        return "状态 :" + applicationStatus;
    }

    public void setApplicationStatus(int status) {
        this.applicationStatus = ApplicationStatueEmun.getState(status);
    }

    public String getUseReason() {
        return "使用原因  :" + useReason;
    }

    public void setUseReason(String useReason) {
        this.useReason = useReason;
    }
}
