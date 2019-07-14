package com.study.community.dto;

import com.study.community.model.User;
import lombok.Data;

@Data
public class QuestionDTO {
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
    private User user;

    public void setUser(User user){
        this.user = user;
    }

    public User getUser(){
        return user;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
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
