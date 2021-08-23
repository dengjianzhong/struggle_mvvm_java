package com.zhong.struggle_mvvm.view.activity;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;

import com.struggle.base.base.mvvm.BaseVMActivity;
import com.struggle.base.http.observer.SimpleObserver;
import com.struggle.base.launcher.SPManager;
import com.struggle.base.launcher.TxToast;
import com.struggle.base.utils.GsonUtils;
import com.zhong.struggle_mvvm.R;
import com.zhong.struggle_mvvm.bean.TestBean;
import com.zhong.struggle_mvvm.databinding.ActivityFramework1Binding;
import com.zhong.struggle_mvvm.mvvm.model.MyModel;
import com.zhong.struggle_mvvm.view.fragment.MyFragment;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/6 17:39
 * @Description 架构功能演示(1)
 */
public class FrameworkActivity1 extends BaseVMActivity<ActivityFramework1Binding, MyModel> {
    @Override
    public int getLayoutId() {
        return R.layout.activity_framework1;
    }

    @Override
    public void initView() {
        setTransparentStatusBar();
        {
            FragmentTransaction transaction =
                    getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.flContainer, new MyFragment());
            transaction.commit();
        }
    }

    @Override
    public void initData() {
        bind.tvView.setText("模拟网络请求");
    }

    @Override
    public void initEvent() {
        bind.tvView.setOnClickListener(v -> viewModel.requestGirl());
    }

    @SuppressLint("CheckResult")
    @Override
    public void observer() {
        super.observer();

        /**干货所有子分类*/
        viewModel.ganHuoLiveData.observe(this, testBeans -> {
            List<TestBean> testBeanList = new ArrayList<>();
            if (testBeans != null && testBeans.size() > 0) {
                Observable.fromIterable(testBeans)
                        .filter(testBean -> !testBean.getTitle().contains("苹果"))
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .subscribe(new SimpleObserver<TestBean>() {
                            @Override
                            public void onNext(@NonNull TestBean o) {
                                testBeanList.add(o);
                            }

                            @Override
                            public void onComplete() {
                                if (testBeanList != null && testBeanList.size() > 0) {
                                    TxToast.showToast("数据请求成功");
                                }
                            }
                        });
            }
        });

        /**文章详情*/
        viewModel.articleDetail.observe(this, articleDetailBean -> TxToast.showToast("数据请求成功"));

        /**分类*/
        viewModel.classify.observe(this, articleDetailBean -> {
            SPManager.Instance().putValue("user_info", GsonUtils.toJson(articleDetailBean));
            TxToast.showToast("分类请求成功");
        });
    }


    @SuppressLint("CheckResult")
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt1:
                /**Rxjava数据模拟*/
                LinkedList<String> linkedList = new LinkedList<>();
                Observable.fromIterable(getSourceData())
                        .flatMap((Function<List<String>, ObservableSource<String>>) strings -> Observable.fromIterable(strings))
                        .filter(s -> s.contains("3"))
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .subscribe(s -> linkedList.add(s)
                                , throwable -> Log.i("===>", throwable.getMessage())
                                , () -> {
                                    if (linkedList != null) {
                                        TxToast.showToast("数据处理成功");
                                    }
                                });
                break;
            case R.id.bt2:
                /**跳转页面*/
                openActivity(FrameworkActivity2.class, null);
                break;
            case R.id.bt3:
                openActivity(FrameworkActivity3.class, null);
                break;
        }
    }

    /**
     * 获取数据源
     *
     * @return
     */
    private List<List<String>> getSourceData() {
        List<List<String>> lists = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            List<String> childList = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                childList.add(i + "-" + j);
            }
            lists.add(childList);
        }

        return lists;
    }
}