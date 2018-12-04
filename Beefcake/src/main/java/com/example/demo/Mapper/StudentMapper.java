package com.example.demo.Mapper;

import com.example.demo.Entity.TeamStudentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

@Mapper
public interface StudentMapper {
    public TeamStudentEntity findTeamId(@Param("studentId") String studentId);

    public boolean ask(@Param("studentId")String studentId,@Param("seminarId")Long seminarId,@Param("teamId")Long teamId,@Param("presentId")Long presentId,@Param("round")int round);
}

