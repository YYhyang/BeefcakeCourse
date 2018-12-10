package com.example.demo.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    public void login(@RequestParam("account")int account,@RequestParam("password")String password)
    {

    }

    @RequestMapping(value = "/user/modifycode",method = RequestMethod.PUT)//发送修改密码、找回密码的验证码
    public void modifyCode()
    {

    }

    @RequestMapping(value = "/user/activecode",method = RequestMethod.PUT)//发送激活账号的验证码
    public void activeCode(@RequestParam("email")String email)
    {

    }

    @RequestMapping(value="/user/verifycode",method = RequestMethod.PUT)//发送修改密码的验证码
    public void verifyCode(@RequestParam("code")int code)
    {

    }

    @RequestMapping(value = "/user/active",method = RequestMethod.PUT)
    public void active(@RequestParam("password")String password,@RequestParam("email")String email,@RequestParam("code")int code)
    {

    }

    @RequestMapping(value = "/user/information",method = RequestMethod.GET)//获取个人信息
    public void getInfo()
    {

    }

    @RequestMapping(value = "/user/password",method = RequestMethod.PUT)
    public void changePassword(@RequestParam("password")String password,@RequestParam("code")int code)
    {

    }

    @RequestMapping(value = "/user/email",method = RequestMethod.PUT)
    public void changeEmail(@RequestParam("email")String email,@RequestParam("code")int code)
    {

    }

    @RequestMapping(value = "/user/messageinterval",method = RequestMethod.PUT)
    public void messageInterval(@RequestParam("messageInterval")int messageInterval)
    {

    }
}
