package com.example.demo.VO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SeminarShareVO {
    Long seminarShareId;
    MasterCourseVO masterCourse;
    ReceiveCourseVO receiveCourse;
}
