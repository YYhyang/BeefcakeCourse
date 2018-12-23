package com.example.demo.VO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
public class CourseVO {
    String name;
    String intro;
    double presentationWeight;
    double questionWeight;
    double reportWeight;
    int minMemberNumber;
    int maxMemberNumber;
    String startTeamTime;
    String endTeamTime;

    public void setStartTeamTime(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:MM:ss");
        startTeamTime = dateFormat.format(date);
    }

    public void setEndTeamTime(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:MM:ss");
        endTeamTime = dateFormat.format(date);
    }
}
