package com.example.demo.Service;

import com.example.demo.Dao.*;
import com.example.demo.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    public Long createCourse(String courseName, String introduction, int pPercent, int qPercent, int rPercent, Date teamStartTime,Date teamEndTime){
        Long jwt_teacherId = new Long(7);
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
        return course;
    }

    public List<CourseEntity> getCourse(){
        String role = "teacher";//老师或学生
        Long jwt_Id = new Long(8);
        if(role=="student"){
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

    public TeamEntity getMyTeam(Long courseId){
        Long jwt_studentId = new Long(11);
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
}
