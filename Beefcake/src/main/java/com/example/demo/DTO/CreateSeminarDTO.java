package com.example.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class CreateSeminarDTO {
    Long courseId;
    Long roundId;
    String seminarName;
    String introduction;
    Integer maxTeam;
    Integer visible;
    //所属轮次
    Integer roundOrder;
    Date start;
    Date end;
}
