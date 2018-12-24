package com.example.demo.Sercurity;

import com.example.demo.Entity.UserEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;

public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService=new JwtService();

    public JWTLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager=authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)throws AuthenticationException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");

        if(username==null){
            username="";
        }

        if(password==null){
            password="";
        }

        //测试用户名密码
        System.out.println("Login: "+username);
        System.out.println("Login password: "+password);


        username=username.trim();

        ArrayList<GrantedAuthority> authorities=new ArrayList<>();
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username,password,authorities);
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,Authentication authResult){
        UserEntity userEntity=(UserEntity)authResult.getDetails();
        //以下为测试
        /*
        System.out.println(userEntity.getId());
        System.out.println(userEntity.getUsername());
        System.out.println(userEntity.getPassword());
        System.out.println(userEntity.getRole());*/

        String jwtString = jwtService.generateJwt(userEntity);
        System.out.println("jwtString: "+jwtString);

        String  role;
        if(userEntity.getRole()==1)
            role="Teacher";
        else role="Student";
        response.addHeader("token","Bearer "+jwtString);
        response.addHeader("role",role);

    }
}
