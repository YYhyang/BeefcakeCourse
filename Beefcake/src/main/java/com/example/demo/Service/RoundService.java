package com.example.demo.service;

import com.example.demo.dto.changeRoundDTO;
import com.example.demo.dto.changeRoundScoreDTO;
import com.example.demo.dao.KlassDao;
import com.example.demo.dao.RoundDao;
import com.example.demo.entity.*;
import com.example.demo.mapper.RoundMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class RoundService {
    @Autowired
    RoundDao roundDao;
    @Autowired
    RoundMapper roundMapper;
    @Autowired
    KlassDao klassDao;

    public List<SeminarEntity> findAllSeminarByRoundId(Long roundId) {
        return roundDao.findAllSeminarByRoundId(roundId);
    }

    public RoundEntity getRoundByRoundId(Long roundId) {
        return roundDao.getRoundByRoundId(roundId);
    }

    public RoundscoreEntity getAllRoundScoreByRoundIdAndTeamId(Long roundId, Long teamId){
        return roundDao.getAllRoundScoreByRoundIdAndTeamId(roundId, teamId);
    }

    public List<RoundscoreEntity> getAllRoundScoreByRoundId(Long roundId){
        return roundDao.getAllRoundScoreByRoundId(roundId);
    }

    public boolean changeRound(Long roundId, changeRoundDTO changeRoundDTO)
    {
        boolean roundinfo=roundDao.changeRoundInfo(roundId,changeRoundDTO.getPresentation_score_method(),changeRoundDTO.getReport_score_method(),changeRoundDTO.getQuestion_score_method());
        boolean klassRoundInfo=roundDao.changeSignUpnum(roundId,changeRoundDTO.getClassRoundDTOList());
        if(roundinfo&&klassRoundInfo) {
            return true;
        } else {
            return false;
        }
    }

    public boolean changeRoundScore(Long roundId, Long teamId, changeRoundScoreDTO dto){
        double finalScore=(dto.getPresentationScore()+dto.getQuestionScore()+dto.getReportScore())/3;
        return roundDao.changeRoundScore(roundId,teamId,dto.getPresentationScore(),dto.getReportScore(),dto.getQuestionScore(),finalScore);
    }

    public Long createRound(int roundSerial, Long courseId) {

        roundDao.createRound(roundSerial, courseId);
        Long roundId=roundMapper.returnId(courseId,roundSerial);
        List<Long>ids=klassDao.getAllKlassId(courseId);
        for(Long classId:ids){
            roundDao.createEnrollNumber(classId,roundId,1);
        }
        Long id=roundMapper.returnId(courseId,roundSerial);
        return id;
    }
}
