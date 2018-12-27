package com.example.demo.Controller;

import com.example.demo.DTO.StudentIdDTO;
import com.example.demo.DTO.TeamMemberDTO;
import com.example.demo.DTO.TeamRequestDTO;
import com.example.demo.DTO.createTeamDTO;
import com.example.demo.Entity.ClassEntity;
import com.example.demo.Entity.CourseEntity;
import com.example.demo.Entity.StudentEntity;
import com.example.demo.Entity.TeamEntity;
import com.example.demo.Service.CourseService;
import com.example.demo.Service.KlassService;
import com.example.demo.Service.StudentService;
import com.example.demo.Service.TeamService;
import com.example.demo.VO.CourseInTeamVO;
import com.example.demo.VO.GetTeamByIdVO;
import com.example.demo.VO.KlassInTeamVO;
import com.example.demo.VO.StudentInTeamVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TeamController {

    @Autowired
    private TeamService teamService;

    @RequestMapping(value="/team",method = RequestMethod.POST)  //新建队伍
    public Long postTeam(@RequestBody createTeamDTO team, HttpServletRequest request){
        return teamService.postTeam(team.getKlassId(),team.getCourseId(),team.getTeamName(),team.getMembers(),request); //创建时默认为合法
    }

    @RequestMapping(value="/team/{teamId}",method = RequestMethod.GET)  //获取队伍信息(测试通过）
    public GetTeamByIdVO getTeamInfoById(@PathVariable("teamId") Long teamId ){

        //获得初始Entity类
        TeamEntity teamEntity = teamService.getTeamById(teamId);
        ClassEntity classEntity = teamEntity.getKlass();
        CourseEntity courseEntity = teamEntity.getCourse();
        StudentEntity leaderEntity = teamEntity.getLeader();
        List<StudentEntity> studentEntities = teamEntity.getMembers();
        //初始化内部VO类
        KlassInTeamVO klass = new KlassInTeamVO();
        klass.setId(classEntity.getId());
        klass.setName("("+ String.valueOf(classEntity.getGrade())+")"+ String.valueOf(classEntity.getKlass_serial()));

        CourseInTeamVO course = new CourseInTeamVO();
        course.setId(courseEntity.getId());
        course.setName(courseEntity.getCourse_name());

        StudentInTeamVO leader = new StudentInTeamVO();
        leader.setId(leaderEntity.getId());
        leader.setAccount(leaderEntity.getAccount());
        leader.setName(leaderEntity.getStudent_name());

        List<StudentInTeamVO> members = new ArrayList<>();
        for(StudentEntity stu:studentEntities){
            StudentInTeamVO member = new StudentInTeamVO();
            member.setId(stu.getId());
            member.setAccount(stu.getAccount());
            member.setName(stu.getStudent_name());
            members.add(member);
        }

        //给最终返回VO类赋值
        GetTeamByIdVO team = new GetTeamByIdVO();
        team.setId(teamEntity.getId());
        team.setName(teamEntity.getKlass().getKlass_serial()+"-"+teamEntity.getTeam_serial()+" "+teamEntity.getTeam_name());
        team.setStatus(teamEntity.getStatus());
        team.setKlass(klass);
        team.setCourse(course);
        team.setLeader(leader);
        team.setMembers(members);

        return team;
    }

    @RequestMapping(value="/team/{teamId}",method = RequestMethod.DELETE)  //按ID删除队伍及队长解散队伍(测试成功）
    public void deleteTeam(@PathVariable("teamId") Long teamId ){
        teamService.deleteTeam(teamId);
    }

    @RequestMapping(value="/team/{teamId}/add",method = RequestMethod.PUT)  //添加成员(测试通过）
    public void addTeamMember(@PathVariable("teamId") Long teamId, @RequestBody StudentIdDTO student){
        teamService.addTeamMember(teamId,student.getId());
        //return Long.parseLong(info.getKlass().get("id"));  //测试嵌套表单
    }

    @RequestMapping(value="/team/{teamId}/remove",method = RequestMethod.PUT)  //移除成员和退出队伍(测试通过）
    public void removeTeam(@PathVariable("teamId") Long teamId, @RequestBody StudentIdDTO student  ){
        teamService.deleteTeamMember(teamId,student.getId());
    }

    @RequestMapping(value="/team/{teamId}/teamvalidrequest",method = RequestMethod.POST)  //发起队伍状态申请（应该不用改，未测试）
    public void teamRequest(@PathVariable("teamId") Long teamId, @RequestBody TeamRequestDTO teamRequest){
        teamService.teamValidRequest(teamId,teamRequest.getCourseId(),teamRequest.getReason());
    }

    @RequestMapping(value="/team/{teamId}/approve",method = RequestMethod.PUT)  //老师同意学生组队合法(应该不用改，未测试）
    public void approveTeam(@PathVariable("teamId") Long teamId ){
        teamService.approveTeam(teamId);
    }

}
