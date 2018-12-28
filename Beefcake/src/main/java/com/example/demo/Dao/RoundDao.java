package com.example.demo.dao;

import com.example.demo.dto.classRoundDTO;
import com.example.demo.entity.RoundEntity;
import com.example.demo.entity.RoundscoreEntity;
import com.example.demo.entity.SeminarEntity;
import com.example.demo.mapper.RoundMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoundDao {
    @Autowired
    RoundMapper roundMapper;

    public List<Long> getAllRoundId(Long courseId){
        return roundMapper.getAllRoundId(courseId);
    }

    public boolean createEnrollNumber(Long klassId,Long roundId,int enrollNumber){return roundMapper.createEnrollNumber(klassId, roundId, enrollNumber);}


    public List<SeminarEntity> findAllSeminarByRoundId(Long roundId){
        return roundMapper.finaAllSeminarByRoundId(roundId);
    }

    public RoundEntity getRoundByRoundId(Long roundId){
        return roundMapper.getRoundByRoundId(roundId);
    }

    public RoundscoreEntity getAllRoundScoreByRoundIdAndTeamId(Long roundId,Long teamId){return roundMapper.getAllRoundScoreByRoundIdAndTeamId(roundId, teamId);}

    public List<RoundscoreEntity> getAllRoundScoreByRoundId(Long roundId){return roundMapper.getAllRoundScoreByRoundId(roundId);}
    public boolean createRound(int roundSerial,Long courseId){
        return roundMapper.createRound(roundSerial, courseId);
    }
    public boolean changeRoundInfo(Long roundId,int presentation,int report,int question)
    {
        return roundMapper.changeRoundInfo(roundId, presentation, report, question);
    }

    public boolean changeSignUpnum(Long roundId,List<classRoundDTO>classRoundDTOList)
    {
        boolean change;
        for(classRoundDTO temp:classRoundDTOList){
            change=roundMapper.changeSignUpnum(temp.getKlass_id(),roundId,temp.getEnroll_number());
            if(!change) {
                return false;
            }
        }
        return true;
    }
    public boolean changeRoundScore(Long roundId,Long teamId,double presentationScore,double reportScore,
                                    double questionScore,double finalScore)
    {
        return roundMapper.changeRoundScore(roundId, teamId, presentationScore, reportScore, questionScore, finalScore);
    }

    public double averageScore(double presentationScore,double reportScore, double questionScore){
        return (presentationScore+reportScore+questionScore)/3;
    }
}
