package com.example.demo.Service;

import com.example.demo.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UserService {

    @Autowired
    public UserMapper userMapper;
    public boolean userLogin(String account, String password)
    {
        if(!(userMapper.studentLogin(account,password)== null) || !(userMapper.teacherLogin(account,password)== null))
            return true;
        else
            return false;
    }

    public String forgetPassword(String account) //返回密码
    {
        if(!(userMapper.studentForgetPassword(account)==null)){
            return userMapper.studentForgetPassword(account).getPassword();
        }
        else if(!(userMapper.teacherForgetPassword(account)==null)){
            return userMapper.teacherForgetPassword(account).getPassword();
        }
        else
            return "";
    }

    public String getEmail(String account)
    {
        if(!(userMapper.studentForgetPassword(account)==null)){
            return userMapper.studentForgetPassword(account).getEmail();
        }
        else if(!(userMapper.teacherForgetPassword(account)==null)){
            return userMapper.teacherForgetPassword(account).getEmail();
        }
        else
            return "";
    }

    public ArrayList<String> getInfo(String account)//分条返回账户
    {
        ArrayList<String> info = new ArrayList<String>();
        if(!(userMapper.studentForgetPassword(account)==null)){
            info.add(userMapper.studentGetInfo(account).getStudent_name());
            info.add(userMapper.studentGetInfo(account).getAccount());
            info.add(userMapper.studentGetInfo(account).getId().toString());
            info.add(userMapper.studentGetInfo(account).getEmail());
            info.add(userMapper.studentGetInfo(account).getPassword());
            return info;
        }
        else if(!(userMapper.teacherForgetPassword(account)==null)){
            info.add(userMapper.teacherGetInfo(account).getTeacher_name());
            info.add(userMapper.teacherGetInfo(account).getAccount());
            info.add(userMapper.teacherGetInfo(account).getId().toString());
            info.add(userMapper.teacherGetInfo(account).getEmail());
            info.add(userMapper.teacherGetInfo(account).getPassword());
            return info;
        }
        else
            return info;
    }

    public void changePassword(String account, String password){
        if(!(userMapper.studentForgetPassword(account)==null)){
            userMapper.studentChangePassword(account,password);
        }
        else if(!(userMapper.teacherForgetPassword(account)==null)){
            userMapper.teacherChangePassword(account,password);
        }
    }

    public void changeEmail(String account, String email)
    {
        if(!(userMapper.studentForgetPassword(account)==null)){
            userMapper.studentChangeEmail(account,email);
        }
        else if(!(userMapper.teacherForgetPassword(account)==null)){
            userMapper.teacherChangeEmail(account,email);
        }
    }

}
