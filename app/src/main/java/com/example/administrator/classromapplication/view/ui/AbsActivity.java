package com.example.administrator.classromapplication.view.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.tool.util.ToastHelp;
import com.tool.util.widget.CustomTitleBar;

/**
 * Created by Administrator on 2017/11/13.
 */

public abstract class AbsActivity<BD extends ViewDataBinding> extends AppCompatActivity implements IBaseActivity {
    protected BD binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, getLayoutId());
        initView();
        initListener();
        initData();
    }

    protected abstract int getLayoutId();

    public void showToast(String content) {
        ToastHelp.showToast(content);
    }

    @Override
    public void goActivity(Class act, Bundle bundle) {
        Intent intent = new Intent(this, act);
        if (bundle != null)
            intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void startActForResult(Class actClass, Bundle bundle, int requestCode) {
        Intent intent = new Intent(this, actClass);
        if (bundle != null)
            intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }

    protected void initListener() {

    }

    protected void initView() {

    }

    protected abstract void initData();
}
