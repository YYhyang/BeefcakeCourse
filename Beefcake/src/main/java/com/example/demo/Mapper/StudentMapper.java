package com.example.demo.mapper;

import com.example.demo.entity.StudentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface StudentMapper {

    public List<StudentEntity> getAllStudent();

    public List<StudentEntity> searchStudentByAccount(@Param("account") String identity);

    public List<StudentEntity> searchStudentByName(@Param("student_name") String identity);

    public Boolean putStudentInfo(@Param("studentId") Long studentId, @Param("account") String account, @Param("student_name") String studentName, @Param("email") String email);

    public Boolean putStudentPassword(@Param("studentId") Long stduentId);

    public void deleteStudent(@Param("studentId") Long studentId);

    public Boolean activateStudent(@Param("studentId") Long studentId, @Param("password") String password, @Param("email") String email);

    public StudentEntity selectStudentById(@Param("studentId") Long studentId);

}

