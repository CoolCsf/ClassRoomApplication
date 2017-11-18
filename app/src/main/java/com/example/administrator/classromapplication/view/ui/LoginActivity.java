package com.example.administrator.classromapplication.view.ui;

import android.os.Bundle;
import android.view.View;

import com.example.administrator.classromapplication.R;
import com.example.administrator.classromapplication.databinding.ActivityLoginBinding;
import com.example.administrator.classromapplication.viewmodel.LoginViewModel;
import com.tool.util.Bmob.BmobHelper;
import com.tool.util.ToastHelp;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.SaveListener;


public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.setLogin(viewModel);
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                loginToBmob();
            }
        });
    }

    private void loginToBmob() {
        BmobUser.loginByAccount(viewModel.userName, viewModel.pwd, new LogInListener<LoginViewModel>() {
            @Override
            public void done(LoginViewModel loginViewModel, BmobException e) {
                if (loginViewModel != null) {
                    if (loginViewModel.isRoot) {

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
    protected LoginViewModel getViewModel() {
        return new LoginViewModel();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }
}
