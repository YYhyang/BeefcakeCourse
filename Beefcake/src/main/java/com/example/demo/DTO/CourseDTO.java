package com.example.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CourseDTO {
    private String name;
    private String intro;
    private Integer presentationWeight;
    private Integer questionWeight;
    private Integer reportWeight;
    private String startTeamTime;
    private String endTeamTime;
}
