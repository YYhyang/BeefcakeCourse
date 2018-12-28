package com.example.demo.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeamRequestDTO {
    private String requestType;
    private Long courseId;
    private Long classId;
    private Long teamId;
    private Long leaderId;
    private String reason;

}
