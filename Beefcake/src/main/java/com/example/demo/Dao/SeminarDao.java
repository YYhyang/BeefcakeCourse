package com.example.demo.dao;

import com.example.demo.entity.SeminarEntity;
import com.example.demo.entity.SeminarScoreEntity;
import com.example.demo.mapper.KlassMapper;
import com.example.demo.mapper.SeminarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class SeminarDao {
    @Autowired
    private SeminarMapper seminarMapper;
    @Autowired
    private KlassMapper klassMapper;

    public Long createSeminar(Long courseId, Long roundId, String seminarName,
                                 String introduction, int maxTeam, int visible,
                                  Date start,Date end)
    {
        Integer order=seminarMapper.getMaxSerial(courseId);
        if(order==null) {
            order=1;
        } else {
            order++;
        }
         seminarMapper.createSeminar(courseId, roundId, seminarName, introduction, maxTeam, visible, order, start, end);
         return seminarMapper.returnId(courseId,roundId,order);
    }

    public boolean insertIntoKlassSeminar(Long courseId,Long seminarId){
        List<Long> klassIds=klassMapper.getAllKlassId(courseId);
        for(Long id:klassIds)
        {
            if(!seminarMapper.insertIntoKlassSeminar(id,seminarId,0)) {
                return false;
            }
        }
        return true;
    }

    public int getStatus(Long seminarId,Long classId)
    {
        return seminarMapper.getStatus(seminarId, classId);
    }

    public boolean changeSeminar(Long courseId, String seminarName,
                                 String introduction, int maxTeam, int visible,
                                 Date start,Date end,Long seminarId)
    {
        return seminarMapper.changeSeminar(courseId, seminarName, introduction, maxTeam, visible, start, end, seminarId);
    }

    public List<Long> getClassIdBySeminarId(Long seminarId)
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

    public boolean updatePresentationScore(Long klassSeminarId, Long teamId,double presentationScore){return seminarMapper.updatePresentationScore(klassSeminarId,teamId,presentationScore);}
}
