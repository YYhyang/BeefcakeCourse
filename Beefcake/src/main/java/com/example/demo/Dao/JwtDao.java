package com.example.demo.Dao;

import com.example.demo.Sercurity.JWTPayLoad;
import com.example.demo.Sercurity.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class JwtDao {
    @Autowired
    private JwtService jwtService;
    public JWTPayLoad getJwtPayLoad(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (token != null && token.startsWith("Bearer ")) {
            String authToken = token.replace("Bearer ", "");
            System.out.println(authToken);
            JWTPayLoad jwtPayLoad = jwtService.verifyJwt(authToken);
            if (jwtPayLoad == null) {
                System.out.println("jwtPayLoad is null");
                return null;
            }
            else
                return jwtPayLoad;
        }
        System.out.println("token is null");
        return null;
    }

    private Collection<?extends GrantedAuthority> getAuthorities(int role){
        List<GrantedAuthority> authorities=new ArrayList<>();
        if(role==0){
            authorities.add(new SimpleGrantedAuthority("ROLE_STUDENT"));

        }else if(role==1){
            authorities.add(new SimpleGrantedAuthority("ROLE_TEACHER"));
        }
        return authorities;
    }
}
