package com.example.administrator.classromapplication.view.ui;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2017/11/13.
 */

public abstract class AbsActivity<BD extends ViewDataBinding> extends AppCompatActivity {
    protected BD binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, getLayoutId());
    }
    protected abstract int getLayoutId();

}
