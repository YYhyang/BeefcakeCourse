package com.example.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private Integer teamMaxMember;
    private Integer teamMinMember;
    private List<CourseMemberLimitDTO> courseMemberLimitDTOList;
    //0表示满足其一，或关系，1表示两者都满足，与关系
    private Integer relation;
    private List<List<Long>> confictCourseLists;
}
