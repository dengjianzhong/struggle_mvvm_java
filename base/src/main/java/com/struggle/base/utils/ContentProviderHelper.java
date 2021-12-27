package com.struggle.base.utils;

import android.content.Context;
import android.database.Cursor;
import android.provider.CallLog;
import android.provider.Telephony;

import com.struggle.base.app.bean.CallLogBean;
import com.struggle.base.app.bean.SmsLogBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 邓建忠
 * @CreateTime 2021/12/6 13:52
 * @Description TODO
 */
public class ContentProviderHelper {
    /**
     * 查询短信记录
     *
     * @param context
     */
    public static List<SmsLogBean> querySms(Context context, long startTime, long endTime) {
        List<SmsLogBean> beans = new ArrayList<>();
        String sql = Telephony.BaseMmsColumns.DATE + "> ? and " + Telephony.BaseMmsColumns.DATE + "< ?";

        Cursor c = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            c = context.getContentResolver().query(Telephony.Sms.CONTENT_URI,
                    null,
                    sql,
                    new String[]{String.valueOf(startTime), String.valueOf(endTime)},
                    "date desc");
        }
        if (c == null) return beans;

        while (c != null && c.moveToNext()) {
            String address = c.getString(c.getColumnIndex("address"));//发送人电话号码
            String subject = c.getString(c.getColumnIndex("subject"));//主题
            String body = c.getString(c.getColumnIndex("body"));//内容
            long date = c.getLong(c.getColumnIndex("date"));//发送时间
            int status = c.getInt(c.getColumnIndex("status"));//发送时间

            SmsLogBean bean = new SmsLogBean();
            bean.address = address;
            bean.subject = subject;
            bean.body = body;
            bean.date = date;
            bean.status = status;

            beans.add(bean);
        }

        return beans;
    }

    /**
     * 查询电话记录
     *
     * @param context
     */
    public static List<CallLogBean> queryPhone(Context context, long startTime, long endTime) {
        List<CallLogBean> beans = new ArrayList<>();
        String sql = CallLog.Calls.DATE + "> ? and " + CallLog.Calls.DATE + "< ?";

        Cursor c = context.getContentResolver().query(CallLog.Calls.CONTENT_URI,
                null,
                sql,
                new String[]{String.valueOf(startTime), String.valueOf(endTime)},
                "date desc");
        if (c == null) return beans;

        while (c.moveToNext()) {
            String name = c.getString(c.getColumnIndex("name"));//联系人姓名
            String number = c.getString(c.getColumnIndex("number"));//联系人电话
            long date = c.getLong(c.getColumnIndex("date"));//拨打时间
            int duration = c.getInt(c.getColumnIndex("duration"));//通话时间

            CallLogBean bean = new CallLogBean();
            bean.name = name;
            bean.number = number;
            bean.date = date;
            bean.duration = duration;
            bean.isItConnected = duration > 0;

            beans.add(bean);
        }
        c.close();

        return beans;
    }
}
