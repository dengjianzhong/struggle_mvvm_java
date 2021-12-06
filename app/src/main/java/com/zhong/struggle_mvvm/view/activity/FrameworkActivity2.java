package com.zhong.struggle_mvvm.view.activity;

import android.Manifest;
import android.app.Activity;
import android.view.View;

import com.struggle.base.base.mvvm.BaseVMActivity;
import com.struggle.base.base.plugins.PermissionPlugin;
import com.struggle.base.launcher.TxToast;
import com.struggle.base.other.NotificationHelper;
import com.zhong.struggle_mvvm.R;
import com.zhong.struggle_mvvm.databinding.ActivityFramework2Binding;
import com.zhong.struggle_mvvm.logic.mvvm.model.MyModel;
import com.zhong.struggle_mvvm.other.ContentProviderHelper;
import com.zhong.struggle_mvvm.view.dialog.MyDialog;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/6 17:39
 * @Description 架构功能演示(2)
 */
public class FrameworkActivity2 extends BaseVMActivity<ActivityFramework2Binding, MyModel> implements PermissionPlugin {

    @Override
    public void initView() {
        setTransparentStatusBar();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                viewModel.requestArticleDetail("5e777432b8ea09cade05263f");
                break;
            case R.id.button3:
                NotificationHelper.init(getApplication());
                NotificationHelper.sendNotification();
                break;
            case R.id.button4:
                requestPermission(new String[]{Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS}, b -> {
                    if (b) {
                        ContentProviderHelper.querySms(this);
                    }
                });
                break;
            default:
                MyDialog dialog = new MyDialog();
                dialog.show(getSupportFragmentManager());
                break;
        }
    }

    @Override
    public void observer() {
        viewModel.articleDetail.observe(this, articleDetailBean -> {
            TxToast.showToast("数据请求成功");
        });
    }

    @Override
    public Activity getActivity() {
        return this;
    }
}