package com.example.demo.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class changeRoundScoreDTO {
    private double presentationScore;
    private double reportScore;
    private double questionScore;
}
