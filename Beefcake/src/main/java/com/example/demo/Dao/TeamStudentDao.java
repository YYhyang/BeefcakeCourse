package com.example.demo.Dao;

import com.example.demo.Mapper.TeamStudentMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TeamStudentDao {
    @Autowired
    private TeamStudentMapper teamStudentMapper;

    public List<Long> getTeamMembersId(Long teamId){
        return teamStudentMapper.getTeamMembersId(teamId);
    }
}
