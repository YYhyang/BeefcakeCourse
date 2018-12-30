package com.example.demo.dao;

import com.example.demo.mapper.TeamStrategyMapper;
import com.example.demo.strategy.TeamStrategyAdd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TeamStrategyDao {
    @Autowired
    private TeamStrategyMapper teamStrategyMapper;

    public boolean addTeamStrategy(TeamStrategyAdd teamStrategyAdd){
        List<TeamStrategyAdd> teamStrategyAddList=teamStrategyMapper.selectTeamStrategyByCourseId(teamStrategyAdd.getCourse().getId());
        Integer count=teamStrategyAddList.size();
        teamStrategyAdd.setStrategySerial(count+1);

        return teamStrategyMapper.addTeamStrategy(teamStrategyAdd.getCourse().getId(),teamStrategyAdd.getStrategySerial(),teamStrategyAdd.getStrategyName()
                                                ,teamStrategyAdd.getStrategyList().get(0).getId());
    }
}
