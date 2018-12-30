package com.example.demo.service;

import com.example.demo.dto.CreateSeminarDTO;
import com.example.demo.dto.changeSeminarRoundDTO;
import com.example.demo.dto.changeSeminarStatusDTO;
import com.example.demo.dao.KlassDao;
import com.example.demo.dao.SeminarDao;
import com.example.demo.entity.ClassEntity;
import com.example.demo.entity.SeminarEntity;
import com.example.demo.entity.SeminarScoreEntity;
import com.example.demo.mapper.SeminarMapper;
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
    @Autowired
    private RoundService roundService;
    @Autowired
    private SeminarMapper seminarMapper;

    public boolean createSeminar(CreateSeminarDTO createSeminarDTO)
    {
        Long seminarId;
        if(createSeminarDTO.getRoundId()!=-1){
             seminarId= seminarDao.createSeminar(createSeminarDTO.getCourseId(),createSeminarDTO.getRoundId(),createSeminarDTO.getSeminarName(),
                                        createSeminarDTO.getIntroduction(),createSeminarDTO.getMaxTeam(),createSeminarDTO.getVisible()
                ,createSeminarDTO.getStart(),createSeminarDTO.getEnd());
             return seminarDao.insertIntoKlassSeminar(createSeminarDTO.getCourseId(),seminarId);
        }
        else{
            Long roundId=roundService.createRound(createSeminarDTO.getRoundOrder(),createSeminarDTO.getCourseId());
             seminarId= seminarDao.createSeminar(createSeminarDTO.getCourseId(),roundId,createSeminarDTO.getSeminarName(),
                    createSeminarDTO.getIntroduction(),createSeminarDTO.getMaxTeam(),createSeminarDTO.getVisible(),
                    createSeminarDTO.getStart(),createSeminarDTO.getEnd());
             return seminarDao.insertIntoKlassSeminar(createSeminarDTO.getCourseId(),seminarId);
        }
    }

    public boolean changeSeminar(Long seminarId, CreateSeminarDTO createSeminarDTO)
    {
        return seminarDao.changeSeminar(createSeminarDTO.getCourseId(),createSeminarDTO.getSeminarName(),
                createSeminarDTO.getIntroduction(),createSeminarDTO.getMaxTeam(),createSeminarDTO.getVisible()
                ,createSeminarDTO.getStart(),createSeminarDTO.getEnd(),seminarId);
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
        if(score==null) {
            return seminarDao.setPresentationScore(klassSeminarId,teamId,presentationScore);
        } else {
            return seminarDao.updatePresentationScore(klassSeminarId,teamId,presentationScore);
        }
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
            if(!temp) {
                return temp;
            }
        }
        return true;
    }

    public boolean deleteSeminarFromKlassSeminar(Long seminarId,Long classId)
    {
        return seminarMapper.deleteKlassSeminar(classId,seminarId);
    }
}
