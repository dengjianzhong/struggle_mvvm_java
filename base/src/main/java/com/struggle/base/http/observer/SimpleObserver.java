package com.struggle.base.http.observer;

import androidx.annotation.NonNull;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/10 16:06
 * @Description TODO
 */
public class SimpleObserver<T> implements Observer<T> {
    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull T o) {

    }

    @Override
    public void onError(@NonNull Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
