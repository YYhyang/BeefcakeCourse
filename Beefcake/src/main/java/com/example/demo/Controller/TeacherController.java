package com.example.demo.Controller;

import com.example.demo.Entity.TeacherEntity;
import com.example.demo.Mapper.TeacherMapper;
import com.example.demo.Service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private TeacherMapper teacherMapper;

    @RequestMapping(value="/teacher",method = RequestMethod.POST)   //管理员创建一个教师
    public Boolean creatTeacher(@RequestParam("account")String account, @RequestParam("password")String password, @RequestParam("teacher_name")String teacher_name, @RequestParam("is_active")int is_active,@RequestParam("email")String email)
    {
        return teacherMapper.createTeacher(account,password,teacher_name,is_active,email);
    }

    @RequestMapping(value="/teacher",method = RequestMethod.GET)  //管理员获得所有教师信息
    public List<TeacherEntity> getAll( ){
        return teacherMapper.getAll();
    }

    @RequestMapping(value="/teacher/searchTeacher",method = RequestMethod.GET)  //管理员根据姓名或教工号查询教师
    public TeacherEntity searchTeacher(@RequestParam("identity")String identity ){
        return teacherService.searchTeacher(identity);
    }

    @RequestMapping(value="/teacher/{teacherId}/information",method = RequestMethod.PUT)  //管理员修改某一教师的信息
    public Boolean putTeacherInfo(@PathVariable("teacherId")Integer teacherId, @RequestParam("account")String account, @RequestParam("teacher_name")String teacher_name, @RequestParam("email")String email ){
        return teacherMapper .putTeacherInfo(teacherId,account,teacher_name,email);
    }

    @RequestMapping(value="teacher/{teacherId}/password",method = RequestMethod.PUT)  //管理员重置某一教师的密码
    public Boolean putTeacherPassword(@PathVariable("teacherId")Integer teacherId ){
        return teacherMapper.putTeacherPassword(teacherId);
    }

    @RequestMapping(value="teacher/{teacherId}",method = RequestMethod.DELETE)  //管理员按ID删除某一教师
    public Boolean deleteTeacher(@PathVariable("teacherId")Integer teacherId ){
        return teacherMapper.deleteTeacher(teacherId);
    }

    @RequestMapping(value="teacher/active",method = RequestMethod.PUT)  //教师激活
    public Boolean activateTeacher(@PathVariable("teacherId")Integer teacherId,@RequestParam("password")String password ){
        return teacherMapper.activateTeacher(teacherId,password);
    }

}
