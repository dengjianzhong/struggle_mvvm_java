package com.zhong.struggle_mvvm.bean;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/8 12:18
 * @Description TODO
 */
public class TestBean {
    /**
     * code : 500
     * msg : 用户ID丢失
     * serviceSuccess : true
     * data : {}
     */

    private int code;
    private String msg;
    private boolean serviceSuccess;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isServiceSuccess() {
        return serviceSuccess;
    }

    public void setServiceSuccess(boolean serviceSuccess) {
        this.serviceSuccess = serviceSuccess;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
    }
}
