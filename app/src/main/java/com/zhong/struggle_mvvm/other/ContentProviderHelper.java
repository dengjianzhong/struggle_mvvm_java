package com.zhong.struggle_mvvm.other;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

/**
 * @Author 邓建忠
 * @CreateTime 2021/12/6 13:52
 * @Description TODO
 */
public class ContentProviderHelper {
    public static void querySms(Context context) {
        ContentResolver resolver = context.getContentResolver();
        Cursor cursor = resolver.query(Uri.parse("content://sms//"), null, null, null, null);
        while (cursor.moveToNext()) {
            String address = cursor.getString(cursor.getColumnIndex("address"));
            String subject = cursor.getString(cursor.getColumnIndex("subject"));
            String body = cursor.getString(cursor.getColumnIndex("body"));
            String date = cursor.getString(cursor.getColumnIndex("date"));

            Log.i("==>", String.format("address: %s, subject: %s, body: %s, date: %s", address, subject, body, date));
        }
    }
}
