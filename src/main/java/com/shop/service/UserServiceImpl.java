package com.shop.service;


import com.shop.mapper.UserMapper;
import com.shop.pojo.User;
import com.shop.pojo.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> isLogin(String username, String userPassword) {


        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        criteria.andUserpasswordEqualTo(userPassword);
        List<User> userList = this.userMapper.selectByExample(example);
        return userList;
    }

    @Override
    public void regesiterUser(User user) {
       this.userMapper.insert(user);
    }
}
