package com.example.demo.Service;

import com.example.demo.Dao.StudentDao;
import com.example.demo.Entity.TeamStudentEntity;
import com.example.demo.Mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;

    public boolean ask(String studentId,Long seminarId,Long present_id,int round)
    {
        TeamStudentEntity teamStudentEntity=new TeamStudentEntity();
        teamStudentEntity=studentMapper.findTeamId(studentId);
        Long teamId=teamStudentEntity.getTeamId();
        return studentMapper.ask(studentId,seminarId,teamId,present_id,round);
    }
}
