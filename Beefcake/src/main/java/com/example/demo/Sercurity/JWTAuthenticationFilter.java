package com.example.demo.Sercurity;

import com.example.demo.Entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class JWTAuthenticationFilter extends BasicAuthenticationFilter {
    @Autowired
    private JwtService jwtService=new JwtService();
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager){
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException{
        String token=request.getHeader("token");

        if(token!=null&&token.startsWith("Bearer "))
        {
            String authToken=token.replace("Bearer ","");
            System.out.println("Token at Authentication"+authToken);
            JWTPayLoad jwtPayLoad=jwtService.verifyJwt(authToken);
            if(jwtPayLoad==null)
                System.out.println("jwtPayLoad is null at AuthenticationFilter");
            if(jwtPayLoad!=null){
                UserEntity user=jwtPayLoad.toUser();
                user.setAuthorities(getAuthorities(user.getRole()));
                UsernamePasswordAuthenticationToken authentication;
                authentication=new UsernamePasswordAuthenticationToken(user.getUsername(),null,getAuthorities(user.getRole()));

                authentication.setDetails(user);

                //以下为测试
                /*UserEntity userEntity2=(UserEntity)authentication.getDetails();
                System.out.println("Test2: ");
                System.out.println(userEntity2.getId());
                System.out.println(userEntity2.getUsername());
                System.out.println(userEntity2.getPassword());
                System.out.println(userEntity2.getAuthorities());
                System.out.println(userEntity2.getRole());*/


                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }


        chain.doFilter(request,response);
    }

    private Collection<?extends GrantedAuthority>getAuthorities(int role){
        List<GrantedAuthority>authorities=new ArrayList<>();
        if(role==0){
            authorities.add(new SimpleGrantedAuthority("ROLE_STUDENT"));

        }else if(role==1){
            authorities.add(new SimpleGrantedAuthority("ROLE_TEACHER"));
        }
        return authorities;
    }
}
