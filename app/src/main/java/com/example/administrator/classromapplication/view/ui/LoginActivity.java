package com.example.administrator.classromapplication.view.ui;

import android.view.View;

import com.example.administrator.classromapplication.LoginUtil;
import com.example.administrator.classromapplication.R;
import com.example.administrator.classromapplication.databinding.ActivityLoginBinding;
import com.example.administrator.classromapplication.viewmodel.UserViewModel;


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
        LoginUtil.login(viewModel.userName, viewModel.pwd, this);
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
