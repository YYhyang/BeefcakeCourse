package com.example.demo.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SeminarScoreEntity {
    private Long klass_seminar_id;
    private TeamEntity team;
    private double total_score;
    private double presentation_score;
    private double report_score;
    private double question_score;
}
