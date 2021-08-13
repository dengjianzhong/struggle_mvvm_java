package com.struggle.base.base.basics;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.struggle.base.base.model.IViewModel;
import com.struggle.base.widgets.loadding.LoadingDialog;

import org.greenrobot.eventbus.EventBus;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/5 11:47
 * @Description TODO
 */
public abstract class BaseFragment extends Fragment implements IViewModel {
    private LoadingDialog dialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(getLayoutId(), container, false);
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

        //必要做法，防止内存泄漏
        if (dialog != null) {
            dialog.setOnCancelListener(null);
            dialog.setOnDismissListener(null);
            dialog.setOnShowListener(null);
            dialog = null;
        }
    }
}
