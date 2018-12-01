package com.example.demo.Dao;

import com.example.demo.Entity.TeamSeminarEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeamSeminarDao {

    public List<TeamSeminarEntity> findAll(@Param("seminarId")Long seminarId);

    public TeamSeminarEntity find(@Param("teamId")long teamId, @Param("no")long no);

    public boolean save(@Param("seminarId")Long seminarId,@Param("teamId")Long teamId,@Param("no")int no);

    public boolean start(@Param("seminarId")Long seminarId);

}
