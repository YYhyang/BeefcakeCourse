package com.example.demo.Service;

import com.example.demo.Dao.*;
import com.example.demo.Entity.StudentEntity;
import com.example.demo.Entity.TeamEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
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
    private MemberLimitDao memberLimitDao;
    @Autowired
    private TeamValidApplicationDao teamValidApplicationDao;

    public Long postTeam(Long klassId, Long courseId, Long leaderId, String teamName, int status){
        if(teamDao.postTeam(klassId,courseId,leaderId,teamName,status)){
            return teamDao.returnId(klassId,courseId,teamName);
        }
        else
            return new Long((long) 0);

    }

    public TeamEntity getTeamById(Long teamId){
        TeamEntity teamEntity=teamDao.getTeamById(teamId);
        List<Long> membersId= klassStudentDao.getStudentIdByTeamId(teamId);
        List<StudentEntity> members = new ArrayList<>();
        for(Long memberId:membersId){
            members.add(studentDao.selectStudentById(memberId));
        }
        teamEntity.setMembers(members);
        return teamEntity;
    }//获得队伍信息

    public List<TeamEntity> getAllTeamByKlassId(Long klassId){
        List<TeamEntity> teamEntities = new ArrayList<>();
        List<Long> teamsId = teamDao.getAllTeamIdByKlassId(klassId);
        for(Long teamId:teamsId){
            teamEntities.add(getTeamById(teamId));
        }
        return teamEntities;
    }

    public void deleteTeam(Long teamId){
        teamDao.deleteTeam(teamId);

        //将所有小组成员置为未组队状态
        List<Long> membersId= klassStudentDao.getStudentIdByTeamId(teamId);
        TeamEntity team=teamDao.getTeamById(teamId);
        for(Long mem:membersId) {
            klassStudentDao.deleteTeamMember(mem, teamId);
        }

    }//删除队伍 待异常处理

    public void addTeamMember(Long teamId, Long studentId){
        klassStudentDao.addTeamMember(teamDao.getTeamById(teamId).getKlass().getId(),studentId,teamId);
        invalidTeam(teamId);
    }//添加成员 待异常处理

    public void deleteTeamMember(Long teamId, Long studentId){
        TeamEntity team=teamDao.getTeamById(teamId);
        klassStudentDao.deleteTeamMember(studentId,teamId);
        invalidTeam(teamId);
        if(studentId==teamDao.getTeamById(teamId).getLeader().getId())
            deleteTeam(teamId); //如果组长退出，则小组解散
    }//删除成员 待异常处理

    public void invalidTeam(Long teamId){
        //获得系统日期
        Date datetime = new Date(System.currentTimeMillis());

        //计算小组人数
        int count = 0;
        List<Long> membersId= klassStudentDao.getStudentIdByTeamId(teamId);
        for(Long mem:membersId){
            count=count+1;
        }

        //删除组员后判断是否超过组队日期或者是否不满足组队要求，是则将小组状态置为invalid
        if(datetime.compareTo(courseDao.getTeamEndTime(teamDao.getTeamById(teamId).getCourse().getId()))>0)
            teamDao.changeTeamStatus(teamId,0);
        else if(count>memberLimitDao.getMax()||count<memberLimitDao.getMin())
            teamDao.changeTeamStatus(teamId,0);

    }//判断是否将小组状态置为不合法

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
}
