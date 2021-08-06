package com.struggle.base.base;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.widget.PopupWindow;

import com.struggle.base.model.IPopupModel;
import com.struggle.base.utils.UIUtils;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/6 17:45
 * @Description TODO
 */
public abstract class BasePopupWindow extends PopupWindow implements IPopupModel {

    private Context mContext;
    //获取屏幕参数
    private DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
    private final int dp1;

    public BasePopupWindow(Context context) {
        this.mContext = mContext;
        this.dp1 = UIUtils.dip2px(mContext, 1);
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public int getWidth() {
        return (int) (metrics.widthPixels * 0.8);
    }

    @Override
    public int getHeight() {
        return -2;
    }

    @Override
    public int getWindowElevation() {
        return dp1 * 5;
    }
}
