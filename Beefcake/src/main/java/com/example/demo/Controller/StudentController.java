package com.example.demo.controller;

import com.example.demo.dto.UserInfoDTO;
import com.example.demo.entity.StudentEntity;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.vo.UserInfoVO;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentMapper studentMapper;

    @RequestMapping(value = "/student",method = RequestMethod.GET)//管理员获得所有学生信息
    public List<UserInfoVO> getAllStudent()
    {
        List<UserInfoVO> list =new ArrayList<>();
        List<StudentEntity> students=studentService.getAllStudent();
        for(StudentEntity temp:students){
            UserInfoVO vo=new UserInfoVO();
            vo.setId(temp.getId());
            vo.setAccount(temp.getAccount());
            vo.setName(temp.getStudent_name());
            vo.setEmail(temp.getEmail());
            list.add(vo);
        }
        return list;
    }

    @RequestMapping(value = "/student/searchstudent",method = RequestMethod.GET)
    public List<UserInfoVO> searchStudent(@RequestParam("identity")String identity)//管理员根据姓名或学号查询学生
    {
        List<UserInfoVO> list =new ArrayList<>();
        List<StudentEntity> students=studentService.searchStudent(identity);
        for(StudentEntity temp:students){
            UserInfoVO vo=new UserInfoVO();
            vo.setId(temp.getId());
            vo.setAccount(temp.getAccount());
            vo.setName(temp.getStudent_name());
            vo.setEmail(temp.getEmail());
            list.add(vo);
        }
        return list;
    }

    @RequestMapping(value = "/student/{studentId}/information",method = RequestMethod.PUT)
    public UserInfoVO putStudentInfo(@PathVariable("studentId")Long studentId, @RequestBody UserInfoDTO dto)//管理员修改某一学生信息
    {
        if(studentService.putStudentInfo(studentId,dto.getAccount(),dto.getName(),dto.getEmail())) {
            UserInfoVO vo = new UserInfoVO();
            vo.setId(studentId);
            vo.setAccount(dto.getAccount());
            vo.setName(dto.getName());
            vo.setEmail(dto.getEmail());
            return vo;
        }
        else {
            //异常处理之后
            return new UserInfoVO();
        }
    }

    @RequestMapping(value= "/student/{studentId}/password",method = RequestMethod.PUT)
    public UserInfoVO putStudentPassword(@PathVariable("studentId")Long studentId)//管理员重置学生密码
    {
        if(studentService.putStudentPassword(studentId)) {
            UserInfoVO vo=new UserInfoVO();
            StudentEntity student=studentService.getStudentById(studentId);
            vo.setId(student.getId());
            vo.setAccount(student.getAccount());
            vo.setName(student.getStudent_name());
            vo.setEmail(student.getEmail());
            return vo;
        }
        else {
            //异常处理之后
            return new UserInfoVO();
        }
    }

    @RequestMapping(value = "/student/{studentId}",method = RequestMethod.DELETE)
    public void deleteStudent(@PathVariable("studentId")Long studentId)//管理员按ID删除某学生
    {
        studentService.deleteStudent(studentId);
    }

    @RequestMapping(value = "/student/active",method = RequestMethod.PUT)
    public boolean activateStudent(@RequestParam("password")String password, @RequestParam("email")String email, HttpServletRequest request)
    {
        return studentService.activeStudent(password,email,request);
    }
}

