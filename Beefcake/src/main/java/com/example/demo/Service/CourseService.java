package com.example.demo.service;

import com.example.demo.dao.*;
import com.example.demo.entity.*;
import com.example.demo.mapper.StrategyMapper;
import com.example.demo.mapper.TeamMapper;
import com.example.demo.sercurity.JWTPayLoad;
import com.example.demo.strategy.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class CourseService {
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private RoundDao roundDao;
    @Autowired
    private KlassService klassService;
    @Autowired
    private KlassDao klassDao;
    @Autowired
    private KlassStudentDao klassStudentDao;
    @Autowired
    private TeamService teamService;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private JwtDao jwtDao;
    @Autowired
    private ShareTeamApplicationDao shareTeamApplicationDao;
    @Autowired
    private TeamDao teamDao;
    @Autowired
    private CourseMemberLimitStrategyDao courseMemberLimitStrategyDao;
    @Autowired
    private StrategyService strategyService;
    @Autowired
    private TeamOrStrategyDao teamOrStrategyDao;
    @Autowired
    private TeamAndStrategyDao teamAndStrategyDao;
    @Autowired
    private TeamStrategyDao teamStrategyDao;
    @Autowired
    private StrategyMapper strategyMapper;
    @Autowired
    private ConflictCourseStrategyDao conflictCourseStrategyDao;


    public boolean createCourse(CourseEntity course, MemberLimitStrategy memberLimitStrategy,
                             List<CourseMemberLimitStrategy>courseMemberLimitStrategyList,
                             Integer relation,List<List<Long>>conflictCourseLists){
        /*JWTPayLoad jwtPayLoad=jwtDao.getJwtPayLoad(request);
        Long jwtTeacherId = jwtPayLoad.getId();
        courseDao.createCourse(jwtTeacherId,courseName,introduction,pPercent,qPercent,rPercent,teamStartTime,teamEndTime);
        return courseDao.getCourseId(jwtTeacherId,courseName);*/
        boolean status=true;
        //添加课程(有sql)
        status=courseDao.addCourse(course)==false?false:status;

        status=strategyMapper.addMemberLimitStrategy(memberLimitStrategy);

        //记录选修课人数限制策略(有sql)
        if(courseMemberLimitStrategyList!=null&&!courseMemberLimitStrategyList.isEmpty()){
            for(CourseMemberLimitStrategy courseMemberLimitStrategy:courseMemberLimitStrategyList){
                status=courseMemberLimitStrategyDao.addCourseMemberLimitStrategy(courseMemberLimitStrategy)==false?false:true;
            }
            if(relation==0){
                //0表示满足其一，或关系
                //将已经插入的选修课人数限制策略之间的或关系存在team_or_strategy表中
                String strategyName=new String("CourseMemberLimitStrategy");
                List<TeamOrStrategy> teamOrStrategyList=strategyService.getTeamOrStrategyList(strategyName,courseMemberLimitStrategyList);
                List<TeamOrStrategy> returnTeamOrStrategyList=teamOrStrategyDao.addTeamOrStrategyList(teamOrStrategyList);

                //组队人数和选修课人数限制策略是与关系
                String strategyName2=new String("TeamOrStrategy");
                List<TeamAndStrategy> teamAndStrategyList=strategyService.getTeamAndStrategyList1(memberLimitStrategy,returnTeamOrStrategyList);
                List<TeamAndStrategy> returnTeamAndStrategyList=teamAndStrategyDao.addTeamAndStrategyList(teamAndStrategyList);

                //将策略加到team_stategy表中
                TeamStrategyAdd teamStrategyAdd=strategyService.getTeamStrategyAdd(course,returnTeamAndStrategyList);
                status=teamStrategyDao.addTeamStrategy(teamStrategyAdd)==false?false:true;


            }else{
                //1表示均满足，与关系
                //将已经插入的选修人数限制策略之间的与关系存在team_and_strategy表中
                String strategyName=new String("CourseMemberLimitStrategy");
                List<TeamAndStrategy> teamAndStrategyList=strategyService.getTeamAndStrategyList(strategyName,courseMemberLimitStrategyList);
                List<TeamAndStrategy> returnTeamAndStrategyList=teamAndStrategyDao.addTeamAndStrategyList(teamAndStrategyList);

                //组队人数限制策略和选修课人数限制策略是与关系
                List<TeamAndStrategy> teamAndStrategyList1=strategyService.getTeamAndStrategyList2(memberLimitStrategy,returnTeamAndStrategyList);
                List<TeamAndStrategy> returnTeamAndStrategyList2=teamAndStrategyDao.addTeamAndStrategyList(teamAndStrategyList1);

                //将策略加到team_strategy表中
                TeamStrategyAdd teamStrategyAdd=strategyService.getTeamStrategyAdd(course,returnTeamAndStrategyList2);
                status=teamStrategyDao.addTeamStrategy(teamStrategyAdd);
            }
        }else{
            TeamStrategyAdd teamStrategyAdd=strategyService.getTeamStrategyAdd(course,memberLimitStrategy);
            status=teamStrategyDao.addTeamStrategy(teamStrategyAdd);
        }

        //记录冲突课程
        if(conflictCourseLists!=null&&!conflictCourseLists.isEmpty()){
            for(List<Long> conflictCourseList:conflictCourseLists){
                List<ConflictCourseStrategy> conflictCourseStrategyList=strategyService.getConflictCourseStrategyList(conflictCourseList);
                List<ConflictCourseStrategy>returnConflictCourseStrategyList=conflictCourseStrategyDao.addConflictCourseStrategyList(conflictCourseStrategyList);
                TeamStrategyAdd teamStrategyAdd=strategyService.getTeamStrategyAdd2(course,returnConflictCourseStrategyList);
                status=teamStrategyDao.addTeamStrategy(teamStrategyAdd);
            }
        }
        return status;
    }



    public CourseEntity getCourseById(Long courseId){
        CourseEntity course= courseDao.getCourseById(courseId);
        List<Long> allRoundId = roundDao.getAllRoundId(courseId);
        List<Long> allKlassId = klassDao.getAllKlassId(courseId);
        List<RoundEntity> rounds = new ArrayList<>();
        List<ClassEntity> klasses = new ArrayList<>();
        for(Long roundId:allRoundId){
            rounds.add(roundDao.getRoundByRoundId(roundId));
        }
        for(Long klassId:allKlassId){
            klasses.add(klassService.getKlassById(klassId));
        }
        course.setKlasses(klasses);
        course.setRounds(rounds);
        course.setTeacher(courseDao.getCourseById(courseId).getTeacher());
        return course;
    }

    public List<CourseEntity> getCourse(HttpServletRequest request){
        int role;//老师或学生
        JWTPayLoad jwtPayLoad=jwtDao.getJwtPayLoad(request);
        Long jwtId = jwtPayLoad.getId();
        System.out.println(jwtId);
        role=jwtPayLoad.getRole();
        if(role==0){
            List<CourseEntity> courseEntities = new ArrayList<>();
            List<Long> coursesId = klassStudentDao.getCoursesIdByStudentId(jwtId);
            for(Long courseId:coursesId){
                CourseEntity course=courseDao.getCourseById(courseId);
                Long klassId=klassStudentDao.getKlassIdByStudentId(courseId,jwtId);
                ClassEntity klass=klassDao.getKlassById(klassId);
                course.setKlassName(klass.getGrade()+"-"+klass.getKlass_serial());
                courseEntities.add(course);
            }
            return courseEntities;
        }
        else {
            return courseDao.getCoursesByTeacherId(jwtId);
        }
    }

    public void deleteCourse(Long courseId){
        courseDao.deleteCourse(courseId);
    }

    public List<TeamEntity> getAllTeam(Long courseId){
        //判断是否为从课程
        Long teamMainCourseId = courseDao.getTeamMainCourseId(courseId);
        List<Long> allTeamsId = getAllTeamsId(courseId);
        List<TeamEntity>  allTeams = new ArrayList<>();
        //若为从课程 则筛选学生
        if(teamMainCourseId!=null)
        {
            for(Long teamId : allTeamsId){
                allTeams.add(teamService.getTeamInCourse(teamId, courseId));
            }
        }
        else
        {
            for(Long teamId:allTeamsId){
                allTeams.add(teamDao.getTeamById(teamId));
            }
        }
        return allTeams;
    }

    public TeamEntity getMyTeam(Long courseId,Long studentId){
        List<Long> klassesId = klassDao.getAllKlassId(courseId);
        Long teamId = null;
        for(Long klassId:klassesId){
            teamId = klassStudentDao.getTeamId(klassId,studentId);
            if(teamId!=null)
            {
                break;
            }
        }
        if(teamId==null){
            return null;
        }
        Long teamMainCourseId = courseDao.getTeamMainCourseId(courseId);
        TeamEntity myTeam;
        if(teamMainCourseId==null)
        {
            myTeam = teamService.getTeamById(teamId);
        }
        else
        {
            myTeam = teamService.getTeamInCourse(teamId,courseId);
        }
        return myTeam;
    }

    public List<StudentEntity> getNoTeamStudents(Long courseId){
        List<Long> noTeamStudentId = klassStudentDao.getNoTeamStudentId(courseId);
        List<StudentEntity> studentEntities = new ArrayList<>();
        for(Long studentId:noTeamStudentId) {
            studentEntities.add(studentDao.selectStudentById(studentId));
        }
        return studentEntities;
    }

    public List<StudentEntity> getNoTeamStudents(Long courseId,String info){
        List<Long> noTeamStudentId = klassStudentDao.getNoTeamStudentId(courseId);
        List<StudentEntity> studentEntities = new ArrayList<>();
        for(Long studentId:noTeamStudentId) {
            StudentEntity studentEntity = studentDao.selectStudentById(studentId);
            if(String.valueOf(studentEntity.getAccount()).equals(info)||studentEntity.getStudent_name().equals(info))
            {
                studentEntities.add(studentEntity);
            }
        }
        return studentEntities;
    }


    public void createTeamShare(Long mainCourseId,Long subCourseId){
        //主课程才可操作
        if(courseDao.getTeamMainCourseId(mainCourseId)==null) {
            if (shareTeamApplicationDao.getId(mainCourseId, subCourseId) != null) {
                String status = shareTeamApplicationDao.getShareTeamApplication(mainCourseId, subCourseId).getStatus();
                if ("0".equals(status)) {
                    shareTeamApplicationDao.setStatusNull(mainCourseId, subCourseId);
                }
            } else {
                Long subCourseTeacherId = courseDao.getTeacherId(subCourseId);
                shareTeamApplicationDao.createShareTeamApplication(mainCourseId, subCourseId, subCourseTeacherId);
            }
        }
    }

    public void deleteTeamShare(Long teamShareId){
        ShareApplicationEntity shareApplicationEntity = shareTeamApplicationDao.getShareById(teamShareId);
        if ("1".equals(shareApplicationEntity.getStatus())){
            //从课程的主课程Id删除
            courseDao.deleteTeamMainCourseId(shareApplicationEntity.getSub_course_id());

            //对从课程的小组相关信息删除
        }//若该分享有效

        shareTeamApplicationDao.deleteTeamShare(teamShareId);
        //将共享信息删除
    }

    public void deleteAllTeams(Long courseId){
        List<Long> teamsId = getAllTeamsId(courseId);
        for(Long teamId:teamsId){
            teamDao.deleteTeam(teamId);
            teamDao.deleteTeamFromKlassTeam(teamId);
            teamDao.deleteTeamFromTeamStudent(teamId);
        }
    }//删除课程下所有team

    public List<Long> getAllTeamsId(Long courseId){
        List<Long> klassesId = klassDao.getAllKlassId(courseId);
        List<Long> teamsId = new ArrayList<>();
        for(Long klassId:klassesId){
            teamsId.addAll(teamDao.getAllTeamIdByKlassId(klassId));
        }
        return teamsId;
    }



    public List<RoundEntity> getRoundByCourseId(Long courseId){return courseDao.getRoundByCourseId(courseId);}


}
