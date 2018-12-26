package com.example.demo.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class AttendanceEntity {
    private Long id;
    private Long klass_seminar_id;
    private TeamEntity team;
    private int team_order;
    private String report_name;
    private String report_url;
    private String ppt_name;
    private String ppt_url;


}
