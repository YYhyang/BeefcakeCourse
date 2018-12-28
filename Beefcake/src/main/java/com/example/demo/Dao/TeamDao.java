package com.example.demo.Dao;


import com.example.demo.DTO.TeamMemberDTO;
import com.example.demo.Entity.StudentEntity;
import com.example.demo.strategy.AndOrStrategy;
import com.example.demo.strategy.CourseMemberLimitStrategy;
import com.example.demo.strategy.MemberLimitStrategy;
import com.example.demo.Entity.TeamEntity;
import com.example.demo.Mapper.TeamMapper;
import com.example.demo.strategy.TeamStrategy;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TeamDao {

    @Autowired
    public TeamMapper teamMapper;

    public Integer getMaxTeamSerial(Long klassId){return teamMapper.getMaxTeamSerial(klassId);}

    public boolean postTeam(Long klassId, Long courseId, Long leaderId, String teamName,Integer teamSerial,Integer klassSerial){
        return teamMapper.postTeam(klassId, courseId, leaderId, teamName,teamSerial,klassSerial);
    }

    public boolean createTeamInKlassTeam(Long klassId,Long teamId){return teamMapper.createTeamInKlassTeam(klassId, teamId);}
    public boolean createTeamInTeamStudent(Long teamId,Long studentId){return teamMapper.createTeamInTeamStudent(teamId, studentId);}

    public Long returnId(Integer team_serial, Integer klass_serial){
        return teamMapper.returnId(team_serial,klass_serial);
    }

    public TeamEntity getTeamById(Long teamId) {
        return teamMapper.getTeamById(teamId);
    }

    public void deleteTeam(Long teamId){
        teamMapper.deleteTeam(teamId);
    }

    public List<TeamStrategy> getStrategyByCourseId(Long courseId){return teamMapper.getStrategyByCourseId(courseId);}

    public void deleteTeamFromTeamStudent(Long teamId){ teamMapper.deleteTeamFromTeamStudent(teamId);}

    public void deleteTeamFromKlassTeam(Long teamId){ teamMapper.deleteTeamFromKlassTeam(teamId);}

    public List<Long> getAllTeamIdByKlassId(Long klassId){
        return teamMapper.getAllTeamIdByKlassId(klassId);
    }

    public void changeTeamStatus(Long teamId, int status){
        teamMapper.changeTeamStatus(teamId,status);
    }
    public boolean isValidByMemberLimit(TeamEntity team,Long strategeId)
    {
        MemberLimitStrategy memberLimitStrategy=teamMapper.getMemberLimit(strategeId);
        return memberLimitStrategy.isValid(team);
    }
    public boolean isValidByCourseMemberLimit(TeamEntity team,Long strategyId)
    {
        CourseMemberLimitStrategy courseMemberLimitStrategy=teamMapper.getCourseMemberLimit(strategyId);
        List<Long> members= teamMapper.getMemberIdByCourseId(courseMemberLimitStrategy.getCourse_id(),team.getId());
        int count=members.size();
        return courseMemberLimitStrategy.isValid(count);
    }

    public boolean isValidByConflictCourse(TeamEntity team, Long strategyId)
    {
        List<Long> courseIds=teamMapper.getConflictCourseId(strategyId);
        int sum=0;
        for(Long id:courseIds)
        {
            List<Long>ids=teamMapper.getMemberIdByCourseId(id,team.getId());
            int size=ids.size();
            if(size!=0)
                sum++;
            if(sum>1)
                return false;
        }
        return true;
    }

    public boolean isValidByOrStrategy(TeamEntity team,Long strategyId)
    {
        List<AndOrStrategy> OrStrategies=teamMapper.getOrStrategy(strategyId);
        for(AndOrStrategy OrStrategy:OrStrategies)
        {
            if(OrStrategy.getStrategy_name().equals("MemberLimitStrategy"))
            {
                boolean valid=isValidByMemberLimit(team,OrStrategy.getStrategy_id());
                if(valid)
                    return true;
            }
            else if(OrStrategy.getStrategy_name().equals("CourseMemberLimitStrategy"))
            {
                boolean valid=isValidByCourseMemberLimit(team,OrStrategy.getStrategy_id());
                if(valid)
                    return true;
            }
            else if(OrStrategy.getStrategy_name().equals("ConflictCourseStrategy"))
            {
                boolean valid=isValidByConflictCourse(team,OrStrategy.getStrategy_id());
                if(valid)
                    return true;
            }
            else if(OrStrategy.getStrategy_name().equals("TeamOrStrategy")) {
                boolean valid=isValidByOrStrategy(team,OrStrategy.getStrategy_id());
                if(valid)
                    return true;
            }
            else if(OrStrategy.getStrategy_name().equals("TeamAndStrategy")){
                boolean valid=isValidByAndStrategy(team,OrStrategy.getStrategy_id());
                if(valid)
                    return true;
            }
        }
        return false;
    }

    public boolean isValidByAndStrategy(TeamEntity team,Long strategyId)
    {
        List<AndOrStrategy> AndStrategies=teamMapper.getAndStrategy(strategyId);
        for(AndOrStrategy AndStrategy:AndStrategies)
        {
            if(AndStrategy.getStrategy_name().equals("MemberLimitStrategy"))
            {
                boolean valid=isValidByMemberLimit(team,AndStrategy.getStrategy_id());
                if(!valid)
                    return false;
            }
            else if(AndStrategy.getStrategy_name().equals("CourseMemberLimitStrategy"))
            {
                boolean valid=isValidByCourseMemberLimit(team,AndStrategy.getStrategy_id());
                if(!valid)
                    return false;
            }
            else if(AndStrategy.getStrategy_name().equals("ConflictCourseStrategy"))
            {
                boolean valid=isValidByConflictCourse(team,AndStrategy.getStrategy_id());
                if(!valid)
                    return false;
            }
            else if(AndStrategy.getStrategy_name().equals("TeamOrStrategy")) {
                boolean valid=isValidByOrStrategy(team,AndStrategy.getStrategy_id());
                if(!valid)
                    return false;
            }
            else if(AndStrategy.getStrategy_name().equals("TeamAndStrategy")){
                boolean valid=isValidByAndStrategy(team,AndStrategy.getStrategy_id());
                if(!valid)
                    return false;
            }
        }
        return true;
    }

    public List<Long> getSomeMembersId(Long teamId,Long klassId){
        return teamMapper.getSomeMembersId(teamId,klassId);
    }

    public List<Long> getPartMembersId(Long teamId, Long courseId) {return teamMapper.getPartMembersId(teamId,courseId);
    }
}
