package com.study.community.model;

import lombok.Data;

@Data
public class User {
    private Integer id; //为主键，自动增长
    private String name;
    private String accountId;   //github账户传来的Id，也不会改变
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;
}
