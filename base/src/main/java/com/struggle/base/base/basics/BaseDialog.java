package com.struggle.base.base.basics;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewbinding.ViewBinding;

import com.struggle.base.R;
import com.struggle.base.base.plugins.DialogPlugin;
import com.struggle.base.base.plugins.KeyboardPlugin;
import com.struggle.base.utils.ClassUtil;
import com.struggle.base.widgets.loadding.LoadingDialog;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Method;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/5 11:47
 * @Description TODO
 */
public abstract class BaseDialog<VB extends ViewBinding> extends DialogFragment implements DialogPlugin, KeyboardPlugin {

    //获取屏幕参数
    protected DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
    private LoadingDialog dialog;
    protected VB bind;

    @Override
    public int getTheme() {
        return R.style.alertDialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return getBindingView();
    }

    @Override
    public void onViewCreated(@Nullable View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (userEventBus()) {
            EventBus.getDefault().register(this);
        }

        initView();
        initData();
        initEvent();
        initSoftKeyboard();
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
    public void onStart() {
        super.onStart();

        Window window;
        {
            window = getDialog().getWindow();
            window.setWindowAnimations(getAnimationStyle());
            window.setLayout(getWidth(), getHeight());
            window.setGravity(getGravity());
        }
    }

    public void show(@NonNull FragmentManager manager) {
        super.show(manager, "dialog");
    }

    @Override
    public int getGravity() {
        return Gravity.CENTER;
    }

    @Override
    public int getHeight() {
        return -2;
    }

    @Override
    public int getWidth() {
        return (int) (metrics.widthPixels * 0.8);
    }

    /**
     * 初始化软键盘
     * <p>
     * 点击外部隐藏软键盘，提升用户体验
     */
    protected void initSoftKeyboard() {
        getDialog().getWindow().findViewById(Window.ID_ANDROID_CONTENT).setOnClickListener(v -> hideKeyboard(v));
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
    public void onDestroyView() {
        if (getDialog() != null) {
            getDialog().setOnCancelListener(null);
        }
        super.onDestroyView();
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
