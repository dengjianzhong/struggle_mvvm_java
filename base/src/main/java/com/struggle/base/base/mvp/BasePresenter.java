package com.struggle.base.base.mvp;

import com.struggle.base.base.mvp.impl.IPresenter;
import com.struggle.base.base.mvp.impl.IView;
import com.struggle.base.utils.ClassUtil;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/23 12:39
 * @Description P层需要继承
 */
public abstract class BasePresenter<V extends IView, M extends BaseModel> implements IPresenter<V> {
    private WeakReference<V> weakReference;
    public V mView;
    /**
     * 动态创建M层对象
     **/
    protected M model;

    {
        Class<M> pClass = (Class<M>) ClassUtil.getParentGeneric(this, 1);
        try {
            model = pClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void attachView(V view) {
        weakReference = new WeakReference(view);

        MvpViewHandler viewHandler = new MvpViewHandler();
        mView = (V) Proxy.newProxyInstance(view.getClass().getClassLoader(), view.getClass().getInterfaces(), viewHandler);

        model.attachView(mView);
    }

    @Override
    public void detachView() {
        if (isAttachView()) {
            weakReference.clear();
            weakReference = null;
        }
    }

    @Override
    public boolean isAttachView() {
        return weakReference != null && weakReference.get() != null;
    }

    /**
     * 利用动态代理模式
     * <p>
     * 防止空指针
     */
    private class MvpViewHandler implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            //如果V层没被销毁, 执行V层的方法.
            if (isAttachView()) {
                return method.invoke(weakReference.get(), args);
            }
            //P层不需要关注V层的返回值
            return null;
        }
    }
}
