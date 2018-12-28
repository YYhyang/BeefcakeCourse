package com.example.demo.controller;

import com.example.demo.service.AdminService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class AdminController {

    @Resource
    AdminService adminService;

    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)//通过
    public boolean login(@RequestParam("account")String account, @RequestParam("password")String password)
    {
        return adminService.login(account,password);
    }

}
