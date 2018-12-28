package com.example.demo.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class SeminarInfoVO {
    Long id;
    String seminarName;
    String introduction;
    Integer visible;
    Long roundId;
    Integer roundOrder;
    Integer maxTeam;
    Integer order;
    Date start;
    Date end;
}
