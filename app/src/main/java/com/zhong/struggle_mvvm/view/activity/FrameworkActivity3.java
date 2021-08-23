package com.zhong.struggle_mvvm.view.activity;

import com.struggle.base.base.mvp.BaseMVPActivity;
import com.struggle.base.launcher.TxToast;
import com.zhong.struggle_mvvm.R;
import com.zhong.struggle_mvvm.bean.ArticleDetailBean;
import com.zhong.struggle_mvvm.mvp.contract.MyContract;
import com.zhong.struggle_mvvm.mvp.presenter.MyPresenter;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/6 17:39
 * @Description 架构功能演示(3)
 */
public class FrameworkActivity3 extends BaseMVPActivity<MyPresenter> implements MyContract.View {

    private Disposable subscribe;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_framework3;
    }

    @Override
    protected void initView() {
        setTransparentStatusBar();
    }

    @Override
    protected void initData() {
        super.initData();

        subscribe = Observable.interval(3000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> presenter.requestArticleDetail("5e777432b8ea09cade05263f"));
    }

    @Override
    public void onArticleDetail(ArticleDetailBean bean) {
        if (bean != null) {
            TxToast.showToast("获取成功");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        subscribe.dispose();
    }
}