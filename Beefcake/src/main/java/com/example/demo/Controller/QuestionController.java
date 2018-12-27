package com.example.demo.Controller;

import com.example.demo.Entity.QuestionEntity;
import com.example.demo.Mapper.QuestionMapper;
import com.example.demo.Service.QuestionService;
import com.example.demo.VO.SelectQuestionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@RestController
public class QuestionController {

    @Autowired
   private QuestionService questionService;
    @Autowired
    private QuestionMapper questionMapper;

    @RequestMapping(value="/seminar/{seminarId}/class/{classId}/question",method = RequestMethod.GET)  //本节讨论课所有提问（通过）
    public List<QuestionEntity> getAllQuestion(@PathVariable("seminarId")Long seminarId, @PathVariable("classId")Long classId )
    {
        return questionService.getAllQuestion(seminarId, classId);
    }

    @RequestMapping(value="/seminar/{seminarId}/class/{classId}/question",method = RequestMethod.POST)  //提问(通过)
    public boolean question(@PathVariable("seminarId")Long seminarId, @PathVariable("classId")Long classId, @RequestParam("attendanceId")Long attendanceId, HttpServletRequest request){
        return questionService.askQuestion(seminarId,classId,attendanceId,request);
    }

   @RequestMapping(value = "/attendance/{attendanceId}/selectquestion",method = RequestMethod.GET)//抽取提问（通过）
    public SelectQuestionVO getQuestion(@PathVariable("attendanceId")Long attendanceId,@RequestParam("teamId")Long teamId)
   {
       SelectQuestionVO vo=new SelectQuestionVO();
       QuestionEntity question=questionService.getQuestion(attendanceId,teamId);
       vo.setQuestion_id(question.getId());
       vo.setStudentName(question.getStudent().getStudent_name());
       int team_serial=question.getTeam().getTeam_serial();
       int class_serial=question.getTeam().getClass_serial();
       vo.setTeamClassSerial(String.valueOf(class_serial)+"-"+String.valueOf(team_serial));
       return vo;
   }

    @RequestMapping(value="/question/{questionId}",method = RequestMethod.POST)  //给提问打分，修改提问打分（通过）
    public boolean scoreQuestion(@PathVariable("questionId")Long questionId,@RequestParam("score")double score ){
        return questionMapper.score(questionId,score);
    }

}
