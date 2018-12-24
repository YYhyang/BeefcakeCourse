package com.example.demo.Entity;

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
    private int presentation_percentage;
    private int question_percentage;
    private int report_percentage;
    private Date team_start_time;
    private Timestamp team_end_time;
    private Long team_main_course_id;
    private Long seminar_main_course_id;
    private List<ClassEntity> klasses;
    private List<RoundEntity> rounds;
}
