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

    public Date getTeamEndTime(@Param("courseId") Long courseId);

    public void deleteCourse(@Param("courseId") Long courseId);

    public List<CourseEntity> getCoursesByTeacherId(@Param("teacherId") Long teacherId);
}
