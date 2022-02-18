package com.struggle.base.launcher;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/8 16:46
 * @Description 运行在指定Handler线程的run代码块
 */
public class IOHandler {
    private static Context mContext;
    private static Handler workHandler;
    private static Handler mainHandler;

    /**
     * 初始化Handler
     * 注：建议在Application中进行初始化
     *
     * @param context
     */
    public static void init(Context context) {
        mContext = context.getApplicationContext();

        initMainHandler();
        initWorkHandler();
    }

    /**
     * 初始化主线程Handler
     */
    private static void initMainHandler() {
        mainHandler = new Handler(Looper.getMainLooper());
    }

    /**
     * 初始化子线程Handler
     */
    private static void initWorkHandler() {
        HandlerThread workThread = new HandlerThread("workThread");
        workThread.start();
        workHandler = new Handler(workThread.getLooper());
    }

    /**
     * 将 runnable添加到消息队列中。 runnable 将在此处理程序附加到的主线程上运行。
     *
     * @param runnable
     */
    public static void postMain(Runnable runnable) {
        if (mainHandler == null) {
            throw new RuntimeException("IOHandler is not initialized");
        }

        postMain(runnable, 0);
    }

    /**
     * 将 runnable添加到消息队列中。 runnable 将在此处理程序附加到的主线程上运行。
     *
     * @param runnable
     */
    public static void postMain(Runnable runnable, long delayMillis) {
        if (mainHandler == null) {
            throw new RuntimeException("IOHandler is not initialized");
        }

        mainHandler.postDelayed(runnable, delayMillis);
    }


    /**
     * 将 runnable添加到消息队列中。 runnable 将在此处理程序附加到的子线程上运行。
     *
     * @param runnable
     */
    public static void postWork(Runnable runnable) {
        if (workHandler == null) {
            throw new RuntimeException("IOHandler is not initialized");
        }

        postWork(runnable, 0);
    }

    /**
     * 将 runnable添加到消息队列中。 runnable 将在此处理程序附加到的子线程上运行。
     *
     * @param runnable
     * @param delayMillis
     */
    public static void postWork(Runnable runnable, long delayMillis) {
        if (workHandler == null) {
            throw new RuntimeException("IOHandler is not initialized");
        }

        workHandler.postDelayed(runnable, delayMillis);
    }

    /**
     * 移除指定消息队列
     *
     * @param runnable
     */
    public static void remove(Runnable runnable) {
        if (workHandler == null) {
            throw new RuntimeException("IOHandler is not initialized");
        }

        workHandler.removeCallbacks(runnable);
    }

    /**
     * 移除所有消息队列
     */
    public static void removeAll() {
        if (workHandler == null) {
            throw new RuntimeException("IOHandler is not initialized");
        }

        workHandler.removeCallbacksAndMessages(null);
    }
}
