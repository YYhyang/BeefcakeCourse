package com.example.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class createTeamDTO {
    private String teamName;
    private Long courseId;
    private List<Long> members;
}
