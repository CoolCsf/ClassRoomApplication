package com.example.administrator.classromapplication.view.ui;

import android.view.View;

import com.example.administrator.classromapplication.R;
import com.example.administrator.classromapplication.databinding.FragmentApplicationBinding;
import com.example.administrator.classromapplication.model.ApplicationStatueEmun;
import com.example.administrator.classromapplication.viewmodel.ApplicationViewModel;
import com.example.administrator.classromapplication.viewmodel.UserViewModel;
import com.tool.util.ToastHelp;
import com.tool.util.widget.CustomTitleBar;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2017/11/14.
 */

public class ApplicationFragment extends BaseFragment<FragmentApplicationBinding> {
    private ApplicationViewModel model;

    @Override
    protected void initView() {
        ((CustomTitleBar) binding.titleBar).setTitle("课室申请");
        ((CustomTitleBar) binding.titleBar).setRightTitle("申请");
    }

    @Override
    protected void initListener() {
        ((CustomTitleBar) binding.titleBar).setRightTitleOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (model.canSubmit()) {
                    model.setApplicationStatus(ApplicationStatueEmun.PENDING.getStatus());
                    model.setUserId(BmobUser.getCurrentUser().getObjectId());
                    model.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if (e == null) {
                                ToastHelp.showToast("申请成功");
                            } else {
                                ToastHelp.showToast("申请失败" + e.getMessage());
                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void initData() {
        model = new ApplicationViewModel();
        binding.setData(model);
        model.setApplicaUser(BmobUser.getCurrentUser(UserViewModel.class).name);
        model.setUseDate("2015-10-10");
        model.setEndTime("2015-50-60");
        model.setStartTime("2015-40-30");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_application;
    }


}
