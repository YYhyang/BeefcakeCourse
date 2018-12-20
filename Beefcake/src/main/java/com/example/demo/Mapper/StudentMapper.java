package com.example.demo.Mapper;

import com.example.demo.Entity.StudentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface StudentMapper {
    //public TeamStudentEntity findTeamId(@Param("studentId") String studentId);

    // public boolean ask(@Param("studentId")String studentId,@Param("seminarId")Long seminarId,@Param("teamId")Long teamId,@Param("presentId")Long presentId,@Param("round")int round);

    public List<StudentEntity> getAllStudent();

    public StudentEntity searchStudentAccount(@Param("account")String identity );

    public StudentEntity searchStudentName(@Param("student_name")String identity );

    public Boolean putStudentInfo(@Param("studentId")Integer studentId,@Param("account")String account,@Param("student_name")String student_name,@Param("email")String email );

    public Boolean putStudentPassword(@Param("studentId")Integer stduentId );

    public Boolean deleteStudent(@Param("studentId")Integer studentId );

    public Boolean activateStudent(@Param("studentId")Integer studentId,@Param("password")String password ,@Param("email")String email);
}

