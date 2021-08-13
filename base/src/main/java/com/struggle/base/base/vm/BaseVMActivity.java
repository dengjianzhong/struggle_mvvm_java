package com.struggle.base.base.vm;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.struggle.base.app.bean.DataResponse;
import com.struggle.base.base.basics.BaseActivity;
import com.struggle.base.base.model.IActivityVmModel;
import com.struggle.base.launcher.TxToast;
import com.struggle.base.utils.ClassUtil;

import java.lang.reflect.Method;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/8 15:37
 * @Description TODO
 */
public abstract class BaseVMActivity<VB extends ViewBinding, VM extends BaseViewModel>
        extends BaseActivity implements IActivityVmModel {

    protected VM viewModel;
    protected VB bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initBinding();
        initViewModel();
        initLiveData();
        observer();

        super.onCreate(savedInstanceState);
    }

    @Override
    public void initBinding() {
        Class<VB> vbClass = (Class<VB>) ClassUtil.getParentGeneric(this, 0);
        try {
            Method inflate = vbClass.getDeclaredMethod("inflate", LayoutInflater.class);
            bind = (VB) inflate.invoke(null, getLayoutInflater());

            setContentView(bind.getRoot());
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    /**是否禁用setContentView，由于跟DataBinding存在冲突故此做法*/
    @Override
    public boolean disableSetView() {
        return false;
    }
}
