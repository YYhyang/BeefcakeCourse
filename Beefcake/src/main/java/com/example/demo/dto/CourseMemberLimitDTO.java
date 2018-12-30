package com.example.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CourseMemberLimitDTO {
    private Long courseId;
    private Integer maxMember;
    private Integer minMember;
}
