package com.example.demo.Service;

import com.example.demo.Entity.TeacherEntity;
import com.example.demo.Mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;

    public TeacherEntity searchTeacher(String identity ){
        for (int i = identity.length();--i>=0;){
            if (!Character.isDigit(identity.charAt(i))){ return teacherMapper.searchTeacherName(identity);}
        }
        return teacherMapper.searchTeacherAccount(identity);
    }

}
