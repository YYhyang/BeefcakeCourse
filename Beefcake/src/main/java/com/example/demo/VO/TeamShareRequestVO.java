package com.example.demo.VO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeamShareRequestVO {
    Long teamShareId;
    MasterCourseVO masterCourse;
}
