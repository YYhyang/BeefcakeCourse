package com.example.demo.Dao;

import com.example.demo.Entity.SeminarEntity;
import com.example.demo.Entity.SeminarScoreEntity;
import com.example.demo.Mapper.SeminarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class SeminarDao {
    @Autowired
    private SeminarMapper seminarMapper;

    public boolean createSeminar(Long courseId, Long roundId, String seminarName,
                                 String introduction, int maxTeam, int visible,
                                 int order, Date start,Date end)
    {
        return seminarMapper.createSeminar(courseId, roundId, seminarName, introduction, maxTeam, visible, order, start, end);
    }

    public boolean changeSeminar(Long courseId, Long roundId, String seminarName,
                                 String introduction, int maxTeam, int visible,
                                 int order, Date start,Date end,Long seminarId)
    {
        return seminarMapper.changeSeminar(courseId, roundId, seminarName, introduction, maxTeam, visible, order, start, end, seminarId);
    }

    public Long getClassIdBySeminarId(Long seminarId)
    {
        return seminarMapper.getClassIdBySeminarId(seminarId);
    }

    public boolean deleteSeminar(Long seminarId)
    {
        return seminarMapper.deleteSeminar(seminarId);
    }
    public boolean changeReportDDL(Long seminarId,Long classId, Date ddl){return seminarMapper.changeReportDDL(seminarId, classId, ddl);}
    public boolean setRound(Long seminarId,Long roundId){return seminarMapper.setRound(seminarId, roundId);}
    public boolean setStatus(Long seminarId,Long classId,int status){return seminarMapper.setStatus(seminarId, classId, status);}
    public SeminarEntity getSeminarById(Long seminarId){return seminarMapper.getSeminarBySeminarId(seminarId);}
    public Long getClassIdByTeamId(Long teamId){return seminarMapper.getClassIdByTeamId(teamId);}
    public Long getKlassSeminarIdByClassIdAndSeminarId(Long classId,Long seminarId){return seminarMapper.getKlassSeminarIdByClassIdAndSeminarId(classId, seminarId);}
    public SeminarScoreEntity getScoreByKlassSeminarIdAndTeamId(Long klassSeminarId, Long teamId){return seminarMapper.getScoreByKlassSeminarIdAndTeamId(klassSeminarId, teamId);}
    public boolean setReportScore(Long klassSeminarId,Long teamId,double reportScore){return seminarMapper.setReportScore(klassSeminarId, teamId, reportScore);}
    public boolean setPresentationScore(Long klassSeminarId,Long teamId,double presentationScore){return seminarMapper.setPresentationScore(klassSeminarId, teamId, presentationScore);}

    public List<Long> getAllTeamId(Long klassSeminarId){return seminarMapper.getAllTeamId(klassSeminarId);}
    public double getScoreByTeamId(Long teamId){return seminarMapper.getScoreByTeamId(teamId);}
    public String findPresentation(Long klassSeminarId,Long teamId){return seminarMapper.findPresentation(klassSeminarId, teamId);}
    public boolean setQuestionScore(Long klassSeminarId,Long teamId,double questionScore){return seminarMapper.setQuestionScore(klassSeminarId, teamId, questionScore);}
    public boolean createQuestionScore(Long klassSeminarId,Long teamId,double questionScore){return seminarMapper.createQuestionScore(klassSeminarId, teamId, questionScore);}
}
