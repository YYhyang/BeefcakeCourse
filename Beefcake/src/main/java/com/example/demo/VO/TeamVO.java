package com.example.demo.VO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TeamVO {
    String name;
    int status;
    StudentInTeamVO leader;
    List<StudentInTeamVO> members;
}
