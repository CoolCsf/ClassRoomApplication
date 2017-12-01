package com.example.administrator.classromapplication.view.ui;

import android.util.Log;
import android.view.View;

import com.example.administrator.classromapplication.R;
import com.example.administrator.classromapplication.databinding.ActivityLoginBinding;
import com.example.administrator.classromapplication.model.Installation;
import com.example.administrator.classromapplication.viewmodel.UserViewModel;
import com.google.gson.internal.bind.util.ISO8601Utils;
import com.tool.util.CollectionUtils;
import com.tool.util.ToastHelp;

import java.util.List;

import cn.bmob.v3.BmobInstallationManager;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;


public class LoginActivity extends BaseActivity<ActivityLoginBinding, UserViewModel> {


    @Override
    protected void initListener() {
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                loginToBmob();
            }
        });
        binding.tvRegistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toRegisterAct();
            }
        });
    }

    private void toRegisterAct() {
        startActForResult(RegisteredActivity.class, null, 0);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }


    private void loginToBmob() {
        BmobUser.loginByAccount(viewModel.userName, viewModel.pwd, new LogInListener<UserViewModel>() {
            @Override
            public void done(UserViewModel userViewModel, BmobException e) {
                if (userViewModel != null) {
                    modifyInstallation(userViewModel.isRoot);
                } else {
                    ToastHelp.showToast("登录失败" + e.toString());
                }
            }
        });
    }

    private void modifyInstallation(final Boolean isRoot) {
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
                                    goActivity(RootMainActivity.class, null);
                                } else {
                                    goActivity(MainActivity.class, null);
                                }
                            } else {
                                ToastHelp.showToast(e.getMessage());
                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void initView() {
        binding.setLogin(viewModel);
    }

    @Override
    protected UserViewModel getViewModel() {
        UserViewModel model = new UserViewModel();
        binding.setLogin(model);
        return model;
    }
}
