package com.example.demo.mapper;

import com.example.demo.entity.TeamValidApplicationEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface TeamValidApplicationMapper {

    void createRequest(@Param("teamId") Long teamId, @Param("teacherId") Long teacherId, @Param("reason") String reason);

    void changeApplicationStatus(@Param("teamId") Long teamId, @Param("status") int status);

    TeamValidApplicationEntity getTeamValidRequest(@Param("requestId") Long requestId);

    List<TeamValidApplicationEntity>  getTeamValidRequests(@Param("teacherId") Long teacherId);

    public Long haveApplication(@Param("teamId")Long teamId);


}
