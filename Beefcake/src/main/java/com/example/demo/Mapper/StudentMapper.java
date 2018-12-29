package com.example.demo.mapper;

import com.example.demo.entity.StudentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface StudentMapper {

    void deleteStudent(@Param("studentId") Long studentId);

    Boolean putStudentInfo(@Param("studentId") Long studentId, @Param("account") String account, @Param("student_name") String studentName, @Param("email") String email);

    Boolean putStudentPassword(@Param("studentId") Long stduentId);

    StudentEntity selectStudentById(@Param("studentId") Long studentId);

    List<StudentEntity> getAllStudent();

    List<StudentEntity> searchStudentByAccount(@Param("account") String identity);

    List<StudentEntity> searchStudentByName(@Param("student_name") String identity);


    //************待分离*************
    Boolean activateStudent(@Param("studentId") Long studentId, @Param("password") String password, @Param("email") String email);

}

