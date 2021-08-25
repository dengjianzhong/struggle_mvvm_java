package com.struggle.base.app.bean;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/25 11:55
 * @Description TODO
 */
public class Event<T> {
    private int code;
    private T data;

    public Event() {
    }

    public Event(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
