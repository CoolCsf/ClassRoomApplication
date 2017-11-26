package com.example.administrator.classromapplication.viewmodel;

import com.tool.util.DataUtils;

/**
 * Created by Administrator on 2017/11/19.
 */

public class ApplicationDetailViewModel {
    private String applicationTime;
    private String applicationUser;
    private String useReason;
    private String useTime;
    private int state;
    private String createTime;//创建时间，即待审核和审核中时间
    private String updateTime;//更新时间，即完成审核时间
    private String finalRoom;
    private String text;//审核备注

    public String getText() {
        return "审核备注 :" + text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFinalRoom() {
        return "审批课室 :" + (DataUtils.checkStrNotNull(finalRoom) ? finalRoom : "无");
    }

    public void setFinalRoom(String finalRoom) {
        this.finalRoom = finalRoom;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getCreateTime() {
        return "时间" + createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return "时间" + updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

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
