package com.struggle.base;


/**
 * *******************************************
 * 标题 :                                     *
 * 编辑 : 向绍谷                               *
 * 日期 : 2019/5/23                             *
 * 描述 :                                     *
 * *******************************************
 */
public class PushBean<T> {


    /**
     * actionType : 3
     * title : 有新订单来了
     * content : 新订单 1100249 来了快抢，点击查看...
     * voice : 3.wav
     * msgId : 2ebcc9c8-f80f-4a92-84b1-77da17d72a91
     */

    private int id;
    private int actionType;
    private String title;
    private T content;
    private String voice;
    private String msgId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getActionType() {
        return actionType;
    }

    public void setActionType(int actionType) {
        this.actionType = actionType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

}
