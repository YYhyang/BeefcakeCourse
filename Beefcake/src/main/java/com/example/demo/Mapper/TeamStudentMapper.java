package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface TeamStudentMapper {
    List<Long> getTeamMembersId(@Param("teamId") Long teamId);
}
