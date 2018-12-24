package com.example.demo.Service;

import com.example.demo.Dao.TeacherDao;
import com.example.demo.Entity.TeacherEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TeacherService {
    @Autowired
    private TeacherDao teacherDao;

    public Boolean createTeacher(String account,String password,String teacher_name,String email){return teacherDao.createTeacher(account,password,teacher_name,email);}

    public List<TeacherEntity> getAllTeacher()
    {
        return teacherDao.getAllTeacher();
    }

    public List<TeacherEntity> searchTeacher(String identity ){
        for (int i = identity.length();--i>=0;){
            if (!Character.isDigit(identity.charAt(i))){ return teacherDao.searchTeacherByName(identity);}
        }
        return teacherDao.searchTeacherByAccount(identity);
    }

    public Boolean putTeacherInfo(Long teacherId,String account,String teacher_name,String email ) {
        return teacherDao.putTeacherInfo(teacherId,account,teacher_name,email);
    }

    public TeacherEntity getTeacherById(Long teacherId){
        return teacherDao.selectTeacherById(teacherId);
    }

    public Boolean putTeacherPassword(Long teacherId )
    {
        return teacherDao.putTeacherPassword(teacherId);
    }

    public void deleteTeacher(Long teacherId)
    {
        teacherDao.deleteTeacher(teacherId);
    }
}
