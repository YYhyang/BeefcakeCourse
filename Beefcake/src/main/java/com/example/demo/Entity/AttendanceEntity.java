package com.example.demo.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
public class
AttendanceEntity {
    private Long id;
    private Long klass_seminar_id;
    private Long team_id;
    private int team_order;
    private String report_name;
    private String report_url;
    private String ppt_name;
    private String ppt_url;
}
