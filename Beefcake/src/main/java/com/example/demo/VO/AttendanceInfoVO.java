package com.example.demo.VO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AttendanceInfoVO {
    private Long id;
    private Long teamId;
    private String teamSerialName;
    private String pptStatus;
    private int order;
}
