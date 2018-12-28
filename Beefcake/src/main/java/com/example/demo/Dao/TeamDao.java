package com.example.demo.dao;


import com.example.demo.strategy.AndOrStrategy;
import com.example.demo.strategy.CourseMemberLimitStrategy;
import com.example.demo.strategy.MemberLimitStrategy;
import com.example.demo.entity.TeamEntity;
import com.example.demo.mapper.TeamMapper;
import com.example.demo.strategy.TeamStrategy;
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

    public Long returnId(Integer teamSerial, Integer klassSerial){
        return teamMapper.returnId(teamSerial,klassSerial);
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
            if(size!=0) {
                sum++;
            }
            if(sum>1) {
                return false;
            }
        }
        return true;
    }

    public boolean isValidByOrStrategy(TeamEntity team,Long strategyId)
    {
        List<AndOrStrategy> orStrategies=teamMapper.getOrStrategy(strategyId);
        for(AndOrStrategy orStrategy:orStrategies)
        {
            if("MemberLimitStrategy".equals(orStrategy.getStrategy_name()))
            {
                boolean valid=isValidByMemberLimit(team,orStrategy.getStrategy_id());
                if(valid) {
                    return true;
                }
            }
            else if("CourseMemberLimitStrategy".equals(orStrategy.getStrategy_name()))
            {
                boolean valid=isValidByCourseMemberLimit(team,orStrategy.getStrategy_id());
                if(valid) {
                    return true;
                }
            }
            else if("ConflictCourseStrategy".equals(orStrategy.getStrategy_name()))
            {
                boolean valid=isValidByConflictCourse(team,orStrategy.getStrategy_id());
                if(valid) {
                    return true;
                }
            }
            else if("TeamOrStrategy".equals(orStrategy.getStrategy_name())) {
                boolean valid=isValidByOrStrategy(team,orStrategy.getStrategy_id());
                if(valid) {
                    return true;
                }
            }
            else if("TeamAndStrategy".equals(orStrategy.getStrategy_name())){
                boolean valid=isValidByAndStrategy(team,orStrategy.getStrategy_id());
                if(valid) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isValidByAndStrategy(TeamEntity team,Long strategyId)
    {
        List<AndOrStrategy> andStrategies=teamMapper.getAndStrategy(strategyId);
        for(AndOrStrategy andStrategy:andStrategies)
        {
            if("MemberLimitStrategy".equals(andStrategy.getStrategy_name()))
            {
                boolean valid=isValidByMemberLimit(team,andStrategy.getStrategy_id());
                if(!valid) {
                    return false;
                }
            }
            else if("CourseMemberLimitStrategy".equals(andStrategy.getStrategy_name()))
            {
                boolean valid=isValidByCourseMemberLimit(team,andStrategy.getStrategy_id());
                if(!valid) {
                    return false;
                }
            }
            else if("ConflictCourseStrategy".equals(andStrategy.getStrategy_name()))
            {
                boolean valid=isValidByConflictCourse(team,andStrategy.getStrategy_id());
                if(!valid) {
                    return false;
                }
            }
            else if("TeamOrStrategy".equals(andStrategy.getStrategy_name())) {
                boolean valid=isValidByOrStrategy(team,andStrategy.getStrategy_id());
                if(!valid) {
                    return false;
                }
            }
            else if("TeamAndStrategy".equals(andStrategy.getStrategy_name())){
                boolean valid=isValidByAndStrategy(team,andStrategy.getStrategy_id());
                if(!valid) {
                    return false;
                }
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
