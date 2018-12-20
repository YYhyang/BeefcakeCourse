package com.example.demo.Sercurity;

import com.example.demo.Entity.UserEntity;
import com.example.demo.Mapper.UserMapper;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
            /*authorities.add(new SimpleGrantedAuthority("ROLE_STUDENT"));
            userEntity.setAuthorities(authorities);*/
            userEntity.setRole(0);
            return userEntity;
        }
        else
        {
            userEntity=userMapper.findFromTeacher(account);
            if(userEntity!=null)
            {
                /*authorities.add(new SimpleGrantedAuthority("ROLE_TEACHER"));
               userEntity.setAuthorities(authorities);*/
                userEntity.setRole(1);
                return userEntity;
            }
        }
        //userEntity.setAuthorities(authorities);
        /*UserEntity userEntity=new UserEntity("yyh",new BCryptPasswordEncoder().encode("1234"),true);
        authorities.add(new SimpleGrantedAuthority("ADMIN"));
        userEntity.setAuthorities(authorities);*/
        return userEntity;
    }
}
