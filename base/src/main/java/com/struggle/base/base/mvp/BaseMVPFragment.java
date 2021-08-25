package com.struggle.base.base.mvp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import com.struggle.base.app.bean.DataResponse;
import com.struggle.base.app.bean.LoadingBean;
import com.struggle.base.base.basics.BaseFragment;
import com.struggle.base.base.mvp.impl.IView;
import com.struggle.base.launcher.TxToast;
import com.struggle.base.utils.ClassUtil;

import java.lang.reflect.Method;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/23 12:39
 * @Description V层需要继承的Fragment
 */
public abstract class BaseMVPFragment<VB extends ViewBinding, T extends BasePresenter> extends BaseFragment implements IView {
    protected VB bind;
    protected T presenter;

    {
        Class<T> pClass = (Class<T>) ClassUtil.getParentGeneric(this, 0);
        try {
            presenter = pClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        /**绑定P层*/
        if (presenter != null && !presenter.isAttachView()) {
            presenter.attachView(this);
        }

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return getBindingView();
    }

    /**
     * 初始化ViewBinding
     *
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

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (presenter != null) {
            presenter.detachView();
        }
    }

    //---V层回调统一处理----

    @Override
    public void showLoad(LoadingBean bean) {
        showLoading(bean.isCancelable(), bean.getContent());
    }

    @Override
    public void hideLoad() {
        hideLoading();
    }

    @Override
    public void onMessage(DataResponse bean) {
        TxToast.showToast(bean.getMessage());
    }
}
