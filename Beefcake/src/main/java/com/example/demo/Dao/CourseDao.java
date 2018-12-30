package com.example.demo.dao;

import com.example.demo.entity.CourseEntity;
import com.example.demo.entity.RoundEntity;
import com.example.demo.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class CourseDao {
    @Autowired
    private CourseMapper courseMapper;

    public List<Long>getTeamIdByCourseId(Long courseId){return courseMapper.getTeamIdByCourseId(courseId);}

    public Long getTeacherId(Long courseId){
        return courseMapper.getTeacherId(courseId);
    }

    public Long getCourseId(Long teacherId,String courseName){
        return courseMapper.getCourseId(teacherId,courseName);
    }

    public CourseEntity getCourseById(Long courseId){
        return courseMapper.getCourseById(courseId);
    }

    public Date getTeamEndTime(Long courseId){
        return courseMapper.getTeamEndTime(courseId);
    }

    public void deleteCourse(Long courseId){
        courseMapper.deleteCourse(courseId);
    }

    public List<RoundEntity> getRoundByCourseId(Long courseId){return courseMapper.getRoundByCourseId(courseId);}

    public List<CourseEntity> getCoursesByTeacherId(Long teacherId){
        return courseMapper.getCoursesByTeacherId(teacherId);
    }

    public void deleteTeamMainCourseId(Long courseId){
        courseMapper.deleteTeamMainCourseId(courseId);
    }
    public void setTeamMainCourseId(Long courseId,Long teamMainCourseId){
        courseMapper.setTeamMainCourseId(courseId,teamMainCourseId);
    }
    public Long getTeamMainCourseId(Long courseId){return courseMapper.getTeamMainCourseId(courseId);}

    public List<CourseEntity> getAllExistCourse(){
        return courseMapper.getAllExistCourse();
    }

    public boolean addCourse(CourseEntity course){
        return courseMapper.addCourse(course);
    }
}
