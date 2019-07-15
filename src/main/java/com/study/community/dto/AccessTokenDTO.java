package com.study.community.dto;

import lombok.Data;

@Data
public class AccessTokenDTO {
    //这里的变量名不能改！与请求服务的路径有关
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
