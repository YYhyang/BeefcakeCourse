package com.example.demo.Dao;

import com.example.demo.Entity.QuestionEntity;
import com.example.demo.Entity.TeacherEntity;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

public interface TeacherDao {

    public QuestionEntity extractQuestion(@Param("seminarId")Long seminarId,@Param("presentId")Long presentId);

    public Boolean scoreQuestion(@Param("seminarId")Long seminarId,@Param("presentId")Long presentId,@Param("round")Integer round,@Param("score") BigDecimal score);
}
