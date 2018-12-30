package com.example.demo.controller;

import com.example.demo.dao.JwtDao;
import com.example.demo.mapper.UserMapper;
import com.example.demo.sercurity.JWTPayLoad;
import com.example.demo.service.UserService;
import com.example.demo.service.utils.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
public class UserController {
    @Autowired
    public UserService userService;
    @Autowired
    public UserMapper userMapper;
    @Autowired
    private JwtDao jwtDao;
    @RequestMapping(value="/user/password",method = RequestMethod.GET)
    public String forgetPassword(HttpServletRequest request) throws Exception
    {
        JWTPayLoad jwtPayLoad=jwtDao.getJwtPayLoad(request);
        String jwtAccount = jwtPayLoad.getUsername();
        String passWord=userService.forgetPassword(jwtAccount);
        String email=userService.getEmail(jwtAccount);
        if("".equals(email)) {
            return ("fail");
        }
        MailUtils.sendEmail(email,passWord);
        return ("success");
    }

    @RequestMapping(value = "/user/information",method = RequestMethod.GET)//获取个人信息
    public ArrayList<String> getInfo(HttpServletRequest request)
    {
        JWTPayLoad jwtPayLoad=jwtDao.getJwtPayLoad(request);
        String jwtAccount = jwtPayLoad.getUsername();
        return userService.getInfo(jwtAccount);
    }

    @RequestMapping(value = "/user/password",method = RequestMethod.PUT)//修改密码
    public void changePassword( @RequestParam("password") String password,HttpServletRequest request)
    {
        JWTPayLoad jwtPayLoad=jwtDao.getJwtPayLoad(request);
        String jwtAccount = jwtPayLoad.getUsername();
        userService.changePassword(jwtAccount,password);
    }

    @RequestMapping(value = "/user/email",method = RequestMethod.PUT)//修改邮箱
    public void changeEmail( @RequestParam("email") String email,HttpServletRequest request)
    {
        JWTPayLoad jwtPayLoad=jwtDao.getJwtPayLoad(request);
        String jwtAccount = jwtPayLoad.getUsername();
        userService.changeEmail(jwtAccount,email);
    }

}
