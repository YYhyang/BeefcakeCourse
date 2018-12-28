package com.example.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuestionEntity {
    private Long id;
    private Long klass_seminar_id;
    private Long attendance_id;
    private TeamEntity team;
    private StudentEntity student;
    private int is_selected;
    private double score;

}
