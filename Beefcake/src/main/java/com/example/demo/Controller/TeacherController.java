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

@RestController
public class TeacherController {
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private TeacherService teacherService;

    @RequestMapping(value="/Teacher/ExtractQuestion",method = RequestMethod.POST)
    public QuestionEntity extractQuestion(@RequestParam("seminarId")long seminarId,@RequestParam("presentId")long presentId)
    {
        return teacherService.extractQuestion(seminarId,presentId);
    }

}
