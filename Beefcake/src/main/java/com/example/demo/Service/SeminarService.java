package com.example.demo.Service;

import com.example.demo.Dao.TeamSeminarDao;
import com.example.demo.Entity.TeamSeminarEntity;
import com.example.demo.Entity.TeamStudentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class SeminarService {

    @Autowired
    private TeamSeminarDao teamSeminarDao;

    public List<TeamSeminarEntity> findAllTeam(Long seminarId) {
        return teamSeminarDao.findAll(seminarId);
    }

}