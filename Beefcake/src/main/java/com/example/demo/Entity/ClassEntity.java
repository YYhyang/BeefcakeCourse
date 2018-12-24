package com.example.demo.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ClassEntity {
    private Long id;
    private int grade;
    private CourseEntity course;
    private int klass_serial;
    private String klass_time;
    private String klass_location;
    private List<RoundEntity> rounds;
    private List<TeamEntity> teams;
    private List<StudentEntity> students;
}
