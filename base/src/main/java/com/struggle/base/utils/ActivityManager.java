package com.struggle.base.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/8 13:30
 * @Description Activity管理器
 */
public class ActivityManager {
    private static ActivityManager manager;
    private static List<Activity> activityList;

    private ActivityManager() {
    }

    /**
     * Gets instance.
     *
     * @return the ActivityCollectorHelper instance
     */
    public static ActivityManager getInstance() {
        if (manager == null) {
            synchronized (ActivityManager.class) {
                if (manager == null) {
                    manager = new ActivityManager();
                }
                activityList = new ArrayList<>();
            }
        }

        return manager;
    }

    /**
     * 添加Activity
     *
     * @param activity the activity
     */
    public void addActivity(Activity activity) {
        if (activity != null) {
            activityList.add(activity);
        }
    }

    /**
     * 得到容器里面的所有Activity
     *
     * @return the activity list
     */
    public List<Activity> getActivityList() {

        return activityList;
    }

    /**
     * 关闭所有activity
     */
    public void finishAll() {
        for (Activity activity : activityList) {
            if (activity != null && !activity.isFinishing()) {
                activity.finish();
            }
        }
        activityList.clear();
    }

    /**
     * 关闭指定activity
     *
     * @param activity the activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null && !activity.isFinishing()) {
            activity.finish();
            activityList.remove(activity);
        }
    }

    /**
     * 移除指定activity
     *
     * @param activity
     */
    public void removeActivity(Activity activity) {
        activityList.remove(activity);
    }
}
