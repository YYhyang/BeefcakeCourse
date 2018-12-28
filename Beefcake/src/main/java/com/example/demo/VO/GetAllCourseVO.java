package com.example.demo.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetAllCourseVO {
    private Long id;
    private Integer isShareTeam;
    private Integer isShareSeminar;
    private String name;
    private String className;
    private Long classId;
    private String teacherName;
}
