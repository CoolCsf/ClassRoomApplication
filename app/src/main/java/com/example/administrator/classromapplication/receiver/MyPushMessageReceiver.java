package com.example.administrator.classromapplication.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;

import com.example.administrator.classromapplication.R;
import com.example.administrator.classromapplication.model.ApplicationStatueEmun;
import com.example.administrator.classromapplication.model.event.UpdateBadgeNum;
import com.example.administrator.classromapplication.view.ui.RoomListActivity;
import com.example.administrator.classromapplication.viewmodel.UserViewModel;
import com.tool.util.ToastHelp;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import cn.bmob.push.PushConstants;
import cn.bmob.v3.BmobUser;

/**
 * Created by Administrator on 2017/11/29.
 */

public class MyPushMessageReceiver extends BroadcastReceiver {
    int count = 1;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        if (intent.getAction().equals(PushConstants.ACTION_MESSAGE)) {
            count++;
            String msg = intent.getStringExtra("msg");
            try {
                JSONObject jsonObject = new JSONObject(msg);
                String m = jsonObject.getString("alert");
                NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                Notification.Builder builder = new Notification.Builder(context);
                Bundle bundle = new Bundle();
                Boolean isRoot = BmobUser.getCurrentUser(UserViewModel.class).isRoot;
                if (isRoot)
                    bundle.putInt(RoomListActivity.Companion.getROOM_KEY(), ApplicationStatueEmun.PENDING.getStatus());
                else
                    bundle.putInt(RoomListActivity.Companion.getROOM_KEY(), ApplicationStatueEmun.AUDITED.getStatus());
                bundle.putBoolean(RoomListActivity.Companion.getROOT_KEY(), isRoot);
                Intent intentActivity = new Intent(context, RoomListActivity.class);
                intentActivity.putExtras(bundle);
                PendingIntent contentIndent = PendingIntent.getActivity(context, 0, intentActivity, PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(contentIndent).setSmallIcon(R.mipmap.ic_launcher)//设置通知栏图标 　　　　　　　　　　　　　　　　　　　　
                        .setTicker(m) //设置状态栏的显示的信息
                        .setWhen(System.currentTimeMillis())//设置时间发生时间
                        .setAutoCancel(true)//设置可以清除
                        .setContentTitle(context.getResources().getString(R.string.app_name))//设置下拉列表里的标题
                        .setContentText(m);//设置上下文内容
                Notification notification = builder.build();
                //加i是为了显示多条Notification
                notificationManager.notify(count, notification);
                EventBus.getDefault().post(new UpdateBadgeNum());
            } catch (Exception e) {
                e.printStackTrace();
                ToastHelp.showToast(e.getMessage());
            }
        }
    }

}
