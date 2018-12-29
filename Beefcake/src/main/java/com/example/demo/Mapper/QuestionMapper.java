package com.example.demo.mapper;

import com.example.demo.entity.QuestionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface QuestionMapper {

    /**
     * 学生提问
     *
     * @param klassSeminarId
     * @param attendanceId
     * @param teamId
     * @param studentId
     * @return
     */
    boolean askQuestion(@Param("klassSeminarId") Long klassSeminarId, @Param("attendanceId") Long attendanceId, @Param("teamId") Long teamId, @Param("studentId") Long studentId);

    /**
     * 给学生提问打分
     *
     * @param questionId
     * @param score
     * @return
     */
    boolean score(@Param("questionId") Long questionId, @Param("score") double score);


    /**
     * 获取所有提问
     *
     * @param klassSeminarId
     * @return
     */
    List<QuestionEntity> getAllQuestion(@Param("klassSeminarId") Long klassSeminarId);


    /**
     * 老师抽取提问
     *
     * @param attendanceId
     * @param teamId
     * @return
     */
    QuestionEntity getQuestion(@Param("attendanceId") Long attendanceId, @Param("teamId") Long teamId);
}
