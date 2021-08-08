package com.struggle.base.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.struggle.base.base.basics.BaseDialog;
import com.struggle.base.launcher.TxToast;
import com.struggle.base.model.IDialogVmModel;
import com.struggle.base.utils.ClassUtil;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/8 22:21
 * @Description TODO
 */
public abstract class BaseVMDialog<VB extends ViewBinding, VM extends BaseViewModel>
        extends BaseDialog implements IDialogVmModel {

    protected VM viewModel;
    protected VB bind;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return getBindingView();
    }

    @Override
    public void onViewCreated(@Nullable View view, @Nullable Bundle savedInstanceState) {
        initViewModel();
        observer();

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View getBindingView() {
        Class<VB> vbClass = (Class<VB>) ClassUtil.getParentGeneric(this, 0);
        try {
            Method inflate = vbClass.getDeclaredMethod("inflate", LayoutInflater.class);
            bind = (VB) inflate.invoke(null, getLayoutInflater());

            return bind.getRoot();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void initViewModel() {
        Class<VM> vmClass = (Class<VM>) ClassUtil.getParentGeneric(this, 1);
        viewModel = new ViewModelProvider(this).get(vmClass);
    }

    @Override
    public void observer() {
        /**数据获取失败观察者*/
        viewModel.messageLiveData.observe(this, bean -> {
            TxToast.showToast(bean.getMessage());
        });

        /**加载弹窗观察者*/
        viewModel.dialogLiveData.observe(this, aBoolean -> {

        });
    }
}
