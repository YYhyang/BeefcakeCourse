package com.example.demo.VO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetAllCourseVO {
    private Long id;
    private boolean isShareTeam;
    private boolean isShareSeminar;
    private String name;
    private String className;
    private Long classId;
    private String teacherName;
}
