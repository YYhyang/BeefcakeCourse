package com.example.demo.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TeamVO {
    Long id;
    String serial_name;
    String team_name;
    Integer status;
    StudentInTeamVO leader;
    List<StudentInTeamVO> members;
    String isLeader;
}
