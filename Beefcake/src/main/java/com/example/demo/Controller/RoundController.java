package com.example.demo.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoundController {

    @RequestMapping(value = "/round/{roundId}/seminar",method = RequestMethod.GET)//获取某轮次的所有讨论课
    public void getSeminar(@PathVariable("roundId")int roundId)
    {

    }

    @RequestMapping(value = "/round/{roundId}",method = RequestMethod.GET)
    public void getRound(@PathVariable("roundId")int roundId)
    {

    }

    @RequestMapping(value = "/round/{roundId}",method = RequestMethod.PUT)
    public void changeRound(@PathVariable("roundId")int roundId)
    {

    }

    @RequestMapping(value = "/round",method = RequestMethod.POST)
    public void createRound()
    {

    }
}
