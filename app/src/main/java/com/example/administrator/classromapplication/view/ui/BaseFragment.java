package com.example.administrator.classromapplication.view.ui;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/11/16.
 */

public abstract class BaseFragment<BD extends ViewDataBinding> extends Fragment {
    protected BD binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        initView();
        initListener();
        initData();
        return binding.getRoot();
    }

    protected void initListener() {

    }

    protected abstract void initView() ;

    protected void initData() {

    }

    protected abstract int getLayoutId();
}
