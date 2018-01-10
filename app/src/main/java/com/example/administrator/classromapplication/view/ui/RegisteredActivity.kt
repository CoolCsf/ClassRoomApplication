package com.example.administrator.classromapplication.view.ui

import android.widget.EditText
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import com.example.administrator.classromapplication.LoginUtil
import com.example.administrator.classromapplication.R
import com.example.administrator.classromapplication.databinding.ActivityRegisteredBinding
import com.example.administrator.classromapplication.viewmodel.UserViewModel
import com.tool.util.DataUtils
import com.tool.util.ToastHelp
import com.tool.util.widget.CustomTitleBar
import kotlinx.android.synthetic.main.activity_registered.*

/**
 * Created by Ervin on 2018/1/10.
 */
class RegisteredActivity : BaseActivity<ActivityRegisteredBinding, UserViewModel>() {
    override fun getViewModel(): UserViewModel {
        val model = UserViewModel()
        binding.data = model
        return model
    }

    override fun initView() {
        (titleBar as CustomTitleBar).setTitle("注册")
    }

    override fun initListener() {
        btn_regist.setOnClickListener {
            if (checkParam()) {
                showLoading()
                viewModel.setRoot(false)
                viewModel.signUp(object : SaveListener<UserViewModel>() {
                    override fun done(model: UserViewModel?, e: BmobException?) {
                        if (e == null) {
                            ToastHelp.showToast("注册成功，开始登录")
                            LoginUtil.login(model?.userName, model?.pwd, this@RegisteredActivity)
                        } else {
                            ToastHelp.showToast("注册失败" + e.message)
                        }
                    }
                })
            }
        }
    }

    fun checkEtnNotNull(editText: EditText, errorMsg: String): Boolean {
        if (DataUtils.checkStrNotNull(editText.text.toString())) return true
        ToastHelp.showToast(errorMsg)
        return false
    }

    fun checkParam(): Boolean {
        return checkEtnNotNull(binding.etUserName, "请输入用户名")
                && checkEtnNotNull(binding.etPwd, "请输入密码")
                && checkEtnNotNull(binding.etPwdConfirm, "请输入确认密码")
                && checkPwdConfirm()
                && checkEtnNotNull(binding.etName, "请输入您的名字")
    }

    fun checkPwdConfirm(): Boolean {
        return et_pwd.text.toString().trim() == et_pwd.text.toString().trim()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_registered
    }
}