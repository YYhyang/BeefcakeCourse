package com.example.demo.Service;

import com.example.demo.DTO.TeamMemberDTO;
import com.example.demo.Dao.*;
import com.example.demo.Entity.StudentEntity;
import com.example.demo.Entity.TeamEntity;
import com.example.demo.Sercurity.JWTPayLoad;
import com.example.demo.strategy.TeamStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    private KlassDao klassDao;
    @Autowired
    private JwtDao jwtDao;

   public Long postTeam(Long klassId, Long courseId, String teamName, List<TeamMemberDTO>members, HttpServletRequest request) {
        /*if (teamDao.postTeam(klassId, courseId, leaderId, teamName, members)) {
            return teamDao.returnId(klassId, courseId, teamName);
        } else
            return new Long((long) 0);*/
       JWTPayLoad jwtPayLoad=jwtDao.getJwtPayLoad(request);
       Long jwt_leaderId = jwtPayLoad.getId();
        Integer team_serial=teamDao.getMaxTeamSerial(klassId)+1;
        Integer klass_serial=klassDao.getKlassSerial(klassId);

        boolean post=teamDao.postTeam(klassId,courseId,jwt_leaderId,teamName,team_serial,klass_serial);

        Long teamId=teamDao.returnId(team_serial,klass_serial);

        boolean create=teamDao.createTeamInKlassTeam(klassId,teamId);

        for(TeamMemberDTO member:members){
            teamDao.createTeamInTeamStudent(teamId,member.getId());
        }
        teamDao.createTeamInTeamStudent(teamId,jwt_leaderId);
        boolean valid=isValid(teamId);
        if(!valid)
            teamDao.changeTeamStatus(teamId,0);
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
        isValid(teamId);
    }//添加成员 待异常处理

    public void deleteTeamMember(Long teamId, Long studentId) {
        TeamEntity team = teamDao.getTeamById(teamId);
        klassStudentDao.deleteTeamMember(studentId, teamId);
        isValid(teamId);
        if (studentId == teamDao.getTeamById(teamId).getLeader().getId())
            deleteTeam(teamId); //如果组长退出，则小组解散
    }//删除成员 待异常处理

    public boolean isValid(Long teamId)//判断小组是否合法
    {
        /*//获得系统日期
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
            teamDao.changeTeamStatus(teamId,0);*/

        TeamEntity team = getTeamById(teamId);
        Long courseId = team.getCourse().getId();
        List<TeamStrategy> strategies = teamDao.getStrategyByCourseId(courseId);
        for (TeamStrategy strategy : strategies) {
            if (strategy.getStrategy_name().equals("MemberLimitStrategy")) {
                boolean valid = teamDao.isValidByMemberLimit(team, strategy.getStrategy_id());
                if (!valid)
                    return false;
            } else if (strategy.getStrategy_name().equals("CourseMemberLimitStrategy")) {
                boolean valid = teamDao.isValidByCourseMemberLimit(team, strategy.getStrategy_id());
                if (!valid)
                    return false;
            } else if (strategy.getStrategy_name().equals("ConflictCourseStrategy")) {
                boolean valid = teamDao.isValidByConflictCourse(team, strategy.getStrategy_id());
                if (!valid)
                    return false;
            } else if (strategy.getStrategy_name().equals("TeamOrStrategy")) {
                boolean valid = teamDao.isValidByOrStrategy(team, strategy.getStrategy_id());
                if (!valid)
                    return false;
            } else if (strategy.getStrategy_name().equals("TeamAndStrategy")) {
                boolean valid = teamDao.isValidByAndStrategy(team, strategy.getStrategy_id());
                if (!valid)
                    return false;
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

}
