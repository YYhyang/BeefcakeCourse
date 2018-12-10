package com.example.demo.Service;

import com.example.demo.Dao.UserDao;
import com.example.demo.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public String getRole(String account,String password)
    {
        try
        {
            int role = userMapper.getRole(account, password);
            int status = userMapper.getStatus(account, password);
            String Role=new String();

            switch (role)
            {
                case 0:Role="teacher";break;
                case 1:Role="student";break;
            }


            return Role;
        }
        catch (Exception e)
        {
            return"error";
        }
    }

}
