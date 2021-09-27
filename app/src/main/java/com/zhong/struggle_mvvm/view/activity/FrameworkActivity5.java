package com.zhong.struggle_mvvm.view.activity;

import android.util.Log;
import android.view.View;

import com.contrarywind.adapter.WheelAdapter;
import com.contrarywind.view.WheelView;
import com.struggle.base.base.basics.BaseActivity;
import com.zhong.struggle_mvvm.R;
import com.zhong.struggle_mvvm.databinding.ActivityFramework5Binding;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/6 17:39
 * @Description RxJava功能演示(3)
 */
public class FrameworkActivity5 extends BaseActivity<ActivityFramework5Binding> {

    @Override
    protected void initView() {
        setTransparentStatusBar();

        List<Integer> dataList = getSingleListSourceData();//test

        bind.wheelView.setDividerType(WheelView.DividerType.CIRCLE);
        bind.wheelView.setCyclic(false);
        bind.wheelView.setAdapter(new WheelAdapter<Integer>() {
            @Override
            public int getItemsCount() {
                return dataList.size();
            }

            @Override
            public Integer getItem(int index) {
                return dataList.get(index);
            }

            @Override
            public int indexOf(Integer o) {
                return dataList.indexOf(o);
            }
        });
    }

    @Override
    protected void initEvent() {
        super.initEvent();

        bind.wheelView.setOnItemSelectedListener(index -> {
            Log.i("===>", String.format("当前数据:%d", index));
        });
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt1:
                Observable.fromArray(getArraySourceData())
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(s -> {
                            Log.i("===>", String.format("fromArray:%s", s));
                        });
                break;
            case R.id.bt2:
                Observable.fromIterable(getSingleListSourceData())
                        .filter(s -> s % 2 == 0)
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(s -> Log.i("===>", String.format("fromIterable:%s", s)));
                break;
            case R.id.bt3:
                Observable.fromIterable(getTwoListSourceData())
                        .concatMap((Function<List<String>, ObservableSource<String>>) strings -> Observable.fromIterable(strings))
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .subscribe(s -> {
                            Log.i("===>", String.format("flatMap:%s", s));
                        });
                break;
            case R.id.bt4:
                Observable.zip(getObservables(), objects -> {
                    StringBuffer stringBuffer = new StringBuffer();
                    for (Object o : objects) {
                        stringBuffer.append(o);
                    }
                    return stringBuffer.toString();
                })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(s -> {
                            Log.i("===>", String.valueOf(s));
                        });
                break;
            case R.id.bt5:
                Observable.merge(getObservables())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(integer -> Log.i("===>", String.format("%d", integer))
                                , throwable -> Log.i("===>", "错误中断")
                                , () -> Log.i("===>", "执行完成")
                                , disposable -> Log.i("===>", "开始执行"));
                break;
        }
    }

    /**
     * 获取双层列表数据源
     *
     * @return
     */
    private List<List<String>> getTwoListSourceData() {
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

    /**
     * 获取单层列表数据源
     *
     * @return
     */
    private List<Integer> getSingleListSourceData() {
        List<Integer> lists = new ArrayList<>();
        for (int i = 9; i >= 0; i--) {
            lists.add(i);
        }

        return lists;
    }

    /**
     * 获取数组数据源
     *
     * @return
     */
    private String[] getArraySourceData() {
        return new String[]{"a", "b", "c", "d", "e"};
    }

    /**
     * 获取Observable集合
     *
     * @return
     */
    private List<Observable<Integer>> getObservables() {
        List<Observable<Integer>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(Observable.just(i)
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io()));
        }
        return list;
    }
}