package com.example.demo.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MasterCourseVO {
    Long masterCourseId;
    String masterCourseName;
    String teacherName;
}
