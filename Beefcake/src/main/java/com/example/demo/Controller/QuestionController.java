package com.example.demo.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

public class QuestionController {

    @RequestMapping(value="/seminar/{seminarId}/class/{classId}/question}",method = RequestMethod.GET)  //本节讨论课所有提问
    public void getAllQuestion(@RequestParam("seminarId")Integer seminarId,@RequestParam("classId")Integer classId ){

    }

    @RequestMapping(value="/seminar/{seminarId}/class/{classId}/question}",method = RequestMethod.POST)  //提问
    public void question(@RequestParam("seminarId")Integer seminarId,@RequestParam("classId")Integer classId,@RequestParam("studentId")Integer studentId ){

    }

    @RequestMapping(value="/question/{questionId}}",method = RequestMethod.POST)  //给提问打分，修改提问打分
    public void scoreQuestion(@RequestParam("questionId")Integer question,@RequestParam("score")Integer score ){

    }

}
