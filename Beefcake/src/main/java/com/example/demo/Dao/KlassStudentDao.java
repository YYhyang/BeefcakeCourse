package com.example.demo.dao;

import com.example.demo.mapper.KlassStudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KlassStudentDao {

    @Autowired
    private KlassStudentMapper klassStudentMapper;

    public Long selectTeamMember(Long teamId, Long studentId){
        return klassStudentMapper.selectTeamMember(teamId,studentId);
    }//查询小组里是否有某学生 返回其ID（未实现）

    public List<Long> getStudentIdByTeamId(Long teamId){
        return klassStudentMapper.getStudentIdByTeamId(teamId);
    }//返回所有在该小组的学生id

    public void addTeamMember(Long studentId, Long teamId){
        klassStudentMapper.addTeamMember(studentId,teamId);
    }//为某班级下的某学生增加小组信息

    public void deleteTeamMember(Long studentId, Long teamId){
        klassStudentMapper.deleteTeamMember(studentId,teamId);
    }//为某班级下的某学生删除小组信息

    public Long getKlassIdByStudentId(Long courseId,Long studentId){return klassStudentMapper.getKlassIdByStudentId(courseId, studentId);}

    public Long getTeamId(Long klassId, Long studentId){
        return klassStudentMapper.getTeamId(klassId,studentId);
    }//只能用班级和学生查询

    public List<Long> getNoTeamStudentsId(Long courseId){
        return klassStudentMapper.getNoTeamStudentsId(courseId);
    }

    public List<Long> getCoursesIdByStudentId(Long studentId){
        return klassStudentMapper.getCoursesIdByStudentId(studentId);
    }
}
