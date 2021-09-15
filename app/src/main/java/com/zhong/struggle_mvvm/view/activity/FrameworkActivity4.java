package com.zhong.struggle_mvvm.view.activity;

import android.view.View;

import com.struggle.base.base.basics.BaseActivity;
import com.zhong.struggle_mvvm.databinding.ActivityFramework4Binding;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/6 17:39
 * @Description 架构功能演示(4)
 */
public class FrameworkActivity4 extends BaseActivity<ActivityFramework4Binding> {

    @Override
    protected void initView() {
        setTransparentStatusBar();

        bind.guideline.setVisibility(View.VISIBLE);
    }
}