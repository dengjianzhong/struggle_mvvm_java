package com.struggle.base;

import android.app.Application;

import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.struggle.base.launcher.IOHandler;
import com.struggle.base.launcher.SPManager;
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
        initSmartRefresh();
    }

    private void init() {
        /**初始化Toast*/
        TxToast.init(this);

        /**初始化handler线程*/
        IOHandler.init(this);

        /**初始化屏幕参数*/
        UIPix.init(this);

        /**初始化SharedPreferences*/
        SPManager.init(this);
    }

    /**
     * 初始化全局智能刷新控件
     */
    private void initSmartRefresh() {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {
            layout.setPrimaryColorsId(R.color.color_00000000); //全局设置主题颜色
            return new ClassicsHeader(context)
                    .setDrawableSize(getResources().getDimension(R.dimen.dp_5))
                    .setDrawableMarginRight(getResources().getDimension(R.dimen.dp_5))
                    .setTextSizeTitle(getResources().getDimension(R.dimen.dp_4_5))
                    .setEnableLastTime(false); //.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header;
        });

        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) ->
                new ClassicsFooter(context)
                        .setDrawableSize(getResources().getDimension(R.dimen.dp_5))
                        .setDrawableMarginRight(getResources().getDimension(R.dimen.dp_5))
                        .setTextSizeTitle(getResources().getDimension(R.dimen.dp_4_5))
                        .setFinishDuration(0));
    }
}
