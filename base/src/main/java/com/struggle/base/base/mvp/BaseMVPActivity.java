package com.struggle.base.base.mvp;

import android.os.Bundle;

import com.struggle.base.app.bean.DataResponse;
import com.struggle.base.app.bean.LoadingBean;
import com.struggle.base.base.basics.BaseActivity;
import com.struggle.base.base.mvp.impl.IView;
import com.struggle.base.launcher.TxToast;
import com.struggle.base.utils.ClassUtil;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/23 12:39
 * @Description TODO
 */
public abstract class BaseMVPActivity<T extends BasePresenter> extends BaseActivity implements IView {
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
    protected void onCreate(Bundle savedInstanceState) {
        /**绑定P层*/
        if (presenter != null && !presenter.isAttachView()) {
            presenter.attachView(this);
        }

        super.onCreate(savedInstanceState);
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