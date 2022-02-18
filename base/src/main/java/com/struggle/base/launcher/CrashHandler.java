package com.struggle.base.launcher;

import android.app.Application;
import android.os.Environment;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @Author 邓建忠
 * @CreateTime 2021/12/27 16:59
 * @Description 捕获全局异常
 */
public final class CrashHandler implements Thread.UncaughtExceptionHandler {

    private final Application application;
    private final Thread.UncaughtExceptionHandler mOldHandler;

    /**
     * 注册 Crash 监听
     *
     * @param application
     */
    public static void register(Application application) {
        Thread.setDefaultUncaughtExceptionHandler(new CrashHandler(application));
    }

    public CrashHandler(Application application) {
        this.application = application;

        mOldHandler = Thread.getDefaultUncaughtExceptionHandler();
        if (getClass().getName().equals(mOldHandler.getClass().getName())) {
            // 请不要重复注册 Crash 监听
            throw new IllegalStateException("are you ok?");
        }
    }

    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
        saveCrashInfoToFile(e);
        mOldHandler.uncaughtException(t, e);
    }

    /**
     * 保存错误信息到文件中
     *
     * @param throwable
     */
    private void saveCrashInfoToFile(Throwable throwable) {
        TxToast.showToast("很抱歉，程序出现异常，即将退出");
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        throwable.printStackTrace(printWriter);
        printWriter.close();

        long timeMillis = System.currentTimeMillis();
        //错误日志文件名称
        String fileName = String.format("crash-%d.log", timeMillis);
        //默认存储在应用内部私有文件目录
        String storagePath = String.format("%s/log/", application.getCacheDir().getAbsolutePath());
        //判断sd卡可正常使用，如果正常存储在应用的外部私有文件目录
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //文件存储位置
            storagePath = String.format("%s/log/", application.getExternalCacheDir().getAbsolutePath());
        }

        try {
            File fl = new File(storagePath);
            if (!fl.exists()) {
                fl.mkdirs();
            }

            FileOutputStream fileOutputStream = new FileOutputStream(String.format("%s%s", storagePath, fileName));
            fileOutputStream.write(stringWriter.toString().getBytes());
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
