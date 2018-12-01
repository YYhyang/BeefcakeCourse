package com.example.demo.Controller;

import com.example.demo.Dao.StudentDao;
import com.example.demo.Entity.TeamStudentEntity;
import com.example.demo.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/Student/Ask",method = RequestMethod.POST)
    public boolean ask(@RequestParam("studentId")String studentId,@RequestParam("seminarId")Long seminarId,@RequestParam("presentId")Long presentId,@RequestParam("round")int round)
    {
        return studentService.ask(studentId, seminarId, presentId, round);
    }

}
