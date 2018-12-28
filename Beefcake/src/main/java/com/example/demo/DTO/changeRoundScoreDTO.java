package com.example.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class changeRoundScoreDTO {
    private Double presentationScore;
    private Double reportScore;
    private Double questionScore;
}
