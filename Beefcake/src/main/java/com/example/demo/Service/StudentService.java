package com.example.demo.service;

import com.example.demo.dao.JwtDao;
import com.example.demo.dao.StudentDao;
import com.example.demo.entity.StudentEntity;
import com.example.demo.sercurity.JWTPayLoad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component
public class StudentService {
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private JwtDao jwtDao;

    public List<StudentEntity> getAllStudent()
    {
        return studentDao.getAllStudent();
    }

    public List<StudentEntity> searchStudent(String identity ){
        for (int i = identity.length();--i>=0;){
            if (!Character.isDigit(identity.charAt(i))){ return studentDao.searchStudentByName(identity);}
        }
        return studentDao.searchStudentByAccount(identity);
    }

    public StudentEntity getStudentById(Long studentId){
        return studentDao.selectStudentById(studentId);
    }

    public Boolean putStudentInfo(Long studentId,String account,String studentName,String email )
    {
        return studentDao.putStudentInfo(studentId,account,studentName,email);
    }

    public Boolean putStudentPassword(Long studentId )
    {
        return studentDao.putStudentPassword(studentId);
    }

    public void deleteStudent(Long studentId)
    {
        studentDao.deleteStudent(studentId);
    }

    public boolean activeStudent(String password, String email, HttpServletRequest request)
    {
        JWTPayLoad jwtPayLoad=jwtDao.getJwtPayLoad(request);
        Long jwtStudentId = jwtPayLoad.getId();
        return studentDao.activateStudent(jwtStudentId,password,email);
    }
}
