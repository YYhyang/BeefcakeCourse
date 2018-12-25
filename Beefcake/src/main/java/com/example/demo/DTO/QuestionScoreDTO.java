package com.example.demo.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuestionScoreDTO {
    private Long team_id;
    private double score;
}
