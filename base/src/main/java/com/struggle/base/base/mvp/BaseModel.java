package com.struggle.base.base.mvp;

import androidx.annotation.NonNull;

import com.struggle.base.app.bean.DataResponse;
import com.struggle.base.base.mvp.impl.IView;
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
 * @CreateTime 2021/8/23 14:05
 * @Description TODO
 */
public abstract class BaseModel<T> {
    public IView mView;
    /**
     * 创建API请求对象
     */
    protected T api;

    {
        Class<T> apiClass = (Class<T>) ClassUtil.getParentGeneric(this, 0);
        api = GoHttp.Instance().create(apiClass);
    }

    /**
     * 与V层绑定
     *
     * @param mView
     */
    public void attachView(IView mView) {
        this.mView = mView;
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
        launch(observable, true, listener);
    }

    /**
     * 开始请求网络
     *
     * @param <R>
     * @param observable
     * @param listener
     * @param useLoading 是否显示加载弹窗
     */
    protected <R> void launch(Observable<DataResponse<R>> observable,
                              boolean useLoading,
                              OnSubscribeListener<R> listener) {

        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataResponse<R>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        if (useLoading) {
                            mView.showLoad();
                        }
                    }

                    @Override
                    public void onNext(@NonNull DataResponse<R> response) {
                        if (response.getStatus() == 100) {
                            listener.onSuccess(response.getData());
                            return;
                        }
                        listener.onError(response);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        DataResponse<Object> response = new DataResponse<>();
                        response.setMessage(NetUtil.analyzeException(e));

                        listener.onError(response);
                        if (useLoading) {
                            mView.hideLoad();
                        }
                    }

                    @Override
                    public void onComplete() {
                        if (useLoading) {
                            mView.hideLoad();
                        }
                    }
                });
    }
}
