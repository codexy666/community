package com.study.community.service;

import com.study.community.mapper.UserMapper;
import com.study.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    //查询数据库是否存在该用户（以AccountId为准）如果存在刷新用户部分信息，否则插入改用户
    public void createOrUpdate(User user){
        User dbUser = userMapper.findByAccountId(user.getAccountId());
        if(dbUser == null){  //插入
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }else{  //更新token
            dbUser.setGmtModified(System.currentTimeMillis());
            dbUser.setAvatarUrl(user.getAvatarUrl());
            dbUser.setName(user.getName());
            dbUser.setToken(user.getToken());
            userMapper.update(dbUser);
        }
    }
}
