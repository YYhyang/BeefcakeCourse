package com.example.demo.Service;

import com.example.demo.DTO.changeRoundDTO;
import com.example.demo.DTO.changeRoundScoreDTO;
import com.example.demo.Dao.KlassDao;
import com.example.demo.Dao.RoundDao;
import com.example.demo.Entity.*;
import com.example.demo.Mapper.RoundMapper;
import com.example.demo.Sercurity.JWTPayLoad;
import com.example.demo.Sercurity.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
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
        boolean klass_roundInfo=roundDao.changeSignUpnum(roundId,changeRoundDTO.getClassRoundDTOList());
        if(roundinfo&&klass_roundInfo)
            return true;
        else
            return false;
    }

    public boolean changeRoundScore(Long roundId, Long teamId, changeRoundScoreDTO dto){
        double finalScore=(dto.getPresentationScore()+dto.getQuestionScore()+dto.getReportScore())/3;
        return roundDao.changeRoundScore(roundId,teamId,dto.getPresentationScore(),dto.getReportScore(),dto.getQuestionScore(),finalScore);
    }

    public Long createRound(int round_serial, Long courseId) {

        roundDao.createRound(round_serial, courseId);
        Long roundId=roundMapper.returnId(courseId,round_serial);
        List<Long>ids=klassDao.getAllKlassId(courseId);
        for(Long classId:ids){
            roundDao.createEnrollNumber(classId,roundId,1);
        }
        Long id=roundMapper.returnId(courseId,round_serial);
        return id;
    }
}
