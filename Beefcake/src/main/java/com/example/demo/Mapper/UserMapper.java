package com.example.demo.Mapper;

import com.example.demo.Entity.StudentEntity;
import com.example.demo.Entity.TeacherEntity;
import com.example.demo.Entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {
    public UserEntity findFromStudent(@Param("account") String account);
    public UserEntity findFromTeacher(@Param("account") String account);

    public StudentEntity studentForgetPassword(@Param("account")String account);

    public TeacherEntity teacherForgetPassword(@Param("account")String account);

    public StudentEntity studentGetInfo(@Param("account")String account);

    public TeacherEntity teacherGetInfo(@Param("account")String account);

    public void studentChangePassword(@Param("account")String account,@Param("password")String password);

    public void teacherChangePassword(@Param("account")String account,@Param("password")String password);

    public void studentChangeEmail(@Param("account")String account,@Param("email")String email);

    public void teacherChangeEmail(@Param("account")String account,@Param("email")String email);

    public StudentEntity studentLogin(@Param("account")String account, @Param("password")String password);

    public TeacherEntity teacherLogin(@Param("account")String account, @Param("password")String password);
}
