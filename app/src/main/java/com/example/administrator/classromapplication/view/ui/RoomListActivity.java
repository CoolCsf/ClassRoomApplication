package com.example.administrator.classromapplication.view.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.classromapplication.R;
import com.example.administrator.classromapplication.adapter.RoomListAdapter;
import com.example.administrator.classromapplication.databinding.ActivityRoomListBinding;
import com.example.administrator.classromapplication.model.ApplicationStatueEmun;
import com.example.administrator.classromapplication.viewmodel.ApplicationRoomItemViewModel;
import com.example.administrator.classromapplication.viewmodel.ApplicationViewModel;
import com.tool.util.CollectionUtils;
import com.tool.util.widget.CustomTitleBar;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2017/11/19.
 */

public class RoomListActivity extends AbsActivity<ActivityRoomListBinding> {
    private RoomListAdapter mAdapter;
    public static final String ROOM_KEY = "room_key";
    private int statusKey;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_room_list;
    }

    @Override
    protected void initView() {
        statusKey = getIntent().getExtras().getInt(ROOM_KEY, 0);
        ((CustomTitleBar) binding.titleBar).setTitle(ApplicationStatueEmun.getState(statusKey) + "列表");
        binding.rvApplicationList.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RoomListAdapter();
        binding.rvApplicationList.setAdapter(mAdapter);
    }

    @Override
    protected void initListener() {
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }

    @Override
    protected void initData() {
        queryFromBmob();
    }

    private void queryFromBmob() {
        BmobQuery<ApplicationViewModel> query = new BmobQuery<>();
        query.addWhereEqualTo("userId", BmobUser.getCurrentUser().getObjectId());
        BmobQuery<ApplicationViewModel> query1 = new BmobQuery<>();
        query1.addWhereEqualTo("applicationStatus", statusKey);
        List<BmobQuery<ApplicationViewModel>> queries = new ArrayList<>();
        queries.add(query);
        queries.add(query1);
        BmobQuery<ApplicationViewModel> queryAnd = new BmobQuery<>();
        queryAnd.and(queries);
        queryAnd.findObjects(new FindListener<ApplicationViewModel>() {
            @Override
            public void done(List<ApplicationViewModel> list, BmobException e) {
                if (e == null) {
                    if (CollectionUtils.collectionState(list) == CollectionUtils.COLLECTION_UNEMPTY) {
                        List<ApplicationRoomItemViewModel> itemList = new ArrayList<>();
                        for (ApplicationViewModel model : list) {
                            ApplicationRoomItemViewModel itemModel = new ApplicationRoomItemViewModel();
                            itemModel.setUseReason(model.getUseReason());
                            itemModel.setApplicationUser(model.getApplicaUser());
                            itemModel.setApplicationTime(model.getCreatedAt());
                            itemModel.setApplicationStatus(model.getApplicationStatus());
                            itemModel.setUserId(model.getUserId());
                            itemList.add(itemModel);
                        }
                        mAdapter.setNewData(itemList);
                    } else {
                        showToast("查询不到数据");
                    }
                } else {
                    showToast(e.getMessage());
                }
            }
        });
    }
}
