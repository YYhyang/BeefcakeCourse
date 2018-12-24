package com.example.demo.Mapper;

import com.example.demo.Entity.TeamEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface TeamMapper {

    public boolean postTeam(@Param("klassId") Long klassId, @Param("courseId") Long courseId, @Param("leaderId") Long leaderId, @Param("teamName") String teamName, @Param("status") int status);

    public Long returnId(@Param("klassId") Long klassId, @Param("courseId") Long courseId, @Param("teamName") String teamName);

    public TeamEntity getTeamById(@Param("teamId") Long teamId);

    public void deleteTeam(@Param("teamId") Long teamId);

    public void changeTeamStatus(@Param("teamId") Long teamId, @Param("status") int status);

    public List<Long> getAllTeamIdByKlassId(@Param("klassId") Long klassId);
}
