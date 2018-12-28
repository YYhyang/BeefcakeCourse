package com.example.demo.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SeminarShareVO {
    Long seminarShareId;
    MasterCourseVO masterCourse;
    ReceiveCourseVO receiveCourse;
}
