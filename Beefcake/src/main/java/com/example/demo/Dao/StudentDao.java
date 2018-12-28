package com.example.demo.dao;

import com.example.demo.entity.StudentEntity;
import com.example.demo.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentDao {
    @Autowired
    private StudentMapper studentMapper;

    public List<StudentEntity> getAllStudent()
    {
        return studentMapper.getAllStudent();
    }

    public StudentEntity selectStudentById(Long id){
        return studentMapper.selectStudentById(id);
    }//按id返回学生类

    public List<StudentEntity> searchStudentByName(String identity){ return studentMapper.searchStudentByName(identity); }

    public List<StudentEntity> searchStudentByAccount(String identity){ return studentMapper.searchStudentByAccount(identity); }

    public Boolean putStudentInfo(Long studentId,String account,String studentName,String email )
    {
        return studentMapper.putStudentInfo(studentId,account,studentName,email);
    }

    public Boolean putStudentPassword(Long studentId )
    {
        return studentMapper.putStudentPassword(studentId);
    }

    public void deleteStudent(Long studentId)
    {
        studentMapper.deleteStudent(studentId);
    }

    public Boolean activateStudent(Long studentId, String password,  String email){return studentMapper.activateStudent(studentId, password, email);}
}
