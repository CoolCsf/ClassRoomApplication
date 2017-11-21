package com.example.administrator.classromapplication.view.ui;

import android.view.View;

import com.example.administrator.classromapplication.R;
import com.example.administrator.classromapplication.databinding.ActivityLoginBinding;
import com.example.administrator.classromapplication.viewmodel.UserViewModel;
import com.tool.util.ToastHelp;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.SaveListener;


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
                    if (userViewModel.isRoot) {

                    } else {
                        goActivity(MainActivity.class, null);
                    }
                } else {
                    ToastHelp.showToast("登录失败" + e.toString());
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
