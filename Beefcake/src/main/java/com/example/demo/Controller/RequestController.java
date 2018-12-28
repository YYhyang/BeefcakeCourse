package com.example.demo.controller;

import com.example.demo.dao.TeamDao;
import com.example.demo.entity.CourseEntity;
import com.example.demo.entity.ShareApplicationEntity;
import com.example.demo.entity.TeamEntity;
import com.example.demo.entity.TeamValidApplicationEntity;
import com.example.demo.service.CourseService;
import com.example.demo.service.RequestService;
import com.example.demo.vo.MasterCourseVO;
import com.example.demo.vo.TeamShareRequestVO;
import com.example.demo.vo.TeamValidApplicationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class RequestController {

    @Autowired
    RequestService requestService;
    @Autowired
    CourseService courseService;
    @Autowired
    TeamDao teamDao;

    @RequestMapping(value = "/request/teamshare",method = RequestMethod.GET)//获得共享申请列表
    public List<TeamShareRequestVO> getAllTeamShare(HttpServletRequest request)
    {
        List<TeamShareRequestVO> teamShareRequests = new ArrayList<>();
        List<ShareApplicationEntity> teamShares = requestService.getShareTeamRequest(request);
        for(ShareApplicationEntity teamShare:teamShares){
            TeamShareRequestVO teamShareRequest = new TeamShareRequestVO();
            teamShareRequest.setTeamShareId(teamShare.getId());

            MasterCourseVO masterCourseVO = new MasterCourseVO();
            CourseEntity masterCourse = courseService.getCourseById(teamShare.getMain_course_id());
            masterCourseVO.setTeacherName(masterCourse.getTeacher().getTeacher_name());
            masterCourseVO.setMasterCourseId(masterCourse.getId());
            masterCourseVO.setMasterCourseName(masterCourse.getCourse_name());

            teamShareRequest.setMasterCourse(masterCourseVO);

            teamShareRequests.add(teamShareRequest);
        }
        return teamShareRequests;
    }



    @RequestMapping(value = "/request/teamvalid",method = RequestMethod.GET)//获取组队申请信息
    public List<TeamValidApplicationVO> getAllTeamRequest(HttpServletRequest request)
    {
        List<TeamValidApplicationEntity> teamValidApplicationEntities = requestService.getTeamValidRequest(request);
        List<TeamValidApplicationVO> teamRequests = new ArrayList<>();
        for(TeamValidApplicationEntity teamValidApplicationEntity:teamValidApplicationEntities){
            TeamValidApplicationVO teamRequest = new TeamValidApplicationVO();
            teamRequest.setRequestId(teamValidApplicationEntity.getId());
            TeamEntity team = teamDao.getTeamById(teamValidApplicationEntity.getTeam_id());
            teamRequest.setCourseName(team.getCourse().getCourse_name());
            teamRequest.setLeaderName(team.getLeader().getStudent_name());
            teamRequest.setTeamId(team.getId());
            teamRequest.setTeamNo(team.getKlass().getKlass_serial()+"-"+team.getTeam_serial());
            teamRequest.setReason(teamValidApplicationEntity.getReason());
            teamRequests.add(teamRequest);
        }
        return teamRequests;
    }


    @RequestMapping(value = "/request/teamshare",method = RequestMethod.PUT)//根据id修改组队共享请求的状态
    public void dealTeamShare(@RequestParam("shareId")Long shareId,@RequestParam("handleType")String handleType)
    {
        requestService.dealTeamShare(shareId,handleType);
    }




    @RequestMapping(value = "/request/teamvalid",method = RequestMethod.PUT)//修改请求状态
    public String dealTeamRequest(@RequestParam("requestId")Long requestId,@RequestParam("handleType")String handleType)
    {
        requestService.dealTeamRequest(requestId,handleType);
        return "success";
    }

}
