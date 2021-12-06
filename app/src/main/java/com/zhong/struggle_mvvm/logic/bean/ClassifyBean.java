package com.zhong.struggle_mvvm.logic.bean;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/13 17:16
 * @Description TODO
 */
public class ClassifyBean {
    /**
     * _id : 5e5a26366e851660b43ec6c2
     * coverImageUrl : http://gank.io/images/30bc3da361ca47fcbe5bc945aae29aa9
     * desc : - 念念不忘，必有回响
     * title : 妹纸
     * type : Girl
     */

    private String _id;
    private String coverImageUrl;
    private String desc;
    private String title;
    private String type;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
