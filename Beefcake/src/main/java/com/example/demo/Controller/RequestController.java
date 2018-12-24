package com.example.demo.Controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class RequestController {

    @RequestMapping(value = "/request/share",method = RequestMethod.GET)//获得共享申请列表
    public void getAllShare(@RequestParam("handletype")String handletype)
    {

    }

    @RequestMapping(value = "/request/share/{shareId}",method = RequestMethod.GET)
    public void getShare(@PathVariable("shareId")int shareId,@RequestParam("handletype")String handletype)
    {

    }

    @RequestMapping(value = "/request/{requestId}/share",method = RequestMethod.PUT)//根据id修改共享请求的状态
    public void changeStatus(@PathVariable("requestId")int requestId)
    {

    }

    @RequestMapping(value = "/request/team/teamvalid",method = RequestMethod.GET)//获取组队申请信息
    public void getAllTeamRequest(@RequestParam("handletype")String handletype,@RequestParam("requesttype")String requesttype)
    {

    }

    @RequestMapping(value = "/request/team/{teamId}/teamvalid",method = RequestMethod.GET)
    public void getTeamRequest(@PathVariable("teamId")int teamId,@RequestParam("handletype")String handletype,@RequestParam("requesttype")String requesttype)
    {

    }

    @RequestMapping(value = "/request/{requestId}/team/teamvalid",method = RequestMethod.PUT)//修改请求状态
    public void changeTeam(@PathVariable("requestId")int requestId)
    {

    }
}
