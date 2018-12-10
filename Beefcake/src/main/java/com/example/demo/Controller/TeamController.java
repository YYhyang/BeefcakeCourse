package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

public class TeamController {

    //尚未完善
    @RequestMapping(value="/team",method = RequestMethod.POST)  //新建队伍
    public void postTeam(@RequestParam("name")String name,@RequestParam("courseId")Integer courseId,@RequestParam("classId")Integer classId ){

    }

    @RequestMapping(value="/team/{teamId}",method = RequestMethod.GET)  //按ID获取队伍信息
    public void postTeam(@RequestParam("teamId")Integer teamId ){

    }

    //尚未完善
    @RequestMapping(value="/team/{teamId}",method = RequestMethod.POST)  //更新队伍
    public void updateTeam(@RequestParam("name")String name,@RequestParam("courseId")Integer courseId,@RequestParam("classId")Integer classId ){

    }

    @RequestMapping(value="/team/{teamId}",method = RequestMethod.DELETE)  //按ID删除队伍及队长解散队伍
    public void deleteTeam(@RequestParam("teamId")Integer teamId ){

    }

    @RequestMapping(value="/team/{teamId}/add",method = RequestMethod.PUT)  //添加成员
    public void putTeam(@RequestParam("teamId")Integer teamId,@RequestParam("studentId")Integer studentId  ){

    }

    @RequestMapping(value="/team/{teamId}/remove",method = RequestMethod.PUT)  //移除成员和退出队伍
    public void removeTeam(@RequestParam("teamId")Integer teamId,@RequestParam("studentId")Integer studentId  ){

    }

    @RequestMapping(value="/team/{teamId}/teamvalidrequest",method = RequestMethod.POST)  //发起队伍状态申请
    public void teamRequest(@RequestParam("requestType")String requestType,@RequestParam("courseId")Integer courseId,@RequestParam("classId")Integer classId,@RequestParam("teamId")Integer teamId,@RequestParam("leaderId")Integer leaderId,@RequestParam("reason")String reason ){

    }

    @RequestMapping(value="/team/{teamId}/approve",method = RequestMethod.PUT)  //老师同意学生组队合法
    public void approveTeam(@RequestParam("teamId")Integer teamId ){

    }

}
