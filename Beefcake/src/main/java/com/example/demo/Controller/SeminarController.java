package com.example.demo.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SeminarController {
    @RequestMapping(value = "/seminar",method = RequestMethod.POST)
    public void createSeminar()
    {

    }

    @RequestMapping(value = "/seminar/{seminarId}/class",method = RequestMethod.GET)//获取讨论课所属的班级
    public void getClass(@PathVariable("seminarId")int seminarId)
    {

    }

    @RequestMapping(value = "/seminar/{seminarId}",method = RequestMethod.PUT)//修改讨论课
    public void changeSeminar(@PathVariable("seminarId")int seminarId)
    {

    }

    @RequestMapping(value = "/seminar/{seminarId}",method = RequestMethod.DELETE)
    public void deleteSeminar(@PathVariable("seminarId")int seminarId)
    {

    }

    @RequestMapping(value = "/seminar/{seminarId}",method = RequestMethod.GET)
    public void getSeminar(@PathVariable("seminarId")int seminarId)
    {

    }

    @RequestMapping(value = "/seminar/{seminarId}/round",method = RequestMethod.PUT)//设置讨论课轮次
    public void setRound(@PathVariable("seminarId")int seminarId)
    {

    }

    @RequestMapping(value = "/seminar/{seminarId}/score",method = RequestMethod.GET)//根据ID查成绩
    public void getScore(@PathVariable("seminarId")int seminarId)
    {

    }
}
