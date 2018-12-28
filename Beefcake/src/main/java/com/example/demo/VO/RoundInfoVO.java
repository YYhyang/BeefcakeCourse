package com.example.demo.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoundInfoVO {
    private Long id;
    private Integer order;
    private Integer presentation_score_method;
    private Integer report_score_method;
    private Integer question_score_method;

}
