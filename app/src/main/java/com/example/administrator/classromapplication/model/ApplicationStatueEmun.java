package com.example.administrator.classromapplication.model;

/**
 * Created by Administrator on 2017/11/19.
 */

public enum ApplicationStatueEmun {
    /**
     * 待审核
     */
    PENDING(0, "待审核"),
    /**
     * 审核中
     */
    UNDERREVIEW(1, "审核中"),
    /**
     * 审核结束
     */
    AUDITED(2, "已审批");
    private int status;
    private String state;

    ApplicationStatueEmun(int status, String state) {
        this.state = state;
        this.status = status;
    }

    public static String getState(int status) {
        String s;
        switch (status) {
            case 0:
                s = PENDING.state;
                break;
            case 1:
                s = UNDERREVIEW.state;
                break;
            case 2:
                s = AUDITED.state;
                break;
            default:
                s = "";
        }
        return s;
    }

    public int getStatus() {
        return status;
    }
}
