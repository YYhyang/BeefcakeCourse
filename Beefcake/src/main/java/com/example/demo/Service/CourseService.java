package com.example.demo.Service;

import com.example.demo.Dao.*;
import com.example.demo.Entity.*;
import com.example.demo.Mapper.TeamMapper;
import com.example.demo.Sercurity.JWTPayLoad;
import com.example.demo.VO.TeamSimpleVO;
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
    private TeamMapper teamMapper;
    @Autowired
    private ShareSeminarApplicationDao shareSeminarApplicationDao;
    @Autowired
    private ShareTeamApplicationDao shareTeamApplicationDao;
    @Autowired
    private TeamDao teamDao;

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
        Long jwt_Id = jwtPayLoad.getId();
        System.out.println(jwt_Id);
        role=jwtPayLoad.getRole();
        if(role==0){
            List<CourseEntity> courseEntities = new ArrayList<>();
            List<Long> coursesId = klassStudentDao.getCoursesIdByStudentId(jwt_Id);
            for(Long courseId:coursesId){
                CourseEntity course=courseDao.getCourseById(courseId);
                Long klassId=klassStudentDao.getKlassIdByStudentId(courseId,jwt_Id);
                ClassEntity klass=klassDao.getKlassById(klassId);
                course.setKlassName(klass.getGrade()+"-"+klass.getKlass_serial());
                courseEntities.add(course);
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
        Long teamMainCourseId = courseDao.getTeamMainCourseId(courseId); //判断是否为从课程
        List<Long> allTeamsId = getAllTeamsId(courseId);
        List<TeamEntity>  allTeams = new ArrayList<>();
        if(teamMainCourseId!=null)//若为从课程 则筛选学生
            for(Long teamId : allTeamsId){
                allTeams.add(teamService.getTeamInCourse(teamId, courseId));
            }
        else
            for(Long teamId:allTeamsId){
                allTeams.add(teamDao.getTeamById(teamId));
            }
        return allTeams;
    }

    public TeamEntity getMyTeam(Long courseId,Long studentId){
        List<Long> klassesId = klassDao.getAllKlassId(courseId);
        Long teamId = null;
        for(Long klassId:klassesId){
            teamId = klassStudentDao.getTeamId(klassId,studentId);
            if(teamId!=null)
                break;
        }
        Long teamMainCourseId = courseDao.getTeamMainCourseId(courseId);
        TeamEntity myTeam;
        if(teamMainCourseId==null)
            myTeam = teamService.getTeamById(teamId);
        else
            myTeam = teamService.getTeamInCourse(teamId,courseId);
        return myTeam;
    }

    public List<StudentEntity> getNoTeamStudents(Long courseId){
        List<Long> noTeamStudentsId = klassStudentDao.getNoTeamStudentsId(courseId);
        List<StudentEntity> studentEntities = new ArrayList<>();
        for(Long studentId:noTeamStudentsId){
            studentEntities.add(studentDao.selectStudentById(studentId));
        }
        return studentEntities;
    }


    public void createTeamShare(Long mainCourseId,Long subCourseId){
        //主课程才可操作
        if(courseDao.getTeamMainCourseId(mainCourseId)==null) {
            if (shareTeamApplicationDao.getId(mainCourseId, subCourseId) != null) {
                String status = shareTeamApplicationDao.getShareTeamApplication(mainCourseId, subCourseId).getStatus();
                if (status.equals("0")) {
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
        if (shareApplicationEntity.getStatus().equals("1")){
            courseDao.deleteTeamMainCourseId(shareApplicationEntity.getSub_course_id());//从课程的主课程Id删除

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
