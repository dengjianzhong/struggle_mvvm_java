package com.struggle.base.utils;

import com.struggle.base.app.bean.Event;

import org.greenrobot.eventbus.EventBus;

/**
 * EventBus二次封装
 */
public class EventBusUtil {

    /**
     * 注册EventBus
     *
     * @param subscriber the subscriber
     */
    public static void register(Object subscriber) {
        if (!EventBus.getDefault().isRegistered(subscriber)) {//加上判断
            EventBus.getDefault().register(subscriber);
        }
    }

    /**
     * 注销EventBus.
     *
     * @param subscriber the subscriber
     */
    public static void unregister(Object subscriber) {
        if (EventBus.getDefault().isRegistered(subscriber))//加上判断
            EventBus.getDefault().unregister(subscriber);
    }

    /**
     * 发送普通事件
     *
     * @param event the event
     */
    public static void sendEvent(Event event) {
        EventBus.getDefault().post(event);
    }

    /**
     * 发送沾性事件
     *
     * @param event the event
     */
    public static void sendStickyEvent(Event event) {
        EventBus.getDefault().postSticky(event);
    }

    /**
     * 移除黏性事件
     */
    public static void removeStickyEvent(Event event) {
        EventBus.getDefault().removeStickyEvent(event);
    }
}
