package com.example.administrator.classromapplication.view.ui;

import android.os.Bundle;
import android.view.View;

import com.example.administrator.classromapplication.R;
import com.example.administrator.classromapplication.databinding.FragmentMineBinding;
import com.example.administrator.classromapplication.model.ApplicationStatueEmun;
import com.example.administrator.classromapplication.viewmodel.UserViewModel;
import com.tool.util.ToastHelp;

import cn.bmob.v3.BmobUser;

/**
 * Created by Administrator on 2017/11/14.
 */

public class MineFragment extends BaseFragment<FragmentMineBinding> {
    private UserViewModel model;

    @Override
    protected void initData() {
        model = BmobUser.getCurrentUser(UserViewModel.class);
        if (model != null)
            binding.setData(model);
        else {
            ToastHelp.showToast("请先登录");
            startActivity(LoginActivity.class, null);
        }
    }

    @Override
    protected void initListener() {

        binding.tvPendingRoom.setOnClickListener(new View.OnClickListener() {//未审核
            @Override
            public void onClick(View v) {
                goRoomActivity(ApplicationStatueEmun.PENDING.getStatus());
            }
        });
        binding.tvUnderReviewRoom.setOnClickListener(new View.OnClickListener() {//审核中
            @Override
            public void onClick(View v) {
                goRoomActivity(ApplicationStatueEmun.UNDERREVIEW.getStatus());
            }
        });
        binding.tvAuditedRoom.setOnClickListener(new View.OnClickListener() {//已审批
            @Override
            public void onClick(View v) {
                goRoomActivity(ApplicationStatueEmun.AUDITED.getStatus());
            }
        });
    }

    private void goRoomActivity(int status) {
        Bundle bundle = new Bundle();
        bundle.putInt(RoomListActivity.ROOM_KEY, status);
        bundle.putBoolean(RoomListActivity.ROOT_KEY, model.isRoot());
        startActivity(RoomListActivity.class, bundle);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }
}
