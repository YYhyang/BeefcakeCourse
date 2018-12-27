package com.example.demo.Mapper;

import com.example.demo.Entity.QuestionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface QuestionMapper {
    //获取所有提问
    public List<QuestionEntity> getAllQuestion(@Param("klassSeminarId")Long klassSeminarId);
    //学生提问
    public boolean askQuestion(@Param("klassSeminarId")Long klassSeminarId,@Param("attendanceId")Long attendanceId,@Param("teamId")Long teamId,@Param("studentId")Long studentId);
    //给学生打分
    public boolean score(@Param("questionId")Long questionId,@Param("score")double score);
    //教师抽取提问
    public QuestionEntity getQuestion(@Param("attendanceId")Long attendanceId,@Param("teamId")Long teamId);
}
