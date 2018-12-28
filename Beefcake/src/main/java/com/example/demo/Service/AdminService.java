package com.example.demo.service;

import com.example.demo.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class AdminService {

    @Autowired
    public AdminMapper adminMapper;

    public boolean login(String account, String password){
        if(adminMapper.login(account,password).equals(account)){
            return true;
        }
        else{
            return false;
        }
    }

}
