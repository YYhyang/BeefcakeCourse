package com.example.demo.Controller;

import com.example.demo.Entity.UserEntity;
import com.example.demo.Mapper.AttendanceMapper;
import com.example.demo.Mapper.KlassStudentMapper;
import com.example.demo.Mapper.TeamMapper;
import com.example.demo.Sercurity.MyUserService;
import com.example.demo.Service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    @RequestMapping("hello")
    public String hello(){
        return "Hello spring security";
    }
    @RequestMapping("admin")
    public String admin(){return "Admin";}
    @RequestMapping(value = "/test1",method = RequestMethod.GET)
    public boolean test(@RequestParam("teamId")Long TeamId)
    {
        return teamService.isValid(TeamId);
    }
}
