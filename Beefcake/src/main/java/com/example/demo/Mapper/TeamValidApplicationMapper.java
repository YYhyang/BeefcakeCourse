package com.example.demo.Mapper;

import com.example.demo.Entity.TeamValidApplicationEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface TeamValidApplicationMapper {
    public void createRequest(@Param("teamId") Long teamId, @Param("teacherId") Long teacherId, @Param("reason") String reason);

    public void changeApplicationStatus(@Param("teamId") Long teamId, @Param("status") int status);

    public List<TeamValidApplicationEntity>  getTeamValidRequests(@Param("teacherId") Long teacherId);

    public TeamValidApplicationEntity getTeamValidRequest(@Param("requestId") Long requestId);

}
