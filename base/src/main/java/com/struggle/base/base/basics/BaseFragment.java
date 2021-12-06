package com.struggle.base.base.basics;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import com.struggle.base.base.plugins.ViewPlugin;
import com.struggle.base.utils.ClassUtil;
import com.struggle.base.widgets.loadding.LoadingDialog;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Method;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/5 11:47
 * @Description TODO
 */
public abstract class BaseFragment<VB extends ViewBinding> extends Fragment implements ViewPlugin {

    private LoadingDialog dialog;
    protected VB bind;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return getBindingView();
    }

    @Override
    public void onViewCreated(@Nullable View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //是否开启EventBus事件总线
        if (userEventBus()) {
            EventBus.getDefault().register(this);
        }

        initView();
        initData();
        initEvent();
    }

    protected void initView() {
    }

    protected void initData() {
    }

    protected void initEvent() {
    }

    /**
     * 初始化ViewBinding
     * @return
     */
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

    /**
     * 是否开启EventBus事件总线
     * <p>
     * false 默认关闭
     */
    protected boolean userEventBus() {
        return false;
    }

    @Override
    public Context getContext() {
        return getActivity();
    }

    /**
     * 显示加载弹窗
     *
     * @param mCancelable 设置弹窗是否可以取消
     * @param content     弹窗提示文本
     */
    protected void showLoading(boolean mCancelable, String content) {
        if (dialog == null) {
            dialog = new LoadingDialog(getContext(), content);
        }
        dialog.setCancelable(mCancelable);
        dialog.show();
    }

    /**
     * 隐藏加载弹窗
     */
    protected void hideLoading() {
        if (dialog != null && dialog.isShowing()) {
            dialog.cancel();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (userEventBus()) {
            EventBus.getDefault().unregister(this);
        }

        //必要做法，防止内存泄漏
        if (dialog != null) {
            dialog.setOnCancelListener(null);
            dialog.setOnDismissListener(null);
            dialog.setOnShowListener(null);
            dialog = null;
        }
    }
}
