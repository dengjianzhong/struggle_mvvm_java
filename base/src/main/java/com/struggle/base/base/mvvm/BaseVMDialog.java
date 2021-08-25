package com.struggle.base.base.mvvm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.struggle.base.app.bean.DataResponse;
import com.struggle.base.app.bean.LoadingBean;
import com.struggle.base.base.basics.BaseDialog;
import com.struggle.base.launcher.TxToast;
import com.struggle.base.utils.ClassUtil;

import java.lang.reflect.Method;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/8 22:21
 * @Description TODO
 */
public abstract class BaseVMDialog<VB extends ViewBinding, VM extends BaseViewModel> extends BaseDialog<VB> {

    protected VM viewModel;

    @Override
    public void onViewCreated(@Nullable View view, @Nullable Bundle savedInstanceState) {
        initViewModel();
        initLiveData();
        observer();

        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * 初始化ViewModel
     */
    public void initViewModel() {
        Class<VM> vmClass = (Class<VM>) ClassUtil.getParentGeneric(this, 1);
        viewModel = new ViewModelProvider(this).get(vmClass);
    }

    public void initLiveData() {
        /**数据获取失败观察者*/
        viewModel.rep.messageLiveData.observe(this, (Observer<DataResponse<Object>>) bean -> {
            TxToast.showToast(bean.getMessage());
        });

        /**加载弹窗观察者*/
        viewModel.rep.dialogLiveData.observe(this, (Observer<LoadingBean>) b -> {
            if (b.isShow()) {
                showLoading(b.isCancelable(), b.getContent());
            } else {
                hideLoading();
            }
        });
    }

    protected void observer() {
    }
}
