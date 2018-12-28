package com.example.demo.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SelectQuestionVO {
    private Long question_id;
    private String teamClassSerial;
    private String studentName;
}
