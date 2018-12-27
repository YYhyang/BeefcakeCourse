package com.example.demo.VO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoundScoreInfoVO {
    private double  presentation_score;
    private double report_score;
    private double question_score;
    private double total_score;
    private Long team_id;
    private String team_name;
    private String team_serial_name;
    private Long round_id;
    private int round_order;

}
