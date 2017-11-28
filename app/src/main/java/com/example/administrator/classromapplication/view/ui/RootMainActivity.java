package com.example.administrator.classromapplication.view.ui;


import android.support.v4.app.FragmentTransaction;

import com.example.administrator.classromapplication.R;
import com.example.administrator.classromapplication.databinding.ActivityRootMainBinding;

public class RootMainActivity extends AbsActivity<ActivityRootMainBinding> {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_root_main;
    }

    @Override
    protected void initView() {
        super.initView();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.content, new MineFragment());
        ft.commit();
    }

    @Override
    protected void initData() {

    }
}
