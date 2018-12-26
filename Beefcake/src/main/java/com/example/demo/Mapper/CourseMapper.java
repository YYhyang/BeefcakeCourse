package com.example.demo.Mapper;

import com.example.demo.Entity.CourseEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Mapper
@Component
public interface CourseMapper {
    public CourseEntity getCourseById(@Param("courseId") Long courseId);

    public Long getTeacherId(@Param("courseId") Long courseId);

    public Long getCourseId(@Param("teacherId") Long teacherId, @Param("courseName") String courseName);

    public Date getTeamEndTime(@Param("courseId") Long courseId);

    public void deleteCourse(@Param("courseId") Long courseId);

    public List<CourseEntity> getCoursesByTeacherId(@Param("teacherId") Long teacherId);

    public void createCourse(@Param("teacherId") Long teacherId, @Param("courseName") String courseName, @Param("introduction") String introduction, @Param("pPercent") int pPercent, @Param("qPercent") int qPercent, @Param("rPercent") int rPercent, @Param("teamStartTime") Date teamStartTime, @Param("teamEndTime") Date teamEndTime);

    public void deleteTeamMainCourseId(@Param("courseId") Long courseId);

    public void deleteSeminarMainCourseId(@Param("courseId") Long courseId);
}
