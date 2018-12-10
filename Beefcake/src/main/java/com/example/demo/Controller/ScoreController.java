package com.example.demo.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScoreController {

    @RequestMapping(value = "/score",method = RequestMethod.POST)
    public void createScore()//参数未写
    {

    }

    @RequestMapping(value = "/score/{scoreId}",method = RequestMethod.GET)//按照id获取某次成绩
    public void getScore(@PathVariable("scoreId")int scoreId)
    {

    }

    @RequestMapping(value = "/score/{scoreId}",method = RequestMethod.PUT)//按id修改成绩
    public void changeScore(@PathVariable("scoreId")int scoreId)//参数未写
    {

    }
}
