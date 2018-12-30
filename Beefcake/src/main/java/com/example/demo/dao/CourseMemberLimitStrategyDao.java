package com.example.demo.dao;

import com.example.demo.mapper.CourseMemberLimitStrategyMapper;
import com.example.demo.mapper.KlassStudentMapper;
import com.example.demo.strategy.CourseMemberLimitStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CourseMemberLimitStrategyDao {
    @Autowired
    CourseMemberLimitStrategyMapper courseMemberLimitStrategyMapper;

    public boolean addCourseMemberLimitStrategy(CourseMemberLimitStrategy record){
        return courseMemberLimitStrategyMapper.addCourseMemberLimitStrategy(record);
    }
}
