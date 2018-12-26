package com.example.demo.Service;

import com.example.demo.Dao.*;
import com.example.demo.Entity.*;
import com.example.demo.Sercurity.JWTPayLoad;
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
    private ShareSeminarApplicationDao shareSeminarApplicationDao;
    @Autowired
    private ShareTeamApplicationDao shareTeamApplicationDao;

    public Long createCourse(String courseName, String introduction, int pPercent, int qPercent, int rPercent, Date teamStartTime, Date teamEndTime, HttpServletRequest request){
        JWTPayLoad jwtPayLoad=jwtDao.getJwtPayLoad(request);
        Long jwt_teacherId = jwtPayLoad.getId();
        courseDao.createCourse(jwt_teacherId,courseName,introduction,pPercent,qPercent,rPercent,teamStartTime,teamEndTime);
        return courseDao.getCourseId(jwt_teacherId,courseName);
    }


    public CourseEntity getCourseById(Long courseId){
        CourseEntity course= courseDao.getCourseById(courseId);
        List<Long> allRoundId = roundDao.getAllRoundId(courseId);
        List<Long> allKlassId = klassDao.getAllKlassId(courseId);
        List<RoundEntity> rounds = new ArrayList<>();
        List<ClassEntity> klasses = new ArrayList<>();
        for(Long roundId:allRoundId){
            rounds.add(roundDao.getRoundById(roundId));
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
        Long jwt_Id = jwtPayLoad.getId();
        role=jwtPayLoad.getRole();
        if(role==0){
            List<CourseEntity> courseEntities = new ArrayList<>();
            List<Long> coursesId = klassStudentDao.getCoursesIdByStudentId(jwt_Id);
            for(Long courseId:coursesId){
                courseEntities.add(courseDao.getCourseById(courseId));
            }
            return courseEntities;
        }
        else {
            return courseDao.getCoursesByTeacherId(jwt_Id);
        }
    }

    public void deleteCourse(Long courseId){
        courseDao.deleteCourse(courseId);
    }

    public List<TeamEntity> getAllTeam(Long courseId){
        List<ClassEntity> klasses = getCourseById(courseId).getKlasses();
        List<TeamEntity> teams = new ArrayList<>();
        for(ClassEntity klass:klasses){
            for(TeamEntity team:klass.getTeams()){
                teams.add(team);
            }
        }
        return teams;
    }

    public TeamEntity getMyTeam(Long courseId,HttpServletRequest request){
        JWTPayLoad jwtPayLoad=jwtDao.getJwtPayLoad(request);
        Long jwt_studentId=jwtPayLoad.getId() ;
        Long teamId=klassStudentDao.getTeamId(courseId,jwt_studentId);
        return teamService.getTeamById(teamId);
    }

    public List<StudentEntity> getNoTeamStudents(Long courseId){
        List<Long> noTeamStudentsId = klassStudentDao.getNoTeamStudentsId(courseId);
        List<StudentEntity> studentEntities = new ArrayList<>();
        for(Long studentId:noTeamStudentsId){
            studentEntities.add(studentDao.selectStudentById(studentId));
        }
        return studentEntities;
    }

    public void createSeminarShare(Long mainCourseId,Long subCourseId){
        //判断是否已经分享
        if(shareSeminarApplicationDao.getId(mainCourseId,subCourseId)!=null) {
            String status = shareSeminarApplicationDao.getShareSeminarApplication(mainCourseId, subCourseId).getStatus(); //若未查到抛出异常
            if (status.equals("0")) {
                shareSeminarApplicationDao.setStatusNull(mainCourseId, subCourseId);
            }
        }
        else{
            Long subCourseTeacherId = courseDao.getTeacherId(subCourseId);
            shareSeminarApplicationDao.createShareSeminarApplication(mainCourseId, subCourseId, subCourseTeacherId);
        }
    }

    public void createTeamShare(Long mainCourseId,Long subCourseId){
        if (shareTeamApplicationDao.getId(mainCourseId, subCourseId) != null) {
            String status = shareTeamApplicationDao.getShareTeamApplication(mainCourseId,subCourseId).getStatus();
            if(status.equals("0")){
                shareTeamApplicationDao.setStatusNull(mainCourseId,subCourseId);
            }
        }
        else{
            Long subCourseTeacherId = courseDao.getTeacherId(subCourseId);
            shareTeamApplicationDao.createShareTeamApplication(mainCourseId,subCourseId,subCourseTeacherId);
        }
    }

    public void deleteTeamShare(Long teamShareId){
        ShareApplicationEntity shareApplicationEntity = shareTeamApplicationDao.getShareById(teamShareId);
        if (shareApplicationEntity.getStatus().equals("1")){
            courseDao.deleteTeamMainCourseId(shareApplicationEntity.getSub_course_id());
        }
        shareTeamApplicationDao.deleteTeamShare(teamShareId);
    }

    public void deleteSeminarShare(Long seminarShareId){
        ShareApplicationEntity shareApplicationEntity = shareSeminarApplicationDao.getShareById(seminarShareId);
        if (shareApplicationEntity.getStatus().equals("1")){
            courseDao.deleteSeminarMainCourseId(shareApplicationEntity.getSub_course_id());
        }
        shareSeminarApplicationDao.deleteSeminarShare(seminarShareId);
    }
}
