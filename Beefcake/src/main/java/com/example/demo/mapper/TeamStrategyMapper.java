package com.example.demo.mapper;

import com.example.demo.strategy.TeamStrategy;
import com.example.demo.strategy.TeamStrategyAdd;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface TeamStrategyMapper {

    boolean addTeamStrategy(@Param("courseId")Long courseId,@Param("strategySerial")Integer strategySerial,@Param("strategyName")String strategyName,@Param("strategyId")Long strategyId);

    List<TeamStrategyAdd> selectTeamStrategyByCourseId(Long id);
}
