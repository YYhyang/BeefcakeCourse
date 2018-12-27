package com.example.demo.Service;

import com.example.demo.Dao.JwtDao;
import com.example.demo.Entity.QuestionEntity;
import com.example.demo.Mapper.AttendanceMapper;
import com.example.demo.Mapper.KlassStudentMapper;
import com.example.demo.Mapper.QuestionMapper;
import com.example.demo.Sercurity.JWTPayLoad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component
public class QuestionService {
    @Autowired
    AttendanceMapper attendanceMapper;
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    KlassStudentMapper klassStudentMapper;
    @Autowired
    JwtDao jwtDao;

    public List<QuestionEntity> getAllQuestion(Long seminarId,Long classId)
    {
        Long klassSeminarId=attendanceMapper.findKlassSeminarId(seminarId, classId);
        return questionMapper.getAllQuestion(klassSeminarId);
    }

    public boolean askQuestion(Long seminarId, Long classId, Long attendanceId, HttpServletRequest request)
    {
        JWTPayLoad jwtPayLoad=jwtDao.getJwtPayLoad(request);
        Long jwt_studentId=jwtPayLoad.getId() ;
        Long klassSeminarId=attendanceMapper.findKlassSeminarId(seminarId, classId);
        Long teamId=klassStudentMapper.getTeamId(classId,jwt_studentId);
        return questionMapper.askQuestion(klassSeminarId,attendanceId,teamId,jwt_studentId);
    }

    public QuestionEntity getQuestion(Long attendanceId,Long teamId)
    {
        return questionMapper.getQuestion(attendanceId,teamId);
    }
}
