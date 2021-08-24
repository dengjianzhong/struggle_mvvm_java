package com.struggle.base.base.mvvm;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.struggle.base.app.bean.DataResponse;
import com.struggle.base.app.bean.LoadingBean;
import com.struggle.base.app.constants.AppConstants;
import com.struggle.base.http.GoHttp;
import com.struggle.base.http.observer.OnSubscribeListener;
import com.struggle.base.utils.ClassUtil;
import com.struggle.base.utils.NetUtil;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/13 14:40
 * @Description TODO
 */
public abstract class BaseRepository<T> {

    /**
     * 显示错误相关信息
     */
    public MutableLiveData<DataResponse<Object>> messageLiveData = new MutableLiveData();

    /**
     * 显示/隐藏加载弹窗
     * <p>
     * true 显示加载弹窗
     * false 隐藏加载弹窗
     */
    public MutableLiveData<LoadingBean> dialogLiveData = new MutableLiveData();

    /**
     * 创建API请求对象
     */
    protected T api;

    {
        Class<T> apiClass = (Class<T>) ClassUtil.getParentGeneric(this, 0);
        api = GoHttp.Instance().create(apiClass);
    }

    /**
     * 开始请求网络
     *
     * @param <R>
     * @param observable
     * @param listener
     */
    protected <R> void launch(Observable<DataResponse<R>> observable,
                              OnSubscribeListener<R> listener) {
        launch(observable, true, "加载中", false, listener);
    }

    /**
     * 开始请求网络
     *
     * @param <R>
     * @param observable
     * @param useLoading 控制loading显示/隐藏
     * @param listener
     */
    protected <R> void launch(Observable<DataResponse<R>> observable,
                              boolean useLoading,
                              OnSubscribeListener<R> listener) {
        launch(observable, useLoading, "加载中", false, listener);
    }

    /**
     * 开始请求网络
     *
     * @param <R>
     * @param observable
     * @param content    loading显示内容
     * @param listener
     */
    protected <R> void launch(Observable<DataResponse<R>> observable,
                              String content,
                              OnSubscribeListener<R> listener) {
        launch(observable, true, content, false, listener);
    }

    /**
     * 开始请求网络
     *
     * @param <R>
     * @param observable
     * @param useLoading 控制loading显示/隐藏
     * @param content    loading显示内容
     * @param listener
     */
    protected <R> void launch(Observable<DataResponse<R>> observable,
                              boolean useLoading,
                              String content,
                              OnSubscribeListener<R> listener) {
        launch(observable, useLoading, content, false, listener);
    }

    /**
     * 开始请求网络
     *
     * @param <R>
     * @param observable
     * @param listener
     * @param useLoading  是否显示加载弹窗
     * @param content     加载loading的内容
     * @param mCancelable 是否可以取消loading
     */
    protected <R> void launch(Observable<DataResponse<R>> observable,
                              boolean useLoading,
                              String content,
                              boolean mCancelable,
                              OnSubscribeListener<R> listener) {

        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataResponse<R>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        if (useLoading) {
                            LoadingBean bean = new LoadingBean(content, mCancelable, true);
                            dialogLiveData.postValue(bean);//显示加载弹窗
                        }
                    }

                    @Override
                    public void onNext(@NonNull DataResponse<R> response) {
                        if (response.getStatus() == AppConstants.SUCCESS_CODE) {
                            listener.onSuccess(response.getData());
                            return;
                        }
                        listener.onError(response);
                        messageLiveData.postValue((DataResponse<Object>) response);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        DataResponse<Object> response = new DataResponse<>();
                        response.setMessage(NetUtil.analyzeException(e));

                        listener.onError(response);
                        messageLiveData.postValue(response);

                        if (useLoading) {
                            dialogLiveData.postValue(new LoadingBean("", false, false));//隐藏加载弹窗
                        }
                    }

                    @Override
                    public void onComplete() {
                        if (useLoading) {
                            dialogLiveData.postValue(new LoadingBean("", false, false));//隐藏加载弹窗
                        }
                    }
                });
    }
}
