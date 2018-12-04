package com.example.demo.Controller;

import com.example.demo.Dao.TeamSeminarDao;
import com.example.demo.Entity.TeamSeminarEntity;
import com.example.demo.Mapper.TeamSeminarMapper;
import com.example.demo.Service.SeminarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SeminarController {
    @Autowired
    private SeminarService seminarService;
    @RequestMapping(value="/Seminar/Team",method = RequestMethod.POST)
    public List<TeamSeminarEntity> findAll(@RequestParam("seminarId")long seminarid)
    {
        return seminarService.findAllTeam(seminarid);
    }

    @RequestMapping(value="/Seminar/Team/1",method = RequestMethod.POST)
    public TeamSeminarEntity find(@PathVariable("teamId")long id, @RequestParam("no")long no)
    {
        return seminarService.find(id,no);
    }
    @RequestMapping(value = "/Seminar/Save",method = RequestMethod.POST)
    public boolean teamsave(@RequestParam("seminarId")Long seminarId,@RequestParam("teamId")Long teamId,@RequestParam("no")int no)
    {
        return seminarService.save(seminarId, teamId, no);
    }

    @RequestMapping(value = "/Seminar/Start",method = RequestMethod.GET)
    public boolean start(@RequestParam("seminarId")Long seminarId)
    {
        return seminarService.start(seminarId);
    }


}
