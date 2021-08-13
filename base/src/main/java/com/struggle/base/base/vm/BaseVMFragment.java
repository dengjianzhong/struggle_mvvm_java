package com.struggle.base.base.vm;

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
import com.struggle.base.base.basics.BaseFragment;
import com.struggle.base.base.model.IFragmentVmModel;
import com.struggle.base.launcher.TxToast;
import com.struggle.base.utils.ClassUtil;

import java.lang.reflect.Method;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/8 22:05
 * @Description TODO
 */
public abstract class BaseVMFragment<VB extends ViewBinding, VM extends BaseViewModel>
        extends BaseFragment implements IFragmentVmModel {

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
        initLiveData();
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
    public void initLiveData() {
        /**数据获取失败观察者*/
        viewModel.messageLiveData.observe(this, (Observer<DataResponse<Object>>) bean -> {
            TxToast.showToast(bean.getMessage());
        });

        /**加载弹窗观察者*/
        viewModel.dialogLiveData.observe(this, (Observer<Boolean>)b -> {
            if (b){
                showLoading(false,"加载中...");
            }else {
                hideLoading();
            }
        });
    }
}
