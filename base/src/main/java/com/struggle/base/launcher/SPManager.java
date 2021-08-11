package com.struggle.base.launcher;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/11 14:50
 * @Description TODO
 */
public class SPManager {
    private static Context mContext;
    private static SPManager spManager;
    private final SharedPreferences sp;
    private final SharedPreferences.Editor edit;

    /**
     * 初始化SPManager
     * 注：建议在Application中进行初始化
     *
     * @param context
     */
    public static void init(Context context) {
        mContext = context.getApplicationContext();
    }

    public static SPManager Instance() {
        if (spManager == null) {
            synchronized (SPManager.class) {
                if (spManager == null) {
                    spManager = new SPManager();
                }
            }
        }
        return spManager;
    }

    private SPManager() {
        this.sp = mContext.getSharedPreferences("application", Context.MODE_PRIVATE);
        this.edit = sp.edit();
    }

    //todo--------------------存储数据------------------------

    /**
     * 存储boolean类型数据
     *
     * @param key
     * @param value
     */
    public void putValue(String key, boolean value) {
        edit.putBoolean(key, value);
        edit.apply();
    }

    /**
     * 存储float类型数据
     *
     * @param key
     * @param value
     */
    public void putValue(String key, float value) {
        edit.putFloat(key, value);
        edit.apply();
    }

    /**
     * 存储int类型数据
     *
     * @param key
     * @param value
     */
    public void putValue(String key, int value) {
        edit.putInt(key, value);
        edit.apply();
    }

    /**
     * 存储long类型数据
     *
     * @param key
     * @param value
     */
    public void putValue(String key, long value) {
        edit.putLong(key, value);
        edit.apply();
    }

    /**
     * 存储String类型数据
     *
     * @param key
     * @param value
     */
    public void putValue(String key, String value) {
        edit.putString(key, value);
        edit.apply();
    }

    //todo--------------------获取数据------------------------

    /**
     * 获取Boolean类型的变量值
     *
     * @param key
     * @return
     */
    public boolean getBooleanValue(String key) {
        return sp.getBoolean(key, false);
    }

    /**
     * 获取Float类型的变量值
     *
     * @param key
     * @return
     */
    public float getFloatValue(String key) {
        return sp.getFloat(key, 0);
    }

    /**
     * 获取int类型的变量值
     *
     * @param key
     * @return
     */
    public int getIntegerMenuValue(String key) {
        return sp.getInt(key, -1);
    }

    /**
     * 获取Long类型的变量值
     *
     * @param key
     * @return
     */
    public long getLongValue(String key) {
        return sp.getLong(key, -1);
    }

    /**
     * 获取String类型的变量值
     *
     * @param key
     * @return
     */
    public String getStringValue(String key) {
        return sp.getString(key, "");
    }

    /**
     * 移除某个key值已经对应的值
     *
     * @param key
     */
    public void remove(String key) {
        edit.remove(key);
        edit.apply();
    }

    /**
     * 查询某个key是否已经存在
     *
     * @return
     */
    public boolean contains(String key) {
        return sp.contains(key);
    }

    /**
     * 清除SharedPreferences数所有数据
     */
    public void clear() {
        edit.clear();
        edit.apply();
    }
}
