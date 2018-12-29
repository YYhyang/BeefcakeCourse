package com.example.demo.controller;

import com.example.demo.dto.CourseDTO;
import com.example.demo.dto.KlassDTO;
import com.example.demo.dto.ShareRequestIdDTO;
import com.example.demo.dao.CourseDao;
import com.example.demo.dao.JwtDao;
import com.example.demo.dao.ShareTeamApplicationDao;
import com.example.demo.entity.*;
import com.example.demo.sercurity.JWTPayLoad;
import com.example.demo.service.CourseService;
import com.example.demo.service.KlassService;
import com.example.demo.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    private ShareTeamApplicationDao shareTeamApplicationDao;
    @Autowired
    JwtDao jwtDao;
    @Autowired
    CourseDao courseDao;

    @RequestMapping(value = "/course",method = RequestMethod.POST)//创建课程
    public IdVO createCourse(@RequestBody CourseDTO course,HttpServletRequest request)
    {
        SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Long id = courseService.createCourse(course.getName(), course.getIntro(), course.getPresentationWeight(),
                    course.getQuestionWeight(), course.getReportWeight(), sim.parse(course.getStartTeamTime()), sim.parse(course.getEndTeamTime()),request);
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
    public List<GetAllCourseVO> getCourse(HttpServletRequest request)
    {
        List<GetAllCourseVO> courses = new ArrayList<>();
        List<CourseEntity> courseEntities = courseService.getCourse(request);
        for(CourseEntity courseEntity:courseEntities){
            GetAllCourseVO course = new GetAllCourseVO();
            course.setId(courseEntity.getId());
            course.setName(courseEntity.getCourse_name());
            course.setTeacherName(courseEntity.getTeacher().getTeacher_name());
            course.setClassName(courseEntity.getKlassName());
            courses.add(course);
        }
        return courses;
    }

    @RequestMapping(value = "/course",method = RequestMethod.PUT)
    public List<AllExistCourseVO> allExistCourse()
    {
        List<AllExistCourseVO> allCourses = new ArrayList<>();
        List<CourseEntity> courseEntities = courseDao.getAllExistCourse();
        for(CourseEntity courseEntity:courseEntities){
            AllExistCourseVO course = new AllExistCourseVO();
            course.setId(courseEntity.getId());
            course.setName(courseEntity.getCourse_name());
            course.setTecherId(courseEntity.getTeacher().getId());
            course.setTeacherName(courseEntity.getTeacher().getTeacher_name());
            allCourses.add(course);
        }
        return allCourses;
    }

    @RequestMapping(value="/course/{courseId}/round",method = RequestMethod.GET)
    public List<RoundVO> getAllRound(@PathVariable("courseId")Long courseId)
    {
        List<RoundVO> rounds = new ArrayList<>();
        List<RoundEntity> roundlist=courseService.getRoundByCourseId(courseId);
        for(RoundEntity roundEntity:roundlist)
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
    public List<TeamSimpleVO> getAllTeam(@PathVariable("courseId")Long courseId)
    {
        //获得所有初始Team类
        List<TeamEntity> teamEntities = courseService.getAllTeam(courseId);
        List<TeamSimpleVO> teams = new ArrayList<>();
        for(TeamEntity teamEntity:teamEntities){
            TeamSimpleVO team = new TeamSimpleVO();
            team.setId(teamEntity.getId());
            team.setSerial_name(teamEntity.getClass_serial()+"-"+teamEntity.getTeam_serial());
            team.setTeam_name(teamEntity.getTeam_name());
            team.setStatus(teamEntity.getStatus());
            teams.add(team);
        }
        return  teams;
    }//已更改 测试完成

    @RequestMapping(value = "/course/{courseId}/myTeam",method = RequestMethod.GET)
    public TeamVO getMyTeam(@PathVariable("courseId")Long courseId, HttpServletRequest request)
    {
        JWTPayLoad jwtPayLoad=jwtDao.getJwtPayLoad(request);
        Long jwtStudentId=jwtPayLoad.getId();
        TeamEntity teamEntity = courseService.getMyTeam(courseId,jwtStudentId);
        TeamVO myTeam=new TeamVO();
        if(teamEntity==null){
            myTeam.setId(Long.valueOf(-1));
            return myTeam;
        }
         myTeam = TeamEntityToTeamVO(teamEntity);
        if(myTeam.getLeader()!=null&&myTeam.getLeader().getId().equals(jwtStudentId))
        {
            myTeam.setIsLeader("yes")
            ;}
        else{
            myTeam.setIsLeader("no");}
        return myTeam;
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
        String[] s = klassDTO.getName().split("-");
        klassService.createKlass(courseId,Integer.parseInt(s[0]),Integer.parseInt(s[1]),klassDTO.getTime(),klassDTO.getClassroom());
        Long id = klassService.getKlassId(courseId,Integer.parseInt(s[0]),Integer.parseInt(s[1]));
        IdVO idVO = new IdVO();
        idVO.setId(id);
        return idVO;
    }



    @RequestMapping(value = "/course/{courseId}/teamshare",method = RequestMethod.GET)//获取所有共享
    public List<TeamShareVO> getTeamShare(@PathVariable("courseId")Long courseId)
    {
        List<Long> teamSharesId = shareTeamApplicationDao.getTeamSharesId(courseId);
        List<TeamShareVO> teams = new ArrayList<>();
        for(Long teamShareId:teamSharesId){

            TeamShareVO team = new TeamShareVO();
            MasterCourseVO masterCourse = new MasterCourseVO();
            ReceiveCourseVO receiveCourse = new ReceiveCourseVO();

            ShareApplicationEntity shareTeam=shareTeamApplicationDao.getShareById(teamShareId);
            CourseEntity mainCourse=courseService.getCourseById(shareTeam.getMain_course_id());
            CourseEntity subCourse=courseService.getCourseById(shareTeam.getSub_course_id());

            masterCourse.setMasterCourseId(shareTeam.getMain_course_id());
            masterCourse.setMasterCourseName(mainCourse.getCourse_name());
            masterCourse.setTeacherName(mainCourse.getTeacher().getTeacher_name());
            receiveCourse.setReceiveCourseId(shareTeam.getSub_course_id());
            receiveCourse.setReceiveCourseName(subCourse.getCourse_name());
            receiveCourse.setTeacherName(subCourse.getTeacher().getTeacher_name());

            team.setTeamShareId(teamShareId);
            team.setMasterCourse(masterCourse);
            team.setReceiveCourse(receiveCourse);

            teams.add(team);
        }
        return teams;
    }

    @RequestMapping(value = "/course/teamshare/{teamShareId}",method = RequestMethod.DELETE)//取消小组分享
    public void deleteTeamShare(@PathVariable("teamShareId")Long teamShareId)
    {
        courseService.deleteTeamShare(teamShareId);
    }



    @RequestMapping(value= "/course/{courseId}/teamsharerequest",method = RequestMethod.POST)//发起组队共享请求
    public void createTeamShare(@PathVariable("courseId")Long courseId,@RequestBody ShareRequestIdDTO shareRequestIdDTO){
        courseService.createTeamShare(courseId,shareRequestIdDTO.getSubCourseId());
    }


    public TeamVO TeamEntityToTeamVO(TeamEntity teamEntity){
        TeamVO teamVO = new TeamVO();
        teamVO.setId(teamEntity.getId());
        teamVO.setName(teamEntity.getKlass().getKlass_serial()+"-"+teamEntity.getTeam_serial()+" "+teamEntity.getTeam_name());
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
