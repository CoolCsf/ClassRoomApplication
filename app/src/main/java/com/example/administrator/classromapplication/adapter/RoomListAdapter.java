package com.example.administrator.classromapplication.adapter;

import android.view.LayoutInflater;
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
    public RoomListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        ItemApplicationRcyBinding itemBinding =
                ItemApplicationRcyBinding.inflate(layoutInflater, parent, false);
        return new RoomListItemViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(RoomListItemViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    @Override
    protected void convert(RoomListItemViewHolder helper, ApplicationRoomItemViewModel item) {

    }
}
