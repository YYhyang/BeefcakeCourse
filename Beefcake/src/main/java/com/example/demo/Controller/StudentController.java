package com.example.demo.Controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

    @RequestMapping(value = "/student",method = RequestMethod.GET)//管理员获得所有学生信息
    public void getAllStudent()
    {

    }

    @RequestMapping(value = "/student/searchstudent",method = RequestMethod.GET)
    public void findStudent(@RequestParam("identity")String identity)//管理员根据姓名或学号查询学生
    {

    }

    @RequestMapping(value = "/student/{studentId}/information",method = RequestMethod.PUT)
    public void changeInfo(@PathVariable("studentId")int studentId)//管理员修改某一学生信息
    {

    }

    @RequestMapping(value="/student/{studentId}/password",method = RequestMethod.PUT)
    public void resetPassword(@PathVariable("studentId")int studentId)
    {

    }

    @RequestMapping(value = "/student/{studentId}",method = RequestMethod.DELETE)
    public void deleteStudent(@PathVariable("studentId")int studentId)
    {

    }

}
