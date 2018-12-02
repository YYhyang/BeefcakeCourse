package com.example.demo.Service;

import com.example.demo.Dao.TeacherDao;
import com.example.demo.Entity.QuestionEntity;
import com.example.demo.Entity.TeacherEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeacherService {
    @Autowired
    private TeacherDao teacherDao;

    public QuestionEntity extractQuestion(Long seminarId,Long presentId){return teacherDao.extractQuestion(seminarId,presentId);}

}
