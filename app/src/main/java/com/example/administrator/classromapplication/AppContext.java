package com.example.administrator.classromapplication;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import com.tool.util.ToastHelp;

import java.util.Stack;

import cn.bmob.push.BmobPush;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobInstallation;
import cn.bmob.v3.BmobInstallationManager;
import cn.bmob.v3.InstallationListener;
import cn.bmob.v3.exception.BmobException;

/**
 * Created by Administrator on 2017/11/10.
 */

public class AppContext extends Application {
    private Stack<Activity> activities = new Stack<>();
    public static AppContext instance;
    private int pendNum = 0;
    private int auditedNum = 0;
    private int readPendNum = 0;
    private int readAuditendNum = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        ToastHelp.init(this);
        //第一：默认初始化
        Bmob.initialize(this, "fd56c9cd73e94e83752bcda4fc410938");
        // 使用推送服务时的初始化操作
        BmobInstallationManager.getInstance().initialize(new InstallationListener<BmobInstallation>() {
            @Override
            public void done(BmobInstallation bmobInstallation, BmobException e) {
                if (e == null) {
                    Log.d("", "获取设备数据成功" + bmobInstallation.getObjectId() + "-" + bmobInstallation.getInstallationId());
//                    Log.i(bmobInstallation.getObjectId() + "-" + bmobInstallation.getInstallationId());
                } else {
                    Log.d("", "获取设备数据失败");
//                    Logger.e(e.getMessage());
                }
            }
        });
// 启动推送服务
        BmobPush.startWork(this);
    }

    //完全关闭app
    public void exitApp() {
        finishAll();
//        System.exit(0);
    }

    //清楚栈中所有的activity
    private void finishAll() {
        if (activities != null && !activities.isEmpty()) {
            for (Activity activity : activities) {
                activity.finish();
            }
            activities.clear();
        }
    }

    public void removeActivity(String strActivity) {
        for (Activity activity : activities) {
            String name = activity.getClass().getSimpleName();
            if (name.equals(strActivity)) {
                Log.i("application", "removeActivity:" + strActivity);
                activities.remove(activity);
                break;
            }
        }
    }

    public int getPendNum() {
        return pendNum;
    }

    public void setPendNum(int pendNum) {
        this.pendNum = pendNum - readPendNum;
    }

    public void setPendNumZero() {
        this.readPendNum += pendNum;
        this.pendNum = 0;
    }

    public int getAuditedNum() {
        return auditedNum;
    }

    public void setAuditedNum(int auditedNum) {
        this.auditedNum = auditedNum - readAuditendNum;
    }

    public void setAuditedNumZero() {
        this.readAuditendNum += auditedNum;
        this.auditedNum = 0;
    }

    public void addActivities(Activity activity) {
        activities.push(activity);
    }
}
