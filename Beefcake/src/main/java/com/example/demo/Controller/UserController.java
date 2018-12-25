package com.example.demo.Controller;

import com.example.demo.Mapper.UserMapper;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class UserController {
    @Autowired
    public UserService userService;
    @Autowired
    public UserMapper userMapper;
    /*@RequestMapping(value = "/user/login",method = RequestMethod.POST)
    public boolean login(@RequestParam("username") String account, @RequestParam("password") String password)
    {
        return userService.userLogin(account,password);
    }*/

    @RequestMapping(value="/user/password",method = RequestMethod.GET)
    public String forgetPassword(@RequestParam("account") String account)//暂时先返回密码  待修改
    {
        return userService.forgetPassword(account);
    }

    @RequestMapping(value = "/user/information",method = RequestMethod.GET)//获取个人信息
    public ArrayList<String> getInfo(@RequestParam("account") String account)
    {
        return userService.getInfo(account);
    }

    @RequestMapping(value = "/user/password",method = RequestMethod.PUT)//修改密码
    public void changePassword(@RequestParam("account") String account, @RequestParam("password") String password)
    {
        userService.changePassword(account,password);
    }

    @RequestMapping(value = "/user/email",method = RequestMethod.PUT)//修改邮箱
    public void changeEmail(@RequestParam("account") String account, @RequestParam("email") String email)
    {
        userService.changeEmail(account,email);
    }

}
