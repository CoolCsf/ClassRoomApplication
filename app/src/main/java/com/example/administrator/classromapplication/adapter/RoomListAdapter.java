package com.example.administrator.classromapplication.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.classromapplication.R;
import com.example.administrator.classromapplication.databinding.ItemApplicationRcyBinding;
import com.example.administrator.classromapplication.viewmodel.ApplicationRoomItemViewModel;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/11/19.
 */

public class RoomListAdapter extends BaseQuickAdapter<ApplicationRoomItemViewModel, RoomListItemViewHolder> {
    public RoomListAdapter() {
        super(R.layout.item_application_rcy, new ArrayList<ApplicationRoomItemViewModel>());
    }

    @Override
    protected void convert(RoomListItemViewHolder helper, ApplicationRoomItemViewModel item) {
        helper.getBinding().setData(item);
    }

    @Override
    protected View getItemView(int layoutResId, ViewGroup parent) {
        ViewDataBinding binding = DataBindingUtil.inflate(mLayoutInflater, layoutResId, parent, false);
        if (binding == null) {
            return super.getItemView(layoutResId, parent);
        }
        View view = binding.getRoot();
        view.setTag(R.id.BaseQuickAdapter_databinding_support, binding);
        return view;
    }
}
