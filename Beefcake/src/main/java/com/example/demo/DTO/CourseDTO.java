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
    private int presentationWeight;
    private int questionWeight;
    private int reportWeight;
    private int minMemberNumber;
    private int maxMemberNumber;
    private String startTeamTime;
    private String endTeamTime;
    private List<Map<String,String>> conflictCourses;
}
