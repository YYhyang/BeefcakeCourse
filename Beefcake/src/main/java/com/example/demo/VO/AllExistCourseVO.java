package com.example.demo.VO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AllExistCourseVO {
    Long id;
    String name;
    Long techerId;
    String teacherName;
}
