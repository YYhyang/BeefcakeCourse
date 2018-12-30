package com.example.demo.mapper;

import com.example.demo.strategy.TeamOrStrategy;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface TeamOrStrategyMapper {

    public int addTeamOrStrategy(@Param("id")Long id,@Param("strategyName")String strategyName,@Param("strategyId")Long strategyId);

    public Long getId();

    List<TeamOrStrategy> selectTeamOrStrategyById(@Param("id")Long id);

}
