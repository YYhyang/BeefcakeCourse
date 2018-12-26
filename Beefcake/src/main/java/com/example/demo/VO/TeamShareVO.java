package com.example.demo.VO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeamShareVO {
    Long teamShareId;
    MasterCourseVO masterCourse;
    ReceiveCourseVO receiveCourse;
}
