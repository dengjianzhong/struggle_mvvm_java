package com.struggle.base.launcher;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/9 15:27
 * @Description TODO
 */
public class UIPix {
    private static Context mContext;

    /**
     * 初始化UIPix
     *
     * @param context
     */
    public static void init(Context context) {
        mContext = context.getApplicationContext();
    }

    /**
     * 得到屏幕的宽度
     *
     * @return the screen width
     */
    public static int getScreenWidth() {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return metrics.widthPixels;
    }

    /**
     * 得到屏幕的高度
     *
     * @return the screen width
     */
    public static int getScreenHeight() {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return metrics.heightPixels;
    }


    /**
     * dip转px
     *
     * @param dipValue the dip value
     * @return the int
     */
    public static int dip2px(float dipValue) {
        if (mContext == null) {
            throw new RuntimeException("IOHandler is not initialized");
        }
        float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
