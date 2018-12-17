package com.example.demo.Service;

import com.example.demo.Entity.StudentEntity;
import com.example.demo.Mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;

    public StudentEntity searchStudent(String identity ){
        for (int i = identity.length();--i>=0;){
            if (!Character.isDigit(identity.charAt(i))){ return studentMapper.searchStudentName(identity);}
        }
        return studentMapper.searchStudentAccount(identity);
    }

}
