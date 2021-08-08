package com.struggle.base.app.bean;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/8 15:22
 * @Description TODO
 */
public class DataResponse<T> {
    /**
     * status : 12
     * isSuccess : true
     * message :
     * data : T
     */

    private int status;
    private boolean isSuccess;
    private String message;
    private T data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
