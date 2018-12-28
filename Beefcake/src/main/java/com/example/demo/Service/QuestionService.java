package com.example.demo.service;

import com.example.demo.dao.JwtDao;
import com.example.demo.entity.QuestionEntity;
import com.example.demo.mapper.AttendanceMapper;
import com.example.demo.mapper.KlassStudentMapper;
import com.example.demo.mapper.QuestionMapper;
import com.example.demo.sercurity.JWTPayLoad;
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
        Long jwtStudentId=jwtPayLoad.getId() ;
        Long klassSeminarId=attendanceMapper.findKlassSeminarId(seminarId, classId);
        Long teamId=klassStudentMapper.getTeamId(classId,jwtStudentId);
        return questionMapper.askQuestion(klassSeminarId,attendanceId,teamId,jwtStudentId);
    }

    public QuestionEntity getQuestion(Long attendanceId,Long teamId)
    {
        return questionMapper.getQuestion(attendanceId,teamId);
    }
}
