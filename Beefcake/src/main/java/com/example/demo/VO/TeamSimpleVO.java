package com.example.demo.VO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeamSimpleVO {
    private Long id;
    private String team_name;
    private String serial_name;
    private int status;
}
