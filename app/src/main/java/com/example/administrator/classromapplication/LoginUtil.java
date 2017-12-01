package com.example.administrator.classromapplication;

import android.app.Activity;
import android.util.Log;

import com.example.administrator.classromapplication.model.Installation;
import com.example.administrator.classromapplication.view.ui.AbsActivity;
import com.example.administrator.classromapplication.view.ui.MainActivity;
import com.example.administrator.classromapplication.view.ui.RootMainActivity;
import com.example.administrator.classromapplication.viewmodel.UserViewModel;
import com.tool.util.CollectionUtils;
import com.tool.util.ToastHelp;

import java.util.List;

import cn.bmob.v3.BmobInstallationManager;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by Administrator on 2017/12/2.
 */

public class LoginUtil {
    public static void login(String userName, String pwd, final AbsActivity activity) {
        activity.showLoading();
        BmobUser.loginByAccount(userName, pwd, new LogInListener<UserViewModel>() {
            @Override
            public void done(UserViewModel userViewModel, BmobException e) {
                if (userViewModel != null) {
                    modifyInstallation(userViewModel.isRoot, activity);
                } else {
                    ToastHelp.showToast("登录失败" + e.toString());
                    activity.closeLoading();
                }
            }
        });
    }

    private static void modifyInstallation(final Boolean isRoot, final AbsActivity absActivity) {
        BmobQuery<Installation> bmobQuery = new BmobQuery<>();
        final String id = BmobInstallationManager.getInstallationId();
        bmobQuery.addWhereEqualTo("installationId", id);
        bmobQuery.findObjects(new FindListener<Installation>() {
            @Override
            public void done(List<Installation> list, BmobException e) {
                if (CollectionUtils.collectionState(list) == CollectionUtils.COLLECTION_UNEMPTY) {
                    Installation installation = list.get(0);
                    installation.setRoot(isRoot);
                    installation.update(new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null) {
                                Log.d("LoginActivity", "更新设备成功");
                                if (isRoot) {
                                    absActivity.goActivity(RootMainActivity.class, null);
                                } else {
                                    absActivity.goActivity(MainActivity.class, null);
                                }
                            } else {
                                ToastHelp.showToast(e.getMessage());
                            }
                            absActivity.closeLoading();
                        }
                    });
                }
            }
        });
    }
}
