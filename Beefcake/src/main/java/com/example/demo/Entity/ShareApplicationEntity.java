package com.example.demo.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShareApplicationEntity {
    Long id;
    Long main_course_id;
    Long sub_course_id;
    Long sub_course_teacher_id;
    String status;
}
