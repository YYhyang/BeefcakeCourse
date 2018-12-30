package com.example.demo.dao;

import com.example.demo.mapper.TeamAndStrategyMapper;
import com.example.demo.strategy.TeamAndStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TeamAndStrategyDao {
    @Autowired
    private TeamAndStrategyMapper teamAndStrategyMapper;
    public List<TeamAndStrategy> addTeamAndStrategyList(List<TeamAndStrategy>teamAndStrategies){
        Long teamAndStrategyId=teamAndStrategyMapper.getId()+1;
        for(TeamAndStrategy teamAndStrategy:teamAndStrategies){
            teamAndStrategyMapper.addTeamAndStrategy(teamAndStrategyId,teamAndStrategy.getStrategyName(),teamAndStrategy.getStrategyList().get(0).getId());
        }
        return teamAndStrategyMapper.selectTeamAndStrategyById(teamAndStrategyId);
    }
}
