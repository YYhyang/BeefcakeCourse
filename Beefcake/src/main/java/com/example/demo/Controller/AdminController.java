package com.example.demo.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    public void login(@RequestParam("account")int account, @RequestParam("password")String password)
    {

    }


    @RequestMapping(value = "/admin/email",method = RequestMethod.GET)
    public void getAdminEmail()
    {

    }
}
