package com.struggle.base.base.basics;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.PopupWindow;

import com.struggle.base.base.action.PopupModule;
import com.struggle.base.launcher.UIPix;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/6 17:45
 * @Description TODO
 */
public abstract class BasePopupWindow extends PopupWindow implements PopupModule {

    private final Context mContext;
    //获取屏幕参数
    protected DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
    private final int dp1;

    public BasePopupWindow(Context context) {
        this.mContext = context;
        this.dp1 = UIPix.dip2px(1);

        initView();
        initData();
        initEvent();
    }

    @Override
    public void initView() {
        setContentView(LayoutInflater.from(mContext).inflate(getLayoutId(), new FrameLayout(mContext), false));

        setOutsideTouchable(isCanTCancel());
        setWidth(getWindowWidth());
        setHeight(getWindowHeight());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setElevation(getWindowElevation());
        }
    }

    @Override
    public int getWindowElevation() {
        return dp1 * 5;
    }
}
