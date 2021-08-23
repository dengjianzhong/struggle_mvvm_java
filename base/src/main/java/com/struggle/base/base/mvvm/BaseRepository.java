package com.struggle.base.base.mvvm;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.struggle.base.app.bean.DataResponse;
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
     * 加载错误相关信息
     */
    public MutableLiveData<DataResponse<Object>> messageLiveData = new MutableLiveData();

    /**
     * 显示/隐藏加载弹窗
     * <p>
     * true 显示加载弹窗
     * false 隐藏加载弹窗
     */
    public MutableLiveData<Boolean> dialogLiveData = new MutableLiveData();

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
                            dialogLiveData.postValue(true);//显示加载弹窗
                        }
                    }

                    @Override
                    public void onNext(@NonNull DataResponse<R> response) {
                        if (response.getStatus() == 100) {
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
                            dialogLiveData.postValue(false);//隐藏加载弹窗
                        }
                    }

                    @Override
                    public void onComplete() {
                        if (useLoading) {
                            dialogLiveData.postValue(false);//隐藏加载弹窗
                        }
                    }
                });
    }
}
