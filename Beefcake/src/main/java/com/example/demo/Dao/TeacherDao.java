package com.example.demo.Dao;

import com.example.demo.Entity.QuestionEntity;
import com.example.demo.Entity.TeacherEntity;
import org.apache.ibatis.annotations.Param;

public interface TeacherDao {

    public QuestionEntity extractQuestion(@Param("seminarId")Long seminarId,@Param("presentId")Long presentId);

}
