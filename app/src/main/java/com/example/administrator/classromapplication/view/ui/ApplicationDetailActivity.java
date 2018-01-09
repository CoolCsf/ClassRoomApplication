package com.example.administrator.classromapplication.view.ui;

import com.example.administrator.classromapplication.R;
import com.example.administrator.classromapplication.databinding.ActivityRoomDetailBinding;
import com.example.administrator.classromapplication.viewmodel.ApplicationDetailViewModel;
import com.example.administrator.classromapplication.viewmodel.ApplicationViewModel;
import com.tool.util.widget.CustomTitleBar;

public class ApplicationDetailActivity extends BaseActivity<ActivityRoomDetailBinding, ApplicationDetailViewModel> {

    @Override
    protected void initView() {
        super.initView();
        ((CustomTitleBar) binding.titleBar).setTitle("申请详情");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_room_detail;
    }

    @Override
    protected ApplicationDetailViewModel getViewModel() {
        ApplicationViewModel viewModel = (ApplicationViewModel) getIntent().getSerializableExtra("applicationViewModel");
        ApplicationDetailViewModel detailViewModel = new ApplicationDetailViewModel();
        detailViewModel.setApplicationTime(viewModel.getCreatedAt());
        detailViewModel.setApplicationUser(viewModel.getApplicaUser());
        detailViewModel.setCreateTime(viewModel.getCreatedAt());
        detailViewModel.setState(viewModel.getApplicationStatus());
        detailViewModel.setUpdateTime(viewModel.getUpdatedAt());
        detailViewModel.setUseReason(viewModel.getUseReason());
        detailViewModel.setUseTime(viewModel.getUseDate() + " " + viewModel.getStartTime() + "至" + viewModel.getEndTime());
        detailViewModel.setFinalRoom(viewModel.getFinalRoom());
        detailViewModel.setText(viewModel.getText());
        detailViewModel.setRefuse(viewModel.getRefuseReason());
        binding.setData(detailViewModel);
        return detailViewModel;
    }
}
