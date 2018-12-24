package com.example.demo.VO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class GetTeamByIdVO {
    private Long id;
    private String name;
    private CourseInTeamVO course;
    private KlassInTeamVO klass;
    private StudentInTeamVO leader;
    private List<StudentInTeamVO> members;
    private int status;
}
