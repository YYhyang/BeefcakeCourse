package com.example.demo.mapper;

import com.example.demo.strategy.CourseMemberLimitStrategy;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface CourseMemberLimitStrategyMapper {

    public boolean addCourseMemberLimitStrategy(CourseMemberLimitStrategy record);
}
