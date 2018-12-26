package com.example.demo.VO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReceiveCourseVO {
    Long receiveCourseId;
    String receiveCourseName;
    String teacherName;
}
