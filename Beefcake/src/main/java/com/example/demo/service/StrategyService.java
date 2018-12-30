package com.example.demo.service;

import com.example.demo.dao.CourseDao;
import com.example.demo.entity.CourseEntity;
import com.example.demo.strategy.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StrategyService {
    @Autowired
    CourseDao courseDao;

    public List<TeamAndStrategy>getTeamAndStrategyList1(MemberLimitStrategy memberLimitStrategy,List<TeamOrStrategy> teamOrStrategyList){
        List<TeamAndStrategy> teamAndStrategyList=new ArrayList<>();

        TeamAndStrategy teamAndStrategy1=new TeamAndStrategy();
        teamAndStrategy1.setStrategyName(new String("MemberLimitStrategy"));
        List<Strategy> strategyList=new ArrayList<>();
        strategyList.add(memberLimitStrategy);
        teamAndStrategy1.setStrategyList(strategyList);
        teamAndStrategyList.add(teamAndStrategy1);

        TeamAndStrategy teamAndStrategy2=new TeamAndStrategy();
        teamAndStrategy2.setStrategyName(new String("TeamOrStrategy"));
        List<Strategy> strategyList1=new ArrayList<>();
        for(TeamOrStrategy teamOrStrategy:teamOrStrategyList){
            strategyList1.add(teamOrStrategy);
        }
        teamAndStrategy2.setStrategyList(strategyList1);
        teamAndStrategyList.add(teamAndStrategy2);

        return teamAndStrategyList;
    }

    public List<TeamAndStrategy>getTeamAndStrategyList2(MemberLimitStrategy memberLimitStrategy,List<TeamAndStrategy> teamAndStrategyList){
        List<TeamAndStrategy>teamAndStrategyList2=new ArrayList<>();

        TeamAndStrategy teamAndStrategy1=new TeamAndStrategy();
        teamAndStrategy1.setStrategyName(new String("MemberLimitStrategy"));
        List<Strategy> strategyList=new ArrayList<>();
        strategyList.add(memberLimitStrategy);
        teamAndStrategy1.setStrategyList(strategyList);
        teamAndStrategyList2.add(teamAndStrategy1);

        TeamAndStrategy teamAndStrategy2=new TeamAndStrategy();
        teamAndStrategy2.setStrategyName(new String("TeamAndStrategy"));
        List<Strategy>strategyList1=new ArrayList<>();
        for(TeamAndStrategy teamAndStrategy:teamAndStrategyList){
            strategyList1.add(teamAndStrategy);
        }
        teamAndStrategy2.setStrategyList(strategyList1);
        teamAndStrategyList2.add(teamAndStrategy2);

        return teamAndStrategyList2;
    }

    public List<TeamOrStrategy> getTeamOrStrategyList(String strategeName, List<CourseMemberLimitStrategy>courseMemberLimitStrategyList){
        List<TeamOrStrategy> teamOrStrategyList=new ArrayList<>();
        for(CourseMemberLimitStrategy courseMemberLimitStrategy:courseMemberLimitStrategyList){
            TeamOrStrategy teamOrStrategy=new TeamOrStrategy();
            teamOrStrategy.setStrategyName(strategeName);
            List<Strategy> strategyList=new ArrayList<>();
            strategyList.add(courseMemberLimitStrategy);
            teamOrStrategy.setStrategyList(strategyList);
            teamOrStrategyList.add(teamOrStrategy);
        }
        return teamOrStrategyList;
    }

    public List<TeamAndStrategy> getTeamAndStrategyList(String strategyName,List<CourseMemberLimitStrategy>courseMemberLimitStrategyList){
        List<TeamAndStrategy> teamAndStrategyList=new ArrayList<>();
        for(CourseMemberLimitStrategy courseMemberLimitStrategy:courseMemberLimitStrategyList){
            TeamAndStrategy teamAndStrategy=new TeamAndStrategy();
            teamAndStrategy.setStrategyName(strategyName);
            List<Strategy> strategyList=new ArrayList<>(1);
            strategyList.add(courseMemberLimitStrategy);
            teamAndStrategy.setStrategyList(strategyList);
            teamAndStrategyList.add(teamAndStrategy);
        }
        return teamAndStrategyList;
    }

    public TeamStrategyAdd getTeamStrategyAdd(CourseEntity course, List<TeamAndStrategy> teamAndStrategyList){
        TeamStrategyAdd teamStrategyAdd=new TeamStrategyAdd();
        teamStrategyAdd.setCourse(course);
        teamStrategyAdd.setStrategyName(new String("TeamAndStrategy"));
        List<Strategy> strategyList2=new ArrayList<>();
        for(TeamAndStrategy teamAndStrategy: teamAndStrategyList){
            strategyList2.add(teamAndStrategy);
        }
        teamStrategyAdd.setStrategyList(strategyList2);

        return teamStrategyAdd;
    }

    public TeamStrategyAdd getTeamStrategyAdd(CourseEntity course,MemberLimitStrategy memberLimitStrategy){
        TeamStrategyAdd teamStrategyAdd=new TeamStrategyAdd();
        teamStrategyAdd.setCourse(course);
        teamStrategyAdd.setStrategyName(new String("MemberLimitStrategy"));
        List<Strategy> strategyList=new ArrayList<>();
        strategyList.add(memberLimitStrategy);
        teamStrategyAdd.setStrategyList(strategyList);
        return teamStrategyAdd;
    }

    public TeamStrategyAdd getTeamStrategyAdd2(CourseEntity course,List<ConflictCourseStrategy>conflictCourseStrategyList){
        TeamStrategyAdd teamStrategyAdd=new TeamStrategyAdd();
        teamStrategyAdd.setCourse(course);
        teamStrategyAdd.setStrategyName("ConflictCourseStrategy");
        List<Strategy>strategyList=new ArrayList<>();
        for(ConflictCourseStrategy item:conflictCourseStrategyList){
            strategyList.add(item);
        }
        teamStrategyAdd.setStrategyList(strategyList);
        return teamStrategyAdd;
    }

    public List<ConflictCourseStrategy>getConflictCourseStrategyList(List<Long>conflictCourseList){
        List<ConflictCourseStrategy> conflictCourseStrategyList=new ArrayList<>();
        for(Long courseId:conflictCourseList){
            ConflictCourseStrategy conflictCourseStrategy=new ConflictCourseStrategy();
            conflictCourseStrategy.setCourse(courseDao.getCourseById(courseId));
            conflictCourseStrategyList.add(conflictCourseStrategy);
        }
        return conflictCourseStrategyList;
    }
}
