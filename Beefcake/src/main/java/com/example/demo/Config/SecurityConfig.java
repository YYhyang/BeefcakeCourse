package com.example.demo.Config;

import com.example.demo.Sercurity.CustomAuthenticationProvider;
import com.example.demo.Sercurity.JWTAuthenticationFilter;
import com.example.demo.Sercurity.JWTLoginFilter;
import com.example.demo.Sercurity.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserService myUserService;


    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.userDetailsService(myUserService).passwordEncoder(new BCryptPasswordEncoder());
        auth.authenticationProvider(new CustomAuthenticationProvider(myUserService,new BCryptPasswordEncoder()));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers("/user/login").permitAll()
                .anyRequest().authenticated()
               /* .and()
                .formLogin()
                .loginProcessingUrl("/user/login")*/
                .and()
                .addFilter(new JWTLoginFilter(authenticationManager()))
                .addFilter(new JWTAuthenticationFilter(authenticationManager()));
    }
}
