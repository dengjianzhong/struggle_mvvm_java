package com.struggle.base.app.bean;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/24 9:50
 * @Description 加载loading需附属的实体
 */
public class LoadingBean {
    //加载loading的内容
    private String content;
    //是否可以取消loading
    private boolean mCancelable;
    //控制loading加载显示
    private boolean isShow;

    public LoadingBean(String content, boolean mCancel, boolean isShow) {
        this.content = content;
        this.mCancelable = mCancel;
        this.isShow = isShow;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isCancelable() {
        return mCancelable;
    }

    public void setCancelable(boolean mCancelable) {
        this.mCancelable = mCancelable;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }
}
