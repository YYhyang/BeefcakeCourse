package com.example.demo.Service;

import com.example.demo.Dao.JwtDao;
import com.example.demo.Dao.StudentDao;
import com.example.demo.Entity.StudentEntity;
import com.example.demo.Sercurity.JWTPayLoad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    public Boolean putStudentInfo(Long studentId,String account,String student_name,String email )
    {
        return studentDao.putStudentInfo(studentId,account,student_name,email);
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
        Long jwt_studentId = jwtPayLoad.getId();
        return studentDao.activateStudent(jwt_studentId,password,email);
    }
}
