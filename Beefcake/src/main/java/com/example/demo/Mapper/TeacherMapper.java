package com.example.demo.Mapper;

import com.example.demo.Entity.TeacherEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherMapper {
    //public List<QuestionEntity> extractQuestion(@Param("seminarId")Long seminarId, @Param("presentId")Long presentId);

    // public Boolean scoreQuestion(@Param("seminarId")Long seminarId,@Param("presentId")Long presentId,@Param("studentId")Long studentId,@Param("score")BigDecimal score);

    public Boolean createTeacher(@Param("account") String account, @Param("password")String password, @Param("teacher_name")String teacher_name, @Param("is_active")int is_active,@Param("email")String email);

    public List<TeacherEntity> getAll( );

    public TeacherEntity searchTeacherAccount(@Param("account")String identity );

    public TeacherEntity searchTeacherName(@Param("teacher_name")String identity );

    public Boolean putTeacherInfo(@Param("teacherId")Integer teacherId,@Param("account")String account,@Param("teacher_name")String teacher_name,@Param("email")String email );

    public Boolean putTeacherPassword(@Param("teacherId")Integer teacherId );

    public Boolean deleteTeacher(@Param("teacherId")Integer teacherId );

    public Boolean activateTeacher(@Param("teacherId")Integer teacherId,@Param("password")String password );

}
