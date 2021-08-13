package com.zhong.struggle_mvvm.view;

import android.view.View;

import com.struggle.base.base.basics.BaseActivity;
import com.struggle.base.base.vm.BaseVMActivity;
import com.struggle.base.launcher.SPManager;
import com.struggle.base.launcher.TxToast;
import com.struggle.base.utils.GsonUtils;
import com.zhong.struggle_mvvm.R;
import com.zhong.struggle_mvvm.databinding.ActivityTestBinding;
import com.zhong.struggle_mvvm.model.MyModel;

public class TestActivity extends BaseVMActivity<ActivityTestBinding, MyModel> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_test;
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