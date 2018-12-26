package com.example.demo.VO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MasterCourseVO {
    Long masterCourseId;
    String masterCourseName;
    String teacherName;
}
