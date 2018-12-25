package com.example.demo.Controller;

import com.example.demo.DTO.CourseDTO;
import com.example.demo.DTO.KlassDTO;
import com.example.demo.DTO.TeamDTO;
import com.example.demo.Dao.KlassDao;
import com.example.demo.Entity.*;
import com.example.demo.Service.CourseService;
import com.example.demo.Service.KlassService;
import com.example.demo.Service.StudentService;
import com.example.demo.Service.TeacherService;
import com.example.demo.VO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private KlassService klassService;

    @RequestMapping(value = "/course",method = RequestMethod.POST)//创建课程
    public IdVO createCourse(@RequestBody CourseDTO course)
    {
        SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Long id = courseService.createCourse(course.getName(), course.getIntro(), course.getPresentationWeight(),
                    course.getQuestionWeight(), course.getReportWeight(), sim.parse(course.getStartTeamTime()), sim.parse(course.getEndTeamTime()));
            IdVO idVO = new IdVO();
            idVO.setId(id);
            return idVO;
        }
        catch (ParseException e)
        {
            System.out.println(e.getMessage());
            Long id = new Long(1);
            IdVO idVO = new IdVO();
            idVO.setId(id);
            return idVO;
        }
    } //冲突课程待处理

    @RequestMapping(value = "/course",method = RequestMethod.GET)
    public List<GetAllCourseVO> getCourse()
    {
        List<GetAllCourseVO> courses = new ArrayList<>();
        List<CourseEntity> courseEntities = courseService.getCourse();
        for(CourseEntity courseEntity:courseEntities){
            GetAllCourseVO course = new GetAllCourseVO();
            course.setId(courseEntity.getId());
            course.setName(courseEntity.getCourse_name());
            courses.add(course);
        }
        return courses;
    }

    @RequestMapping(value="/course/{courseId}/round",method = RequestMethod.GET)
    public List<RoundVO> getAllRound(@PathVariable("courseId")Long courseId)
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
    public CourseVO getCourseById(@PathVariable("courseId")Long courseId)
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
    public void deleteCourse(@PathVariable("courseId")Long courseId)
    {
        courseService.deleteCourse(courseId);
    }

    @RequestMapping(value = "/course/{courseId}/team",method = RequestMethod.GET)
    public List<TeamVO> getAllTeam(@PathVariable("courseId")Long courseId)
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
    public TeamVO getMyTeam(@PathVariable("courseId")Long courseId)
    {
        TeamEntity teamEntity = courseService.getMyTeam(courseId);
        return TeamEntityToTeamVO(teamEntity);
    }


    @RequestMapping(value = "/course/{courseId}/noTeam",method = RequestMethod.GET)
    public List<StudentInTeamVO> getNoTeam(@PathVariable("courseId")Long courseId)
    {
        List<StudentEntity> studentEntities = courseService.getNoTeamStudents(courseId);
        List<StudentInTeamVO> students = new ArrayList<>();
        for(StudentEntity studentEntity:studentEntities){
            students.add(StudentEntityToStudentInTeamVO(studentEntity));
        }
        return students;
    }

    @RequestMapping(value = "/course/{courseId}/class",method = RequestMethod.GET)
    public List<KlassInfoVO> getClass(@PathVariable("courseId")Long courseId)
    {
        List<ClassEntity> klassEntities = klassService.getKlassesByCourseId(courseId);
        List<KlassInfoVO> klasses = new ArrayList<>();
        for(ClassEntity klassEntity:klassEntities){
            KlassInfoVO klass = new KlassInfoVO();
            klass.setId(klassEntity.getId());
            klass.setName(klassEntity.getGrade()+"-"+klassEntity.getKlass_serial());
            klass.setTime(klassEntity.getKlass_time());
            klass.setClassroom(klassEntity.getKlass_location());
            klasses.add(klass);
        }
        return klasses;
    }

    @RequestMapping(value = "/course/{courseId}/class",method = RequestMethod.POST)//创建班级
    public IdVO createKlass(@PathVariable("courseId")Long courseId, @RequestBody KlassDTO klassDTO)
    {
        String s[] = klassDTO.getName().split("-");
        klassService.createKlass(courseId,Integer.parseInt(s[0]),Integer.parseInt(s[1]),klassDTO.getTime(),klassDTO.getClassroom());
        Long id = klassService.getKlassId(courseId,Integer.parseInt(s[0]),Integer.parseInt(s[1]));
        IdVO idVO = new IdVO();
        idVO.setId(id);
        return idVO;
    }//传入学生名单待实现


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

    @RequestMapping(value = "/course/{courseId}/class/{classId}/team",method = RequestMethod.POST)
    public IdVO createTeam(@PathVariable("courseId")Long courseId, @PathVariable("classId")Long classId,@RequestBody TeamDTO teamDTO)
    {
        IdVO idVO = new IdVO();
        idVO.setId(courseId);
        return idVO;
    }//有关小组 待完善

    public TeamVO TeamEntityToTeamVO(TeamEntity teamEntity){
        TeamVO teamVO = new TeamVO();
        teamVO.setName(teamEntity.getKlass().getId()+"-"+teamEntity.getTeam_serial()+" "+teamEntity.getTeam_name());
        teamVO.setStatus(teamEntity.getStatus());
        //给TeamVO里的Leader赋值
        StudentEntity leaderEntity = teamEntity.getLeader();
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
