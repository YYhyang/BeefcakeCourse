package com.example.demo.Dao;


import com.example.demo.Entity.TeamEntity;
import com.example.demo.Mapper.TeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TeamDao {

    @Autowired
    public TeamMapper teamMapper;

    public boolean postTeam(Long klassId, Long courseId, Long leaderId, String teamName, int status){
        return teamMapper.postTeam(klassId,courseId,leaderId,teamName,status);
    }

    public Long returnId(Long klassId, Long courseId, String teamName){
        return teamMapper.returnId(klassId,courseId,teamName);
    }

    public TeamEntity getTeamById(Long teamId) {
        return teamMapper.getTeamById(teamId);
    }

    public void deleteTeam(Long teamId){
        teamMapper.deleteTeam(teamId);
    }

    public List<Long> getAllTeamIdByKlassId(Long klassId){
        return teamMapper.getAllTeamIdByKlassId(klassId);
    }

    public void changeTeamStatus(Long teamId, int status){
        teamMapper.changeTeamStatus(teamId,status);
    }
}
