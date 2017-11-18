package com.example.administrator.classromapplication.view.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.administrator.classromapplication.R;
import com.example.administrator.classromapplication.adapter.ViewPagerFragmentAdapter;
import com.example.administrator.classromapplication.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AbsActivity<ActivityMainBinding> {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] tabs = getResources().getStringArray(R.array.main_tab);
        ViewPagerFragmentAdapter adapter = new ViewPagerFragmentAdapter(getSupportFragmentManager(), tabs, getFragments());
        binding.viewPager.setAdapter(adapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
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
