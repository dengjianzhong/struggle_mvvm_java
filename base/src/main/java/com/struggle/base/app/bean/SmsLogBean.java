package com.struggle.base.app.bean;

/**
 * @Author 邓建忠
 * @CreateTime 2021/12/17 17:23
 * @Description 通话日志
 */
public class SmsLogBean {
    public String address;//发送人电话号码
    public String subject;//主题
    public String body;//内容
    public long date;//发送时间
    public int status;//短信状态码

    /**
     * 获取短信状态
     *
     * @return
     */
    public String getMessageStatus() {
        switch (status) {
            case -1:
                return "接收成功";
            case 0:
                return "发送成功";
            case 64:
                return "发送中";
            case 128:
                return "发送失败";
            default:
                break;
        }
        return null;
    }
}
