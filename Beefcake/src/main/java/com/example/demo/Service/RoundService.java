package com.example.demo.Service;

import com.example.demo.DTO.changeRoundDTO;
import com.example.demo.Dao.RoundDao;
import com.example.demo.Entity.RoundEntity;
import com.example.demo.Entity.RoundscoreEntity;
import com.example.demo.Entity.SeminarEntity;
import com.example.demo.Entity.UserEntity;
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

    public boolean createRound(int round_serial, Long courseId) {

        return roundDao.createRound(round_serial, courseId);
    }
}
