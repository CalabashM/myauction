package com.shop.service;

import com.shop.pojo.User;

import java.util.List;

public interface UserService {

    /**
     *
     * 校验登录
     * @param username
     * @param userPassword
     * @return
     */
   List<User> isLogin(String username, String userPassword);

    void regesiterUser(User user);
}
