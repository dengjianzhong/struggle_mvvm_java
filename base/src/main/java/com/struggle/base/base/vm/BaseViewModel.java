package com.struggle.base.base.vm;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.struggle.base.app.bean.DataResponse;
import com.struggle.base.http.GoHttp;
import com.struggle.base.http.observer.OnCompleteListener;
import com.struggle.base.utils.ClassUtil;
import com.struggle.base.utils.NetUtil;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/8 15:18
 * @Description TODO
 */
public abstract class BaseViewModel<T> extends ViewModel {
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
    protected <R> void launch(Observable<DataResponse<R>> observable, OnCompleteListener<R> listener) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataResponse<R>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        //显示加载弹窗
                        dialogLiveData.postValue(true);
                    }

                    @Override
                    public void onNext(@NonNull DataResponse<R> response) {
                        if (response.getStatus() == 100) {
                            listener.onSuccess(response.getData());
                            return;
                        }
                        messageLiveData.postValue((DataResponse<Object>) response);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        DataResponse<Object> response = new DataResponse<>();
                        response.setMessage(NetUtil.analyzeException(e));
                        messageLiveData.postValue(response);

                        //隐藏加载弹窗
                        dialogLiveData.postValue(false);
                    }

                    @Override
                    public void onComplete() {
                        //隐藏加载弹窗
                        dialogLiveData.postValue(false);
                    }
                });
    }
}

