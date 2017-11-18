package com.example.administrator.classromapplication.view.ui;

import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.tool.util.ToastHelp;

/**
 * Created by Administrator on 2017/11/10.
 */

public abstract class BaseActivity<BD extends ViewDataBinding, VM> extends AbsActivity<BD> {
    protected VM viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = getViewModel();
    }

    protected abstract VM getViewModel();


    public void showToast(String content) {
        ToastHelp.showToast(content);
    }

    protected void goActivity(Class act, Bundle bundle) {
        Intent intent = new Intent(this, act);
        if (bundle != null)
            intent.putExtras(bundle);
        startActivity(intent);
    }

    protected void startActForResult(String fragmentName, Class actClass, Bundle bundle, int requestCode) {
        Intent intent = new Intent(this, actClass);
        if (bundle != null)
            intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }

}
