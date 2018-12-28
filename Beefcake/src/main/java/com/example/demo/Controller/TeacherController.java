package com.example.demo.controller;

import com.example.demo.dto.CreateTeacherDTO;
import com.example.demo.dto.UserInfoDTO;
import com.example.demo.entity.TeacherEntity;
import com.example.demo.mapper.TeacherMapper;
import com.example.demo.service.TeacherService;
import com.example.demo.vo.UserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private TeacherMapper teacherMapper;

    @RequestMapping(value="/teacher",method = RequestMethod.POST)   //管理员创建一个教师
    public UserInfoVO createTeacher(@RequestBody CreateTeacherDTO dto)
    {
        if(teacherService.createTeacher(dto.getAccount(),dto.getPassword(),dto.getName(),dto.getEmail())) {
            UserInfoVO vo=new UserInfoVO();
            int i=0;
            vo.setId((teacherService.searchTeacher(dto.getAccount())).get(i).getId());
            vo.setAccount(dto.getAccount());
            vo.setName(dto.getName());
            vo.setEmail(dto.getEmail());
            return vo;
        }
        else {
            //待异常处理之后
            return new UserInfoVO();
        }
    }

    @RequestMapping(value="/teacher",method = RequestMethod.GET)  //管理员获得所有教师信息
    public List<UserInfoVO> getAllTeacher() {
        List<UserInfoVO> list =new ArrayList<>();
        List<TeacherEntity> teachers=teacherService.getAllTeacher();
        for(TeacherEntity temp:teachers){
            UserInfoVO vo=new UserInfoVO();
            vo.setId(temp.getId());
            vo.setAccount(temp.getAccount());
            vo.setName(temp.getTeacher_name());
            vo.setEmail(temp.getEmail());
            list.add(vo);
        }
        return list;
    }

    @RequestMapping(value="/teacher/searchteacher",method = RequestMethod.GET)  //管理员根据姓名或教工号查询教师（同名找到多个人）
    public List<UserInfoVO> searchTeacher(@RequestParam("identity")String identity ){
        List<UserInfoVO> list =new ArrayList<>();
        List<TeacherEntity> teachers=teacherService.searchTeacher(identity);
        for(TeacherEntity temp:teachers){
            UserInfoVO vo=new UserInfoVO();
            vo.setId(temp.getId());
            vo.setAccount(temp.getAccount());
            vo.setName(temp.getTeacher_name());
            vo.setEmail(temp.getEmail());
            list.add(vo);
        }
        return list;
    }

    @RequestMapping(value="/teacher/{teacherId}/information",method = RequestMethod.PUT)  //管理员修改某一教师的信息
    public UserInfoVO putTeacherInfo(@PathVariable("teacherId")Long teacherId, @RequestBody UserInfoDTO dto){
        if(teacherService.putTeacherInfo(teacherId,dto.getAccount(),dto.getName(),dto.getEmail())) {
            UserInfoVO vo = new UserInfoVO();
            vo.setId(teacherId);
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

    @RequestMapping(value="/teacher/{teacherId}/password",method = RequestMethod.PUT)  //管理员重置某一教师的密码
    public UserInfoVO putTeacherPassword(@PathVariable("teacherId")Long teacherId ){
        if(teacherService.putTeacherPassword(teacherId)) {
            UserInfoVO vo=new UserInfoVO();
            TeacherEntity teacher=teacherService.getTeacherById(teacherId);
            vo.setId(teacherId);
            vo.setAccount(teacher.getAccount());
            vo.setName(teacher.getTeacher_name());
            vo.setEmail(teacher.getEmail());
            return vo;
        }
        else {
            //异常处理之后
            return new UserInfoVO();
        }
    }

    @RequestMapping(value="/teacher/{teacherId}",method = RequestMethod.DELETE)  //管理员按ID删除某一教师
    public String deleteTeacher(@PathVariable("teacherId")Long teacherId ){
        return teacherService.deleteTeacher(teacherId);
    }

    @RequestMapping(value="/teacher/active",method = RequestMethod.PUT)  //教师激活
    public Boolean activateTeacher(@RequestParam("password")String password, HttpServletRequest request){
        return teacherService.activeTeacher(password,request);
    }

}

