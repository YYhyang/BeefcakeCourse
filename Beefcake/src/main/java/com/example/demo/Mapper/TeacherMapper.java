package com.example.demo.mapper;

import com.example.demo.entity.TeacherEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface TeacherMapper {

    public Boolean createTeacher(@Param("account") String account, @Param("password") String password, @Param("teacher_name") String teacherName, @Param("email") String email);

    public List<TeacherEntity> getAllTeacher();

    public List<TeacherEntity> searchTeacherByAccount(@Param("account") String identity);

    public List<TeacherEntity> searchTeacherByName(@Param("teacher_name") String identity);

    public Boolean putTeacherInfo(@Param("teacherId") Long teacherId, @Param("account") String account, @Param("teacher_name") String teacherName, @Param("email") String email);

    public Boolean putTeacherPassword(@Param("teacherId") Long teacherId);

    public Boolean deleteTeacher(@Param("teacherId") Long teacherId);

    public Boolean activateTeacher(@Param("teacherId") Long teacherId, @Param("password") String password);

    public TeacherEntity selectTeacherById(@Param("teacherId") Long teacherId);

}
