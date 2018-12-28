package com.example.demo.dao;

import com.example.demo.entity.ClassEntity;
import com.example.demo.entity.StudentEntity;
import com.example.demo.mapper.KlassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KlassDao {
    @Autowired
    private KlassMapper klassMapper;

    public ClassEntity getKlassById(Long klassId){
        return klassMapper.getKlassById(klassId);
    }

    public List<Long> getAllKlassId(Long courseId){
        return klassMapper.getAllKlassId(courseId);
    }

    public void createKlass(Long courseId,int grade,int klassSerial,String klassTime,String klassLocation){
        klassMapper.createKlass(courseId,grade,klassSerial,klassTime,klassLocation);
    }

    public Long getKlassId(Long courseId,int grade,int klassSerial){
        return klassMapper.getKlassId(courseId,grade,klassSerial);
    }

    public Integer getKlassSerial(Long klassId){return klassMapper.getKlassSerial(klassId);}
    public void deleteKlass(Long klassId){
        klassMapper.deleteKlass(klassId);
    }

    public void createStudent(StudentEntity student)
    {
        klassMapper.createStudent(student.getAccount(),student.getPassword(),student.getIs_active(),student.getStudent_name());
    }



    public StudentEntity selectStudentByAccount(String account)
    {
        return klassMapper.selectStudentByAccount(account);
    }

    public void insertStudentIntoKlassStudent(Long klassId,Long studentId,Long courseId)
    {
        klassMapper.insertStudentIntoKlassStudent(klassId, studentId, courseId);
    }
}
