package com.example.demo.entity;

import com.example.demo.dto.CourseDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class CourseEntity {
    private Long id;
    private TeacherEntity teacher;
    private String course_name;
    private String introduction;
    private Integer presentation_percentage;
    private Integer question_percentage;
    private Integer report_percentage;
    private String team_start_time;
    private String team_end_time;
    private Long team_main_course_id;
    private Long seminar_main_course_id;
    private String klassName;
    private List<ClassEntity> klasses;
    private List<RoundEntity> rounds;

    public CourseEntity(CourseDTO courseDTO){
        this.course_name=courseDTO.getName();
        this.introduction=courseDTO.getIntro();
        this.presentation_percentage=courseDTO.getPresentationWeight();
        this.question_percentage=courseDTO.getQuestionWeight();
        this.report_percentage=courseDTO.getReportWeight();
        this.team_start_time=courseDTO.getStartTeamTime();
        this.team_end_time=courseDTO.getEndTeamTime();
    }
}


