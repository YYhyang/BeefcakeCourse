package com.example.demo.service;

import com.example.demo.dto.TeamMemberDTO;
import com.example.demo.dao.*;
import com.example.demo.entity.StudentEntity;
import com.example.demo.entity.TeamEntity;
import com.example.demo.sercurity.JWTPayLoad;
import com.example.demo.strategy.TeamStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Component
public class TeamService {

    @Autowired
    private TeamDao teamDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private KlassStudentDao klassStudentDao;
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private TeamValidApplicationDao teamValidApplicationDao;
    @Autowired
    private KlassDao klassDao;
    @Autowired
    private JwtDao jwtDao;

   public Long postTeam(Long klassId, Long courseId, String teamName, List<TeamMemberDTO>members, HttpServletRequest request) {
       JWTPayLoad jwtPayLoad=jwtDao.getJwtPayLoad(request);
       Long jwtLeaderId = jwtPayLoad.getId();
        Integer teamSerial=teamDao.getMaxTeamSerial(klassId)+1;
        Integer klassSerial=klassDao.getKlassSerial(klassId);

        boolean post=teamDao.postTeam(klassId,courseId,jwtLeaderId,teamName,teamSerial,klassSerial);

        Long teamId=teamDao.returnId(teamSerial,klassSerial);

        boolean create=teamDao.createTeamInKlassTeam(klassId,teamId);

        for(TeamMemberDTO member:members){
            teamDao.createTeamInTeamStudent(teamId,member.getId());
        }
        teamDao.createTeamInTeamStudent(teamId,jwtLeaderId);
        boolean valid=isValid(teamId);
        if(!valid) {
            teamDao.changeTeamStatus(teamId,0);
        }
        return teamId;
    }

    public TeamEntity getTeamById(Long teamId) {
        TeamEntity teamEntity = teamDao.getTeamById(teamId);
        List<Long> membersId = klassStudentDao.getStudentIdByTeamId(teamId);
        List<StudentEntity> members = new ArrayList<>();
        for (Long memberId : membersId) {
            members.add(studentDao.selectStudentById(memberId));
        }
        teamEntity.setMembers(members);
        return teamEntity;
    }//获得队伍信息

    public TeamEntity getTeamWithoutMember(Long teamId){
       return teamDao.getTeamById(teamId);
    }

    public List<TeamEntity> getAllTeamByKlassId(Long klassId) {
        List<TeamEntity> teamEntities = new ArrayList<>();
        List<Long> teamsId = teamDao.getAllTeamIdByKlassId(klassId);
        for (Long teamId : teamsId) {
            teamEntities.add(getTeamById(teamId));
        }
        return teamEntities;
    }

    public void deleteTeam(Long teamId) {
        teamDao.deleteTeam(teamId);
        teamDao.deleteTeamFromKlassTeam(teamId);
        teamDao.deleteTeamFromTeamStudent(teamId);
    }//删除队伍 待异常处理

    public void addTeamMember(Long teamId, Long studentId) {
        klassStudentDao.addTeamMember(teamDao.getTeamById(teamId).getKlass().getId(), studentId, teamId);
        if(!isValid(teamId))
        {
            teamDao.changeTeamStatus(teamId,0);
        }
    }//添加成员 待异常处理

    public void deleteTeamMember(Long teamId, Long studentId) {
        //如果组长退出，则小组解散
        if (studentId.equals(teamDao.getTeamById(teamId).getLeader().getId())) {
            deleteTeam(teamId);
            return;
        }
        klassStudentDao.deleteTeamMember(studentId, teamId);
        if(!isValid(teamId))
        {
            teamDao.changeTeamStatus(teamId,0);
        }
    }//删除成员 待异常处理

    public boolean isValid(Long teamId)//判断小组是否合法
    {
        if(teamDao.beenApproved(teamId))
        {
            return false;
        }
        TeamEntity team = getTeamById(teamId);
        Long courseId = team.getCourse().getId();
        List<TeamStrategy> strategies = teamDao.getStrategyByCourseId(courseId);
        for (TeamStrategy strategy : strategies) {
            if ("MemberLimitStrategy".equals(strategy.getStrategy_name())) {
                boolean valid = teamDao.isValidByMemberLimit(team, strategy.getStrategy_id());
                if (!valid) {
                    return false;
                }
            } else if ("CourseMemberLimitStrategy".equals(strategy.getStrategy_name())) {
                boolean valid = teamDao.isValidByCourseMemberLimit(team, strategy.getStrategy_id());
                if (!valid) {
                    return false;
                }
            } else if ("ConflictCourseStrategy".equals(strategy.getStrategy_name())) {
                boolean valid = teamDao.isValidByConflictCourse(team, strategy.getStrategy_id());
                if (!valid) {
                    return false;
                }
            } else if ("TeamOrStrategy".equals(strategy.getStrategy_name())) {
                boolean valid = teamDao.isValidByOrStrategy(team, strategy.getStrategy_id());
                if (!valid) {
                    return false;
                }
            } else if ("TeamAndStrategy".equals(strategy.getStrategy_name())) {
                boolean valid = teamDao.isValidByAndStrategy(team, strategy.getStrategy_id());
                if (!valid) {
                    return false;
                }
            }
        }
        return true;
    }

    public void teamValidRequest(Long teamId, Long courseId, String reason){
        if (teamDao.getTeamById(teamId).getStatus()==0) {
            teamValidApplicationDao.createRequest(teamId, courseDao.getCourseById(courseId).getTeacher().getId(), reason);
            teamDao.changeTeamStatus(teamId,2);
        }//不合法才能申请

    }

    public void approveTeam(Long teamId){
        teamDao.changeTeamStatus(teamId,1);
        teamValidApplicationDao.changeApplicationStatus(teamId,1);
    }
    //获取某一小组在某一班级下的构造
    public TeamEntity getTeamInKlass(Long teamId,Long klassId){
        TeamEntity teamInKlass = teamDao.getTeamById(teamId);
        List<Long> membersId = teamDao.getSomeMembersId(teamId,klassId);
        List<StudentEntity> members = new ArrayList<>();
        for(Long memberId:membersId){
            members.add(studentDao.selectStudentById(memberId));
        }
        teamInKlass.setMembers(members);
        return teamInKlass;
    }

    //获取某一小组在某一课程下的构造（从课程）
    public TeamEntity getTeamInCourse(Long teamId,Long courseId){
        TeamEntity teamInCourse = teamDao.getTeamById(teamId);
        List<Long> membersId = teamDao.getPartMembersId(teamId,courseId);
        if(!leaderExist(teamInCourse,membersId)) {
            teamInCourse.setLeader(null);
        }
        return setMembers(teamInCourse,membersId);
    }

    //为小组添加成员
    public TeamEntity setMembers(TeamEntity teamEntity,List<Long> membersId){
        List<StudentEntity> members = new ArrayList<>();
        for(Long memberId:membersId){
            members.add(studentDao.selectStudentById(memberId));
        }
        teamEntity.setMembers(members);
        return teamEntity;
    }

    //判断该组组长是否在该队中(从课程)
    boolean leaderExist(TeamEntity teamEntity,List<Long> membersId){
        boolean leaderExist = false;
        Long leaderId =teamEntity.getLeader().getId();
        for(Long memberId:membersId) {
            if (leaderId.equals(memberId)) {
                leaderExist = true;
            }
        }
        return leaderExist;
    }

}
