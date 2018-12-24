package com.example.demo.Controller;

import com.example.demo.Entity.QuestionEntity;
import com.example.demo.Mapper.QuestionMapper;
import com.example.demo.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class QuestionController {

    @Autowired
   private QuestionService questionService;
    @Autowired
    private QuestionMapper questionMapper;

    @RequestMapping(value="/seminar/{seminarId}/class/{classId}/question",method = RequestMethod.GET)  //本节讨论课所有提问
    public List<QuestionEntity> getAllQuestion(@PathVariable("seminarId")Long seminarId, @PathVariable("classId")Long classId )
    {
        return questionService.getAllQuestion(seminarId, classId);
    }

   /* @RequestMapping(value="/seminar/{seminarId}/class/{classId}/question",method = RequestMethod.POST)  //提问
    public boolean question(@PathVariable("seminarId")Long seminarId, @PathVariable("classId")Long classId, @RequestParam("studentId")Long studentId, @RequestParam("attendanceId")Long attendanceId ){
        return questionService.askQuestion(seminarId,classId,studentId,attendanceId);
    }*/

    @RequestMapping(value="/question/{questionId}",method = RequestMethod.POST)  //给提问打分，修改提问打分
    public boolean scoreQuestion(@PathVariable("questionId")Long questionId,@RequestParam("score")double score ){
        return questionMapper.score(questionId,score);
    }

}
