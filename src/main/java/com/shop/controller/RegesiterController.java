package com.shop.controller;

import com.shop.pojo.User;
import com.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegesiterController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/regesiterUser")
    public String regesiterUser(User user)
    {
        // 设置只能注册普通用户
        user.setUserisadmin(0);

        this.userService.regesiterUser(user);

        return "redirect:/login";
    }
}
