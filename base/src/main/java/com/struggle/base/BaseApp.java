package com.struggle.base;

import android.app.Application;

import com.struggle.base.launcher.IOHandler;
import com.struggle.base.model.IAppModel;
import com.struggle.base.launcher.TxToast;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/5 11:43
 * @Description TODO
 */
public class BaseApp extends Application implements IAppModel {
    private static Application application;

    /***
     * 获取应用的上下文对象
     *
     * @return
     */
    public static Application getContext() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        application = this;

        init();
    }

    @Override
    public void init() {
        TxToast.init(this);
        IOHandler.init(this);
    }
}
