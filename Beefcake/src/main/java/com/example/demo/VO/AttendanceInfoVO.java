package com.example.demo.VO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AttendanceInfoVO {
    private Long id;
    private Long teamId;
    private String teamSerialName;
    private int order;
}
