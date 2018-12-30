package com.example.demo.dao;

import com.example.demo.mapper.StrategyMapper;
import com.example.demo.strategy.ConflictCourseStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ConflictCourseStrategyDao {
    @Autowired
    private StrategyMapper strategyMapper;

    public List<ConflictCourseStrategy> addConflictCourseStrategyList(List<ConflictCourseStrategy>conflictCourseStrategyList){
        Long conflictCourseStrategyId=strategyMapper.getMaxConflictId()+1;
        for(ConflictCourseStrategy conflictCourseStrategy:conflictCourseStrategyList){
            conflictCourseStrategy.setId(conflictCourseStrategyId);
            strategyMapper.addConflictCourseStrategy(conflictCourseStrategy);
        }
        return strategyMapper.selectConflictCourseStrategyById(conflictCourseStrategyId);
    }
}
