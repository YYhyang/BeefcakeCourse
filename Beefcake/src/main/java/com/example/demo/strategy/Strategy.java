package com.example.demo.strategy;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Strategy {

    protected Long id;
    protected String strategyType;

    public Strategy(Long id) {
        this.id = id;
    }

}
