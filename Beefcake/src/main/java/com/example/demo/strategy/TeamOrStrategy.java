package com.example.demo.strategy;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TeamOrStrategy extends Strategy {
    private String StrategyName;
    private List<Strategy>strategyList;

}
