package com.example.administrator.classromapplication.view.ui;

import android.view.View;

import com.example.administrator.classromapplication.R;
import com.example.administrator.classromapplication.databinding.ActivityRootApplicationDetailBinding;
import com.example.administrator.classromapplication.model.ApplicationStatueEmun;
import com.example.administrator.classromapplication.viewmodel.ApplicationViewModel;
import com.tool.util.DataUtils;
import com.tool.util.widget.CustomTitleBar;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class RootApplicationDetailActivity extends BaseActivity<ActivityRootApplicationDetailBinding, ApplicationViewModel> {
    private ApplicationViewModel mModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_root_application_detail;
    }

    @Override
    protected void beforeInitView() {
        super.beforeInitView();
        mModel = (ApplicationViewModel) getIntent().getSerializableExtra("applicationViewModel");
        binding.setData(mModel);
    }

    @Override
    protected void initView() {
        super.initView();
        ((CustomTitleBar) binding.titleBar).setTitle("课室审批");
        if (mModel.getApplicationStatus() != ApplicationStatueEmun.AUDITED.getStatus())
            ((CustomTitleBar) binding.titleBar).setRightTitle("审批");
        ((CustomTitleBar) binding.titleBar).setRightTitleOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!DataUtils.checkStrNotNull(mModel.getFinalRoom()) && !DataUtils.checkStrNotNull(mModel.getRefuseReason())) {
                    showToast("请进行审批");
                } else {
                    mModel.setApplicationStatus(ApplicationStatueEmun.AUDITED.getStatus());
                    mModel.update(mModel.getObjectId(), new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null) {
                                showToast("审批成功");
                                finish();
                            } else {
                                showToast("审批错误" + e.getMessage());
                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void initListener() {
        binding.btnRefuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.etFinalRoom.setVisibility(View.GONE);
                binding.etRefuseReason.setVisibility(View.VISIBLE);
            }
        });
        binding.btnAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.etFinalRoom.setVisibility(View.VISIBLE);
                binding.etRefuseReason.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected ApplicationViewModel getViewModel() {
        return mModel;
    }
}
