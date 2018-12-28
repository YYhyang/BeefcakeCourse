package com.example.demo.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserEntity implements UserDetails {
    private Long id;
    private String Username;
    private String password;
    private Boolean enabled=true;
    private String name;
    private int is_active;
    //0为学生，1为老师
    private int role;
    private Collection<? extends GrantedAuthority>authorities;
    private Boolean accountNonExpired=true;
    private Boolean accountNonLocked=true;
    private Boolean credentialsNonExpired=true;

   /*public UserEntity(String Username,String password,boolean enabled)
    {
        this.Username=Username;
        this.password=password;
        this.enabled=enabled;
    }*/

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return authorities;
    }

    @Override
    public String getPassword()
    {
        return password;
    }

    @Override
    public String getUsername()
    {
        return Username;
    }
    @Override
    public boolean isAccountNonLocked()
    {
        return accountNonLocked;
    }
    @Override
    public boolean isAccountNonExpired()
    {
        return accountNonExpired;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return isAccountNonExpired();
    }

    @Override
    public boolean isEnabled()
    {
        return enabled;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority>authorities) {
        this.authorities = authorities;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getIs_active() {
        return is_active;
    }

    public void setIs_active(int is_active) {
        this.is_active = is_active;
    }
}
