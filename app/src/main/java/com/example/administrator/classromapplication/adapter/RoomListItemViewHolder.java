package com.example.administrator.classromapplication.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.classromapplication.databinding.ItemApplicationRcyBinding;
import com.example.administrator.classromapplication.viewmodel.ApplicationRoomItemViewModel;

/**
 * Created by Administrator on 2017/11/19.
 */

public class RoomListItemViewHolder extends BaseViewHolder {
    private ItemApplicationRcyBinding binding;

    public RoomListItemViewHolder(ItemApplicationRcyBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(ApplicationRoomItemViewModel item) {
        binding.setData(item);
        binding.executePendingBindings();
    }
}
