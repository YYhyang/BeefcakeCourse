package com.example.demo.Controller;

import com.example.demo.Entity.UserEntity;
import com.example.demo.Sercurity.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    MyUserService myUserService;
    @RequestMapping("hello")
    public String hello(){
        return "Hello spring security";
    }
    @RequestMapping("admin")
    public String admin(){return "Admin";}
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public UserEntity test(@RequestParam("account")String account)
    {
        return myUserService.loadUserByUsername(account);
    }
}
