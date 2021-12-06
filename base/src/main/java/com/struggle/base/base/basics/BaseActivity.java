package com.struggle.base.base.basics;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import com.struggle.base.base.plugins.KeyboardPlugin;
import com.struggle.base.base.plugins.ViewPlugin;
import com.struggle.base.utils.ActivityManager;
import com.struggle.base.utils.ClassUtil;
import com.struggle.base.widgets.loadding.LoadingDialog;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Method;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/5 11:47
 * @Description TODO
 */
public abstract class BaseActivity<VB extends ViewBinding> extends AppCompatActivity implements ViewPlugin, KeyboardPlugin {

    private LoadingDialog dialog;
    protected VB bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityManager.getInstance().addActivity(this);
        //是否禁止横屏
        if (disableHorizontalScreen()) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        //是否开启EventBus事件总线
        if (userEventBus()) {
            EventBus.getDefault().register(this);
        }

        initBinding();
        initView();
        initData();
        initEvent();
        initSoftKeyboard();
    }

    protected void initView() {
    }

    protected void initData() {
    }

    protected void initEvent() {
    }

    @Override
    public Context getContext() {
        return this;
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

    /**
     * 是否开启EventBus事件总线
     * <p>
     * false 默认关闭
     */
    protected boolean userEventBus() {
        return false;
    }

    /**
     * 禁止横屏
     * <p>
     * true 默认开启
     */
    protected boolean disableHorizontalScreen() {
        return true;
    }

    /**
     * 初始化软键盘
     * <p>
     * 点击外部隐藏软键盘，提升用户体验
     */
    protected void initSoftKeyboard() {
        findViewById(Window.ID_ANDROID_CONTENT).setOnClickListener(v -> hideKeyboard(v));
    }

    /**
     * 显示加载弹窗
     *
     * @param mCancelable 设置弹窗是否可以取消
     * @param content     弹窗提示文本
     */
    protected void showLoading(boolean mCancelable, String content) {
        if (dialog == null) {
            dialog = new LoadingDialog(this, content);
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
    protected void onDestroy() {
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
        ActivityManager.getInstance().removeActivity(this);
    }
}
