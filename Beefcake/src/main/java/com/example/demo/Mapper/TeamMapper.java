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

    public List<TeamSimpleVO> getTeamByCourseId(@Param("courseId")Long courseId);

    public boolean postTeam(@Param("klassId") Long klassId, @Param("courseId") Long courseId, @Param("leaderId") Long leaderId, @Param("teamName") String teamName
                           , @Param("teamSerial")Integer teamSerial,@Param("klassSerial")Integer klassSerial);

    public boolean createTeamInKlassTeam(@Param("klassId")Long klassId,@Param("teamId")Long teamId);

    public boolean createTeamInTeamStudent(@Param("teamId")Long teamId,@Param("studentId")Long studentId);

    public Long returnId(@Param("teamSerial") Integer teamSerial, @Param("klassSerial") Integer klassSerial);

    public TeamEntity getTeamById(@Param("teamId") Long teamId);

    public void deleteTeam(@Param("teamId") Long teamId);

    public void deleteTeamFromTeamStudent(@Param("teamId")Long teamId);

    public void deleteTeamFromKlassTeam(@Param("teamId")Long teamId);

    public void changeTeamStatus(@Param("teamId") Long teamId, @Param("status") int status);

    public List<Long> getAllTeamIdByKlassId(@Param("klassId") Long klassId);

    public MemberLimitStrategy getMemberLimit(@Param("strategyId")Long strategyId);

    public CourseMemberLimitStrategy getCourseMemberLimit(@Param("strategyId")Long strategyId);

    public List<Long> getMemberIdByCourseId(@Param("courseId")Long courseId,@Param("teamId")Long teamId);

    public List<Long>getConflictCourseId(@Param("strategyId")Long strategyId);

    public List<AndOrStrategy> getAndStrategy(@Param("strategyId")Long strategyId);

    public List<AndOrStrategy> getOrStrategy(@Param("strategyId")Long strategyId);

    public List<TeamStrategy> getStrategyByCourseId(@Param("courseId")Long courseId);

    public int getMaxTeamSerial(@Param("klassId")Long klassId);

    public List<Long> getSomeMembersId(@Param("teamId")Long teamId,@Param("klassId")Long klassId);

    List<Long> getPartMembersId(@Param("teamId")Long teamId,@Param("courseId")Long courseId);

}
