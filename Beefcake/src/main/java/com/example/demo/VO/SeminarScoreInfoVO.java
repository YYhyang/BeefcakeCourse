package com.example.demo.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SeminarScoreInfoVO {
    private Long team_id;
    private String teamName;
    private Double presentation_score;
    private Double report_score;
    private Double question_score;
    private Double total_score;
}
