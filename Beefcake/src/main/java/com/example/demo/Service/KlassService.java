package com.example.demo.Service;

import com.example.demo.Dao.KlassDao;
import com.example.demo.Dao.TeamDao;
import com.example.demo.Entity.ClassEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

    public List<ClassEntity> getKlassesByCourseId(Long courseId){
        List<Long> klassesId = klassDao.getAllKlassId(courseId);
        List<ClassEntity> classEntities = new ArrayList<>();
        for(Long klassId:klassesId){
            classEntities.add(getKlassById(klassId));
        }
        return classEntities;
    }

    public void createKlass(Long courseId,int grade,int klassSerial,String klassTime,String klassLocation){
        klassDao.createKlass(courseId,grade,klassSerial,klassTime,klassLocation);
    }

    public Long getKlassId(Long courseId,int grade,int klassSerial){
        return klassDao.getKlassId(courseId,grade,klassSerial);
    }
}
