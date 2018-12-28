package com.example.demo.DTO;

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
    int maxTeam;
    int visible;
    int roundOrder;//所属轮次
    Date start;
    Date end;
}
