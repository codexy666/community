package com.study.community.model;

public class Question {
    private Integer id;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private String tag;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String desciption) {
        this.description = desciption;
    }

    public void setGmtCreate(Long gmt_create) {
        this.gmtCreate = gmt_create;
    }

    public void setGmtModified(Long gmt_modified) {
        this.gmtModified = gmt_modified;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public void setViewCount(Integer view_count) {
        this.viewCount = view_count;
    }

    public void setCommentCount(Integer comment_count) {
        this.commentCount = comment_count;
    }

    public void setLikeCount(Integer like_count) {
        this.likeCount = like_count;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public Long getGmtModified() {
        return gmtModified;
    }

    public String getTag() {
        return tag;
    }

    public Integer getCreator() {
        return creator;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public Integer getLikeCount() {
        return likeCount;
    }
}
