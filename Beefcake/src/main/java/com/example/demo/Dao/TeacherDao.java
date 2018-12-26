package com.example.demo.Dao;

import com.example.demo.Entity.TeacherEntity;
import com.example.demo.Mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TeacherDao {
    @Autowired
    private TeacherMapper teacherMapper;

    public Boolean createTeacher(String account,String password,String teacher_name,String email){return teacherMapper.createTeacher(account,password,teacher_name,email);}

    public List<TeacherEntity> getAllTeacher()
    {
        return teacherMapper.getAllTeacher();
    }

    public List<TeacherEntity> searchTeacherByName(String identity){ return teacherMapper.searchTeacherByName(identity); }

    public List<TeacherEntity> searchTeacherByAccount(String identity){ return teacherMapper.searchTeacherByAccount(identity); }

    public Boolean putTeacherInfo(Long teacherId,String account,String teacher_name,String email )
    {
        return teacherMapper.putTeacherInfo(teacherId,account,teacher_name,email);
    }

    public TeacherEntity selectTeacherById(Long id){
        return teacherMapper.selectTeacherById(id);
    }

    public Boolean putTeacherPassword(Long teacherId )
    {
        return teacherMapper.putTeacherPassword(teacherId);
    }

    public String deleteTeacher(Long teacherId)
    {
        if(teacherMapper.deleteTeacher(teacherId))
            return "success";
        else
            return "fail";
    }
}
