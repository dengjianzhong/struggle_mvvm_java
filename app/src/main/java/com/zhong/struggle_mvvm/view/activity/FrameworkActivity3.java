package com.zhong.struggle_mvvm.view.activity;

import android.view.View;

import com.struggle.base.app.bean.DataResponse;
import com.struggle.base.base.mvp.BaseMVPActivity;
import com.struggle.base.launcher.TxToast;
import com.zhong.struggle_mvvm.R;
import com.zhong.struggle_mvvm.bean.ArticleDetailBean;
import com.zhong.struggle_mvvm.databinding.ActivityFramework3Binding;
import com.zhong.struggle_mvvm.mvp.contract.MyContract;
import com.zhong.struggle_mvvm.mvp.presenter.MyPresenter;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/6 17:39
 * @Description 架构功能演示(3)
 */
public class FrameworkActivity3 extends BaseMVPActivity<ActivityFramework3Binding, MyPresenter> implements MyContract.View {

    @Override
    protected void initView() {
        setTransparentStatusBar();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                presenter.requestArticleDetail("5e777432b8ea09cade05263f");
                break;
        }
    }

    //V层数据回调处理

    @Override
    public void onArticleDetail(ArticleDetailBean bean) {
        if (bean != null) {
            TxToast.showToast("获取成功");
        }
    }

    @Override
    public void onMessage(DataResponse bean) {
        super.onMessage(bean);
    }
}