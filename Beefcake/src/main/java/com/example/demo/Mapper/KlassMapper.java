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

    void createKlass(@Param("courseId") Long courseId, @Param("grade") int grade, @Param("klassSerial") int klassSerial, @Param("klassTime") String klassTime, @Param("klassLocation") String klassLocation);

    void deleteKlass(@Param("klassId") Long klassId);

    void insertStudentIntoKlassStudent(@Param("klassId") Long klassId, @Param("studentId") Long studentId, @Param("courseId") Long courseId);

    boolean createStudent(@Param("account") String account, @Param("password") String password, @Param("isActive") int isActive, @Param("studentName") String studentName);

    Long getKlassId(@Param("courseId") Long courseId, @Param("grade") int grade, @Param("klassSerial") int klassSerial);

    Integer getKlassSerial(@Param("klassId") Long klassId);

    StudentEntity selectStudentByAccount(@Param("account") String account);

    ClassEntity getKlassById(@Param("klassId") Long klassId);

    List<Long> getAllKlassId(@Param("courseId") Long courseId);


}
