package com.example.demo.Mapper;

import com.example.demo.Entity.ClassEntity;
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
}
