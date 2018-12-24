package com.example.demo.Sercurity;

import com.example.demo.Entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public class JWTPayLoad {
    private Long id;
    private String username;
    private int role;
    private String name;

    public JWTPayLoad()
    {

    }

    public JWTPayLoad(Long id,String username,int role,String name)
    {
        this.id=id;
        this.username=username;
        this.role=role;
        this.name=name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }
    public UserEntity toUser()
    {
        UserEntity user=new UserEntity();
        user.setId(this.id);
        user.setUsername(this.username);
        user.setRole(this.role);
        user.setName(this.name);
        return user;
    }
}
