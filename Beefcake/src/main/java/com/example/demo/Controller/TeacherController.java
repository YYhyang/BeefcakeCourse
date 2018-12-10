package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class TeacherController {

    @RequestMapping(value="/Teacher",method = RequestMethod.POST)   //管理员创建一个教师
    public void creatTeacher()
    {

    }

    @RequestMapping(value="/Teacher",method = RequestMethod.GET)  //管理员获得所有教师信息
    public void getAll( ){

    }

    @RequestMapping(value="/Teacher/searchTeacher",method = RequestMethod.GET)  //管理员根据姓名或教工号查询教师
    public void searchTeacher(@RequestParam("identity")String identity ){

    }

    @RequestMapping(value="/teacher/{teacherId}/information",method = RequestMethod.PUT)  //管理员修改某一教师的信息
    public void putTeacherInfo(@RequestParam("teacherId")Integer teacherId,@RequestParam("account")String account,@RequestParam("name")String name,@RequestParam("email")String email ){

    }

    @RequestMapping(value="teacher/{teacherId}/password",method = RequestMethod.PUT)  //管理员重置某一教师的密码
    public void putTeacherPassword(@RequestParam("teacherId")Integer teacherId ){

    }

    @RequestMapping(value="teacher/{teacherId}",method = RequestMethod.DELETE)  //管理员按ID删除某一教师
    public void deleteTeacher(@RequestParam("teacherId")Integer teacherId ){

    }

}
