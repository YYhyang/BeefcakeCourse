package com.example.demo.sercurity;

import com.example.demo.entity.UserEntity;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;
@Component
public class MyUserService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public UserEntity loadUserByUsername(String account)
    {
        ArrayList<GrantedAuthority>authorities=new ArrayList<>();
        UserEntity userEntity=userMapper.findFromStudent(account);
        if(userEntity!= null)
        {
            userEntity.setRole(0);
            return userEntity;
        }
        else
        {
            userEntity=userMapper.findFromTeacher(account);
            if(userEntity!=null)
            {
                userEntity.setRole(1);
                return userEntity;
            }
        }
        return userEntity;
    }
}
