package com.zhong.struggle_mvvm.view.widgets.camera;

import android.content.Context;
import android.os.Environment;

import java.io.Closeable;
import java.io.IOException;


/**
 * @Author 邓建忠
 * @CreateTime 2022/2/17 10:12
 * @Description TODO
 */
public final class FileUtils {

    /**
     * SD卡是否存在
     */
    public static boolean isSDCardExist() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取缓存目录路径
     *
     * @return
     */
    public static String getCacheDirPath(Context context) {
        if (isSDCardExist()) {
            return context.getExternalCacheDir().getAbsolutePath();
        } else {
            return context.getCacheDir().getAbsolutePath();
        }
    }

    /**
     * 关闭资源
     *
     * @param closees
     */
    public static void closeCloseable(Closeable... closees) {
        for (Closeable close : closees) {
            if (close != null) {
                try {
                    close.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
