package com.example.demo.config;

import com.example.demo.sercurity.CustomAuthenticationProvider;
import com.example.demo.sercurity.JWTAuthenticationFilter;
import com.example.demo.sercurity.JWTLoginFilter;
import com.example.demo.sercurity.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserService myUserService;


    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(new CustomAuthenticationProvider(myUserService,new BCryptPasswordEncoder()));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/**/*.css","/**/*.js","/**/*.jpg","/**/fonts/*","/**/*.png","/**/*.gif","/**/*.jpeg").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .addFilter(new JWTLoginFilter(authenticationManager()))
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .csrf().disable();
    }
}
