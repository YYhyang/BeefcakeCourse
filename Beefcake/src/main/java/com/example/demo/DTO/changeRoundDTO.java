package com.example.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class changeRoundDTO {
    private Integer presentation_score_method;
    private Integer report_score_method;
    private Integer question_score_method;
    private List<classRoundDTO> classRoundDTOList;

    @Override
    public String toString() {
        return "changeRoundDTO{" +
                "presentation_score_method=" + presentation_score_method +
                ", report_score_method=" + report_score_method +
                ", question_score_method=" + question_score_method +
                ", classRoundDTOList=" + classRoundDTOList +
                '}';
    }
}
