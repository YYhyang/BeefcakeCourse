package com.example.demo.Controller;

import com.example.demo.DTO.CourseDTO;
import com.example.demo.DTO.KlassDTO;
import com.example.demo.DTO.ShareRequestIdDTO;
import com.example.demo.DTO.TeamDTO;
import com.example.demo.Dao.JwtDao;
import com.example.demo.Dao.ShareSeminarApplicationDao;
import com.example.demo.Dao.ShareTeamApplicationDao;
import com.example.demo.Entity.*;
import com.example.demo.Sercurity.JWTPayLoad;
import com.example.demo.Service.CourseService;
import com.example.demo.Service.KlassService;
import com.example.demo.VO.*;
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
    private ShareSeminarApplicationDao shareSeminarApplicationDao;
    @Autowired
    private ShareTeamApplicationDao shareTeamApplicationDao;
    @Autowired
    JwtDao jwtDao;

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

    @RequestMapping(value = "/course/{courseId}/team",method = RequestMethod.GET)//很慢
    public List<TeamSimpleVO> getAllTeam(@PathVariable("courseId")Long courseId)
    {
        //获得所有初始Team类
        List<TeamSimpleVO> teamEntities = courseService.getAllTeam(courseId);
        /*List<TeamVO> teamvo=new ArrayList<>();
        for(TeamEntity team:teamEntities){
            TeamVO vo=TeamEntityToTeamVO(team);
            teamvo.add(vo);
        }
        return teamvo;*/
        return  teamEntities;
    }


    @RequestMapping(value = "/course/{courseId}/myTeam",method = RequestMethod.GET)
    public TeamVO getMyTeam(@PathVariable("courseId")Long courseId, HttpServletRequest request)
    {
        JWTPayLoad jwtPayLoad=jwtDao.getJwtPayLoad(request);
        Long jwt_studentId=jwtPayLoad.getId() ;
        TeamEntity teamEntity = courseService.getMyTeam(courseId,jwt_studentId);
        TeamVO vo= TeamEntityToTeamVO(teamEntity);
        if(vo.getLeader().getId()==jwt_studentId)
            vo.setIsLeader("yes");
        else
            vo.setIsLeader("no");
        return vo;
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


    @RequestMapping(value = "/course/{courseId}/seminarshare",method = RequestMethod.GET)//获取所有共享
    public List<SeminarShareVO> getSeminarShare(@PathVariable("courseId")Long courseId)
    {
        List<Long> seminarSharesId = shareSeminarApplicationDao.getSeminarSharesId(courseId);
        List<SeminarShareVO> seminars = new ArrayList<>();
        for(Long seminarShareId:seminarSharesId){

            SeminarShareVO seminar = new SeminarShareVO();
            MasterCourseVO masterCourse = new MasterCourseVO();
            ReceiveCourseVO receiveCourse = new ReceiveCourseVO();

            ShareApplicationEntity shareSeminar=shareSeminarApplicationDao.getShareById(seminarShareId);
            CourseEntity mainCourse=courseService.getCourseById(shareSeminar.getMain_course_id());
            CourseEntity subCourse=courseService.getCourseById(shareSeminar.getSub_course_id());

            masterCourse.setMasterCourseId(shareSeminar.getMain_course_id());
            masterCourse.setMasterCourseName(mainCourse.getCourse_name());
            masterCourse.setTeacherName(mainCourse.getTeacher().getTeacher_name());
            receiveCourse.setReceiveCourseId(shareSeminar.getSub_course_id());
            receiveCourse.setReceiveCourseName(subCourse.getCourse_name());
            receiveCourse.setTeacherName(subCourse.getTeacher().getTeacher_name());

            seminar.setSeminarShareId(seminarShareId);
            seminar.setMasterCourse(masterCourse);
            seminar.setReceiveCourse(receiveCourse);

            seminars.add(seminar);
        }
        return seminars;
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

    @RequestMapping(value = "/course/{courseId}/class/{classId}/team",method = RequestMethod.POST)
    public IdVO createTeam(@PathVariable("courseId")Long courseId, @PathVariable("classId")Long classId, @RequestBody TeamDTO teamDTO)
    {
        IdVO idVO = new IdVO();
        idVO.setId(courseId);
        return idVO;
    }//有关小组 待完善

    public TeamVO TeamEntityToTeamVO(TeamEntity teamEntity){
        TeamVO teamVO = new TeamVO();
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
