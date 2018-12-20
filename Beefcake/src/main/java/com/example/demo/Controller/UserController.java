package com.example.demo.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    public void login(@RequestParam("account")String account,@RequestParam("password")String password)
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

}
