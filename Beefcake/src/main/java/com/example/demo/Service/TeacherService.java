package com.example.demo.service;

import com.example.demo.dao.JwtDao;
import com.example.demo.dao.TeacherDao;
import com.example.demo.entity.TeacherEntity;
import com.example.demo.sercurity.JWTPayLoad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component
public class TeacherService {
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private JwtDao jwtDao;

    public boolean activeTeacher(String password, HttpServletRequest request)
    {
        JWTPayLoad jwtPayLoad=jwtDao.getJwtPayLoad(request);
        Long jwtTeacherId = jwtPayLoad.getId();
        return teacherDao.activateTeacher(jwtTeacherId,password);
    }

    public Boolean createTeacher(String account,String password,String teacherName,String email){return teacherDao.createTeacher(account,password,teacherName,email);}

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

    public Boolean putTeacherInfo(Long teacherId,String account,String teacherName,String email ) {
        return teacherDao.putTeacherInfo(teacherId,account,teacherName,email);
    }

    public TeacherEntity getTeacherById(Long teacherId){
        return teacherDao.selectTeacherById(teacherId);
    }

    public Boolean putTeacherPassword(Long teacherId )
    {
        return teacherDao.putTeacherPassword(teacherId);
    }

    public String deleteTeacher(Long teacherId)
    {
        return teacherDao.deleteTeacher(teacherId);
    }
}
