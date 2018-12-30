package com.example.demo.dao;

import com.example.demo.mapper.TeamOrStrategyMapper;
import com.example.demo.strategy.TeamOrStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class TeamOrStrategyDao {
    @Autowired
    TeamOrStrategyMapper teamOrStrategyMapper;

    public List<TeamOrStrategy> addTeamOrStrategyList(List<TeamOrStrategy> teamOrStrategies){

        Long teamOrStrategyId=teamOrStrategyMapper.getId()+1;
        for(TeamOrStrategy teamOrStrategy:teamOrStrategies){
            teamOrStrategyMapper.addTeamOrStrategy(teamOrStrategyId,teamOrStrategy.getStrategyName(),teamOrStrategy.getStrategyList().get(1).getId());
        }

        return teamOrStrategyMapper.selectTeamOrStrategyById(teamOrStrategyId);

    }
}
