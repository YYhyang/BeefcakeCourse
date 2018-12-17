package com.example.demo.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface KlassStudentMapper {
    public Long getTeamId(@Param("studentId")Long studentId);
}
