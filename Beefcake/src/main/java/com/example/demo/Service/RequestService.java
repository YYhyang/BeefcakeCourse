package com.example.demo.Service;

import com.example.demo.Dao.*;
import com.example.demo.Entity.ShareApplicationEntity;
import com.example.demo.Entity.TeamValidApplicationEntity;
import com.example.demo.Sercurity.JWTPayLoad;
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

    public List<ShareApplicationEntity> getShareTeamRequest(HttpServletRequest request){
        JWTPayLoad jwtPayLoad=jwtDao.getJwtPayLoad(request);
        Long jwt_teacherId=jwtPayLoad.getId();
        return shareTeamApplicationDao.getShareTeamRequest(jwt_teacherId);
    }

    public List<TeamValidApplicationEntity> getTeamValidRequest(HttpServletRequest request){
        JWTPayLoad jwtPayLoad=jwtDao.getJwtPayLoad(request);
        Long jwt_teacherId=jwtPayLoad.getId();
        return teamValidApplicationDao.getTeamValidRequests(jwt_teacherId);
    }


    public void dealTeamRequest(Long requestId,String handleType){
        TeamValidApplicationEntity teamValidApplicationEntity = teamValidApplicationDao.getTeamValidRequest(requestId);
        if(handleType.equals("refuse")){

            teamValidApplicationDao.changeApplicationStatus(requestId,0); //将申请状态置为拒绝
            teamDao.changeTeamStatus(teamValidApplicationEntity.getTeam_id(),0);//将小组状态置为不合格
            //发通知
        }
        else
        {
            teamService.approveTeam(teamValidApplicationEntity.getTeam_id());//老师同意组队
            //发通知
        }
    }//处理小组申请

    public void dealTeamShare(Long shareId,String handleType){
        ShareApplicationEntity teamShare = shareTeamApplicationDao.getShareTeamById(shareId);
        if(handleType.equals("refuse")){
            shareTeamApplicationDao.setStatus(shareId,0);//将小组分享状态置为拒绝
            //发通知
        }
        else
        {
            courseService.deleteAllTeams(teamShare.getSub_course_id());//从课程删除课程下所有小组 ##待修改
            //建立从课程和主课程小组关系

            courseDao.setTeamMainCourseId(teamShare.getSub_course_id(),teamShare.getMain_course_id());//为从课程添加主课程Id
            shareTeamApplicationDao.setStatus(shareId,1);//
            //发通知
        }
    }



//    public List<ShareApplicationEntity> getShareSeminarRequest(HttpServletRequest request){
//        JWTPayLoad jwtPayLoad=jwtDao.getJwtPayLoad(request);
//        Long jwt_teacherId=jwtPayLoad.getId();
//        return shareSeminarApplicationDao.getShareSeminarRequest(jwt_teacherId);
//    }


//    public void dealSeminarShare(Long shareId,String handleType){
//        if(handleType.equals("refuse")){
//            shareSeminarApplicationDao.setStatus(shareId,0);
//            //发通知
//        }
//        else
//        {
//            shareSeminarApplicationDao.setStatus(shareId,1);
//            //删除所有讨论课
//            //发通知
//        }
//    }

}
