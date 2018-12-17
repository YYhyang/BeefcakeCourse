package com.example.demo.Controller;

import com.example.demo.Entity.StudentEntity;
import com.example.demo.Mapper.StudentMapper;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentMapper studentMapper;

    @RequestMapping(value = "/student",method = RequestMethod.GET)//管理员获得所有学生信息
    public List<StudentEntity> getAllStudent()
    {
        return studentMapper.getAllStudent();
    }

    @RequestMapping(value = "/student/searchstudent",method = RequestMethod.GET)
    public StudentEntity searchStudent(@RequestParam("identity")String identity)//管理员根据姓名或学号查询学生
    {
        return studentService.searchStudent(identity);
    }

    @RequestMapping(value = "/student/{studentId}/information",method = RequestMethod.PUT)
    public Boolean putStudentInfo(@PathVariable("studentId")int studentId,@RequestParam("account")String account, @RequestParam("student_name")String student_name, @RequestParam("email")String email)//管理员修改某一学生信息
    {
        return studentMapper.putStudentInfo(studentId,account,student_name,email);
    }

    @RequestMapping(value="/student/{studentId}/password",method = RequestMethod.PUT)
    public Boolean putStudentPassword(@PathVariable("studentId")int studentId)//管理员重置学生密码
    {
        return studentMapper.putStudentPassword(studentId);
    }

    @RequestMapping(value = "/student/{studentId}",method = RequestMethod.DELETE)
    public Boolean deleteStudent(@PathVariable("studentId")int studentId)//管理员按ID删除某学生
    {
        return studentMapper.deleteStudent(studentId);
    }

    @RequestMapping(value = "/student/active",method = RequestMethod.PUT)
    public Boolean activateStudent(@PathVariable("studentId")int studentId,@RequestParam("password")String password,@RequestParam("email")String email)
    {
        return studentMapper.activateStudent(studentId,password,email);
    }
}
