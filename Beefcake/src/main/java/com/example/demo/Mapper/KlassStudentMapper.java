package com.example.demo.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface KlassStudentMapper {

    public List<Long> getStudentIdByTeamId(@Param("teamId") Long teamId);

    public Long selectTeamMember(@Param("teamId") Long teamId, @Param("studentId") Long studentId);//未实现

    public void addTeamMember(@Param("klassId") Long klassId, @Param("studentId") Long studentId, @Param("teamId") Long teamId);

    public void deleteTeamMember(@Param("studentId") Long studentId, @Param("teamId") Long teamId);

    public Long getTeamId(@Param("courseId") Long courseId, @Param("studentId") Long studentId);

    public List<Long> getNoTeamStudentsId(@Param("courseId") Long courseId);

    public List<Long> getCoursesIdByStudentId(@Param("studentId") Long studentId);
}
