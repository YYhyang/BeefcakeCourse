package com.example.demo.VO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeamSimpleVO {
    private Long id;
    private String team_name;
    private int team_serial;
    private int klass_serial;
    private String serial_name;
}
