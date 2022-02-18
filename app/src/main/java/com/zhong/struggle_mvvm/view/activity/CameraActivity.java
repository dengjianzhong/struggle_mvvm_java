package com.zhong.struggle_mvvm.view.activity;

import android.app.Activity;
import android.view.View;

import com.struggle.base.base.basics.BaseActivity;
import com.struggle.base.base.plugins.PermissionPlugin;
import com.zhong.struggle_mvvm.R;
import com.zhong.struggle_mvvm.databinding.ActivityCameraBinding;

public class CameraActivity extends BaseActivity<ActivityCameraBinding> implements PermissionPlugin, View.OnClickListener {
    @Override
    protected void initView() {
        setTransparentStatusBar();
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    protected boolean disableHorizontalScreen() {
        return true;
    }

    @Override
    protected void initEvent() {
        super.initEvent();

        bind.button6.setOnClickListener(this);
        bind.button7.setOnClickListener(this);
        bind.button8.setOnClickListener(this);
        bind.button9.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button6:
                bind.yxCamera.switchCamera();
                break;
            case R.id.button7:
                bind.yxCamera.photograph();
                break;
            case R.id.button8:
                bind.yxCamera.savePhoto();
                break;
            case R.id.button9:
                bind.yxCamera.initCamera();
                break;
        }
    }
}