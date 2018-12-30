package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface KlassStudentMapper {

    void addTeamMember( @Param("studentId") Long studentId, @Param("teamId") Long teamId);

    void deleteTeamMember(@Param("studentId") Long studentId, @Param("teamId") Long teamId);

    /**
     * 获得某一学生在某一班级下的teamId
     *
     * @param klassId
     * @param studentId
     * @return
     */
    Long getTeamId(@Param("klassId") Long klassId, @Param("studentId") Long studentId);

    /**
     * 获得某一学生在某一课程下的班级Id
     *
     * @param courseId
     * @param studentId
     * @return
     */
    Long getKlassIdByStudentId(@Param("courseId") Long courseId, @Param("studentId") Long studentId);

    List<Long> getStudentIdByTeamId(@Param("teamId") Long teamId);

    List<Long> getCoursesIdByStudentId(@Param("studentId") Long studentId);

    List<Long> getNoTeamStudentId(@Param("courseId")Long courseId);

    //*********未实现************
    Long selectTeamMember(@Param("teamId") Long teamId, @Param("studentId") Long studentId);
}
