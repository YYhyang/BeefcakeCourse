package com.example.demo.VO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SeminarScoreInfoVO {
    private Long team_id;
    private String teamName;
    private double presentation_score;
    private double report_score;
    private double question_score;
    private double total_score;
}
