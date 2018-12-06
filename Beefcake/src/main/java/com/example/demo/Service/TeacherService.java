package com.example.demo.Service;

import com.example.demo.Dao.TeacherDao;
import com.example.demo.Entity.QuestionEntity;
import com.example.demo.Entity.TeacherEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class TeacherService {
    @Autowired
    private TeacherDao teacherDao;

    public List<QuestionEntity> extractQuestion(Long seminarId, Long presentId){return teacherDao.extractQuestion(seminarId,presentId);}

    public Boolean scoreQuestion(Long seminarId, Long presentId, Long studentId, BigDecimal score)
    { return teacherDao.scoreQuestion(seminarId,presentId,studentId,score); }

}
