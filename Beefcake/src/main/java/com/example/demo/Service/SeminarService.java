package com.example.demo.Service;

import com.example.demo.DTO.CreateSeminarDTO;
import com.example.demo.DTO.changeSeminarRoundDTO;
import com.example.demo.DTO.changeSeminarStatusDTO;
import com.example.demo.Dao.KlassDao;
import com.example.demo.Dao.SeminarDao;
import com.example.demo.Entity.ClassEntity;
import com.example.demo.Entity.SeminarEntity;
import com.example.demo.Entity.SeminarScoreEntity;
import com.example.demo.VO.KlassSeminarInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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

    public boolean changeSeminar(Long seminarId, CreateSeminarDTO createSeminarDTO)
    {
        return seminarDao.changeSeminar(createSeminarDTO.getCourseId(),createSeminarDTO.getRoundId(),createSeminarDTO.getSeminarName(),
                createSeminarDTO.getIntroduction(),createSeminarDTO.getMaxTeam(),createSeminarDTO.getVisible(),
                createSeminarDTO.getOrder(),createSeminarDTO.getStart(),createSeminarDTO.getEnd(),seminarId);
    }

    public boolean deleteSeminar(Long seminarId){
        return seminarDao.deleteSeminar(seminarId);
    }
    public boolean changeReportDDL(Long seminarId, Long classId, Date ddl){return seminarDao.changeReportDDL(seminarId, classId, ddl);}
    public List <ClassEntity> getClassBySeminarId(Long seminarId)
    {
        List<Long> classIds=seminarDao.getClassIdBySeminarId(seminarId);
        List<ClassEntity>classEntities=new ArrayList<>();
        for(Long classId:classIds){
            classEntities.add(klassDao.getKlassById(classId));
        }
        return classEntities;
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
    public SeminarScoreEntity getScoreBySeminarIdAndTeamId(Long seminarId, Long teamId)
    {
        Long classId=seminarDao.getClassIdByTeamId(teamId);
        Long klassSeminarId=seminarDao.getKlassSeminarIdByClassIdAndSeminarId(classId,seminarId);
        SeminarScoreEntity score=seminarDao.getScoreByKlassSeminarIdAndTeamId(klassSeminarId,teamId);
        return score;
    }

    public boolean setPresentationScore(Long seminarId,Long classId,Long teamId,double presentationScore)
    {

        Long klassSeminarId=seminarDao.getKlassSeminarIdByClassIdAndSeminarId(classId,seminarId);
        String score=String.valueOf(seminarDao.getScoreByKlassSeminarIdAndTeamId(klassSeminarId,teamId).getPresentation_score());
        if(score==null)
            return seminarDao.setPresentationScore(klassSeminarId,teamId,presentationScore);
        else
            return seminarDao.updatePresentationScore(klassSeminarId,teamId,presentationScore);
    }

    public boolean updatePresentationScore(Long seminarId, Long classId, Long teamId,double presentationScore)
    {
        Long klassSeminarId=seminarDao.getKlassSeminarIdByClassIdAndSeminarId(classId,seminarId);
        return seminarDao.updatePresentationScore(klassSeminarId,teamId,presentationScore);
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
                temp=seminarDao.setQuestionScore(klassSeminarId,teamId,score);
            if(!temp)
                return temp;
        }
        return true;
    }
}
