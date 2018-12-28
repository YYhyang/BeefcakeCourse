package com.example.demo.controller;

import com.example.demo.dao.CourseDao;
import com.example.demo.entity.StudentEntity;
import com.example.demo.mapper.*;
import com.example.demo.sercurity.MyUserService;
import com.example.demo.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {
    @Autowired
    MyUserService myUserService;
    @Autowired
    KlassStudentMapper klassStudentMapper;
    @Autowired
    AttendanceMapper attendanceMapper;
    @Autowired
    TeamService teamService;
    @Autowired
    TeamMapper teamMapper;
    @Autowired
    KlassMapper klassMapper;
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    CourseDao courseDao;
    @RequestMapping("hello")
    public String hello(){
        return "Hello spring security";
    }
    @RequestMapping("admin")
    public String admin(){return "Admin";}
    @RequestMapping(value = "/test1",method = RequestMethod.GET)
    public StudentEntity test(@RequestParam("account")String account)
    {
        return klassMapper.selectStudentByAccount(account);
    }
}
