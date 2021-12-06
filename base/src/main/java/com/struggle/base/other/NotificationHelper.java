package com.struggle.base.other;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.struggle.base.R;

/**
 * @Author 邓建忠
 * @CreateTime 2021/11/29 16:43
 * @Description TODO
 */
public class NotificationHelper {
    private static Application application;
    private static NotificationManager notificationManager;
    private static SharedPreferences preferences;

    public static void init(Application mApplication) {
        application = mApplication;
        notificationManager = (NotificationManager) application.getSystemService(Context.NOTIFICATION_SERVICE);
        preferences = application.getSharedPreferences(NotificationConstants.NOTIFICATION, Context.MODE_PRIVATE);

        /**SDK >= 8.0 时需要创建通知渠道**/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (existNotificationChannel(NotificationConstants.CHANNEL_ID)) {
                return;
            }
            ChannelConfig config = new ChannelConfig();
            config.channelId = NotificationConstants.CHANNEL_ID;
            config.channelName = NotificationConstants.CHANNEL_NAME;
            config.description = NotificationConstants.DESCRIPTION;
            config.enableSound = preferences.getBoolean(NotificationConstants.ENABLE_SOUND, true);
            config.enableVibration = preferences.getBoolean(NotificationConstants.ENABLE_VIBRATION, true);
            config.sound = Uri.parse("android.resource://" + application.getPackageName() + "/" + R.raw.new_order);

            createNotificationChannel(config);
        }
    }

    /**
     * 创建通知渠道
     *
     * @param config
     */
    private static void createNotificationChannel(ChannelConfig config) {
        String channelId = config.channelId;
        String channelName = config.channelName;
        String description = config.description;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH);
            if (config.enableVibration) {
                channel.enableVibration(true);
                channel.setVibrationPattern(NotificationConstants.VIBRATE);
            } else {
                channel.enableVibration(false);
                channel.setVibrationPattern(new long[]{0});
            }
            if (config.enableSound) {
                channel.setSound(config.sound, Notification.AUDIO_ATTRIBUTES_DEFAULT);
            } else {
                channel.setSound(null, null);
            }
            channel.setDescription(description);
            channel.setShowBadge(true);
            channel.setLockscreenVisibility(Notification.DEFAULT_VIBRATE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                channel.setAllowBubbles(true);
            }

            notificationManager.createNotificationChannel(channel);
        }
    }

    /**
     * 是否存在通知渠道
     *
     * @param channelId
     */
    public static boolean existNotificationChannel(String channelId) {
        NotificationManager notificationManager = (NotificationManager) application.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = notificationManager.getNotificationChannel(channelId);
            return channel != null;
        }
        return false;
    }

    /**
     * 根据渠道ID删除通知渠道
     *
     * @param channelId
     */
    public static void deleteNotificationChannel(String channelId) {
        NotificationManager notificationManager = (NotificationManager) application.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = notificationManager.getNotificationChannel(channelId);
            if (channel != null) {
                notificationManager.deleteNotificationChannel(channelId);
            }
        }
    }

    /**
     * 发送通知
     */
    public static void sendNotification() {
        if (!NotificationManagerCompat.from(application).areNotificationsEnabled()) {
            Toast.makeText(application, "通知权限未打开", Toast.LENGTH_SHORT).show();
            return;
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(application, "100")
                .setContentTitle("测试通知")
                .setContentText("aaaaaaaa")
                .setContentInfo("bbb")
                .setSmallIcon(R.drawable.brvah_sample_footer_loading)
                .setLargeIcon(BitmapFactory.decodeResource(application.getResources(), R.drawable.brvah_sample_footer_loading))
                .setAutoCancel(true)
                .setShowWhen(true)
                .setWhen(System.currentTimeMillis());

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            if (preferences.getBoolean(NotificationConstants.ENABLE_SOUND, true)) {
                builder.setSound(Uri.parse("android.resource://" + application.getPackageName() + "/" + R.raw.new_order));
            }
            if (preferences.getBoolean(NotificationConstants.ENABLE_VIBRATION, true)) {
                builder.setVibrate(NotificationConstants.VIBRATE);
            }
        }

        notificationManager.notify((int) (Math.random() * 1000 + 1), builder.build());
    }
}
