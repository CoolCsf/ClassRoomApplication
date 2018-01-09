package com.example.administrator.classromapplication.view.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;

import com.example.administrator.classromapplication.AppContext;
import com.example.administrator.classromapplication.R;
import com.example.administrator.classromapplication.databinding.FragmentMineBinding;
import com.example.administrator.classromapplication.model.ApplicationStatueEmun;
import com.example.administrator.classromapplication.model.event.UpdateBadgeNum;
import com.example.administrator.classromapplication.viewmodel.ApplicationViewModel;
import com.example.administrator.classromapplication.viewmodel.UserViewModel;
import com.tool.util.QBadgeViewUtil;
import com.tool.util.ToastHelp;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.CountListener;
import cn.bmob.v3.listener.FindListener;
import q.rorbin.badgeview.QBadgeView;

/**
 * Created by Administrator on 2017/11/14.
 */

public class MineFragment extends BaseFragment<FragmentMineBinding> {
    private UserViewModel model;
    private QBadgeView pendView;
    private QBadgeView auditendView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void initData() {
        model = BmobUser.getCurrentUser(UserViewModel.class);
        if (model != null)
            binding.setData(model);
        else {
            ToastHelp.showToast("请先登录");
            startActivity(LoginActivity.class, null);
        }
        query();
    }

    private void query() {
        ((AbsActivity) getActivity()).showLoading();
        BmobQuery<ApplicationViewModel> query = new BmobQuery<>();
        query.addWhereNotEqualTo("applicationStatus", ApplicationStatueEmun.UNDERREVIEW.getStatus());
        if (!model.isRoot) {
            query.addWhereEqualTo("userId", BmobUser.getCurrentUser(UserViewModel.class).getObjectId());
        }
        query.findObjects(new FindListener<ApplicationViewModel>() {
            @Override
            public void done(List<ApplicationViewModel> list, BmobException e) {
                ((AbsActivity) getActivity()).closeLoading();
                if (e == null) {
                    if (list.size() > 0) {
                        int pendingNum = 0;
                        int auditedNum = 0;
                        for (ApplicationViewModel viewModel : list) {
                            if (viewModel.getApplicationStatus() == ApplicationStatueEmun.PENDING.getStatus()) {
                                pendingNum++;
                            } else {
                                auditedNum++;
                            }
                        }
                        AppContext.instance.setAuditedNum(auditedNum);
                        AppContext.instance.setPendNum(pendingNum);
                        setNum();
                    }
                } else {
                    ToastHelp.showToast(e.getMessage());
                }
            }
        });
    }

    private void setNum() {
        if (pendView == null) {
            pendView = new QBadgeView(getActivity());
            pendView.bindTarget(binding.tvPendingRoom)
                    .setBadgeGravity(Gravity.END | Gravity.CENTER_VERTICAL)
                    .setGravityOffset(90, false)
                    .setExactMode(false)     //number is 9999, if true ,show 9999, false show 99+
                    .setShowShadow(false);    //Control shadow
        }
        pendView.setBadgeNumber(AppContext.instance.getPendNum());
        if (auditendView == null) {
            auditendView = new QBadgeView(getActivity());
            auditendView.bindTarget(binding.tvAuditedRoom)
                    .setBadgeGravity(Gravity.END | Gravity.CENTER_VERTICAL)
                    .setGravityOffset(90, false)
                    .setExactMode(false)     //number is 9999, if true ,show 9999, false show 99+
                    .setShowShadow(false);    //Control shadow
        }
        auditendView.setBadgeNumber(AppContext.instance.getAuditedNum());
    }

    private void setQBadge(QBadgeView qBadgeView, int count, View view) {

        qBadgeView.setBadgeNumber(count);  // Do not need to judge "count", it will be shown automatically
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void helloEventBus(UpdateBadgeNum message) {
        query();
    }

    @Override
    protected void initListener() {

        binding.tvPendingRoom.setOnClickListener(new View.OnClickListener() {//未审核
            @Override
            public void onClick(View v) {
                AppContext.instance.setPendNumZero();
                setNum();
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
                AppContext.instance.setAuditedNumZero();
                setNum();
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
