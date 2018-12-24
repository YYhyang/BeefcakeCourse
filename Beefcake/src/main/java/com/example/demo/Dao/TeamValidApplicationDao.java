package com.example.demo.Dao;

import com.example.demo.Mapper.TeamValidApplicationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeamValidApplicationDao {
    @Autowired
    private TeamValidApplicationMapper teamValidApplicationMapper;

    public void createRequest(Long teamId, Long teacherId, String reason){
        teamValidApplicationMapper.createRequest(teamId,teacherId,reason);
    }

    public void changeApplicationStatus(Long teamId, int status){
        teamValidApplicationMapper.changeApplicationStatus(teamId,status);
    }
}
