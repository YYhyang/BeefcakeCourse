package com.example.demo.strategy;
import com.example.demo.entity.CourseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TeamStrategyAdd {
    private CourseEntity course;

    private Integer strategySerial;

    private String strategyName;

    private List<Strategy> strategyList;

}
