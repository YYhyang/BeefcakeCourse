package com.example.demo.VO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class SeminarInfoVO {
    Long id;
    String seminarName;
    String introduction;
    int maxTeam;
    int order;
    Date start;
    Date end;
}
