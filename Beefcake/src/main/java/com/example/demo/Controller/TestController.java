package com.example.demo.Controller;

import com.example.demo.DTO.TeamMemberDTO;
import com.example.demo.Dao.CourseDao;
import com.example.demo.Entity.CourseEntity;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Mapper.*;
import com.example.demo.Sercurity.MyUserService;
import com.example.demo.Service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping(value = "/test1",method = RequestMethod.POST)
    public List<TeamMemberDTO>  test(@RequestBody List<TeamMemberDTO> members)
    {
        if(members==null)
            System.out.println("members is null");
        return members;
    }
}
