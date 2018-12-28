package com.example.demo.sercurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public CustomAuthenticationProvider(UserDetailsService userDetailsService,BCryptPasswordEncoder bCryptPasswordEncoder)
    {
        this.userDetailsService=userDetailsService;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
    }

    @Override
    public boolean supports(Class<?>authentication){
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    @Override
    public Authentication authenticate(Authentication authentication)throws AuthenticationException{
        String username=authentication.getName();
        String password=authentication.getCredentials().toString();

        UserDetails userDetails=userDetailsService.loadUserByUsername(username);

        String realPassword=userDetails.getPassword();

        if(!password.equals(realPassword)) {
            throw new BadCredentialsException("");
        }

        Collection<?extends GrantedAuthority> authorities;
        authorities=userDetails.getAuthorities();
        UsernamePasswordAuthenticationToken auth=new UsernamePasswordAuthenticationToken(username,password,authorities);
        auth.setDetails(userDetails);
        return auth;
    }
}
