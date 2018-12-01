package com.example.demo.Controller;

import com.example.demo.Dao.TeamSeminarDao;
import com.example.demo.Entity.TeamSeminarEntity;
import com.example.demo.Service.SeminarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SeminarController {
    @Autowired
    private TeamSeminarDao teamSeminarDao;
    @Autowired
    private SeminarService seminarService;
    @RequestMapping(value="/Seminar/Team",method = RequestMethod.POST)
    public List<TeamSeminarEntity> findAll(@RequestParam("seminarId")long seminarid)
    {
        return seminarService.findAllTeam(seminarid);
    }

    @RequestMapping(value="/Seminar/Team/1",method = RequestMethod.POST)
    public TeamSeminarEntity find(@RequestParam("teamId")long id, @RequestParam("no")long no)
    {
        return teamSeminarDao.find(id,no);
    }
    @RequestMapping(value = "/Seminar/Save",method = RequestMethod.POST)
    public boolean teamsave(@RequestParam("seminarId")Long seminarId,@RequestParam("teamId")Long teamId,@RequestParam("no")int no)
    {
        return teamSeminarDao.save(seminarId, teamId, no);
    }

    @RequestMapping(value = "/Seminar/Start",method = RequestMethod.GET)
    public boolean start(@RequestParam("seminarId")Long seminarId)
    {
        return teamSeminarDao.start(seminarId);
    }

}
