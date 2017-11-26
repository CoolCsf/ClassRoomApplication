package com.example.administrator.classromapplication.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.classromapplication.databinding.ItemApplicationRcyBinding;
import com.example.administrator.classromapplication.viewmodel.ApplicationRoomItemViewModel;

/**
 * Created by Administrator on 2017/11/19.
 */

public class RoomListItemViewHolder extends BaseViewHolder {

    public RoomListItemViewHolder(View view) {
        super(view);
    }

    public ItemApplicationRcyBinding getBinding() {
        return (ItemApplicationRcyBinding) itemView.getTag(com.tool.R.id.BaseQuickAdapter_databinding_support);
    }
}
