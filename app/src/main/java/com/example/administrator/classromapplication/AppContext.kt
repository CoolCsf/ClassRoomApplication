package com.example.administrator.classromapplication

import android.app.Activity
import android.app.Application
import android.util.Log
import cn.bmob.push.BmobPush
import cn.bmob.v3.Bmob
import cn.bmob.v3.BmobInstallation
import cn.bmob.v3.BmobInstallationManager
import cn.bmob.v3.InstallationListener
import cn.bmob.v3.exception.BmobException
import com.tool.util.ToastHelp
import java.util.*

/**
 * Created by Ervin on 2018/1/9.
 */
class AppContext : Application() {
    private var activitys: Stack<Activity> = Stack()
    private var pendNum = 0
    private var auditedNum = 0
    private var readPendNum = 0
    private var readAuditendNum = 0

    init {
        instance = this
    }
    override fun onCreate() {
        super.onCreate()
        ToastHelp.init(this)
        Bmob.initialize(this, "fd56c9cd73e94e83752bcda4fc410938")
        BmobInstallationManager.getInstance().initialize(object : InstallationListener<BmobInstallation>() {
            override fun done(p0: BmobInstallation?, p1: BmobException?) {
                if (p1 == null) {
                    Log.d("AppContext", "获取设备数据成功" + p0?.objectId + "-" + p0?.installationId)
                } else {
                    Log.d("AppContext", "获取数据失败")
                }
            }
        })
        BmobPush.startWork(this)
    }

    fun exitApp() {
        finishAll()
    }

    private fun finishAll() {
        if (!activitys.isEmpty()) {
            for (act in activitys) {
                act.finish()
            }
            activitys.clear()
        }
    }

    fun removeActivity(strActivity: String) {
        for (act in activitys) {
            val name = act?.javaClass?.simpleName
            if (name == strActivity) {
                activitys.remove(act)
                break
            }
        }
    }

    fun addActivities(activity: Activity) {
        activitys.push(activity)
    }

    fun getPendNum(): Int {
        return pendNum
    }

    fun setPendNum(pendNum: Int) {
        this.pendNum = pendNum - readPendNum
    }

    fun setPendNumZero() {
        this.readPendNum += pendNum
        this.pendNum = 0
    }

    fun getAuditedNum(): Int {
        return auditedNum
    }

    fun setAuditedNum(auditedNum: Int) {
        this.auditedNum = auditedNum - readAuditendNum
    }

    fun setAuditedNumZero() {
        this.readAuditendNum += auditedNum
        this.auditedNum = 0
    }

    companion object context{
        lateinit var instance: AppContext
        fun instance(): AppContext {
            return instance
        }
    }
}