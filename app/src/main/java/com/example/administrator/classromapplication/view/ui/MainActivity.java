package com.example.administrator.classromapplication.view.ui;

import android.support.v4.app.Fragment;
import android.view.KeyEvent;

import com.example.administrator.classromapplication.AppContext;
import com.example.administrator.classromapplication.R;
import com.example.administrator.classromapplication.adapter.ViewPagerFragmentAdapter;
import com.example.administrator.classromapplication.databinding.ActivityMainBinding;
import com.tool.util.ToastHelp;

import java.util.ArrayList;

public class MainActivity extends AbsActivity<ActivityMainBinding> {

    private long time = 0;

    @Override
    protected void initView() {
        String[] tabs = getResources().getStringArray(R.array.main_tab);
        ViewPagerFragmentAdapter adapter = new ViewPagerFragmentAdapter(getSupportFragmentManager(), tabs, getFragments());
        binding.viewPager.setAdapter(adapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
    }


    @Override
    protected void initData() {

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
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    public ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new ApplicationFragment());
        fragments.add(new MineFragment());
        return fragments;
    }
}
