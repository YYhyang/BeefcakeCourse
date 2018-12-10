package com.example.demo.Service;

import com.example.demo.Dao.TeamSeminarDao;
import com.example.demo.Entity.TeamSeminarEntity;
import com.example.demo.Entity.TeamStudentEntity;
import com.example.demo.Mapper.TeamSeminarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class SeminarService {

    @Autowired
    private TeamSeminarMapper teamSeminarMapper;

    public List<TeamSeminarEntity> findAllTeam(Long seminarId) {
        return teamSeminarMapper.findAll(seminarId);
    }

    public TeamSeminarEntity find(long id,long no)
    {
        return teamSeminarMapper.find(id, no);
    }

    public boolean save(Long seminarId, Long teamId, int no){
        return teamSeminarMapper.save(seminarId, teamId, no);
    }

    public boolean start(Long seminarId){
        return teamSeminarMapper.start(seminarId);
    }

}