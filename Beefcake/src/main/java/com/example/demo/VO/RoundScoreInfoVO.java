package com.example.demo.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoundScoreInfoVO {
    private Double  presentation_score;
    private Double report_score;
    private Double question_score;
    private Double total_score;
    private Long team_id;
    private String team_name;
    private String team_serial_name;
    private Long round_id;
    private Integer round_order;

}
