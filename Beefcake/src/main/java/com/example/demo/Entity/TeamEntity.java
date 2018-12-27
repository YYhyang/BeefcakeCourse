package com.example.demo.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TeamEntity {
    private Long id;
    private String team_name;
    private StudentEntity leader;
    private int team_serial;
    private int class_serial;
    private ClassEntity klass;
    private CourseEntity course;
    private List<StudentEntity> members;
    private int status;
}
