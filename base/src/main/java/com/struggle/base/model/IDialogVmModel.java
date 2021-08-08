package com.struggle.base.model;

import android.view.View;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/8 15:39
 * @Description TODO
 */
public interface IDialogVmModel {
    void initViewModel();

    void observer();

    View getBindingView();
}
