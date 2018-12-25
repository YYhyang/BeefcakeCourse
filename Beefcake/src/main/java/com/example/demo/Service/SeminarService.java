package com.example.demo.Service;

import com.example.demo.DTO.CreateSeminarDTO;
import com.example.demo.DTO.changeSeminarRoundDTO;
import com.example.demo.DTO.changeSeminarStatusDTO;
import com.example.demo.Dao.KlassDao;
import com.example.demo.Dao.SeminarDao;
import com.example.demo.Entity.ClassEntity;
import com.example.demo.Entity.SeminarEntity;
import com.example.demo.Entity.SeminarScoreEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class SeminarService {
    @Autowired
    private SeminarDao seminarDao;
    @Autowired
    private KlassDao klassDao;

    public boolean createSeminar(CreateSeminarDTO createSeminarDTO)
    {
        return seminarDao.createSeminar(createSeminarDTO.getCourseId(),createSeminarDTO.getRoundId(),createSeminarDTO.getSeminarName(),
                                        createSeminarDTO.getIntroduction(),createSeminarDTO.getMaxTeam(),createSeminarDTO.getVisible(),
                                        createSeminarDTO.getOrder(),createSeminarDTO.getStart(),createSeminarDTO.getEnd());
    }

    public boolean changeSeminar(Long seminarId,CreateSeminarDTO createSeminarDTO)
    {
        return seminarDao.changeSeminar(createSeminarDTO.getCourseId(),createSeminarDTO.getRoundId(),createSeminarDTO.getSeminarName(),
                createSeminarDTO.getIntroduction(),createSeminarDTO.getMaxTeam(),createSeminarDTO.getVisible(),
                createSeminarDTO.getOrder(),createSeminarDTO.getStart(),createSeminarDTO.getEnd(),seminarId);
    }

    public boolean deleteSeminar(Long seminarId){
        return seminarDao.deleteSeminar(seminarId);
    }
    public boolean changeReportDDL(Long seminarId, Long classId, Date ddl){return seminarDao.changeReportDDL(seminarId, classId, ddl);}
    public ClassEntity getClassBySeminarId(Long seminarId)
    {
        Long classId=seminarDao.getClassIdBySeminarId(seminarId);
        return klassDao.getKlassById(classId);
    }
    public SeminarEntity getSeminarBySeminarId(Long seminarId)
    {
        return  seminarDao.getSeminarById(seminarId);
    }
    public boolean setRound(Long seminarId, changeSeminarRoundDTO dto){
        return seminarDao.setRound(seminarId,dto.getId());
    }
    public boolean setStatus(Long seminarId, changeSeminarStatusDTO dto){
        return seminarDao.setStatus(seminarId,dto.getClassId(),dto.getStatus());
    }
    public SeminarScoreEntity getScoreBySeminarIdAndTeamId(Long seminarId,Long teamId)
    {
        Long classId=seminarDao.getClassIdByTeamId(teamId);
        Long klassSeminarId=seminarDao.getKlassSeminarIdByClassIdAndSeminarId(classId,seminarId);
        SeminarScoreEntity score=seminarDao.getScoreByKlassSeminarIdAndTeamId(klassSeminarId,teamId);
        return score;
    }

    public boolean setPresentationScore(Long seminarId,Long teamId,double presentationScore)
    {
        Long classId=seminarDao.getClassIdByTeamId(teamId);
        Long klassSeminarId=seminarDao.getKlassSeminarIdByClassIdAndSeminarId(classId,seminarId);
        return seminarDao.setPresentationScore(klassSeminarId,teamId,presentationScore);
    }

    public boolean setReportScore(Long seminarId,Long teamId,double reportScore)
    {
        Long classId=seminarDao.getClassIdByTeamId(teamId);
        Long klassSeminarId=seminarDao.getKlassSeminarIdByClassIdAndSeminarId(classId,seminarId);
        return seminarDao.setReportScore(klassSeminarId, teamId, reportScore);
    }


    public boolean setQuestionScore(Long klassSeminarId){
        boolean temp;
        List<Long>teamIdList=seminarDao.getAllTeamId(klassSeminarId);
        for(Long teamId:teamIdList){
            double score=seminarDao.getScoreByTeamId(teamId);
            String k;
                    k=seminarDao.findPresentation(klassSeminarId,teamId);
            if(k==null)
                temp=seminarDao.createQuestionScore(klassSeminarId,teamId,score);
            else
                temp=seminarDao.setQuestionScore(klassSeminarId,teamId,score);
            if(!temp)
                return temp;
        }
        return true;
    }
}
