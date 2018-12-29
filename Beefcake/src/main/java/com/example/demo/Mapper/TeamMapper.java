package com.example.demo.mapper;

import com.example.demo.vo.TeamSimpleVO;
import com.example.demo.strategy.AndOrStrategy;
import com.example.demo.strategy.CourseMemberLimitStrategy;
import com.example.demo.strategy.MemberLimitStrategy;
import com.example.demo.entity.TeamEntity;
import com.example.demo.strategy.TeamStrategy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface TeamMapper {

    void deleteTeamFromTeamStudent(@Param("teamId") Long teamId);

    void deleteTeamFromKlassTeam(@Param("teamId") Long teamId);

    void changeTeamStatus(@Param("teamId") Long teamId, @Param("status") int status);

    void deleteTeam(@Param("teamId") Long teamId);

    boolean postTeam(@Param("klassId") Long klassId, @Param("courseId") Long courseId, @Param("leaderId") Long leaderId, @Param("teamName") String teamName
            , @Param("teamSerial") Integer teamSerial, @Param("klassSerial") Integer klassSerial);

    boolean createTeamInKlassTeam(@Param("klassId") Long klassId, @Param("teamId") Long teamId);

    boolean createTeamInTeamStudent(@Param("teamId") Long teamId, @Param("studentId") Long studentId);

    int getMaxTeamSerial(@Param("klassId") Long klassId);

    Long returnId(@Param("teamSerial") Integer teamSerial, @Param("klassSerial") Integer klassSerial);

    TeamEntity getTeamById(@Param("teamId") Long teamId);

    MemberLimitStrategy getMemberLimit(@Param("strategyId") Long strategyId);

    CourseMemberLimitStrategy getCourseMemberLimit(@Param("strategyId") Long strategyId);

    List<Long> getMemberIdByCourseId(@Param("courseId") Long courseId, @Param("teamId") Long teamId);

    List<Long>getConflictCourseId(@Param("strategyId") Long strategyId);

    List<Long> getAllTeamIdByKlassId(@Param("klassId") Long klassId);

    List<Long> getSomeMembersId(@Param("teamId") Long teamId, @Param("klassId") Long klassId);

    List<Long> getPartMembersId(@Param("teamId") Long teamId, @Param("courseId") Long courseId);

    List<AndOrStrategy> getAndStrategy(@Param("strategyId") Long strategyId);

    List<AndOrStrategy> getOrStrategy(@Param("strategyId") Long strategyId);

    List<TeamStrategy> getStrategyByCourseId(@Param("courseId") Long courseId);

}
