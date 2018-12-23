package com.example.demo.Dao;

import com.example.demo.Entity.CourseEntity;
import com.example.demo.Mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class CourseDao {
    @Autowired
    private CourseMapper courseMapper;

    public CourseEntity getCourseById(Long courseId){
        return courseMapper.getCourseById(courseId);
    }

    public Date getTeamEndTime(Long courseId){
        return courseMapper.getTeamEndTime(courseId);
    }

    public void deleteCourse(Long courseId){
        courseMapper.deleteCourse(courseId);
    }

    public List<CourseEntity> getCoursesByTeacherId(Long teacherId){
        return courseMapper.getCoursesByTeacherId(teacherId);
    }
}
