package com.example.demo.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeamValidApplicationEntity {
    private Long id;
    private Long team_id;
    private Long teacher_id;
    private String reason;
}
