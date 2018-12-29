package com.example.demo.mapper;

import com.example.demo.entity.StudentEntity;
import com.example.demo.entity.TeacherEntity;
import com.example.demo.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {

    void studentChangePassword(@Param("account") String account, @Param("password") String password);

    void teacherChangePassword(@Param("account") String account, @Param("password") String password);

    void studentChangeEmail(@Param("account") String account, @Param("email") String email);

    void teacherChangeEmail(@Param("account") String account, @Param("email") String email);

    UserEntity findFromStudent(@Param("account") String account);

    UserEntity findFromTeacher(@Param("account") String account);

    StudentEntity studentForgetPassword(@Param("account") String account);

    TeacherEntity teacherForgetPassword(@Param("account") String account);

    StudentEntity studentGetInfo(@Param("account") String account);

    TeacherEntity teacherGetInfo(@Param("account") String account);

}
