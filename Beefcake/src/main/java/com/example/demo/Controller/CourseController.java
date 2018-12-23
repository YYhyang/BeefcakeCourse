package com.example.demo.Controller;

import com.example.demo.DTO.CourseDTO;
import com.example.demo.Entity.CourseEntity;
import com.example.demo.Entity.RoundEntity;
import com.example.demo.Entity.StudentEntity;
import com.example.demo.Entity.TeamEntity;
import com.example.demo.Service.CourseService;
import com.example.demo.Service.StudentService;
import com.example.demo.Service.TeacherService;
import com.example.demo.VO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;

    @RequestMapping(value = "/course",method = RequestMethod.POST)//创建课程
    public void createCourse(@RequestBody CourseDTO course){
        //courseService.createCourse(/*teacherId,*/course.getName(),course.getIntro(),course.getPresentationWeight(),
        //        course.getQuestionWeight(),course.getReportWeight(),course.getStartTeamTime(),course.getEndTeamTime());
    }

    @RequestMapping(value = "/course",method = RequestMethod.GET)
    public List<GetAllCourseVO> getCourse()
    {
        List<GetAllCourseVO> courses = new ArrayList<>();
        String role = "teacher";//老师或学生
        Long Id = new Long(8);
        List<CourseEntity> courseEntities = courseService.getCourse(role,Id);
        for(CourseEntity courseEntity:courseEntities){
            GetAllCourseVO course = new GetAllCourseVO();
            course.setId(courseEntity.getId());
            course.setName(courseEntity.getCourse_name());
            courses.add(course);
        }
        return courses;
    }


    @RequestMapping(value="/course/{courseId}/round",method = RequestMethod.GET)
    public List<RoundVO> getAllRound(@PathVariable("courseId") Long courseId)
    {
        List<RoundVO> rounds = new ArrayList<>();
        for(RoundEntity roundEntity:courseService.getCourseById(courseId).getRounds())
        {
            RoundVO round=new RoundVO();
            round.setId(roundEntity.getId());
            round.setOrder(roundEntity.getRound_serial());
            rounds.add(round);
        }
        return rounds;
    }

    @RequestMapping(value = "/course/{courseId}",method = RequestMethod.GET)
    public CourseVO getCourseById(@PathVariable("courseId") Long courseId)
    {
        CourseEntity courseEntity = courseService.getCourseById(courseId);
        CourseVO course = new CourseVO();
        course.setName(courseEntity.getCourse_name());
        course.setIntro(courseEntity.getIntroduction());
        course.setPresentationWeight(courseEntity.getQuestion_percentage());
        course.setQuestionWeight(courseEntity.getQuestion_percentage());
        course.setReportWeight(courseEntity.getReport_percentage());
        //course.setMaxMemberNumber();
        //course.setMinMemberNumber();
        course.setStartTeamTime(courseEntity.getTeam_start_time());
        course.setEndTeamTime(courseEntity.getTeam_end_time());
        return course;
    }

    @RequestMapping(value = "/course/{courseId}",method = RequestMethod.DELETE)
    public void deleteCourse(@PathVariable("courseId") Long courseId)
    {
        courseService.deleteCourse(courseId);
    }

    @RequestMapping(value = "/course/{courseId}/team",method = RequestMethod.GET)
    public List<TeamVO> getAllTeam(@PathVariable("courseId") Long courseId)
    {
        //获得所有初始Team类
        List<TeamEntity> teamEntities = courseService.getAllTeam(courseId);
        //给返回VO类赋值
        List<TeamVO> teams = new ArrayList<>();
        for(TeamEntity teamEntity:teamEntities){
            teams.add(TeamEntityToTeamVO(teamEntity));
        }
        return teams;
    }


    @RequestMapping(value = "/course/{courseId}/myteam",method = RequestMethod.GET)
    public TeamVO getMyTeam(@PathVariable("courseId") Long courseId)
    {
        TeamEntity teamEntity = courseService.getMyTeam(courseId);
        return TeamEntityToTeamVO(teamEntity);
    }


    @RequestMapping(value = "/course/{courseId}/noTeam",method = RequestMethod.GET)
    public List<StudentInTeamVO> getNoTeam(@PathVariable("courseId") Long courseId)
    {
        List<StudentEntity> studentEntities = courseService.getNoTeamStudents(courseId);
        List<StudentInTeamVO> students = new ArrayList<>();
        for(StudentEntity studentEntity:studentEntities){
            students.add(StudentEntityToStudentInTeamVO(studentEntity));
        }
        return students;
    }

    @RequestMapping(value = "/course/{courseId}/class",method = RequestMethod.GET)
    public void getClass(@PathVariable("courseId") Long courseId)
    {

    }

    @RequestMapping(value = "/course/{courseId}/class/{classId}",method = RequestMethod.POST)//创建班级
    public void createClass(@PathVariable("courseId")int courseId,@PathVariable("classId")int classId)
    {

    }

    @RequestMapping(value = "/course/{courseId}/class/{classId}",method = RequestMethod.PUT)//上传学生名单
    public void loadStudent(@PathVariable("courseId")int courseId,@PathVariable("classId")int classId)
    {

    }

    @RequestMapping(value = "/course/{courseId}/share",method = RequestMethod.GET)//获取所有共享
    public void getShare(@PathVariable("courseId")int courseId)
    {

    }

    @RequestMapping(value = "/course/{courseId}/share/{shareId}",method = RequestMethod.DELETE)//获取所有共享
    public void deleteShare(@PathVariable("courseId")int courseId,@PathVariable("shareId")int shareId)
    {

    }

    @RequestMapping(value = "/course/{courseId}/sharerequest",method = RequestMethod.POST)//获取所有共享
    public void createShare(@PathVariable("courseId")int courseId)
    {

    }

    public TeamVO TeamEntityToTeamVO(TeamEntity teamEntity){
        TeamVO teamVO = new TeamVO();
        teamVO.setName(teamEntity.getKlass().getId()+"-"+teamEntity.getTeam_serial()+" "+teamEntity.getTeam_name());
        teamVO.setStatus(teamEntity.getStatus());
        //给TeamVO里的Leader赋值
        StudentEntity leaderEntity = studentService.getStudentById(teamEntity.getLeader().getId());
        StudentInTeamVO leader = new StudentInTeamVO();
        leader.setId(leaderEntity.getId());
        leader.setAccount(leaderEntity.getAccount());
        leader.setName(leaderEntity.getStudent_name());
        teamVO.setLeader(leader);
        //给TeamVO里的Members赋值
        List<StudentInTeamVO> students = new ArrayList<>();
        System.out.print(teamEntity.getMembers().size());
        for(StudentEntity studentEntity:teamEntity.getMembers()){
            StudentInTeamVO student = new StudentInTeamVO();
            student.setId(studentEntity.getId());
            student.setAccount(studentEntity.getAccount());
            student.setName(studentEntity.getStudent_name());
            students.add(student);
        }
        teamVO.setMembers(students);
        return teamVO;
    }

    public StudentInTeamVO StudentEntityToStudentInTeamVO(StudentEntity studentEntity){
        StudentInTeamVO studentInTeamVO = new StudentInTeamVO();
        studentInTeamVO.setId(studentEntity.getId());
        studentInTeamVO.setAccount(studentEntity.getAccount());
        studentInTeamVO.setName(studentEntity.getStudent_name());
        return studentInTeamVO;
    }
}
