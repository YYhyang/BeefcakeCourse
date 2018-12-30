package com.example.demo.mapper;

import com.example.demo.entity.SeminarEntity;
import com.example.demo.entity.SeminarScoreEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Mapper
@Component
public interface SeminarMapper {
    boolean createSeminar(@Param("courseId") Long courseId, @Param("roundId") Long roundId, @Param("seminarName") String seminarName,
                          @Param("introduction") String introduction, @Param("max_team") int maxTeam, @Param("isVisible") int visible,
                          @Param("seminarSerial") int order, @Param("enrollStart") Date start, @Param("enrollEnd") Date end);

    boolean changeSeminar(@Param("courseId") Long courseId, @Param("seminarName") String seminarName,
                          @Param("introduction") String introduction, @Param("max_team") int maxTeam, @Param("isVisible") int visible,
                          @Param("enrollStart") Date start, @Param("enrollEnd") Date end, @Param("seminarId") Long seminarId);

    boolean deleteSeminar(@Param("seminarId") Long seminarId);

    boolean deleteKlassSeminar(@Param("klassId")Long klassId,@Param("seminarId")Long seminarId);

    boolean insertIntoKlassSeminar(@Param("klassId") Long klassId, @Param("seminarId") Long seminarId, @Param("status") int status);

    boolean changeReportDDL(@Param("seminarId") Long seminarId, @Param("classId") Long classId, @Param("reportDDL") Date reportDDL);

    boolean setRound(@Param("seminarId") Long seminarId, @Param("roundId") Long roundId);

    boolean setStatus(@Param("seminarId") Long seminarId, @Param("classId") Long classId, @Param("status") int status);

    boolean setPresentationScore(@Param("klass_seminar_id") Long klassSeminarId, @Param("teamId") Long teamId, @Param("presentationScore") double presentationScore);

    boolean setReportScore(@Param("klass_seminar_id") Long klassSeminarId, @Param("teamId") Long teamId, @Param("reportScore") double reportScore);

    boolean setQuestionScore(@Param("klassSeminarId") Long klassSeminarId, @Param("teamId") Long teamId, @Param("questionScore") double questionScore);

    boolean createQuestionScore(@Param("klassSeminarId") Long klassSeminarId, @Param("teamId") Long teamId, @Param("questionScore") double questionScore);

    boolean updatePresentationScore(@Param("klass_seminar_id") Long klassSeminarId, @Param("teamId") Long teamId, @Param("presentationScore") double presentationScore);

    int getStatus(@Param("seminarId") Long seminarId, @Param("classId") Long classId);

    double getScoreByTeamId(@Param("teamId") Long teamId);

    Integer getMaxSerial(@Param("courseId") Long courseId);

    Long getClassIdByTeamId(@Param("teamId") Long teamId);

    Long returnId(@Param("courseId") Long courseId, @Param("roundId") Long roundId, @Param("order") int order);

    Long getKlassSeminarIdByClassIdAndSeminarId(@Param("classId") Long classId, @Param("seminarId") Long seminarId);

    String findPresentation(@Param("klassSeminarId") Long klassSeminarId, @Param("teamId") Long teamId);

    SeminarScoreEntity getScoreByKlassSeminarIdAndTeamId(@Param("klass_seminar_id") Long klassSeminarId, @Param("teamId") Long teamId);

    SeminarEntity getSeminarBySeminarId(@Param("seminarId") Long seminarId);

    List<Long> getClassIdBySeminarId(@Param("seminarId") Long seminarId);

    List<Long> getAllTeamId(@Param("klassSeminarId") Long klassSeminarId);
    }
