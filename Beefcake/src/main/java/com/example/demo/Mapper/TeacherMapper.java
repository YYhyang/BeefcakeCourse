package com.example.demo.mapper;

import com.example.demo.entity.TeacherEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface TeacherMapper {

    Boolean createTeacher(@Param("account") String account, @Param("password") String password, @Param("teacher_name") String teacherName, @Param("email") String email);

    Boolean putTeacherInfo(@Param("teacherId") Long teacherId, @Param("account") String account, @Param("teacher_name") String teacherName, @Param("email") String email);

    Boolean putTeacherPassword(@Param("teacherId") Long teacherId);

    Boolean deleteTeacher(@Param("teacherId") Long teacherId);

    TeacherEntity selectTeacherById(@Param("teacherId") Long teacherId);

    List<TeacherEntity> getAllTeacher();

    List<TeacherEntity> searchTeacherByAccount(@Param("account") String identity);

    List<TeacherEntity> searchTeacherByName(@Param("teacher_name") String identity);

    //***************待分离***************
    Boolean activateTeacher(@Param("teacherId") Long teacherId, @Param("password") String password);

}
