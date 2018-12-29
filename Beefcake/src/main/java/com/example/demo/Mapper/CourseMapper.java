package com.example.demo.mapper;

import com.example.demo.entity.CourseEntity;
import com.example.demo.entity.RoundEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Mapper
@Component
public interface CourseMapper {

    void createCourse(@Param("teacherId") Long teacherId, @Param("courseName") String courseName, @Param("introduction") String introduction, @Param("pPercent") int pPercent, @Param("qPercent") int qPercent, @Param("rPercent") int rPercent, @Param("teamStartTime") Date teamStartTime, @Param("teamEndTime") Date teamEndTime);

    void deleteTeamMainCourseId(@Param("courseId") Long courseId);

    void setTeamMainCourseId(@Param("courseId") Long courseId, @Param("teamMainCourseId") Long teamMainCourseId);

    void deleteCourse(@Param("courseId") Long courseId);

    Long getTeacherId(@Param("courseId") Long courseId);

    Long getCourseId(@Param("teacherId") Long teacherId, @Param("courseName") String courseName);

    Date getTeamEndTime(@Param("courseId") Long courseId);

    Long getTeamMainCourseId(@Param("courseId") Long courseId);

    CourseEntity getCourseById(@Param("courseId") Long courseId);

    List<Long>getTeamIdByCourseId(@Param("courseId") Long courseId);

    /**
     * 获得某一教师创建的所有课程
     *
     * @param teacherId
     * @return
     */
    List<CourseEntity> getCoursesByTeacherId(@Param("teacherId") Long teacherId);

    /**
     * 获得所有课程
     *
     * @return
     */
    List<CourseEntity> getAllExistCourse();

    List<RoundEntity> getRoundByCourseId(@Param("courseId") Long courseId);

}
