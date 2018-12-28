package com.example.demo.mapper;

import com.example.demo.entity.ClassEntity;
import com.example.demo.entity.StudentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface KlassMapper {

    public ClassEntity getKlassById(@Param("klassId")Long klassId);

    public List<Long> getAllKlassId(@Param("courseId")Long courseId);

    public void createKlass(@Param("courseId")Long courseId,@Param("grade")int grade,@Param("klassSerial")int klassSerial,@Param("klassTime")String klassTime,@Param("klassLocation")String klassLocation);

    public Long getKlassId(@Param("courseId")Long courseId,@Param("grade")int grade,@Param("klassSerial")int klassSerial);

    public Integer getKlassSerial(@Param("klassId")Long klassId);

    public void deleteKlass(@Param("klassId")Long klassId);

    public StudentEntity selectStudentByAccount(@Param("account")String account);

    public void insertStudentIntoKlassStudent(@Param("klassId")Long klassId,@Param("studentId")Long studentId,@Param("courseId")Long courseId);

    public boolean createStudent(@Param("account")String account,@Param("password")String password,@Param("isActive")int isActive,@Param("studentName")String studentName);
}
