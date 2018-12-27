package com.example.demo.Service;

import com.example.demo.Dao.JwtDao;
import com.example.demo.Dao.TeacherDao;
import com.example.demo.Entity.TeacherEntity;
import com.example.demo.Sercurity.JWTPayLoad;
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
        Long jwt_teacherId = jwtPayLoad.getId();
        System.out.println("Id:"+jwt_teacherId);
        return teacherDao.activateTeacher(jwt_teacherId,password);
    }

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

    public String deleteTeacher(Long teacherId)
    {
        return teacherDao.deleteTeacher(teacherId);
    }
}
