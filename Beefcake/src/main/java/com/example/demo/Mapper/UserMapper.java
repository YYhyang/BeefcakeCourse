package com.example.demo.Mapper;

import com.example.demo.Entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {
    public UserEntity findFromStudent(@Param("account") String account);
    public UserEntity findFromTeacher(@Param("account") String account);
}
