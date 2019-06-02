package com.shop.controller;

import com.shop.pojo.User;
import com.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    // 跳转显示出登录界面
    @RequestMapping(value = "/login")
    public String login()
    {
       return "login";
    }


    // 登录具体方法
    @RequestMapping(value = "/doLogin")
    public String checkLogin(String username,
                             String userPassword,
                             String valideCode,
                             HttpSession session,
                             Model model)
    {
     // 校验验证码

        String randomCode = (String) session.getAttribute("vrifyCode");

        if(!valideCode.equals(randomCode))
        {
            //验证码不正确
          model.addAttribute("errorMsg","验证码不正确");
            return "login";
        }

        List<User> userList = this.userService.isLogin(username, userPassword);
        if(userList!=null&&userList.size()>0)
        {


            // 查询到了账号和密码
            User user = userList.get(0);

            // 将用户对象存入session域中
            session.setAttribute("user",user);
            // return "index";
            return "redirect:/queryAllAuctions";
        }
        else
        {
            model.addAttribute("errorMsg","账号或密码不正确");
            return "login";
        }

    }

    // 跳转到注册页面
    @RequestMapping(value = "/toregesiter")
    public String toregesiter()
    {
     return "regesiter";
    }

    //注销登录
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:login";
    }
}
