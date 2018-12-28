package com.example.demo.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeamValidApplicationVO {
    Long requestId;
    String courseName;
    Long teamId;
    String teamNo;
    String leaderName;
    String reason;
}
