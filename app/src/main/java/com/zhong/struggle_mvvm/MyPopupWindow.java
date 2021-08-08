package com.zhong.struggle_mvvm;

import android.content.Context;

import com.struggle.base.base.basics.BasePopupWindow;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/8 14:59
 * @Description TODO
 */
public class MyPopupWindow extends BasePopupWindow {
    public MyPopupWindow(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_test;
    }

    @Override
    public int getWindowWidth() {
        return (int) (metrics.widthPixels * 0.8);
    }

    @Override
    public int getWindowHeight() {
        return -2;
    }

    @Override
    public boolean isCanTCancel() {
        return true;
    }
}
