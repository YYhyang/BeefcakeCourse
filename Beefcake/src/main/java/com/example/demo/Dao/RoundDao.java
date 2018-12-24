package com.example.demo.Dao;

import com.example.demo.DTO.classRoundDTO;
import com.example.demo.Entity.RoundEntity;
import com.example.demo.Entity.RoundscoreEntity;
import com.example.demo.Entity.SeminarEntity;
import com.example.demo.Mapper.RoundMapper;
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

    public RoundEntity getRoundById(Long roundId){
        return roundMapper.getRoundById(roundId);
    }


    public List<SeminarEntity> findAllSeminarByRoundId(Long roundId){
        return roundMapper.finaAllSeminarByRoundId(roundId);
    }

    public RoundEntity getRoundByRoundId(Long roundId){
        return roundMapper.getRoundByRoundId(roundId);
    }

    public RoundscoreEntity getAllRoundScoreByRoundIdAndTeamId(Long roundId,Long teamId){return roundMapper.getAllRoundScoreByRoundIdAndTeamId(roundId, teamId);}

    public List<RoundscoreEntity> getAllRoundScoreByRoundId(Long roundId){return roundMapper.getAllRoundScoreByRoundId(roundId);}
    public boolean createRound(int round_serial,Long courseId){
        return roundMapper.createRound(round_serial, courseId);
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
            if(!change)
                return false;
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
