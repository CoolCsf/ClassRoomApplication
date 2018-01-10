package com.example.administrator.classromapplication.view.ui

import com.example.administrator.classromapplication.LoginUtil
import com.example.administrator.classromapplication.R
import com.example.administrator.classromapplication.databinding.ActivityLoginBinding
import com.example.administrator.classromapplication.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_login.*

/**
 * Created by Ervin on 2018/1/10.
 */
class LoginActivity : BaseActivity<ActivityLoginBinding, UserViewModel>() {
    override fun getViewModel(): UserViewModel {
        val model = UserViewModel()
        binding.login = model
        return model
    }

    override fun initListener() {
        btn_login.setOnClickListener {
            LoginUtil.login(viewModel.userName, viewModel.pwd, this)
        }
        tv_registered.setOnClickListener {
            startActForResult(RegisteredActivity::class.java, null, 0)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }
}