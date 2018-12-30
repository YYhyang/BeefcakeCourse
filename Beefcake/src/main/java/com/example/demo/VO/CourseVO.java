package com.example.demo.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
public class CourseVO {
    String name;
    String intro;
    Integer presentationWeight;
    Integer questionWeight;
    Integer reportWeight;
    Integer minMemberNumber;
    Integer maxMemberNumber;
    String startTeamTime;
    String endTeamTime;

    /*public void setStartTeamTime(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
        startTeamTime = dateFormat.format(date);
    }

    public void setEndTeamTime(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
        endTeamTime = dateFormat.format(date);
    }*/
}
