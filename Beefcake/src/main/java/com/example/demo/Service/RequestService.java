package com.example.demo.service;

import com.example.demo.dao.*;
import com.example.demo.entity.ShareApplicationEntity;
import com.example.demo.entity.TeamEntity;
import com.example.demo.entity.TeamValidApplicationEntity;
import com.example.demo.sercurity.JWTPayLoad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component
public class RequestService {
    @Autowired
    private ShareTeamApplicationDao shareTeamApplicationDao;
    @Autowired
    private TeamValidApplicationDao teamValidApplicationDao;
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private TeamService teamService;
    @Autowired
    private TeamDao teamDao;
    @Autowired
    private JwtDao jwtDao;
    @Autowired
    private KlassDao klassDao;

    public List<ShareApplicationEntity> getShareTeamRequest(HttpServletRequest request){
        JWTPayLoad jwtPayLoad=jwtDao.getJwtPayLoad(request);
        Long jwtTeacherId=jwtPayLoad.getId();
        return shareTeamApplicationDao.getShareTeamRequest(jwtTeacherId);
    }

    public List<TeamValidApplicationEntity> getTeamValidRequest(HttpServletRequest request){
        JWTPayLoad jwtPayLoad=jwtDao.getJwtPayLoad(request);
        Long jwtTeacherId=jwtPayLoad.getId();
        return teamValidApplicationDao.getTeamValidRequests(jwtTeacherId);
    }


    public void dealTeamRequest(Long requestId,String handleType){
        TeamValidApplicationEntity teamValidApplicationEntity = teamValidApplicationDao.getTeamValidRequest(requestId);
        if("refuse".equals(handleType)){

            //将申请状态置为拒绝
            teamValidApplicationDao.changeApplicationStatus(requestId,0);
            //将小组状态置为不合格
            teamDao.changeTeamStatus(teamValidApplicationEntity.getTeam_id(),0);
            //发通知
        }
        else
        {
            //老师同意组队
            teamService.approveTeam(teamValidApplicationEntity.getTeam_id());
            //发通知
        }
    }//处理小组申请

    public void dealTeamShare(Long shareId,String handleType){
        ShareApplicationEntity teamShare = shareTeamApplicationDao.getShareTeamById(shareId);
        if("refuse".equals(handleType)){
            //将小组分享状态置为拒绝
            shareTeamApplicationDao.setStatus(shareId,0);
            //发通知
        }
        else
        {
            //从课程删除课程下所有小组
            courseService.deleteAllTeams(teamShare.getSub_course_id());

            //获取主课程所有小组id
            List<Long> mainCourseTeamsId = courseService.getAllTeamsId(teamShare.getMain_course_id());
            //获取从课程所有课程id
            List<Long> subCourseKlassesId = klassDao.getAllKlassId(teamShare.getSub_course_id());

            //建立从课程和主课程小组关系
            for(Long teamId:mainCourseTeamsId){
                int maxNum=0;
                Long targetKlassId = null;
                for(Long klassId:subCourseKlassesId){
                    TeamEntity partTeam = teamService.getTeamInKlass(teamId,klassId);
                    if(partTeam.countMembersNum()>maxNum) {
                        maxNum=partTeam.countMembersNum();
                        targetKlassId = klassId;
                    }
                }
                if(targetKlassId!=null) {
                    teamDao.createTeamInKlassTeam(targetKlassId,teamId);
                }
            }
            //为从课程添加主课程Id
            courseDao.setTeamMainCourseId(teamShare.getSub_course_id(),teamShare.getMain_course_id());
            //将共享申请状态置为同意
            shareTeamApplicationDao.setStatus(shareId,1);
            //发通知
        }
    }





}
