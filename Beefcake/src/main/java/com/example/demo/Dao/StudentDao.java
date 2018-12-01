package com.example.demo.Dao;

import com.example.demo.Entity.TeamStudentEntity;
import org.springframework.data.repository.query.Param;

public interface StudentDao {

    public TeamStudentEntity findTeamId(@Param("studentId") String studentId);

    public boolean ask(@Param("studentId")String studentId,@Param("seminarId")Long seminarId,@Param("teamId")Long teamId,@Param("presentId")Long presentId,@Param("round")int round);
}
