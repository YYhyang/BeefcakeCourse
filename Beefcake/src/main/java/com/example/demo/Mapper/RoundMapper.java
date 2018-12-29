package com.example.demo.mapper;

import com.example.demo.entity.RoundEntity;
import com.example.demo.entity.RoundscoreEntity;
import com.example.demo.entity.SeminarEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface RoundMapper {
    boolean createRound(@Param("round_serial") int roundSerial, @Param("courseId") Long courseId);

    boolean createEnrollNumber(@Param("klassId") Long klassId, @Param("roundId") Long roundId, @Param("enrollNumber") int enrollNumber);

    boolean changeRoundInfo(@Param("roundId") Long roundId, @Param("presentation") int presentation, @Param("report") int report, @Param("question") int question);

    boolean changeSignUpnum(@Param("klassId") Long klassId, @Param("roundId") Long roundId, @Param("enrollNumber") int enrollNumber);

    boolean changeRoundScore(@Param("roundId") Long roundId, @Param("teamId") Long teamId, @Param("presentationScore") double presentationScore, @Param("reportScore") double reportScore,
                             @Param("questionScore") double questionScore, @Param("finalScore") double finalScore);

    Long returnId(@Param("courseId") Long courseId, @Param("round_serial") int roundSerial);

    //***********函数名待验证***********
    List<SeminarEntity> finaAllSeminarByRoundId(@Param("roundId") Long roundId);

    RoundEntity getRoundByRoundId(@Param("roundId") Long roundId);

    RoundscoreEntity getAllRoundScoreByRoundIdAndTeamId(@Param("roundId") Long roundId, @Param("teamId") Long teamId);

    List<Long> getAllRoundId(@Param("courseId") Long courseId);

    List<RoundscoreEntity> getAllRoundScoreByRoundId(@Param("roundId") Long roundId);

}
