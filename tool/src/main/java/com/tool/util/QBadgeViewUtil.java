package com.tool.util;

import android.content.Context;
import android.view.Gravity;
import android.view.View;

import q.rorbin.badgeview.QBadgeView;

/**
 * Created by csf on 2017/6/27.
 * 文件描述
 */

public class QBadgeViewUtil {
    private QBadgeView qBadgeView;

    public void setQBadgeView(Context context, View view, int gravity, int count) {
        if (qBadgeView == null) {
            qBadgeView = new QBadgeView(context);
            qBadgeView.bindTarget(view)
                    .setBadgeGravity(gravity)
                    .setGravityOffset(90, false)
                    .setExactMode(false)     //number is 9999, if true ,show 9999, false show 99+
                    .setShowShadow(false)    //Control shadow
                    .setBadgeNumber(count);  // Do not need to judge "count", it will be shown automatically
        } else {
            qBadgeView.setBadgeNumber(count);
        }
    }
}
