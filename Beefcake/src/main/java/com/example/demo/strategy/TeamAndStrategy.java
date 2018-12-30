package com.example.demo.strategy;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TeamAndStrategy extends Strategy {
    private String strategyName;
    private List<Strategy> strategyList;
}
