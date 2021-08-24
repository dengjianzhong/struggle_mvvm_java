package com.zhong.struggle_mvvm.view.activity;

import android.view.View;

import com.struggle.base.base.mvvm.BaseVMActivity;
import com.struggle.base.launcher.TxToast;
import com.zhong.struggle_mvvm.R;
import com.zhong.struggle_mvvm.databinding.ActivityFramework2Binding;
import com.zhong.struggle_mvvm.mvvm.model.MyModel;
import com.zhong.struggle_mvvm.view.dialog.MyDialog;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/6 17:39
 * @Description 架构功能演示(2)
 */
public class FrameworkActivity2 extends BaseVMActivity<ActivityFramework2Binding, MyModel> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_framework2;
    }

    @Override
    public void initView() {
        setTransparentStatusBar();
    }

    public void onClick(View view) {
        if (view.getId() == R.id.button) {
            viewModel.requestArticleDetail("5e777432b8ea09cade05263f");
        } else {
            MyDialog dialog = new MyDialog();
            dialog.show(getSupportFragmentManager());
        }
    }

    @Override
    public void observer() {
        viewModel.articleDetail.observe(this, articleDetailBean -> {
            TxToast.showToast("数据请求成功");
        });
    }


}