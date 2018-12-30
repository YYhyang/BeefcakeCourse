package com.example.demo.mapper;

import com.example.demo.strategy.TeamAndStrategy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface TeamAndStrategyMapper {

    public Long getId();

    public boolean addTeamAndStrategy(@Param("id")Long id,@Param("strategyName")String strategyName,@Param("strategyId")Long strategyId);

    List<TeamAndStrategy> selectTeamAndStrategyById(Long id);
}
