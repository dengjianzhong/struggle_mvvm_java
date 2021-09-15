package com.struggle.base.base.mvp;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.viewbinding.ViewBinding;

import com.struggle.base.app.bean.DataResponse;
import com.struggle.base.app.bean.LoadingBean;
import com.struggle.base.base.basics.BaseActivity;
import com.struggle.base.base.mvp.impl.IView;
import com.struggle.base.launcher.TxToast;
import com.struggle.base.utils.ClassUtil;

import java.lang.reflect.Method;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/23 12:39
 * @Description V层需要继承的activity
 */
public abstract class BaseMVPActivity<VB extends ViewBinding, T extends BasePresenter> extends BaseActivity implements IView {
    protected VB bind;
    protected T presenter;

    {
        Class<T> pClass = (Class<T>) ClassUtil.getParentGeneric(this, 1);
        try {
            presenter = pClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initBinding();
        /**绑定P层*/
        if (presenter != null && !presenter.isAttachView()) {
            presenter.attachView(this);
        }

        super.onCreate(savedInstanceState);
    }

    /**
     * 初始化ViewBinding
     */
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
    protected void onDestroy() {
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
