package com.struggle.base.base.action;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.jaeger.library.StatusBarUtil;
import com.struggle.base.base.basics.BaseActivity;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/5 14:06
 * @Description TODO
 */
public interface ViewModule {
    /**
     * 具体子类实现返回
     */
    Context getContext();

    /**
     * 获取 Activity 对象
     */
    default Activity getActivity() {
        if (getContext() instanceof Activity) {
            return ((Activity) getContext());
        }
        return null;
    }

    /**
     * 跳转页面
     *
     * @param destinationClass
     * @param options
     */
    default void openActivity(Class<? extends BaseActivity> destinationClass, Bundle options) {
        if (getActivity() == null) {
            return;
        }
        Intent intent = new Intent(getActivity(), destinationClass);
        if (options != null) intent.putExtras(options);
        getActivity().startActivity(intent);
    }

    /**
     * 带返回信息的跳转
     *
     * @param destinationClass
     * @param requestCode
     * @param options
     */
    default void openActivity(Class<? extends BaseActivity> destinationClass, int requestCode, Bundle options) {
        if (getActivity() == null) {
            return;
        }
        Intent intent = new Intent(getActivity(), destinationClass);
        if (options != null) intent.putExtras(options);
        getActivity().startActivityForResult(intent, requestCode);
    }

    /**
     * 设置透明状态栏
     */
    default void setTransparentStatusBar() {
        if (getActivity() == null) {
            return;
        }
        getActivity().findViewById(Window.ID_ANDROID_CONTENT).setFitsSystemWindows(true);
        StatusBarUtil.setTransparent(getActivity());
        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }
}
