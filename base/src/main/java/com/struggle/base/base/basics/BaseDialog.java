package com.struggle.base.base.basics;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.struggle.base.R;
import com.struggle.base.model.IDialogModel;

import org.greenrobot.eventbus.EventBus;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/5 11:47
 * @Description TODO
 */
public abstract class BaseDialog extends DialogFragment implements IDialogModel {

    //获取屏幕参数
    protected DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();

    @Override
    public int getTheme() {
        return R.style.alertDialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(getLayoutId(), container, false);
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
        getActivity().findViewById(Window.ID_ANDROID_CONTENT).setOnClickListener(v -> hideSoftKeyboard());
    }

    /**
     * 隐藏软键盘
     */
    private void hideSoftKeyboard() {
        // 隐藏软键盘，避免软键盘引发的内存泄露
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager manager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (manager != null && manager.isActive(view)) {
                manager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    /**
     * 跳转页面
     *
     * @param destinationClass
     * @param options
     */
    public void openActivity(Class<? extends BaseFragment> destinationClass, Bundle options) {
        Intent intent = new Intent(getContext(), destinationClass);
        if (options != null) intent.putExtras(options);
        startActivity(intent);
    }

    /**
     * 带返回信息的跳转
     *
     * @param destinationClass
     * @param requestCode
     * @param options
     */
    public void openActivity(Class<? extends BaseFragment> destinationClass, int requestCode, Bundle options) {
        Intent intent = new Intent(getContext(), destinationClass);
        if (options != null) intent.putExtras(options);
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (userEventBus()) {
            EventBus.getDefault().unregister(this);
        }
    }
}
