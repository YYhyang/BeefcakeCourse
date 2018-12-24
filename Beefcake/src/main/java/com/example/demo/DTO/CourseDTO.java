package com.example.demo.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class CourseDTO {
    private String name;
    private String intro;
    private double presentationWeight;
    private double questionWeight;
    private double reportWeight;
    private int minMemberNumber;
    private int maxMemberNumber;
    private Date startTeamTime;
    private Date endTeamTime;
    private List<Map<String,String>> conflictCourses;
}
