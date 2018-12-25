package com.example.demo.Mapper;

import com.example.demo.Entity.SeminarEntity;
import com.example.demo.Entity.SeminarScoreEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Mapper
@Component
public interface SeminarMapper {
    public boolean createSeminar(@Param("courseId")Long courseId, @Param("roundId")Long roundId, @Param("seminarName")String seminarName,
                                 @Param("introduction")String introduction, @Param("max_team")int maxTeam, @Param("isVisible")int visible,
                                 @Param("seminarSerial")int order, @Param("enrollStart")Date start,@Param("enrollEnd")Date end);
    public Long getClassIdBySeminarId(@Param("seminarId")Long seminarId);
    public Long getClassIdByTeamId(@Param("teamId")Long teamId);
    public Long getKlassSeminarIdByClassIdAndSeminarId(@Param("classId")Long classId,@Param("seminarId")Long seminarId);
    public boolean changeSeminar(@Param("courseId")Long courseId, @Param("roundId")Long roundId, @Param("seminarName")String seminarName,
                                 @Param("introduction")String introduction, @Param("max_team")int maxTeam, @Param("isVisible")int visible,
                                 @Param("seminarSerial")int order, @Param("enrollStart")Date start,@Param("enrollEnd")Date end,@Param("seminarId")Long seminarId);

    public boolean deleteSeminar(@Param("seminarId")Long seminarId);

    public SeminarScoreEntity getScoreByKlassSeminarIdAndTeamId(@Param("klass_seminar_id")Long klassSeminarId,@Param("teamId")Long teamId);

    public boolean changeReportDDL(@Param("seminarId")Long seminarId,@Param("classId")Long classId,@Param("reportDDL")Date reportDDL);
    public boolean setRound(@Param("seminarId")Long seminarId,@Param("roundId")Long roundId);
    public boolean setStatus(@Param("seminarId")Long seminarId,@Param("classId")Long classId,@Param("status")int status);
    public boolean setPresentationScore(@Param("klass_seminar_id")Long klassSeminarId,@Param("teamId")Long teamId,@Param("presentationScore")double presentationScore);
    public boolean setReportScore(@Param("klass_seminar_id")Long klassSeminarId,@Param("teamId")Long teamId,@Param("reportScore")double reportScore);
    public SeminarEntity getSeminarBySeminarId(@Param("seminarId")Long seminarId);
    public List<Long> getAllTeamId(@Param("klassSeminarId")Long klassSeminarId);
    public double getScoreByTeamId(@Param("teamId")Long teamId);
    public String findPresentation(@Param("klassSeminarId")Long klassSeminarId,@Param("teamId")Long teamId);
    public boolean setQuestionScore(@Param("klassSeminarId")Long klassSeminarId,@Param("teamId")Long teamId,@Param("questionScore")double questionScore);
    public boolean createQuestionScore(@Param("klassSeminarId")Long klassSeminarId,@Param("teamId")Long teamId,@Param("questionScore")double questionScore);
}
