package com.example.demo.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class createTeamDTO {
    private String teamName;
    private Long courseId;
    private Long klassId;
    private List<TeamMemberDTO> members;
}
