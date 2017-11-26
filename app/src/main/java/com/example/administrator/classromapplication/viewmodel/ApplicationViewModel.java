package com.example.administrator.classromapplication.viewmodel;

import android.databinding.Bindable;
import android.text.TextUtils;

import com.tool.util.ToastHelp;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/11/14.
 */

public class ApplicationViewModel extends BmobObject {
    private String applicaUser;
    private String applicaNum;
    private String applicaOffice;
    private String phone;
    private String useDate;
    private String startTime;
    private String endTime;
    private String useReason;
    private boolean isAgree;
    private boolean hasMultiMedia;
    private String classRoom;
    private String other;
    private int applicationStatus;
    private String userId;
    private String finalRoom;
    private String text;//审核备注

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFinalRoom() {
        return finalRoom;
    }

    public void setFinalRoom(String finalRoom) {
        this.finalRoom = finalRoom;
    }

    public int getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(int applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getApplicaUser() {
        return applicaUser;
    }

    public void setApplicaUser(String applicaUser) {
        this.applicaUser = applicaUser;
    }

    public String getApplicaNum() {
        return applicaNum;
    }

    public void setApplicaNum(String applicaNum) {
        this.applicaNum = applicaNum;
    }

    public String getApplicaOffice() {
        return applicaOffice;
    }

    public void setApplicaOffice(String applicaOffice) {
        this.applicaOffice = applicaOffice;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUseDate() {
        return useDate;
    }

    public void setUseDate(String useDate) {
        this.useDate = useDate;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getUseReason() {
        return useReason;
    }

    public void setUseReason(String useReason) {
        this.useReason = useReason;
    }

    public boolean isAgree() {
        return isAgree;
    }

    public void setAgree(boolean agree) {
        isAgree = agree;
    }

    public boolean isHasMultiMedia() {
        return hasMultiMedia;
    }

    public void setHasMultiMedia(boolean hasMultiMedia) {
        this.hasMultiMedia = hasMultiMedia;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public boolean canSubmit() {
        if (TextUtils.isEmpty(applicaUser)) {
            ToastHelp.showToast("申请人不能为空");
            return false;
        }
        if (TextUtils.isEmpty(applicaOffice)) {
            ToastHelp.showToast("申请单位不能为空");
            return false;
        }
        if (TextUtils.isEmpty(applicaOffice)) {
            ToastHelp.showToast("申请单位不能为空");
            return false;
        }
        if (TextUtils.isEmpty(phone)) {
            ToastHelp.showToast("手机号码不能为空");
            return false;
        }
        if (TextUtils.isEmpty(applicaNum)) {
            ToastHelp.showToast("参加人数不能为空");
            return false;
        }
        if (TextUtils.isEmpty(useDate)) {
            ToastHelp.showToast("使用日期不能为空");
            return false;
        }
        if (TextUtils.isEmpty(startTime)) {
            ToastHelp.showToast("开始时间不能为空");
            return false;
        }
        if (TextUtils.isEmpty(endTime)) {
            ToastHelp.showToast("结束时间不能为空");
            return false;
        }
        if (TextUtils.isEmpty(useReason)) {
            ToastHelp.showToast("使用原因不能为空");
            return false;
        }
        if (!isAgree) {
            ToastHelp.showToast("请先学习相关章程");
            return false;
        }
        return true;
    }
}
