package com.example.demo.Dao;

import com.example.demo.Entity.CourseEntity;
import com.example.demo.Entity.RoundEntity;
import com.example.demo.Mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class CourseDao {
    @Autowired
    private CourseMapper courseMapper;

    public void createCourse(Long teacherId,String courseName,String introduction,int pPercent,int qPercent,int rPercent,Date teamStartTime,Date teamEndTime){
        courseMapper.createCourse(teacherId,courseName,introduction,pPercent,qPercent,rPercent,teamStartTime,teamEndTime);
    }

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
    public String getTeamMainCourseId(Long courseId){return courseMapper.getTeamMainCourseId(courseId);}

    public List<CourseEntity> getAllExistCourse(){
        return courseMapper.getAllExistCourse();
    }
}
