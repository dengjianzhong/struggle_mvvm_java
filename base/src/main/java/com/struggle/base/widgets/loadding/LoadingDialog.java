package com.struggle.base.widgets.loadding;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.StyleRes;
import androidx.appcompat.app.AppCompatDialog;

import com.struggle.base.R;
import com.struggle.base.widgets.loadding.renderer.DaisyView;
import com.struggle.base.widgets.loadding.renderer.IMessageLoader;
import com.struggle.base.widgets.loadding.renderer.LoadingView;

/**
 * 迷你loading加载
 *
 * @author XUE
 * @since 2019/4/9 14:16
 */
public class LoadingDialog extends AppCompatDialog implements IMessageLoader {
    private String loadingTipText = "加载中...";
    private TextView mTvTipMessage;
    private DaisyView mLoadingView;
    private LoadingView contentView;

    public LoadingDialog(Context context) {
        super(context, R.style.alertLightDialog);
        initView(loadingTipText);
    }

    public LoadingDialog(Context context, String tipMessage) {
        super(context, R.style.alertLightDialog);
        initView(tipMessage);
    }

    public LoadingDialog(Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        initView(loadingTipText);
    }

    public LoadingDialog(Context context, @StyleRes int themeResId, String tipMessage) {
        super(context, themeResId);
        initView(tipMessage);
    }

    private void initView(String tipMessage) {
        contentView = new LoadingView(getContext());
        setContentView(contentView);

        mTvTipMessage = findViewById(contentView.CONTENT_ID);
        mLoadingView = findViewById(contentView.LOADING_ID);

        updateMessage(tipMessage);
    }

    /**
     * 更新提示信息
     *
     * @param tipMessage
     * @return
     */
    @Override
    public void updateMessage(String tipMessage) {
        if (mTvTipMessage != null) {
            if (!TextUtils.isEmpty(tipMessage)) {
                mTvTipMessage.setText(tipMessage);
                mTvTipMessage.setVisibility(View.VISIBLE);
            } else {
                mTvTipMessage.setText("");
                mTvTipMessage.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 显示加载
     */
    @Override
    public void show() {
        super.show();
        if (mLoadingView != null) {
            mLoadingView.start();
        }
    }

    @Override
    public void cancel() {
        super.cancel();
        if (mLoadingView != null) {
            mLoadingView.stop();
        }
    }

    /**
     * 隐藏加载
     */
    @Override
    public void dismiss() {
        super.dismiss();
        if (mLoadingView != null) {
            mLoadingView.stop();
        }
    }

    /**
     * 资源释放
     */
    @Override
    public void recycle() {

    }

    /**
     * 是否在加载
     *
     * @return
     */
    @Override
    public boolean isLoading() {
        return isShowing();
    }

    /**
     * 设置是否可取消
     *
     * @param flag
     */
    @Override
    public void setCancelable(boolean flag) {
        super.setCancelable(flag);
    }
}
