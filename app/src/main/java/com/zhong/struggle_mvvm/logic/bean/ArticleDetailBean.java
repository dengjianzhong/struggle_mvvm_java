package com.zhong.struggle_mvvm.logic.bean;

import java.util.List;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/10 16:22
 * @Description TODO
 */
public class ArticleDetailBean {
    /**
     * _id : 5e777432b8ea09cade05263f
     * author : 鸢媛
     * category : Girl
     * content : 这世界总有人在笨拙地爱着你，想把全部的温柔都给你。
     * createdAt : 2020-03-25 08:00:00
     * desc : 这世界总有人在笨拙地爱着你，想把全部的温柔都给你。
     * images : ["http://gank.io/images/624ade89f93f421b8d4e8fafd86b1d8d"]
     * index : 35
     * isOriginal : true
     * license :
     * likeCount : 0
     * likeCounts : 1
     * likes : ["DBRef('users', ObjectId('5b6ce9c89d21226f4e09c779'))"]
     * markdown :
     * originalAuthor :
     * publishedAt : 2020-03-25 08:00:00
     * stars : 1
     * status : 1
     * tags : []
     * title : 第35期
     * type : Girl
     * updatedAt : 2020-03-25 08:00:00
     * url : http://gank.io/images/624ade89f93f421b8d4e8fafd86b1d8d
     * views : 1896
     */

    private String _id;
    private String author;
    private String category;
    private String content;
    private String createdAt;
    private String desc;
    private int index;
    private boolean isOriginal;
    private String license;
    private int likeCount;
    private int likeCounts;
    private String markdown;
    private String originalAuthor;
    private String publishedAt;
    private int stars;
    private int status;
    private String title;
    private String type;
    private String updatedAt;
    private String url;
    private int views;
    private List<String> images;
    private List<String> likes;
    private List<?> tags;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isIsOriginal() {
        return isOriginal;
    }

    public void setIsOriginal(boolean isOriginal) {
        this.isOriginal = isOriginal;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getLikeCounts() {
        return likeCounts;
    }

    public void setLikeCounts(int likeCounts) {
        this.likeCounts = likeCounts;
    }

    public String getMarkdown() {
        return markdown;
    }

    public void setMarkdown(String markdown) {
        this.markdown = markdown;
    }

    public String getOriginalAuthor() {
        return originalAuthor;
    }

    public void setOriginalAuthor(String originalAuthor) {
        this.originalAuthor = originalAuthor;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getLikes() {
        return likes;
    }

    public void setLikes(List<String> likes) {
        this.likes = likes;
    }

    public List<?> getTags() {
        return tags;
    }

    public void setTags(List<?> tags) {
        this.tags = tags;
    }
}
