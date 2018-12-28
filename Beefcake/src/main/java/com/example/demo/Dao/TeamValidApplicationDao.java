package com.example.demo.dao;

import com.example.demo.entity.TeamValidApplicationEntity;
import com.example.demo.mapper.TeamValidApplicationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TeamValidApplicationDao {
    @Autowired
    private TeamValidApplicationMapper teamValidApplicationMapper;

    public void createRequest(Long teamId, Long teacherId, String reason){
        teamValidApplicationMapper.createRequest(teamId,teacherId,reason);
    }

    public TeamValidApplicationEntity getTeamValidRequest(Long requestId){
        return teamValidApplicationMapper.getTeamValidRequest(requestId);
    }

    public void changeApplicationStatus(Long teamId, int status){
        teamValidApplicationMapper.changeApplicationStatus(teamId,status);
    }

    public List<TeamValidApplicationEntity> getTeamValidRequests(Long teacherId){
        return teamValidApplicationMapper.getTeamValidRequests(teacherId);
    }


}
