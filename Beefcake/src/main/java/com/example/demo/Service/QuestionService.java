package com.example.demo.Service;

import com.example.demo.Entity.QuestionEntity;
import com.example.demo.Mapper.AttendanceMapper;
import com.example.demo.Mapper.KlassStudentMapper;
import com.example.demo.Mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuestionService {
    @Autowired
    AttendanceMapper attendanceMapper;
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    KlassStudentMapper klassStudentMapper;

    public List<QuestionEntity> getAllQuestion(Long seminarId,Long classId)
    {
        Long klassSeminarId=attendanceMapper.findKlassSeminarId(seminarId, classId);
        return questionMapper.getAllQuestion(klassSeminarId);
    }

    public boolean askQuestion(Long seminarId,Long classId,Long studentId,Long attendanceId)
    {
        Long klassSeminarId=attendanceMapper.findKlassSeminarId(seminarId, classId);
        Long courseId=attendanceMapper.getCourseBySeminarId(seminarId);
        Long teamId=klassStudentMapper.getTeamId(courseId,studentId);
        return questionMapper.askQuestion(klassSeminarId,attendanceId,teamId,studentId);
    }
}
