package com.example.demo.Controller;

import com.example.demo.Dao.TeacherDao;
import com.example.demo.Entity.TeacherEntity;
import com.example.demo.Entity.QuestionEntity;
import com.example.demo.Service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class TeacherController {
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private TeacherService teacherService;

    @RequestMapping(value="/Teacher/ExtractQuestion",method = RequestMethod.POST)
    public List<QuestionEntity> extractQuestion(@RequestParam("seminarId")long seminarId, @RequestParam("presentId")long presentId)
    {
        return teacherService.extractQuestion(seminarId,presentId);
    }

    @RequestMapping(value="/Teacher/ScoreQuestion",method = RequestMethod.PATCH)
    public Boolean scoreQuestion(@RequestParam("seminarId")long seminarId, @RequestParam("presentId")long presentId, @RequestParam("studentId")Long studentId, @RequestParam("score")BigDecimal score)
    { return teacherService.scoreQuestion(seminarId,presentId,studentId,score); }

}
