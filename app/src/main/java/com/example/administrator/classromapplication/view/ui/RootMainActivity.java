package com.example.administrator.classromapplication.view.ui;


import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;

import com.example.administrator.classromapplication.AppContext;
import com.example.administrator.classromapplication.R;
import com.example.administrator.classromapplication.databinding.ActivityRootMainBinding;
import com.tool.util.ToastHelp;

public class RootMainActivity extends AbsActivity<ActivityRootMainBinding> {

    private long time = 0;
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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - time > 2000) {
                ToastHelp.showToast("再按一次推出");
                time = System.currentTimeMillis();
            } else {
                AppContext.context.instance().exitApp();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    protected void initData() {

    }
}
