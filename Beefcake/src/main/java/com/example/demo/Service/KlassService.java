package com.example.demo.Service;

import com.example.demo.Dao.KlassDao;
import com.example.demo.Entity.ClassEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KlassService {
    @Autowired
    private KlassDao klassDao;
    @Autowired
    private TeamService teamService;

    public ClassEntity getKlassById(Long klassId){
        ClassEntity classEntity = klassDao.getKlassById(klassId);
        classEntity.setTeams(teamService.getAllTeamByKlassId(klassId));
        return classEntity;
    }
}
