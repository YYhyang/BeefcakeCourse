package com.example.demo.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class changeSeminarStatusDTO {
    private Long classId;
    private int status;
}
