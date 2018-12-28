package com.example.demo.sercurity;

import com.example.demo.entity.UserEntity;
import lombok.Data;

@Data
public class JWTPayLoad {
    private Long id;
    private String username;
    private int role;
    private int is_active;
    private String name;

    public JWTPayLoad()
    {

    }

    public JWTPayLoad(Long id,String username,int role,int isActive,String name)
    {
        this.id=id;
        this.username=username;
        this.role=role;
        this.is_active=isActive;
        this.name=name;
    }
    public UserEntity toUser()
    {
        UserEntity user=new UserEntity();
        user.setId(this.id);
        user.setUsername(this.username);
        user.setRole(this.role);
        user.setName(this.name);
        user.setIs_active(this.is_active);
        return user;
    }


}
