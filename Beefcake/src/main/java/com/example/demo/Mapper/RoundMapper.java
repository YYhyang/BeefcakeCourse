package com.example.demo.Mapper;

import com.example.demo.Entity.RoundEntity;
import com.example.demo.Entity.RoundscoreEntity;
import com.example.demo.Entity.SeminarEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface RoundMapper {

    public List<SeminarEntity> finaAllSeminarByRoundId(@Param("roundId")Long roundId);
    public RoundEntity getRoundByRoundId(@Param("roundId")Long roundId);
    public RoundscoreEntity getAllRoundScoreByRoundIdAndTeamId(@Param("roundId")Long roundId,@Param("teamId")Long teamId);
    public List<RoundscoreEntity> getAllRoundScoreByRoundId(@Param("roundId")Long roundId);
    public boolean createRound(@Param("round_serial")int round_serial,@Param("courseId")Long courseId);
    public boolean changeRoundInfo(@Param("roundId")Long roundId,@Param("presentation")int presentation,@Param("report")int report,@Param("question")int question);
    public boolean changeSignUpnum(@Param("klassId")Long klassId,@Param("roundId")Long roundId,@Param("enrollNumber")int enrollNumber);
    public boolean changeRoundScore(@Param("roundId")Long roundId,@Param("teamId")Long teamId,@Param("presentationScore")double presentationScore,@Param("reportScore")double reportScore,
                                    @Param("questionScore")double questionScore,@Param("finalScore")double finalScore);
    public boolean createSignUpnum(@Param("klassId")Long klassId,@Param("roundId")Long roundId);
    public List<Long> getAllRoundId(@Param("courseId") Long courseId);
    public Long returnId(@Param("courseId")Long courseId,@Param("round_serial")int round_serial);
    public boolean createEnrollNumber(@Param("klassId")Long klassId,@Param("roundId")Long roundId,@Param("enrollNumber")int enrollNumber);
}
