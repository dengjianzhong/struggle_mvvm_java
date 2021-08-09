package com.struggle.base;

import android.app.Application;

import com.struggle.base.launcher.IOHandler;
import com.struggle.base.launcher.TxToast;
import com.struggle.base.launcher.UIPix;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/5 11:43
 * @Description TODO
 */
public class BaseApp extends Application {
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

    private void init() {
        /**初始化Toast*/
        TxToast.init(this);

        /**初始化handler线程*/
        IOHandler.init(this);

        /**初始化屏幕参数*/
        UIPix.init(this);
    }
}
