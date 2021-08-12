package com.zhong.struggle_mvvm.view;

import android.Manifest;
import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;

import com.jaeger.library.StatusBarUtil;
import com.struggle.base.base.vm.BaseVMActivity;
import com.struggle.base.http.observer.SimpleObserver;
import com.struggle.base.launcher.SPManager;
import com.struggle.base.launcher.TxToast;
import com.struggle.base.utils.GsonUtils;
import com.struggle.base.utils.PermissionsUtil;
import com.zhong.struggle_mvvm.R;
import com.zhong.struggle_mvvm.bean.ArticleDetailBean;
import com.zhong.struggle_mvvm.bean.TestBean;
import com.zhong.struggle_mvvm.databinding.ActivityMainBinding;
import com.zhong.struggle_mvvm.model.MyModel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseVMActivity<ActivityMainBinding, MyModel> {
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StatusBarUtil.setTransparent(this);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {
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
        bind.tvView.setOnClickListener(v -> {
            List<String> permissions = new ArrayList<>();
            permissions.add(Manifest.permission.CALL_PHONE);
            permissions.add(Manifest.permission.CAMERA);


            PermissionsUtil.request(MainActivity.this, permissions, b -> {
                if (b) {
                    viewModel.requestArticleDetail("5e777432b8ea09cade05263f");
                }
            });
        });
    }

    @SuppressLint("CheckResult")
    @Override
    public void observer() {
        super.observer();

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

        viewModel.articleDetail.observe(this, articleDetailBean -> {
            SPManager.Instance().putValue("UserInfo", GsonUtils.toJson(articleDetailBean));
            TxToast.showToast("数据请求成功");
        });
    }

    public void onClick(View view) {
        if (view.getId() == R.id.bt1) {
            List<List<String>> lists = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                List<String> childList = new ArrayList<>();
                for (int j = 0; j < 10; j++) {
                    childList.add(i + "-" + j);
                }
                lists.add(childList);
            }

            LinkedList<String> linkedList = new LinkedList<>();
            Observable.fromIterable(lists)
                    .flatMap((Function<List<String>, ObservableSource<String>>) strings -> Observable.fromIterable(strings))
                    .filter(s -> s.contains("3"))
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .subscribe(s -> linkedList.add(s)
                            , throwable -> Log.i("===>", throwable.getMessage())
                            , () -> {
                                if (linkedList != null) {
                                    Log.i("==>", "完成");
                                }
                            });
        } else {
            openActivity(TestActivity.class, null);
        }
    }
}